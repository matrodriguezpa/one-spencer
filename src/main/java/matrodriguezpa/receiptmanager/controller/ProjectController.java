package matrodriguezpa.receiptmanager.controller;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private static Map<JRadioButton, Project> projects = null;

    private static Project project;
    private static final ProjectDAO projectDao = new ProjectDAO();

    public ProjectController(ProjectView projectView) {

        ProjectController.projectView = projectView;
        projectDao.createTable();
        updateProjectList();

        projectView.getCreateNewUser().addActionListener(e -> createProject());
        projectView.getOpenUserProjects1().addActionListener(e -> editProject());
        projectView.getOpenUserProjects2().addActionListener(e -> deleteProject());

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
        projectView.getMain().setVisible(false);

        //Juntar estos tres de alguna forma
        YearView yearView = new YearView();
        MonthView monthView = new MonthView();
        ExpenseView expenseView = new ExpenseView();

        projectView.add(yearView, BorderLayout.WEST);
        //projectView.add(monthView, BorderLayout.CENTER);
        //projectView.add(expenseView, BorderLayout.EAST);

        new YearController(yearView, projectSelected);

    }

    private void updateProjectList() {
        projectView.getUserSelectionPanel().removeAll();
        projectView.getUserSelectionPanel().repaint();

        // Initialize the map (if it's static, make sure to clear it before reuse)
        if (ProjectController.projects == null) {
            ProjectController.projects = new HashMap<>();
        } else {
            ProjectController.projects.clear();
        }

        for (Project project : projectDao.findAll()) { // findAll() returns a List
            String name = project.getName();
            JRadioButton button = new JRadioButton(name);
            projectView.getUserButtonGroup().add(button);

            // Store the mapping
            ProjectController.projects.put(button, project);

            button.addActionListener(e -> openProject(project));

            projectView.getUserSelectionPanel().add(button);
        }
    }
}
