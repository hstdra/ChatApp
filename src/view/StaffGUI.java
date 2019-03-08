package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class StaffGUI{
    private JPanel panelMain;
    private JTextField txtStaff;
    private JButton connectButton;
    private JTextField txtManagerIP;
    private JTextField txtPort;
    private JPanel chatPanel;
    private JPanel panelInfo;
    private ChatGUI chat;
    private JDesktopPane pane;

    public StaffGUI() {
        update();
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Socket socket = new Socket(txtManagerIP.getText(), Integer.parseInt(txtPort.getText()));

                    OutputStream os = socket.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(txtStaff.getText());
                    oos.flush();

                    chat.setSocket(socket);
                    chat.setPeople(txtStaff.getText());
                    chatPanel.setVisible(true);
                    connectButton.setEnabled(false);
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });


    }
    private void update(){
        chatPanel.removeAll();
        chatPanel.setLayout(new GridLayout());
        chat = new ChatGUI(null, null);
        chatPanel.add(chat.getPanelMain());
        chatPanel.setVisible(false);
    }

    public JPanel getPanelMain() {
        return panelMain;
    }
}