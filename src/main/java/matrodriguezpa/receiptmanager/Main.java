package matrodriguezpa.receiptmanager;

import javax.swing.SwingUtilities;
import matrodriguezpa.receiptmanager.controller.ProjectController;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProjectController().startApplication());
    }
}