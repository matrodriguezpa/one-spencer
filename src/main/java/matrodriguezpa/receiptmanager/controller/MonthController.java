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

    private static Month month;
    private static ProjectController projectController;

    private static MonthView monthView;

    private static DefaultTableModel tableModel;
    private static List<Expense> expenses = new ArrayList<>();

    private final MonthDAO monthDao = new MonthDAO();
    private static final ExpenseDAO expenseDao = new ExpenseDAO();

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

        tableModel = (DefaultTableModel) monthView.getMainTable().getModel();
        tableModel.setRowCount(0);
        MonthController.expenses = expenseDao.findByMonthId(month.getId());
        System.out.println(expenses);
        for (Expense expense : expenses) {
            tableModel.addRow(new Object[]{
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
        if (selectedRow != -1 && selectedRow < expenses.size()) {
            Expense expense = expenses.get(selectedRow);
            projectController.openExpense(expense);
        }
    }
}
