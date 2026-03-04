package matrodriguezpa.receiptmanager.controller;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import matrodriguezpa.receiptmanager.dao.YearDAO;
import matrodriguezpa.receiptmanager.model.Year;
import matrodriguezpa.receiptmanager.model.Project;
import matrodriguezpa.receiptmanager.view.YearView;

public class YearController {

    private final YearView yearView;
    private static DefaultTreeModel treeModel; //Tree with all years

    private static Year year;
    private YearDAO yearDao;

    public YearController(YearView yearView, Project project) {

        this.yearView = yearView;
        yearDao.createTable();
        updateNavigationTree();

        yearView.getNewProjectButton().addActionListener(e -> createProject());
        yearView.getOpenProjectButton().addActionListener(e -> openProject());
    }
    
    /*CRUD */
    private void createProject() {
        /*
        while (true) {
            int result = JOptionPane.showConfirmDialog(
                    view,
                    view.getNewProject(),
                    "New Project",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result != JOptionPane.OK_OPTION) {
                return;
            }

            String rawName = view.getNewProjectName().getText().trim();
            String yearText = view.getNewProjectYear().getText().trim();

            yearDao.isValidName(rawName);
            yearDao.isEmpty(yearText);
            yearDao.validateYear(yearText);

            year.setName(rawName.replace(' ', '_'));
            year.setYEAR(yearDao.validateYear(yearText));
            break;
        }

        // Load existing user instead of creating a new one
        Project existingUser = null; //users.findById(1); // Use your UserDAO

        if (existingUser == null) {
            // Create new user if doesn't exist
            existingUser = Project.builder()
                    .name("Mateo")
                    .build();
            //userDao.save(existingUser);
        }

        Year newProject = Year.builder()
                .name(year.getName())
                .YEAR(year.getYEAR())
                .userId(existingUser)
                .build();

        yearDao.save(newProject);
        updateNavigationTree();
         */
    }

    private void updateProject() {
    }

    private void deleteProject() {
    }

    private void openProject() {
        /*
        List<Long> usersId = null;//users.findDistictUsers();
        List<Project> users2 = new ArrayList<>();

        for (Long user : usersId) {
            //users2.add(users.findByIdLong(user));
        }

        if (users2.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tabla usuarios no existente o no hay usuarios en la tabla.");
            return;
        }

        // Crear un ButtonGroup para que los radio buttons sean mutuamente excluyentes
        ButtonGroup grupoUsuarios = new ButtonGroup();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Usamos un layout vertical para los radio buttons

        // Crear un radio button por cada usuario y añadirlo al panel
        for (Project usuario : users2) {
            JRadioButton radioButton = new JRadioButton(usuario.getName());
            grupoUsuarios.add(radioButton); // Añadimos el radio button al grupo
            panel.add(radioButton); // Añadimos el radio button al panel
        }

        // Mostrar el diálogo con el panel de radio buttons
        int result = JOptionPane
                .showConfirmDialog(null, panel, "Open project", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            // Buscar el radio button seleccionado
            Enumeration<AbstractButton> elementos = grupoUsuarios.getElements();
            while (elementos.hasMoreElements()) {
                AbstractButton radioButton = elementos.nextElement();
                if (radioButton.isSelected()) {
                    //User.projectName = radioButton.getText();// Asigna el usuario seleccionado a projectYear
                    //Controller.updateNavigationTree();
                    break;
                }
            }
            //mostrar los botones de agregar años, mes y gastos 
            //Controller.updateView(true);
        }
         */
    }

    /* Windows */

    
    /*Update the view */
    protected static void updateNavigationTree() {
        if (year.getName() == null) {
            // Clear the tree
            DefaultMutableTreeNode emptyRoot = new DefaultMutableTreeNode("No Project Open");
            treeModel = new DefaultTreeModel(emptyRoot);
            //view.getLeftNavigation().setModel(treeModel);

            return;
        }
        try {
            //Project project = ProjectController.findByUserAndYear(project.getUserId(), project.getYEAR()); //TODO

            if (year == null) {
                // Project not found
                DefaultMutableTreeNode emptyRoot = new DefaultMutableTreeNode("Project Not Found");
                treeModel = new DefaultTreeModel(emptyRoot);
                //view.getLeftNavigation().setModel(treeModel);
                return;
            }

            // Create root node with project name
            DefaultMutableTreeNode root = new DefaultMutableTreeNode(year.getName());

            // Create tree model
            treeModel = new DefaultTreeModel(root);
            //getLeftNavigation().setModel(treeModel);

            // Expand all nodes
            //expandAllNodes(view.getLeftNavigation(), root);
            LeftNavigationValueChanged(root);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            // Add this for debugging
        }
    }

    private static void expandAllNodes(JTree tree, DefaultMutableTreeNode node) {
        // Método auxiliar para expandir todos los nodos
        int row = tree.getRowForPath(new TreePath(node.getPath()));
        if (row != -1) {
            tree.expandRow(row);
            for (int i = 0; i < node.getChildCount(); i++) {
                expandAllNodes(tree, (DefaultMutableTreeNode) node.getChildAt(i));
            }
        }
    }

    private static void LeftNavigationValueChanged(DefaultMutableTreeNode root) {
        // Buscar la primera hoja del árbol
        DefaultMutableTreeNode currentNode = root;
        while (!currentNode.isLeaf() && currentNode.getChildCount() > 0) {
            currentNode = (DefaultMutableTreeNode) currentNode.getFirstChild();
        }

        if (currentNode != root) {  // Si encontramos una hoja (que no sea el root)
            // view.getLeftNavigation().setSelectionPath(new TreePath(currentNode.getPath()));
            //TreePath selectedPath = view.getLeftNavigation().getSelectionPath();
            TreePath selectedPath = null;
            // Verificar si hay un nodo seleccionado
            if (selectedPath != null) {
                // Obtener el nodo seleccionado
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selectedPath.getLastPathComponent();

                // Verificar si el nodo es una hoja (nodo final)
                if (selectedNode.isLeaf()) {
                    /* Asignar el valor del mes correspondiente al nodo seleccionado
                    User.proyectMonth = (String) selectedNode.getUserObject(); // Asegúrate de que el objeto sea del tipo adecuado
                    DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectedNode.getParent();
                    User.projectYear = (String) parent.getUserObject(); // Asegúrate de que el objeto sea del tipo adecuado
                    parent = (DefaultMutableTreeNode) parent.getParent();
                    User.projectName = (String) parent.getUserObject();

                    updateMainTable();*/
                }
            }
        }
    }

}
