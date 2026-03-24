package matrodriguezpa.receiptmanager.controller;

import javax.swing.JOptionPane;
import matrodriguezpa.receiptmanager.dao.ExpenseDAO;
import matrodriguezpa.receiptmanager.model.Expense;
import matrodriguezpa.receiptmanager.view.ExpenseView;

public class ExpenseController {

    private static ProjectController projectController;

    private static ExpenseView expenseView;
    private static ExpenseDAO expenseDao = new ExpenseDAO();
    private static Expense expense;

    public ExpenseController(ProjectController projectController, ExpenseView expenseview, Expense selectedExpense) {
        ExpenseController.projectController = projectController;
        ExpenseController.expenseView = expenseview;
        ExpenseController.expense = selectedExpense;

        expenseDao.createTable();
        updateEditor();

        expenseView.getAddExpenseButton().addActionListener(e -> createExpense());
        expenseView.getEditExpenseButton().addActionListener(e -> updateExpense());
        expenseView.getDeleteExpenseButton().addActionListener(e -> deleteExpense());
    }

    private void createExpense() {
        int year = (int) expenseView.getExpenseDateYear().getValue();
        int month = (int) expenseView.getExpenseDateMonth().getValue();
        int day = (int) expenseView.getExpenseDateDay().getValue();
        System.out.println("Fecha insertada:" + year + "-" + month + "-" + day);
        ExpenseDAO.validateDate(month, day);

        int index = expenseView.getExpense().getSelectedIndex();
        String company = expenseView.getCompany().getItemAt(index);

        Double amount = Double.valueOf(expenseView.getAmount().getText());
        ExpenseDAO.validateAmount(amount);

        index = expenseView.getExpense().getSelectedIndex();
        String expenseType = expenseView.getExpense().getItemAt(index);

        index = expenseView.getMatrix().getSelectedIndex();
        String matrix = expenseView.getMatrix().getItemAt(index);

        index = expenseView.getPayment().getSelectedIndex();
        String payment = expenseView.getPayment().getItemAt(index);

        Expense newExpense = Expense.builder()
                .ProjectId(expense.getProjectId())
                .MonthId(expense.getMonthId())
                .YEAR(year)
                .MONTH(month)
                .DAY(day)
                .company(company)
                .amount(amount)
                .type(expenseType)
                .matrix(matrix)
                .payment(payment)
                .build();

        expenseDao.save(newExpense);

        projectController.updateMonthView();
        JOptionPane.showMessageDialog(null, "Inserción exitosa.");
    }

    private void updateExpense() {
        // Verificar que haya un gasto seleccionado para editar
        if (expense == null || expense.getId() == 0) {
            JOptionPane.showMessageDialog(null, "No hay un gasto seleccionado para editar.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea actualizar este gasto?",
                "Confirmar actualización",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        int year = (int) expenseView.getExpenseDateYear().getValue();
        int month = (int) expenseView.getExpenseDateMonth().getValue();
        int day = (int) expenseView.getExpenseDateDay().getValue();
        ExpenseDAO.validateDate(month, day);

        int index = expenseView.getCompany().getSelectedIndex();
        String company = expenseView.getCompany().getItemAt(index);

        Double amount = Double.valueOf(expenseView.getAmount().getText());
        ExpenseDAO.validateAmount(amount);

        index = expenseView.getExpense().getSelectedIndex();
        String expenseType = expenseView.getExpense().getItemAt(index);

        index = expenseView.getMatrix().getSelectedIndex();
        String matrix = expenseView.getMatrix().getItemAt(index);

        index = expenseView.getPayment().getSelectedIndex();
        String payment = expenseView.getPayment().getItemAt(index);

        Expense updatedExpense = Expense.builder()
                .id(expense.getId()) // Conservar el ID original para el UPDATE
                .ProjectId(expense.getProjectId())
                .MonthId(expense.getMonthId())
                .YEAR(year)
                .MONTH(month)
                .DAY(day)
                .company(company)
                .amount(amount)
                .type(expenseType)
                .matrix(matrix)
                .payment(payment)
                .build();

        expenseDao.updateExpense(updatedExpense);

        // Reflejar los cambios en el objeto local
        expense = updatedExpense;

        projectController.updateMonthView();
        JOptionPane.showMessageDialog(null, "Actualización exitosa.");
    }

    private void deleteExpense() {
        // Verificar que haya un gasto seleccionado para eliminar
        if (expense == null || expense.getId() == 0) {
            JOptionPane.showMessageDialog(null, "No hay un gasto seleccionado para eliminar.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea eliminar este gasto? Esta acción no se puede deshacer.",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            expenseDao.deleteById(expense.getId());

            projectController.updateMonthView();
            JOptionPane.showMessageDialog(null, "Eliminación exitosa.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el gasto: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void updateEditor() {
        expenseView.getExpenseDateMonth().setFocusable(true);
        expenseView.getExpenseDateMonth().setFocusable(true);

        expenseView.getExpenseDateYear().setValue(expense.getYEAR());
        expenseView.getExpenseDateMonth().setValue(expense.getMONTH());
        expenseView.getExpenseDateDay().setValue(expense.getDAY());

        expenseView.getCompany().setSelectedItem(expense.getCompany());
        expenseView.getAmount().setText(String.valueOf(expense.getAmount()));
        expenseView.getExpense().setSelectedItem(expense.getType());
        expenseView.getMatrix().setSelectedItem(expense.getMatrix());
        expenseView.getPayment().setSelectedItem(expense.getPayment());
    }
}
