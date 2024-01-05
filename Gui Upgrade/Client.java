import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Client");
        JTextField textField = new JTextField(20);
        JButton button = new JButton("Gönder");
        JTextArea textArea = new JTextArea(20, 20);
        textArea.setEditable(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Socket socket = new Socket("localhost", 6666);
                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

                    String line = textField.getText(); // metin alanından girdi alır
                    dataOutputStream.writeUTF(line); // server'a mesaj gönderir
                    dataOutputStream.flush();
                    textArea.append("Server'dan gelen mesaj: " + dataInputStream.readUTF() + "\n"); // server'dan gelen mesajı okur ve textArea'ya ekler

                    dataOutputStream.close();
                    socket.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.getContentPane().add(textField, "North");
        frame.getContentPane().add(new JScrollPane(textArea), "Center");
        frame.getContentPane().add(button, "South");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}