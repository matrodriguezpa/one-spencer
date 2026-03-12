package matrodriguezpa.receiptmanager.controller;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import matrodriguezpa.receiptmanager.dao.YearDAO;
import matrodriguezpa.receiptmanager.model.Year;
import matrodriguezpa.receiptmanager.model.Project;
import matrodriguezpa.receiptmanager.view.YearView;

public class YearController {

    private static final YearView yearView;
    private static DefaultTreeModel treeModel; //Tree with all years

    private static Project project;

    private static Year year;
    private YearDAO yearDao;

    public YearController(YearView yearView, Project project) {

        this.yearView = yearView;
        this.project = project;
        yearDao.createTable();
        updateYearTree();

        yearView.getNewProjectButton().addActionListener(e -> createYear());
        yearView.getOpenProjectButton().addActionListener(e -> openYear());
    }

    private void createYear() {
        // Mostrar diálogo para ingresar año y tag, asociado al proyecto actual
        int result = JOptionPane.showConfirmDialog(yearView,
                yearView.getYearFormPanel(),
                "Create New Year",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Obtener valores del formulario
            String yearStr = yearView.getDateSpinner().getValue().toString();
            String tag = yearView.getTagTextField().getText().trim();
            // Proyecto actual

            // Validaciones
            if (this.project == null) {
                JOptionPane.showMessageDialog(yearView, "No project selected!");
                return;
            }
            if (yearStr.isEmpty()) {
                JOptionPane.showMessageDialog(yearView, "Please enter a year!");
                return;
            }

            int yearValue;
            try {
                yearValue = Integer.parseInt(yearStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(yearView, "Year must be a valid number!");
                return;
            }

            // Verificar si ya existe un año con ese valor para el mismo proyecto
            if (yearDao.existsByProjectAndYear(this.project, yearValue)) {
                JOptionPane.showMessageDialog(yearView, "Year already exists for this project!");
                return;
            }

            // Construir objeto Year
            Year newYear = Year.builder()
                    .projectId(this.project.getId())
                    .YEAR(yearValue)
                    .tag(tag)
                    .build();

            // Guardar en BD
            Long yearId = yearDao.createYear(newYear);
            if (yearId == null) {
                JOptionPane.showMessageDialog(yearView, "Error creating year!");
            } else {
                // Actualizar la vista (ej: recargar lista de años)
                updateYearTree();
            }
        }
    }

    private void editYear() {
        // Obtener el año seleccionado actualmente en la vista
        Year selectedYear = getSelectedYear();
        if (selectedYear == null) {
            JOptionPane.showMessageDialog(yearView, "No year selected!");
            return;
        }

        // Pre-cargar el formulario con los datos actuales
        yearView.getDateSpinner().setValue(String.valueOf(selectedYear.getYEAR()));
        yearView.getTagTextField().setText(selectedYear.getTag());

        int result = JOptionPane.showConfirmDialog(yearView,
                yearView.getYearFormPanel(),
                "Edit Year",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String yearStr = yearView.getDateSpinner().getValue().toString();
            String tag = yearView.getTagTextField().getText().trim();

            if (yearStr.isEmpty()) {
                JOptionPane.showMessageDialog(yearView, "Please enter a year!");
                return;
            }

            int yearValue;
            try {
                yearValue = Integer.parseInt(yearStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(yearView, "Year must be a valid number!");
                return;
            }

            // Verificar si ya existe otro año con ese valor para el mismo proyecto
            // (excluyendo el año actual)
            if (yearDao.existsByProjectAndYear(selectedYear.getProjectId(), selectedYear.getYEAR())) {
                JOptionPane.showMessageDialog(yearView, "Another year with that value already exists for this project!");
                return;
            }

            // Actualizar objeto
            selectedYear.setYEAR(yearValue);
            selectedYear.setTag(tag);

            // Guardar cambios
            boolean updated = yearDao.updateYear(selectedYear);
            if (updated) {
                updateYearTree();
            } else {
                JOptionPane.showMessageDialog(yearView, "Error updating year!");
            }
        }
    }

    private void deleteYear() {
        Year selectedYear = getSelectedYear();
        if (selectedYear == null) {
            JOptionPane.showMessageDialog(yearView, "No year selected!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(yearView,
                "Are you sure you want to delete year " + selectedYear.getYEAR() + "?",
                "Delete Year",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean deleted = yearDao.deleteById(selectedYear.getId());
            if (deleted) {
                updateYearTree();
            } else {
                JOptionPane.showMessageDialog(yearView, "Error deleting year!");
            }
        }
    }

    private void openYear() {
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

    protected static void updateYearTree() {
        if (project == null) {
            DefaultMutableTreeNode emptyRoot = new DefaultMutableTreeNode("No Project Open");
            treeModel = new DefaultTreeModel(emptyRoot);
            yearView.getLeftNavigation().setModel(treeModel);
            return;

        }
        //llenar un nodo
        //dentro de ese nodo llenar las hojas
    }

    private Year getSelectedYear() {
        return null;
    }

    /*Update the view 
    protected static void updateNavigationTree() {
        if (year.getTag() == null) {
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
            DefaultMutableTreeNode root = new DefaultMutableTreeNode(year.getTag());

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

                    updateMainTable();
                }
            }
        }
    }
     */
}
