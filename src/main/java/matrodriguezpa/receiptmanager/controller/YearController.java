package matrodriguezpa.receiptmanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import matrodriguezpa.receiptmanager.dao.MonthDAO;

import matrodriguezpa.receiptmanager.dao.YearDAO;
import matrodriguezpa.receiptmanager.model.Month;
import matrodriguezpa.receiptmanager.model.Year;
import matrodriguezpa.receiptmanager.model.Project;
import matrodriguezpa.receiptmanager.view.YearView;

public class YearController {

    private static ProjectController projectController;

    private static YearView yearView;
    private static DefaultTreeModel yearTreeModel;
    private static final Map<DefaultMutableTreeNode, Month> yearMonthNodes = new HashMap<>();

    private static final YearDAO yearDao = new YearDAO();
    private static final MonthDAO monthDao = new MonthDAO();
    private static Project project;

    public YearController(ProjectController projectController, YearView yearView, Project projectSelected) {

        YearController.projectController = projectController;
        YearController.yearView = yearView;
        YearController.project = projectSelected;

        yearDao.createTable();
        monthDao.createTable();
        updateYearTree();

        yearView.getNewMonthButton().addActionListener(e -> createYear());
        yearView.getLeftNavigation().addTreeSelectionListener(e -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) yearView
                    .getLeftNavigation()
                    .getLastSelectedPathComponent();
            openMonth(selectedNode);
        });
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
            if (yearDao.existsByProjectAndYear(this.project.getId(), yearValue)) {
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
        Year selectedYear = null; // mostrar lista para escojer el ano
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
        Year selectedYear = null; // mostrar lista para escojer el ano
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

    protected static void updateYearTree() {
        if (project == null) {
            DefaultMutableTreeNode emptyRoot = new DefaultMutableTreeNode("No Project Open");
            yearTreeModel = new DefaultTreeModel(emptyRoot);
            yearView.getLeftNavigation().setModel(yearTreeModel);
            return;
        }

        // Limpiar el mapa antes de reconstruir
        yearMonthNodes.clear();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode(project.getName());

        for (Year projectYear : yearDao.findByProjectId(project.getId())) {
            String yearText = String.valueOf(projectYear.getYEAR());
            String tag = projectYear.getTag();
            if (tag != null && !tag.trim().isEmpty()) {
                yearText += " (" + tag + ")";
            }

            DefaultMutableTreeNode yearNode = new DefaultMutableTreeNode(yearText);
            // Si quieres asociar el objeto Year al nodo de año, puedes mantener el userObject:
            // yearNode.setUserObject(projectYear);

            List<Month> months = monthDao.findByYearId(projectYear.getId());
            for (Month month : months) {
                // El nodo solo tendrá el nombre (número de mes)
                String monthName = String.valueOf(month.getMONTH());
                DefaultMutableTreeNode monthNode = new DefaultMutableTreeNode(monthName);
                // NO se guarda el objeto Month en el userObject, solo en el mapa
                yearMonthNodes.put(monthNode, month);
                yearNode.add(monthNode);
            }
            root.add(yearNode);
        }

        yearTreeModel = new DefaultTreeModel(root);
        yearView.getLeftNavigation().setModel(yearTreeModel);
    }

    private void openMonth(DefaultMutableTreeNode selectedNode) {
        if (selectedNode != null && selectedNode.isLeaf()) {
            Month selectedMonth = yearMonthNodes.get(selectedNode);
            if (selectedMonth != null) {
                projectController.openMonth(selectedMonth);
            }
        }
    }
}
