package matrodriguezpa.receiptmanager.view;

import lombok.Getter;

@Getter
public class YearView extends javax.swing.JPanel {

    public YearView() {
        initComponents();
        getLeftNavigation();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        YearFormPanel = new javax.swing.JPanel();
        insertName = new javax.swing.JLabel();
        DateSpinner = new javax.swing.JSpinner();
        TagTextField = new javax.swing.JTextField();
        leftSibeBar = new javax.swing.JToolBar();
        jToolBar1 = new javax.swing.JToolBar();
        newYearButton = new javax.swing.JButton();
        leftScroll = new javax.swing.JScrollPane();
        leftNavigation = new javax.swing.JTree();

        YearFormPanel.setLayout(new javax.swing.BoxLayout(YearFormPanel, javax.swing.BoxLayout.Y_AXIS));

        insertName.setText("Insert Name");
        YearFormPanel.add(insertName);
        YearFormPanel.add(DateSpinner);

        TagTextField.setText("Tag");
        TagTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TagTextFieldActionPerformed(evt);
            }
        });
        YearFormPanel.add(TagTextField);

        setLayout(new java.awt.BorderLayout());

        leftSibeBar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20), javax.swing.BorderFactory.createTitledBorder("Project Navigation")));
        leftSibeBar.setOrientation(javax.swing.SwingConstants.VERTICAL);
        leftSibeBar.setRollover(true);
        leftSibeBar.setMaximumSize(new java.awt.Dimension(140, 140));
        leftSibeBar.setPreferredSize(new java.awt.Dimension(200, 140));

        jToolBar1.setRollover(true);

        newYearButton.setBackground(new java.awt.Color(255, 102, 102));
        newYearButton.setText("New");
        newYearButton.setFocusable(false);
        newYearButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newYearButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newYearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newYearButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(newYearButton);

        leftSibeBar.add(jToolBar1);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Unset");
        leftNavigation.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        leftScroll.setViewportView(leftNavigation);

        leftSibeBar.add(leftScroll);

        add(leftSibeBar, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void newYearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newYearButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newYearButtonActionPerformed

    private void TagTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TagTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TagTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner DateSpinner;
    private javax.swing.JTextField TagTextField;
    private javax.swing.JPanel YearFormPanel;
    private javax.swing.JLabel insertName;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTree leftNavigation;
    private javax.swing.JScrollPane leftScroll;
    private javax.swing.JToolBar leftSibeBar;
    private javax.swing.JButton newYearButton;
    // End of variables declaration//GEN-END:variables
}
