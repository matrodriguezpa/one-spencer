package matrodriguezpa.receiptmanager;

import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatLightLaf;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import matrodriguezpa.receiptmanager.controller.ProjectController;
import matrodriguezpa.receiptmanager.view.ProjectView;

public class ReceiptManager extends javax.swing.JFrame {

    ImageIcon icon = new ImageIcon("src\\main\\resources\\Images\\Logo.png"); //App Icon

    public ReceiptManager() {
        initComponents(); 
        aboutItem.addActionListener(e -> openAboutWindow());
        javadocItem.addActionListener(e -> openJavaDoc());
        userDocItem.addActionListener(e -> openDocumentation());
        exitProgramItem.addActionListener(e -> closeProgram());
    }
    
    public static void main(String[] args) {
        ReceiptManager receiptManager = new ReceiptManager();
        receiptManager.showTitleScreen();
        receiptManager.applyFlatLaf();
        receiptManager.setVisible(true);
        receiptManager.start(receiptManager);
    }

    public void start(ReceiptManager view) {
        ProjectView projectView = new ProjectView();
        view.add(projectView);
        new ProjectController(projectView).start();   
    }


    public void showTitleScreen() {
        JFrame loadingFrame = new JFrame();
        loadingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loadingFrame.setUndecorated(true); // Removes window decorations

        loadingFrame.getContentPane().add(this.loadingPanel);
        loadingFrame.pack(); // Sizes frame to fit the loading panel
        loadingFrame.setLocationRelativeTo(null);
        loadingFrame.setVisible(true);

        try {
            Thread.sleep(2500); // Wait for 1 second
            loadingFrame.dispose();
        } catch (InterruptedException ex) {
            Logger.getLogger(ReceiptManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void applyFlatLaf() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Error aplying window styles: " + e);
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    private void openAboutWindow() {
        JOptionPane optionPane = new JOptionPane(aboutPanel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
        JDialog dialog = optionPane.createDialog("About");
        // Optional: make it modal
        dialog.setModal(true);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    private void openJavaDoc() {
        try {
            Desktop.getDesktop().browse(new URI("https://example.com"));
        } catch (IOException | URISyntaxException ex) {
            System.out.println("Error opening link: " + ex);
        }
    }

    private void openDocumentation() {
        try {
            Desktop.getDesktop().browse(new URI("https://example.com"));
        } catch (IOException | URISyntaxException ex) {
            System.out.println("Error opening link: " + ex);
        }
    }

    private void closeProgram() {
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        aboutPanel = new javax.swing.JPanel();
        GitHub = new javax.swing.JLabel();
        Copyrigth = new javax.swing.JLabel();
        loadingPanel = new javax.swing.JPanel();
        loadingImage = new javax.swing.JLabel();
        loadingFooter = new javax.swing.JSplitPane();
        author = new javax.swing.JLabel();
        version = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newProjectItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        openProjectItem = new javax.swing.JMenuItem();
        closeProjectItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        exitProgramItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        userDocItem = new javax.swing.JMenuItem();
        javadocItem = new javax.swing.JMenuItem();
        aboutItem = new javax.swing.JMenuItem();

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
        setTitle("One spencer");
        setIconImage(icon.getImage());
        setMinimumSize(new java.awt.Dimension(960, 540));

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
    private javax.swing.JMenuItem exitProgramItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem javadocItem;
    private javax.swing.JSplitPane loadingFooter;
    private javax.swing.JLabel loadingImage;
    private javax.swing.JPanel loadingPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newProjectItem;
    private javax.swing.JMenuItem openProjectItem;
    private javax.swing.JMenuItem userDocItem;
    private javax.swing.JLabel version;
    // End of variables declaration//GEN-END:variables

}
