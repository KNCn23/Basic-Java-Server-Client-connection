import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 6666);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String line = "";
            while (!line.equals("Son")) {
                line = reader.readLine(); // kullanıcıdan girdi alır
                dataOutputStream.writeUTF(line); // server'a mesaj gönderir
                dataOutputStream.flush();
                System.out.println("Server'dan gelen mesaj: " + dataInputStream.readUTF()); // server'dan gelen mesajı okur
            }

            dataOutputStream.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}