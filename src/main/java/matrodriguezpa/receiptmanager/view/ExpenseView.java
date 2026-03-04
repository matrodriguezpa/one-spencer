package matrodriguezpa.receiptmanager.view;

import lombok.Getter;

@Getter
public class ExpenseView extends javax.swing.JPanel {

    public ExpenseView() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newExpenseAddDate = new javax.swing.JPanel();
        Date = new javax.swing.JLabel();
        expenseDateYear = new javax.swing.JSpinner();
        expenseDateMonth = new javax.swing.JSpinner();
        expenseDateDay = new javax.swing.JSpinner();
        newExpenseAddCompany = new javax.swing.JPanel();
        companyLabel = new javax.swing.JLabel();
        company = new javax.swing.JComboBox<>();
        addCompanyButton = new javax.swing.JButton();
        newExpenseAddAmount = new javax.swing.JPanel();
        Amount = new javax.swing.JLabel();
        amount = new javax.swing.JTextField();
        newExpenseAddExpense = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        expense = new javax.swing.JComboBox<>();
        addExpenseButton = new javax.swing.JButton();
        newExpenseAddMatrix = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        matrix = new javax.swing.JComboBox<>();
        addMatrixButton = new javax.swing.JButton();
        newExpenseAddPayment = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        payment = new javax.swing.JComboBox<>();
        addPaymentButton = new javax.swing.JButton();
        addExpense = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20), javax.swing.BorderFactory.createTitledBorder("Expense")));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        newExpenseAddDate.setLayout(new javax.swing.BoxLayout(newExpenseAddDate, javax.swing.BoxLayout.LINE_AXIS));

        Date.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Date.setText("Date");
        Date.setMaximumSize(new java.awt.Dimension(35, 30));
        Date.setMinimumSize(new java.awt.Dimension(35, 30));
        Date.setPreferredSize(new java.awt.Dimension(35, 30));
        newExpenseAddDate.add(Date);

        expenseDateYear.setModel(new javax.swing.SpinnerNumberModel(2025, null, null, 1));
        expenseDateYear.setEnabled(false);
        expenseDateYear.setMaximumSize(new java.awt.Dimension(65, 30));
        expenseDateYear.setMinimumSize(new java.awt.Dimension(65, 30));
        expenseDateYear.setPreferredSize(new java.awt.Dimension(65, 30));
        newExpenseAddDate.add(expenseDateYear);

        expenseDateMonth.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));
        expenseDateMonth.setEnabled(false);
        expenseDateMonth.setFocusable(false);
        expenseDateMonth.setMaximumSize(new java.awt.Dimension(50, 30));
        expenseDateMonth.setMinimumSize(new java.awt.Dimension(50, 30));
        expenseDateMonth.setPreferredSize(new java.awt.Dimension(50, 30));
        newExpenseAddDate.add(expenseDateMonth);

        expenseDateDay.setModel(new javax.swing.SpinnerNumberModel(1, 1, 31, 1));
        expenseDateDay.setMaximumSize(new java.awt.Dimension(50, 30));
        expenseDateDay.setMinimumSize(new java.awt.Dimension(50, 30));
        expenseDateDay.setPreferredSize(new java.awt.Dimension(50, 30));
        newExpenseAddDate.add(expenseDateDay);

        add(newExpenseAddDate);

        newExpenseAddCompany.setMaximumSize(new java.awt.Dimension(200, 30));
        newExpenseAddCompany.setMinimumSize(new java.awt.Dimension(200, 30));
        newExpenseAddCompany.setPreferredSize(new java.awt.Dimension(200, 30));
        newExpenseAddCompany.setLayout(new javax.swing.BoxLayout(newExpenseAddCompany, javax.swing.BoxLayout.LINE_AXIS));

        companyLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        companyLabel.setText("Company");
        companyLabel.setMaximumSize(new java.awt.Dimension(80, 30));
        companyLabel.setMinimumSize(new java.awt.Dimension(80, 30));
        companyLabel.setPreferredSize(new java.awt.Dimension(80, 30));
        newExpenseAddCompany.add(companyLabel);

        company.setEditable(true);
        company.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "opcion1", "opcion2" }));
        company.setMaximumSize(new java.awt.Dimension(120, 30));
        company.setMinimumSize(new java.awt.Dimension(20, 20));
        company.setPreferredSize(new java.awt.Dimension(80, 20));
        newExpenseAddCompany.add(company);

        addCompanyButton.setBackground(new java.awt.Color(102, 102, 255));
        addCompanyButton.setForeground(new java.awt.Color(255, 255, 255));
        addCompanyButton.setText("+");
        addCompanyButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addCompanyButton.setMaximumSize(new java.awt.Dimension(30, 30));
        addCompanyButton.setMinimumSize(new java.awt.Dimension(30, 30));
        addCompanyButton.setPreferredSize(new java.awt.Dimension(30, 30));
        addCompanyButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newExpenseAddCompany.add(addCompanyButton);

        add(newExpenseAddCompany);

        newExpenseAddAmount.setLayout(new javax.swing.BoxLayout(newExpenseAddAmount, javax.swing.BoxLayout.LINE_AXIS));

        Amount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Amount.setText("Amount");
        Amount.setMaximumSize(new java.awt.Dimension(80, 30));
        Amount.setMinimumSize(new java.awt.Dimension(80, 30));
        Amount.setPreferredSize(new java.awt.Dimension(80, 30));
        newExpenseAddAmount.add(Amount);

        amount.setText("1000");
        amount.setMaximumSize(new java.awt.Dimension(120, 30));
        amount.setMinimumSize(new java.awt.Dimension(120, 30));
        amount.setPreferredSize(new java.awt.Dimension(120, 30));
        newExpenseAddAmount.add(amount);

        add(newExpenseAddAmount);

        newExpenseAddExpense.setMaximumSize(new java.awt.Dimension(200, 30));
        newExpenseAddExpense.setMinimumSize(new java.awt.Dimension(200, 30));
        newExpenseAddExpense.setPreferredSize(new java.awt.Dimension(200, 30));
        newExpenseAddExpense.setLayout(new javax.swing.BoxLayout(newExpenseAddExpense, javax.swing.BoxLayout.LINE_AXIS));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Expense");
        jLabel6.setMaximumSize(new java.awt.Dimension(80, 30));
        jLabel6.setMinimumSize(new java.awt.Dimension(80, 30));
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 30));
        newExpenseAddExpense.add(jLabel6);

        expense.setEditable(true);
        expense.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Personal", "Trabajo" }));
        expense.setMaximumSize(new java.awt.Dimension(120, 30));
        expense.setMinimumSize(new java.awt.Dimension(20, 20));
        expense.setPreferredSize(new java.awt.Dimension(80, 20));
        newExpenseAddExpense.add(expense);

        addExpenseButton.setBackground(new java.awt.Color(102, 102, 255));
        addExpenseButton.setForeground(new java.awt.Color(255, 255, 255));
        addExpenseButton.setText("+");
        addExpenseButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addExpenseButton.setMaximumSize(new java.awt.Dimension(30, 30));
        addExpenseButton.setMinimumSize(new java.awt.Dimension(30, 30));
        addExpenseButton.setPreferredSize(new java.awt.Dimension(30, 30));
        addExpenseButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newExpenseAddExpense.add(addExpenseButton);

        add(newExpenseAddExpense);

        newExpenseAddMatrix.setMaximumSize(new java.awt.Dimension(200, 30));
        newExpenseAddMatrix.setMinimumSize(new java.awt.Dimension(200, 30));
        newExpenseAddMatrix.setPreferredSize(new java.awt.Dimension(200, 30));
        newExpenseAddMatrix.setLayout(new javax.swing.BoxLayout(newExpenseAddMatrix, javax.swing.BoxLayout.LINE_AXIS));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Matrix");
        jLabel7.setMaximumSize(new java.awt.Dimension(80, 30));
        jLabel7.setMinimumSize(new java.awt.Dimension(80, 30));
        jLabel7.setPreferredSize(new java.awt.Dimension(80, 30));
        newExpenseAddMatrix.add(jLabel7);

        matrix.setEditable(true);
        matrix.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hogar", "Universidad" }));
        matrix.setMaximumSize(new java.awt.Dimension(120, 30));
        matrix.setMinimumSize(new java.awt.Dimension(20, 20));
        matrix.setPreferredSize(new java.awt.Dimension(80, 20));
        newExpenseAddMatrix.add(matrix);

        addMatrixButton.setBackground(new java.awt.Color(102, 102, 255));
        addMatrixButton.setForeground(new java.awt.Color(255, 255, 255));
        addMatrixButton.setText("+");
        addMatrixButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addMatrixButton.setMaximumSize(new java.awt.Dimension(30, 30));
        addMatrixButton.setMinimumSize(new java.awt.Dimension(30, 30));
        addMatrixButton.setPreferredSize(new java.awt.Dimension(30, 30));
        addMatrixButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newExpenseAddMatrix.add(addMatrixButton);

        add(newExpenseAddMatrix);

        newExpenseAddPayment.setMaximumSize(new java.awt.Dimension(200, 30));
        newExpenseAddPayment.setMinimumSize(new java.awt.Dimension(200, 30));
        newExpenseAddPayment.setPreferredSize(new java.awt.Dimension(200, 30));
        newExpenseAddPayment.setLayout(new javax.swing.BoxLayout(newExpenseAddPayment, javax.swing.BoxLayout.LINE_AXIS));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Payment");
        jLabel8.setMaximumSize(new java.awt.Dimension(80, 30));
        jLabel8.setMinimumSize(new java.awt.Dimension(80, 30));
        jLabel8.setPreferredSize(new java.awt.Dimension(80, 30));
        newExpenseAddPayment.add(jLabel8);

        payment.setEditable(true);
        payment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tarjeta", "Efectivo" }));
        payment.setMaximumSize(new java.awt.Dimension(120, 30));
        payment.setMinimumSize(new java.awt.Dimension(20, 20));
        payment.setPreferredSize(new java.awt.Dimension(80, 20));
        newExpenseAddPayment.add(payment);

        addPaymentButton.setBackground(new java.awt.Color(102, 102, 255));
        addPaymentButton.setForeground(new java.awt.Color(255, 255, 255));
        addPaymentButton.setText("+");
        addPaymentButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addPaymentButton.setMaximumSize(new java.awt.Dimension(30, 30));
        addPaymentButton.setMinimumSize(new java.awt.Dimension(30, 30));
        addPaymentButton.setPreferredSize(new java.awt.Dimension(30, 30));
        addPaymentButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newExpenseAddPayment.add(addPaymentButton);

        add(newExpenseAddPayment);

        addExpense.setBackground(new java.awt.Color(51, 102, 255));
        addExpense.setForeground(new java.awt.Color(255, 255, 255));
        addExpense.setText("Add");
        addExpense.setAlignmentX(0.5F);
        addExpense.setAlignmentY(1.0F);
        addExpense.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addExpense.setMargin(new java.awt.Insets(1, 1, 1, 1));
        addExpense.setMaximumSize(new java.awt.Dimension(50, 30));
        addExpense.setMinimumSize(new java.awt.Dimension(50, 30));
        addExpense.setPreferredSize(new java.awt.Dimension(50, 30));
        addExpense.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        add(addExpense);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Amount;
    private javax.swing.JLabel Date;
    private javax.swing.JButton addCompanyButton;
    private javax.swing.JButton addExpense;
    private javax.swing.JButton addExpenseButton;
    private javax.swing.JButton addMatrixButton;
    private javax.swing.JButton addPaymentButton;
    private javax.swing.JTextField amount;
    private javax.swing.JComboBox<String> company;
    private javax.swing.JLabel companyLabel;
    private javax.swing.JComboBox<String> expense;
    private javax.swing.JSpinner expenseDateDay;
    private javax.swing.JSpinner expenseDateMonth;
    private javax.swing.JSpinner expenseDateYear;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JComboBox<String> matrix;
    private javax.swing.JPanel newExpenseAddAmount;
    private javax.swing.JPanel newExpenseAddCompany;
    private javax.swing.JPanel newExpenseAddDate;
    private javax.swing.JPanel newExpenseAddExpense;
    private javax.swing.JPanel newExpenseAddMatrix;
    private javax.swing.JPanel newExpenseAddPayment;
    private javax.swing.JComboBox<String> payment;
    // End of variables declaration//GEN-END:variables
}
