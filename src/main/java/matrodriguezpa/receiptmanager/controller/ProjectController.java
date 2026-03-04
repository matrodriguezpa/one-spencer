package matrodriguezpa.receiptmanager.controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import lombok.Setter;

import matrodriguezpa.receiptmanager.model.Project;
import matrodriguezpa.receiptmanager.dao.ProjectDAO;
import matrodriguezpa.receiptmanager.view.ExpenseView;
import matrodriguezpa.receiptmanager.view.MonthView;
import matrodriguezpa.receiptmanager.view.ProjectView;
import matrodriguezpa.receiptmanager.view.YearView;

@Setter
public class ProjectController {

    private static ProjectView projectView;
    List<Project> projects;

    private static Project project;
    private static final ProjectDAO projectDao = new ProjectDAO();

    public ProjectController(ProjectView projectView) {

        ProjectController.projectView = projectView;
        projectDao.createTable();
        updateProjectList();

        projectView.getCreateNewUser().addActionListener(e -> createProject());
        projectView.getOpenUserProjects().addActionListener(e -> openProject(project));
    }

    public void start() {
        projectView.setVisible(true);
        projectView.updateUI();

    }

    private void createProject() {

        System.out.println("New user button:");

        int result = JOptionPane.showConfirmDialog(projectView,
                projectView.getUserFormPanel(),
                "Create New User",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String name = projectView.getNewUserTextField().getText().trim();

            if (name.isEmpty() || projectDao.existsByName(name)) {
                JOptionPane.showMessageDialog(projectView, "Please enter a name valid name!");
            } else {
                Project newUser = Project.builder()
                        .name(name)
                        .build();
                Long userId = projectDao.createProject(newUser);
                if (userId == null) {
                    JOptionPane.showMessageDialog(projectView, "Error creating user!");
                } else {
                    //agregar el nuevo usuario a la view
                    updateProjectList();
                }
            }
        }
    }

    private void editProject() {

        String name = projectView.getNewUserTextField().toString().trim();
        if (!name.isEmpty() && !projectDao.existsByName(name)) {
            Project newUser = Project.builder()
                    .id(null)
                    .name(name)
                    .build();

            boolean update = projectDao.updateProject(newUser);
            if (update) {
                projectView.getUserSelectionPanel()
                        .add(new JRadioButton(newUser.getName()));
            } else {
                JOptionPane.showMessageDialog(projectView, "Error updating user!");
            }
        } else {
            JOptionPane.showMessageDialog(projectView, "Please enter a name valid name!");
        }
    }

    private void deleteProject() {
        String name = projectView.getNewUserTextField().toString().trim();

        if (!name.isEmpty() && projectDao.existsByName(name)) {
            Long id = null;

            boolean deleted = projectDao.deleteById(id);
            if (deleted) {
                projectView.getUserSelectionPanel();//eliminar de la vista?
            } else {
                JOptionPane.showMessageDialog(projectView, "Error deleting user!");
            }
        } else {
            JOptionPane.showMessageDialog(projectView, "Please enter a name valid name!");
        }
    }

    private void openProject(Project projectSelected) {
        if (projectSelected == null) {
            JOptionPane.showMessageDialog(projectView, "Error",   "Select a project.", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            // check if a project is selected
            projectView.getMain().setVisible(false);

            //Juntar estos tres de alguna forma
            YearView yearView = new YearView();
            MonthView monthView = new MonthView();
            ExpenseView expenseView = new ExpenseView();

            new YearController(yearView, ProjectController.project);
            projectView.add(yearView);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(projectView, "Error", e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateProjectList() {
        this.projects = projectDao.findAll();
        projectView.getUserSelectionPanel().removeAll();
        projectView.getUserSelectionPanel().repaint();
        for (Project var : projects) {
            String name = var.getName();
            JRadioButton button = new JRadioButton(name);
            projectView.getUserButtonGroup().add(button);
            //Agregar creado y ultima edición
            projectView.getUserSelectionPanel().add(button);
            button.addActionListener(e -> openProject(var));
        }
    }

    private void projectSelected(){
        //Cambiar por private Project projectSelected()
        //projectView.userButtonGroup.getSelection();
        //return ;
    }
}
