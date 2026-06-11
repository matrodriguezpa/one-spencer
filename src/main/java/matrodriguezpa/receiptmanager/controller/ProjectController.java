package matrodriguezpa.receiptmanager.controller;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatLightLaf;

import lombok.Setter;
import matrodriguezpa.receiptmanager.dao.ProjectDAO;
import matrodriguezpa.receiptmanager.model.Expense;
import matrodriguezpa.receiptmanager.model.Month;
import matrodriguezpa.receiptmanager.model.Project;
import matrodriguezpa.receiptmanager.util.ExcelExportUtil;
import matrodriguezpa.receiptmanager.view.ExpenseView;
import matrodriguezpa.receiptmanager.view.MonthView;
import matrodriguezpa.receiptmanager.view.ProjectView;
import matrodriguezpa.receiptmanager.view.YearView;

public class ProjectController {

    private ExpenseController expenseController;
    private MonthController monthController;
    private YearController yearController;

    private final ProjectView projectView;
    private final YearView yearView;
    private final MonthView monthView;
    private final ExpenseView expenseView;

    private static Map<JRadioButton, Project> projectsMap = null;
    private static final ProjectDAO projectDao = new ProjectDAO();
    private Project selectedProject;

    private final ExcelExportUtil exportExcelUtil = new ExcelExportUtil();

    public ProjectController() {
        this.projectView = new ProjectView();
        this.yearView = new YearView();
        this.monthView = new MonthView();
        this.expenseView = new ExpenseView();

        projectDao.createTable();
        updateProjectList(); //Mostrar los projectos en la lista

        projectView.getOpenProjectItem().addActionListener(e -> openProject());
        projectView.getCreateProjectItem().addActionListener(e -> createProject());
        projectView.getCloseProjectItem().addActionListener(e -> closeProject());
        projectView.getCloseProjectItem().setEnabled(false);

        projectView.getCreateProjectButton().addActionListener(e -> createProject());
        projectView.getOpenProjectButton().addActionListener(e -> openProject(selectedProject));
        projectView.getOpenProjectButton().setEnabled(false);
        projectView.getEditProjectButton().addActionListener(e -> editProject(selectedProject));
        projectView.getEditProjectButton().setEnabled(false);
        projectView.getDeleteProjectButton().addActionListener(e -> deleteProject(selectedProject));
        projectView.getDeleteProjectButton().setEnabled(false);

        projectView.getUserDocItem().addActionListener(e -> openDocumentation());
        projectView.getJavadocItem().addActionListener(e -> openJavaDoc());
        projectView.getAboutItem().addActionListener(e -> openAboutWindow());
        projectView.getExportExcelItem().addActionListener(e -> exportExcel());
        projectView.getExportExcelItem().setEnabled(false);

        projectView.getExitProgramItem().addActionListener(e -> closeProgram());
    }


    /*CRUD OPERATIONS*/
    private void createProject() {
        // Limpiar el campo antes de mostrar
        projectView.getNewProjectTextField().setText("");

        int result = JOptionPane.showConfirmDialog(
                projectView,
                projectView.getProjectFormPanel(),
                "New Project",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String name = projectView.getNewProjectTextField().getText().trim();

        // Validación: nombre vacío
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(projectView,
                    "Project name cannot be empty.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validación: nombre duplicado
        if (projectDao.existsByName(name)) {
            JOptionPane.showMessageDialog(projectView,
                    "A project with the name \"" + name + "\" already exists.",
                    "Duplicate Name",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Project newProject = Project.builder().name(name).build();
        Long generatedId = projectDao.createProject(newProject);

        if (generatedId == null) {
            JOptionPane.showMessageDialog(projectView,
                    "An error occurred while creating the project.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        updateProjectList();
        JOptionPane.showMessageDialog(projectView,
                "Project \"" + name + "\" created successfully.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void openProject(Project selectedProject) {
        if (selectedProject == null) {
            return;
        }

        projectView.getMain().setVisible(false);
        projectView.getCloseProjectItem().setEnabled(true);
        projectView.getExportExcelItem().setEnabled(true);

        projectView.add(yearView, BorderLayout.WEST);
        projectView.add(monthView, BorderLayout.CENTER);
        projectView.add(expenseView, BorderLayout.EAST);

        openYear(selectedProject);
    }

    private void editProject(Project selectedProject) {
        if (selectedProject == null) {
            return;
        }

        // Pre-cargar el nombre actual en el campo
        projectView.getNewProjectTextField().setText(selectedProject.getName());

        int result = JOptionPane.showConfirmDialog(
                projectView,
                projectView.getProjectFormPanel(),
                "Edit Project",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String newName = projectView.getNewProjectTextField().getText().trim();

        // Validación: nombre vacío
        if (newName.isEmpty()) {
            JOptionPane.showMessageDialog(projectView,
                    "Project name cannot be empty.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validación: mismo nombre (sin cambios)
        if (newName.equals(selectedProject.getName())) {
            JOptionPane.showMessageDialog(projectView,
                    "No changes were made.",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Validación: nombre duplicado
        if (projectDao.existsByName(newName)) {
            JOptionPane.showMessageDialog(projectView,
                    "A project with the name \"" + newName + "\" already exists.",
                    "Duplicate Name",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        selectedProject.setName(newName);
        boolean updated = projectDao.updateProject(selectedProject);

        if (!updated) {
            JOptionPane.showMessageDialog(projectView,
                    "An error occurred while updating the project.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        updateProjectList();
        JOptionPane.showMessageDialog(projectView,
                "Project renamed to \"" + newName + "\" successfully.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteProject(Project selectedProject) {
        if (selectedProject == null) {
            return;
        }

        // Primera confirmación
        int confirm = JOptionPane.showConfirmDialog(
                projectView,
                "Are you sure you want to delete \"" + selectedProject.getName() + "\"?\nThis action cannot be undone.",
                "Delete Project",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // Segunda confirmación: escribir el nombre del proyecto
        String input = JOptionPane.showInputDialog(
                projectView,
                "Type the project name to confirm deletion:\n\"" + selectedProject.getName() + "\"",
                "Confirm Deletion",
                JOptionPane.WARNING_MESSAGE
        );

        if (input == null) {
            return; // canceló
        }
        if (!input.trim().equals(selectedProject.getName())) {
            JOptionPane.showMessageDialog(projectView,
                    "The name you entered does not match. Deletion cancelled.",
                    "Mismatch",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean deleted = projectDao.deleteById(selectedProject.getId());

        if (!deleted) {
            JOptionPane.showMessageDialog(projectView,
                    "An error occurred while deleting the project.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.selectedProject = null;
        projectView.getOpenProjectButton().setEnabled(false);
        projectView.getEditProjectButton().setEnabled(false);
        projectView.getDeleteProjectButton().setEnabled(false);

        updateProjectList();
        JOptionPane.showMessageDialog(projectView,
                "Project deleted successfully.",
                "Deleted",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /*VIEW UPDATE*/
    private void updateProjectList() {
        projectView.getProjectSelectionPanel().removeAll();
        projectView.getProjectSelectionPanel().repaint();

        if (ProjectController.projectsMap == null) {
            ProjectController.projectsMap = new HashMap<>();
        } else {
            ProjectController.projectsMap.clear();
        }

        for (Project project : projectDao.findAll()) {
            String name = project.getName();
            JRadioButton button = new JRadioButton(name);
            projectView.getProjectButtonGroup().add(button);

            ProjectController.projectsMap.put(button, project);

            button.addActionListener((e) -> {
                projectView.getOpenProjectButton().setEnabled(true);
                projectView.getEditProjectButton().setEnabled(true);
                projectView.getDeleteProjectButton().setEnabled(true);
                this.selectedProject = project;
            });

            projectView.getProjectSelectionPanel().add(button);
        }
    }

    protected void updateYearView() {
        yearController.updateYearTree();
    }

    protected void updateMonthView() {
        monthController.updateMainTable();
    }

    protected void updateExpenseView() {
        expenseController.updateEditor();
    }

    public void openYear(Project selectedProject) {
        yearController = new YearController(this, yearView, selectedProject);
    }

    protected void openMonth(Month selectedMonth) {
        monthController = new MonthController(this, monthView, selectedMonth);
    }

    protected void openExpense(Expense selectedExpense) {
        expenseController = new ExpenseController(this, expenseView, selectedExpense);
    }

    void updateYearView() {
        yearController.updateYearTree();
    }

    void updateMonthView() {
        monthController.updateMainTable();
    }

    void updateExpenseView() {
        expenseController.updateEditor();
    }
}
