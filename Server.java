import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    int port = 3030;

    public void start(){
        try (ServerSocket serverSocket = new ServerSocket(port)){
            Socket client = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());

            while(true){
                String message = dataInputStream.readUTF();
                System.out.println("Message received: " + message);
                if(message.equals("exit")){
                    System.out.println("Closing connection");
                    break;
                }
            }
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
