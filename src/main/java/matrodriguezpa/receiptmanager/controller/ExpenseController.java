package matrodriguezpa.receiptmanager.controller;

import javax.swing.JOptionPane;
import matrodriguezpa.receiptmanager.dao.ExpenseDAO;
import matrodriguezpa.receiptmanager.dao.MonthDAO;
import matrodriguezpa.receiptmanager.model.Expense;
import matrodriguezpa.receiptmanager.view.ExpenseView;

public class ExpenseController {

    private static Expense expense;

    private static ExpenseView expenseView;

    private final MonthDAO monthDao = new MonthDAO();
    private static ExpenseDAO expenseDao = new ExpenseDAO();

    public ExpenseController(ExpenseView expenseview, Expense selectedExpense) {
        ExpenseController.expenseView = expenseview;
        ExpenseController.expense = selectedExpense;
        expenseDao.createTable();
        expenseView.getAddExpense().addActionListener(e -> createExpense());
    }

    private void createExpense() {
        // Establecer los valores para cada columna (ajustar según los tipos de datos de las columnas)
        int year = (int) expenseView.getExpenseDateYear().getValue();
        int month = (int) expenseView.getExpenseDateMonth().getValue();
        int day = (int) expenseView.getExpenseDateDay().getValue();
        System.out.println("Fecha insertada:" + year + "-" + month + "-" + day);
        expenseDao.validateDate(month, day);

        int index = expenseView.getExpense().getSelectedIndex();
        String company = expenseView.getCompany().getItemAt(index);  // Razon social

        Double amount = Double.valueOf(expenseView.getAmount().getText()); // Monto
        expenseDao.validateAmount(amount);

        index = expenseView.getExpense().getSelectedIndex();
        String expense = expenseView.getExpense().getItemAt(index); // Gasto

        index = expenseView.getMatrix().getSelectedIndex();
        String matrix = expenseView.getMatrix().getItemAt(index); // Matriz

        index = expenseView.getPayment().getSelectedIndex();
        String payment = expenseView.getPayment().getItemAt(index); // Forma de pago

        Expense newExpense = Expense.builder()
                .YEAR(year)
                .MONTH(month)
                .DAY(day)
                .company(company)
                .amount(amount)
                .type(expense)
                .matrix(matrix)
                .payment(payment)
                .build();

        expenseDao.save(newExpense);

        //Controller.updateMainTable();
        JOptionPane.showMessageDialog(null, "Inserción exitosa.");
    }

    private void updateExpense() {
    }

    private void deleteExpense() {
    }
}
