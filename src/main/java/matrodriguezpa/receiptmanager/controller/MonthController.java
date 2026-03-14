package matrodriguezpa.receiptmanager.controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import matrodriguezpa.receiptmanager.dao.ExpenseDAO;

import matrodriguezpa.receiptmanager.dao.MonthDAO;
import matrodriguezpa.receiptmanager.model.Expense;
import matrodriguezpa.receiptmanager.model.Month;
import matrodriguezpa.receiptmanager.view.MonthView;

public class MonthController {

    private static ProjectController projectController;
    
    private static MonthView monthView;
    private static DefaultTableModel monthTableModel;
    private static List<Expense> monthExpenses = new ArrayList<>();

    private final MonthDAO monthDao = new MonthDAO();
    private static final ExpenseDAO expenseDao = new ExpenseDAO();
    private static Month month;

    public MonthController(ProjectController projectController, MonthView monthView, Month selectedMonth) {
        MonthController.projectController = projectController;
        MonthController.monthView = monthView;
        MonthController.month = selectedMonth;

        monthDao.createTable();
        updateMainTable();

        monthView.getMainTable().getSelectionModel().addListSelectionListener(e -> openExpense());
    }

    public void createMonth() {
    }

    public void updateMonth() {
    }

    public void deleteMonth() {
    }

    private static void updateMainTable() {
        if (month == null || month.getId() == null) {
            return;
        }

        monthTableModel = (DefaultTableModel) monthView.getMainTable().getModel();
        monthTableModel.setRowCount(0);
        MonthController.monthExpenses = expenseDao.findByMonthId(month.getId());
        System.out.println(monthExpenses);
        for (Expense expense : monthExpenses) {
            monthTableModel.addRow(new Object[]{
                expense.getDAY(), // Día
                expense.getCompany(), // Compañía
                expense.getAmount(), // Importe formateado
                expense.getType(), // Tipo de gasto
                expense.getMatrix(), // Matriz
                expense.getPayment(), // Forma de pago
            });
        }
        monthView.getMainTable().repaint();
    }

    private void openExpense() { // Evita eventos intermedios
        int selectedRow = monthView.getMainTable().getSelectedRow();
        if (selectedRow != -1 && selectedRow < monthExpenses.size()) {
            Expense expense = monthExpenses.get(selectedRow);
            projectController.openExpense(expense);
        }
    }
}
