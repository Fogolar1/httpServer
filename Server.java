import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    int port = 3030;

    public void start(){
        try (ServerSocket serverSocket = new ServerSocket(port)){
            Socket client = serverSocket.accept();
            redirectPort(client);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public void redirectPort(Socket client){
        FileSenderThread fileSenderThread = new FileSenderThread(client);
        fileSenderThread.start();
    }
}
