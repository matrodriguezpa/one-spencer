package matrodriguezpa.receiptmanager.controller;

import javax.swing.JOptionPane;
import matrodriguezpa.receiptmanager.dao.ExpenseDAO;
import matrodriguezpa.receiptmanager.model.Expense;
import matrodriguezpa.receiptmanager.view.ExpenseView;

public class ExpenseController {

    ExpenseView expenseView;

    private static Expense expenseobj = new Expense();
    ExpenseDAO expenseDao = new ExpenseDAO();

    public ExpenseController(ExpenseView expenseview) {
        this.expenseView = expenseview;

        expenseView.getAddExpense().addActionListener(e -> createExpense());
        expenseView.getAddCompanyButton().addActionListener(e -> ExpenseController.this.updateExpense(0));
        expenseView.getAddExpenseButton().addActionListener(e -> ExpenseController.this.updateExpense(1));
        expenseView.getAddMatrixButton().addActionListener(e -> ExpenseController.this.updateExpense(2));
        expenseView.getAddPaymentButton().addActionListener(e -> ExpenseController.this.updateExpense(3));
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

    //agregar datos para el usuario visualizar
    private void updateExpense(int index) {//el indice seleccionadode la lista company, expense, matrix, payment

        /*expenseView.getAddExpensedata().setSelectedIndex(index);

        int result = JOptionPane.showConfirmDialog(null,
                expenseView.getExpenseDataLoader(),
                "Enter Expense",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String resultado = expenseView.getNewExpenseData().getText();
            switch (index) {
                case 0 ->
                    expenseView.getCompany().addItem(resultado);
                case 1 ->
                    expenseView.getExpense().addItem(resultado);
                case 2 ->
                    expenseView.getMatrix().addItem(resultado);
                case 3 ->
                    expenseView.getPayment().addItem(resultado);

            }
        }*/
    }

    private void deleteExpense() {
    }
}
