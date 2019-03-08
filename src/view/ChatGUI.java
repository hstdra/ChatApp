package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ChatGUI extends Thread {
    private JPanel panelMain;
    private JButton SENDButton;
    private JTextArea txtSend;
    private JTextArea textArea1;
    private JPanel JPanel;
    private JPanel chatPanel;
    private Socket socket;
    private String name;

    public ChatGUI(Socket sk, String n) {
        socket = sk;
        name = n;
        this.start();
        SENDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
    }

    @Override
    public void run() {
        while (true) {
            waitMessage();
        }
    }

    private void waitMessage() {
        try {
            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            textArea1.append(ois.readObject().toString());
            Thread.sleep(1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void sendMessage() {
        String mess = txtSend.getText();
        if (mess.equals("")) return;
        mess = "\n" + name + ": " + mess;
        textArea1.append(mess);
        try {
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(mess);
            oos.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        txtSend.setText(null);
    }

    public javax.swing.JPanel getPanelMain() {
        return panelMain;
    }

    public void setSocket(Socket sc) {
        socket = sc;
    }

    public void setPeople(String s) {
        name = s;
    }
}
