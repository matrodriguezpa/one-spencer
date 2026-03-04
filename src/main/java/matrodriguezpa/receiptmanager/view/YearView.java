package matrodriguezpa.receiptmanager.view;

import lombok.Getter;

@Getter
public class YearView extends javax.swing.JPanel {

    public YearView() {
        initComponents();
        getNewProjectButton();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NewProject = new javax.swing.JPanel();
        addWorkbookLabel1 = new javax.swing.JLabel();
        newProjectName = new javax.swing.JTextField();
        addWorkbookLabel2 = new javax.swing.JLabel();
        newProjectYear = new javax.swing.JTextField();
        warning = new javax.swing.JLabel();
        leftSibeBar = new javax.swing.JToolBar();
        jToolBar1 = new javax.swing.JToolBar();
        newProjectButton = new javax.swing.JButton();
        openProjectButton = new javax.swing.JButton();
        leftScroll = new javax.swing.JScrollPane();
        leftNavigation = new javax.swing.JTree();

        NewProject.setLayout(new javax.swing.BoxLayout(NewProject, javax.swing.BoxLayout.Y_AXIS));

        addWorkbookLabel1.setText("Name*");
        NewProject.add(addWorkbookLabel1);
        NewProject.add(newProjectName);

        addWorkbookLabel2.setText("Year");
        NewProject.add(addWorkbookLabel2);

        newProjectYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newProjectYearActionPerformed(evt);
            }
        });
        NewProject.add(newProjectYear);

        warning.setForeground(java.awt.Color.red);
        NewProject.add(warning);

        setLayout(new java.awt.BorderLayout());

        leftSibeBar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20), javax.swing.BorderFactory.createTitledBorder("Project Navigation")));
        leftSibeBar.setOrientation(javax.swing.SwingConstants.VERTICAL);
        leftSibeBar.setRollover(true);
        leftSibeBar.setMaximumSize(new java.awt.Dimension(140, 140));
        leftSibeBar.setPreferredSize(new java.awt.Dimension(200, 140));

        jToolBar1.setRollover(true);

        newProjectButton.setBackground(new java.awt.Color(255, 102, 102));
        newProjectButton.setText("New");
        newProjectButton.setFocusable(false);
        newProjectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newProjectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(newProjectButton);

        openProjectButton.setBackground(new java.awt.Color(0, 102, 255));
        openProjectButton.setText("Open");
        openProjectButton.setFocusable(false);
        openProjectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openProjectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(openProjectButton);

        leftSibeBar.add(jToolBar1);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Unset");
        leftNavigation.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        leftScroll.setViewportView(leftNavigation);

        leftSibeBar.add(leftScroll);

        add(leftSibeBar, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void newProjectYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newProjectYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newProjectYearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel NewProject;
    private javax.swing.JLabel addWorkbookLabel1;
    private javax.swing.JLabel addWorkbookLabel2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTree leftNavigation;
    private javax.swing.JScrollPane leftScroll;
    private javax.swing.JToolBar leftSibeBar;
    private javax.swing.JButton newProjectButton;
    private javax.swing.JTextField newProjectName;
    private javax.swing.JTextField newProjectYear;
    private javax.swing.JButton openProjectButton;
    private javax.swing.JLabel warning;
    // End of variables declaration//GEN-END:variables
}
