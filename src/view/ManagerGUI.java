package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ManagerGUI extends Thread {
    private ServerSocket serverSocket;
    private JPanel panelStaff;
    private JTextField txtPort;
    private JTabbedPane tabbedPane;

    public ManagerGUI() {
        this.start();
        initSocket();
        txtPort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(txtPort.getText());
            }
        });
    }

    @Override
    public void run() {
        while (true) {
            waitConnection();
        }
    }

    private void initSocket(){
        try {
            serverSocket = new ServerSocket(Integer.parseInt(txtPort.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void waitConnection(){
        try {
            Socket socket = serverSocket.accept();
            if (socket != null) {
                InputStream is = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                String name = ois.readObject().toString();
                JPanel p = new ChatGUI(socket, "Staff").getPanelMain();

                tabbedPane.addTab(name, p);
                p.updateUI();
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JPanel getPanelStaff() {
        return panelStaff;
    }
}
