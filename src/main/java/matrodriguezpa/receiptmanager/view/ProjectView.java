package matrodriguezpa.receiptmanager.view;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ProjectView extends javax.swing.JFrame {

    public ProjectView() {
        initComponents();
    }

    public JMenuBar getMainMenuBar() {
        return menuBar;
    }

    public JMenuItem getExportExcel() {
        return exportExcelItem;
    }

    public JMenuItem getAboutItem() {
        return aboutItem;
    }

    public JMenuItem getJavadocItem() {
        return javadocItem;
    }

    public JMenuItem getUserDocItem() {
        return userDocItem;
    }

    public JMenuItem getExitProgramItem() {
        return exitProgramItem;
    }

    public JPanel getLoadingPanel() {
        return loadingPanel;
    }

    public JPanel getAboutPanel() {
        return aboutPanel;
    }

    public JPanel getMain() {
        return projectPanel;
    }

    public JPanel getProjectSelectionPanel() {
        return projectSelectionPanel;
    }

    public ButtonGroup getProjectButtonGroup() {
        return projectButtonGroup;
    }

    public JMenuItem getCloseProjectItem() {
        return closeProjectItem;
    }

    public JMenuItem getExportExcelItem() {
        return exportExcelItem;
    }

    public JMenuItem getCreateProjectItem() {
        return newProjectItem;
    }

    public JMenuItem getOpenProjectItem() {
        return openProjectItem;
    }

    public JButton getCreateProjectButton() {
        return createProjectButton;
    }

    public JButton getDeleteProjectButton() {
        return deleteProjectButton;
    }

    public JButton getEditProjectButton() {
        return editProjectButton;
    }

    public JButton getOpenProjectButton() {
        return openProjectButton;
    }

    public JScrollPane getUserSelectionScrollPanel() {
        return userSelectionScrollPanel;
    }

    public JPanel getProjectFormPanel() {
        return projectFormPanel;
    }

    public JTextField getNewProjectTextField() {
        return newProjectTextField;
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        projectButtonGroup = new javax.swing.ButtonGroup();
        projectFormPanel = new javax.swing.JPanel();
        insertName = new javax.swing.JLabel();
        newProjectTextField = new javax.swing.JTextField();
        aboutPanel = new javax.swing.JPanel();
        GitHub = new javax.swing.JLabel();
        Copyrigth = new javax.swing.JLabel();
        loadingPanel = new javax.swing.JPanel();
        loadingImage = new javax.swing.JLabel();
        loadingFooter = new javax.swing.JSplitPane();
        author = new javax.swing.JLabel();
        version = new javax.swing.JLabel();
        projectPanel = new javax.swing.JPanel();
        welcomeImage = new javax.swing.JLabel();
        userSelectionScrollPanel = new javax.swing.JScrollPane();
        projectSelectionPanel = new javax.swing.JPanel();
        userSelectionText = new javax.swing.JLabel();
        projectButtonsSplitPanel = new javax.swing.JSplitPane();
        openProjectPanel = new javax.swing.JPanel();
        openProjectButton = new javax.swing.JButton();
        projectOptionsToolBar = new javax.swing.JToolBar();
        createProjectButton = new javax.swing.JButton();
        editProjectButton = new javax.swing.JButton();
        deleteProjectButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newProjectItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        openProjectItem = new javax.swing.JMenuItem();
        closeProjectItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        exportExcelItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        exitProgramItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        userDocItem = new javax.swing.JMenuItem();
        javadocItem = new javax.swing.JMenuItem();
        aboutItem = new javax.swing.JMenuItem();

        projectFormPanel.setLayout(new javax.swing.BoxLayout(projectFormPanel, javax.swing.BoxLayout.Y_AXIS));

        insertName.setText("Insert Name");
        projectFormPanel.add(insertName);

        newProjectTextField.setText("Nuevo usuario");
        projectFormPanel.add(newProjectTextField);

        GitHub.setText("matrodriguezpa on Github");

        Copyrigth.setText("All expencer copygith");

        javax.swing.GroupLayout aboutPanelLayout = new javax.swing.GroupLayout(aboutPanel);
        aboutPanel.setLayout(aboutPanelLayout);
        aboutPanelLayout.setHorizontalGroup(
            aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aboutPanelLayout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(GitHub)
                .addGap(56, 56, 56))
            .addGroup(aboutPanelLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(Copyrigth)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        aboutPanelLayout.setVerticalGroup(
            aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(GitHub)
                .addGap(44, 44, 44)
                .addComponent(Copyrigth)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        loadingPanel.setBackground(new java.awt.Color(255, 102, 153));
        loadingPanel.setLayout(new java.awt.BorderLayout());

        loadingImage.setBackground(new java.awt.Color(255, 51, 153));
        loadingImage.setForeground(new java.awt.Color(255, 51, 102));
        loadingImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Tittle.png"))); // NOI18N
        loadingPanel.add(loadingImage, java.awt.BorderLayout.CENTER);

        loadingFooter.setBackground(new java.awt.Color(255, 51, 102));
        loadingFooter.setDividerLocation(150);
        loadingFooter.setDividerSize(0);

        author.setForeground(new java.awt.Color(255, 255, 255));
        author.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        author.setText("By matrodriguezpa");
        loadingFooter.setRightComponent(author);

        version.setForeground(new java.awt.Color(255, 255, 255));
        version.setText("v0.1 beta");
        loadingFooter.setLeftComponent(version);

        loadingPanel.add(loadingFooter, java.awt.BorderLayout.SOUTH);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        projectPanel.setLayout(new java.awt.BorderLayout());

        welcomeImage.setBackground(new java.awt.Color(255, 204, 204));
        welcomeImage.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        welcomeImage.setForeground(new java.awt.Color(255, 51, 102));
        welcomeImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeImage.setText("RECEIPT MANAGER");
        welcomeImage.setMaximumSize(new java.awt.Dimension(70, 70));
        welcomeImage.setMinimumSize(new java.awt.Dimension(70, 70));
        welcomeImage.setPreferredSize(new java.awt.Dimension(70, 70));
        projectPanel.add(welcomeImage, java.awt.BorderLayout.PAGE_START);

        projectSelectionPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        projectSelectionPanel.setAlignmentY(0.0F);
        projectSelectionPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        projectSelectionPanel.setMaximumSize(new java.awt.Dimension(300, 300));
        projectSelectionPanel.setMinimumSize(new java.awt.Dimension(300, 300));
        projectSelectionPanel.setPreferredSize(new java.awt.Dimension(300, 300));
        projectSelectionPanel.setLayout(new javax.swing.BoxLayout(projectSelectionPanel, javax.swing.BoxLayout.Y_AXIS));

        userSelectionText.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        userSelectionText.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userSelectionText.setText("Project Selection:");
        userSelectionText.setToolTipText("");
        userSelectionText.setAlignmentY(-1.0F);
        userSelectionText.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        userSelectionText.setMaximumSize(new java.awt.Dimension(500, 16));
        projectSelectionPanel.add(userSelectionText);

        userSelectionScrollPanel.setViewportView(projectSelectionPanel);

        projectPanel.add(userSelectionScrollPanel, java.awt.BorderLayout.CENTER);

        openProjectPanel.setMaximumSize(new java.awt.Dimension(70, 40));
        openProjectPanel.setMinimumSize(new java.awt.Dimension(70, 40));
        openProjectPanel.setName(""); // NOI18N
        openProjectPanel.setPreferredSize(new java.awt.Dimension(70, 40));

        openProjectButton.setBackground(new java.awt.Color(153, 153, 153));
        openProjectButton.setForeground(new java.awt.Color(255, 255, 255));
        openProjectButton.setText("Open");
        openProjectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openProjectButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        openProjectButton.setMaximumSize(new java.awt.Dimension(70, 30));
        openProjectButton.setMinimumSize(new java.awt.Dimension(70, 30));
        openProjectButton.setPreferredSize(new java.awt.Dimension(70, 30));
        openProjectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openProjectPanel.add(openProjectButton);

        projectButtonsSplitPanel.setRightComponent(openProjectPanel);

        projectOptionsToolBar.setRollover(true);
        projectOptionsToolBar.setMaximumSize(new java.awt.Dimension(700, 50));
        projectOptionsToolBar.setMinimumSize(new java.awt.Dimension(700, 50));
        projectOptionsToolBar.setPreferredSize(new java.awt.Dimension(700, 50));

        createProjectButton.setBackground(new java.awt.Color(0, 102, 255));
        createProjectButton.setForeground(new java.awt.Color(255, 255, 255));
        createProjectButton.setText("Create");
        createProjectButton.setFocusable(false);
        createProjectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createProjectButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        createProjectButton.setMaximumSize(new java.awt.Dimension(50, 25));
        createProjectButton.setMinimumSize(new java.awt.Dimension(50, 25));
        createProjectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        projectOptionsToolBar.add(createProjectButton);

        editProjectButton.setBackground(new java.awt.Color(153, 153, 153));
        editProjectButton.setForeground(new java.awt.Color(255, 255, 255));
        editProjectButton.setText("Edit");
        editProjectButton.setFocusable(false);
        editProjectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editProjectButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        editProjectButton.setMaximumSize(new java.awt.Dimension(50, 25));
        editProjectButton.setMinimumSize(new java.awt.Dimension(50, 25));
        editProjectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        projectOptionsToolBar.add(editProjectButton);

        deleteProjectButton.setBackground(new java.awt.Color(255, 0, 51));
        deleteProjectButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteProjectButton.setText("Delete");
        deleteProjectButton.setFocusable(false);
        deleteProjectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteProjectButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        deleteProjectButton.setMaximumSize(new java.awt.Dimension(50, 25));
        deleteProjectButton.setMinimumSize(new java.awt.Dimension(50, 25));
        deleteProjectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        projectOptionsToolBar.add(deleteProjectButton);

        projectButtonsSplitPanel.setLeftComponent(projectOptionsToolBar);

        projectPanel.add(projectButtonsSplitPanel, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(projectPanel, java.awt.BorderLayout.CENTER);

        fileMenu.setBackground(new java.awt.Color(51, 102, 255));
        fileMenu.setText("File");

        newProjectItem.setText("New project");
        newProjectItem.setFocusable(true);
        fileMenu.add(newProjectItem);
        fileMenu.add(jSeparator1);

        openProjectItem.setText("Open project");
        fileMenu.add(openProjectItem);

        closeProjectItem.setText("Close project");
        fileMenu.add(closeProjectItem);
        fileMenu.add(jSeparator2);

        exportExcelItem.setText("Export Excel");
        fileMenu.add(exportExcelItem);
        fileMenu.add(jSeparator3);

        exitProgramItem.setText("Exit program");
        fileMenu.add(exitProgramItem);

        menuBar.add(fileMenu);

        helpMenu.setBackground(new java.awt.Color(51, 102, 255));
        helpMenu.setText("Help");

        userDocItem.setText("Documentation");
        helpMenu.add(userDocItem);

        javadocItem.setText("Javadoc");
        helpMenu.add(javadocItem);

        aboutItem.setText("About");
        helpMenu.add(aboutItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Copyrigth;
    private javax.swing.JLabel GitHub;
    private javax.swing.JMenuItem aboutItem;
    private javax.swing.JPanel aboutPanel;
    private javax.swing.JLabel author;
    private javax.swing.JMenuItem closeProjectItem;
    private javax.swing.JButton createProjectButton;
    private javax.swing.JButton deleteProjectButton;
    private javax.swing.JButton editProjectButton;
    private javax.swing.JMenuItem exitProgramItem;
    private javax.swing.JMenuItem exportExcelItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel insertName;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JMenuItem javadocItem;
    private javax.swing.JSplitPane loadingFooter;
    private javax.swing.JLabel loadingImage;
    private javax.swing.JPanel loadingPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newProjectItem;
    private javax.swing.JTextField newProjectTextField;
    private javax.swing.JButton openProjectButton;
    private javax.swing.JMenuItem openProjectItem;
    private javax.swing.JPanel openProjectPanel;
    public javax.swing.ButtonGroup projectButtonGroup;
    private javax.swing.JSplitPane projectButtonsSplitPanel;
    private javax.swing.JPanel projectFormPanel;
    private javax.swing.JToolBar projectOptionsToolBar;
    private javax.swing.JPanel projectPanel;
    private javax.swing.JPanel projectSelectionPanel;
    private javax.swing.JMenuItem userDocItem;
    private javax.swing.JScrollPane userSelectionScrollPanel;
    private javax.swing.JLabel userSelectionText;
    private javax.swing.JLabel version;
    private javax.swing.JLabel welcomeImage;
    // End of variables declaration//GEN-END:variables
}
