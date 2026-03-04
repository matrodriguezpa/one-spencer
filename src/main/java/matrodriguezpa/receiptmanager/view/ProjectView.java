
package matrodriguezpa.receiptmanager.view;

import lombok.Getter;

@Getter
public class ProjectView extends javax.swing.JPanel {

    public ProjectView() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userButtonGroup = new javax.swing.ButtonGroup();
        userFormPanel = new javax.swing.JPanel();
        insertName = new javax.swing.JLabel();
        newUserTextField = new javax.swing.JTextField();
        main = new javax.swing.JPanel();
        welcomeImage = new javax.swing.JLabel();
        userSelectionPanel = new javax.swing.JPanel();
        userSelectionText = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        openUserProjects = new javax.swing.JButton();
        userListOptions = new javax.swing.JToolBar();
        createNewUser = new javax.swing.JButton();
        openUserProjects1 = new javax.swing.JButton();
        openUserProjects2 = new javax.swing.JButton();

        userFormPanel.setLayout(new javax.swing.BoxLayout(userFormPanel, javax.swing.BoxLayout.Y_AXIS));

        insertName.setText("Insert Name");
        userFormPanel.add(insertName);

        newUserTextField.setText("Nuevo usuario");
        newUserTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newUserTextFieldActionPerformed(evt);
            }
        });
        userFormPanel.add(newUserTextField);

        setLayout(new java.awt.BorderLayout());

        main.setLayout(new java.awt.BorderLayout());

        welcomeImage.setBackground(new java.awt.Color(255, 204, 204));
        welcomeImage.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        welcomeImage.setForeground(new java.awt.Color(255, 51, 102));
        welcomeImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeImage.setText("ONE EXPENSE");
        welcomeImage.setMaximumSize(new java.awt.Dimension(70, 70));
        welcomeImage.setMinimumSize(new java.awt.Dimension(70, 70));
        welcomeImage.setPreferredSize(new java.awt.Dimension(70, 70));
        main.add(welcomeImage, java.awt.BorderLayout.PAGE_START);

        userSelectionPanel.setAlignmentX(0.0F);
        userSelectionPanel.setAlignmentY(0.0F);
        userSelectionPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        userSelectionPanel.setMaximumSize(new java.awt.Dimension(300, 300));
        userSelectionPanel.setMinimumSize(new java.awt.Dimension(300, 300));
        userSelectionPanel.setPreferredSize(new java.awt.Dimension(300, 300));
        userSelectionPanel.setLayout(new javax.swing.BoxLayout(userSelectionPanel, javax.swing.BoxLayout.Y_AXIS));

        userSelectionText.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        userSelectionText.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userSelectionText.setText("User Selection:");
        userSelectionText.setToolTipText("");
        userSelectionText.setAlignmentY(-1.0F);
        userSelectionText.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        userSelectionText.setMaximumSize(new java.awt.Dimension(500, 16));
        userSelectionPanel.add(userSelectionText);

        main.add(userSelectionPanel, java.awt.BorderLayout.CENTER);

        jPanel1.setMaximumSize(new java.awt.Dimension(70, 40));
        jPanel1.setMinimumSize(new java.awt.Dimension(70, 40));
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(70, 40));

        openUserProjects.setBackground(new java.awt.Color(153, 153, 153));
        openUserProjects.setForeground(new java.awt.Color(255, 255, 255));
        openUserProjects.setText("Open");
        openUserProjects.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openUserProjects.setMargin(new java.awt.Insets(0, 0, 0, 0));
        openUserProjects.setMaximumSize(new java.awt.Dimension(70, 30));
        openUserProjects.setMinimumSize(new java.awt.Dimension(70, 30));
        openUserProjects.setPreferredSize(new java.awt.Dimension(70, 30));
        openUserProjects.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(openUserProjects);

        jSplitPane1.setRightComponent(jPanel1);

        userListOptions.setRollover(true);
        userListOptions.setMaximumSize(new java.awt.Dimension(700, 50));
        userListOptions.setMinimumSize(new java.awt.Dimension(700, 50));
        userListOptions.setPreferredSize(new java.awt.Dimension(700, 50));

        createNewUser.setBackground(new java.awt.Color(0, 102, 255));
        createNewUser.setForeground(new java.awt.Color(255, 255, 255));
        createNewUser.setText("New");
        createNewUser.setFocusable(false);
        createNewUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createNewUser.setMargin(new java.awt.Insets(0, 0, 0, 0));
        createNewUser.setMaximumSize(new java.awt.Dimension(50, 25));
        createNewUser.setMinimumSize(new java.awt.Dimension(50, 25));
        createNewUser.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        userListOptions.add(createNewUser);

        openUserProjects1.setBackground(new java.awt.Color(153, 153, 153));
        openUserProjects1.setForeground(new java.awt.Color(255, 255, 255));
        openUserProjects1.setText("Edit");
        openUserProjects1.setEnabled(false);
        openUserProjects1.setFocusable(false);
        openUserProjects1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openUserProjects1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        openUserProjects1.setMaximumSize(new java.awt.Dimension(50, 25));
        openUserProjects1.setMinimumSize(new java.awt.Dimension(50, 25));
        openUserProjects1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        userListOptions.add(openUserProjects1);

        openUserProjects2.setBackground(new java.awt.Color(255, 0, 51));
        openUserProjects2.setForeground(new java.awt.Color(255, 255, 255));
        openUserProjects2.setText("Delete");
        openUserProjects2.setEnabled(false);
        openUserProjects2.setFocusable(false);
        openUserProjects2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openUserProjects2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        openUserProjects2.setMaximumSize(new java.awt.Dimension(50, 25));
        openUserProjects2.setMinimumSize(new java.awt.Dimension(50, 25));
        openUserProjects2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        userListOptions.add(openUserProjects2);

        jSplitPane1.setLeftComponent(userListOptions);

        main.add(jSplitPane1, java.awt.BorderLayout.PAGE_END);

        add(main, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void newUserTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newUserTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newUserTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton createNewUser;
    private javax.swing.JLabel insertName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel main;
    private javax.swing.JTextField newUserTextField;
    public javax.swing.JButton openUserProjects;
    public javax.swing.JButton openUserProjects1;
    public javax.swing.JButton openUserProjects2;
    public javax.swing.ButtonGroup userButtonGroup;
    private javax.swing.JPanel userFormPanel;
    private javax.swing.JToolBar userListOptions;
    private javax.swing.JPanel userSelectionPanel;
    private javax.swing.JLabel userSelectionText;
    private javax.swing.JLabel welcomeImage;
    // End of variables declaration//GEN-END:variables
}
