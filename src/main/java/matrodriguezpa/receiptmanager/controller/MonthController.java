package matrodriguezpa.receiptmanager.controller;

import javax.swing.table.DefaultTableModel;

import matrodriguezpa.receiptmanager.dao.MonthDAO;
import matrodriguezpa.receiptmanager.model.Month;
import matrodriguezpa.receiptmanager.view.MonthView;

public class MonthController {

    private MonthView monthView;
    private static DefaultTableModel tableModel;

    private Month month = new Month();
    private MonthDAO monthDao = new MonthDAO();

    private ExpenseController expenseController;

    public MonthController(MonthView monthView) {
        this.monthView = monthView;
    }

    public void createMonth() {
    }

    public void updateMonth() {
    }

    public void deleteMonth() {
    }

    /*
    protected static void updateMainTable() {
        tableModel = (DefaultTableModel) view.getMainTable().getModel();
        tableModel.setRowCount(0);

        if (User.projectName == null) {
            return;
        }

        String sql = "SELECT * FROM " + User.projectName + "_" + User.projectYear + "_" + User.proyectMonth;
        System.out.println("Consulta SQL: " + sql);
        Expense[] expenses = null; //userDao.find(sql);

        for (Expense expense : expenses) {
            tableModel.addRow(new Object[]{
                expense.getYEAR(),
                expense.getCompany(),
                expense.getPayment()
            //Etc
            });
        }
        view.getExpenseDateYear().setValue(Integer.valueOf(User.projectYear));
        view.getExpenseDateMonth().setValue(Integer.valueOf(User.proyectMonth));
    }
     */
    public void closeMainTable() {
    }
}
