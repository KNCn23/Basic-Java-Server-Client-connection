import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Server");
        JLabel statusLabel = new JLabel("Mesaj bekleniyor...");
        statusLabel.setOpaque(true);
        statusLabel.setBackground(Color.RED);

        frame.getContentPane().add(statusLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);

        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            Socket socket = serverSocket.accept(); // bağlantıyı kabul eder

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            String line = "";
            while (!line.equals("Son")) {
                line = dataInputStream.readUTF(); // client'tan gelen mesajı okur
                System.out.println("Client'tan gelen mesaj: " + line);
                dataOutputStream.writeUTF("Mesaj alındı: " + line); // client'a mesaj gönderir
                dataOutputStream.flush();

                statusLabel.setText("Mesaj alındı: " + line);
                statusLabel.setBackground(Color.GREEN);
            }

            dataInputStream.close();
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}