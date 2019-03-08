import view.ManagerGUI;

import javax.swing.*;

public class ManagerMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ManagerGUI gui = new ManagerGUI();
        frame.setContentPane(gui.getPanelStaff());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700,500);
        frame.setVisible(true);
    }
}
