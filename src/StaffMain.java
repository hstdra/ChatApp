import view.StaffGUI;

import javax.swing.*;

public class StaffMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        StaffGUI gui = new StaffGUI();
        frame.getContentPane().add(gui.getPanelMain());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700,500);
        frame.setVisible(true);
    }
}
