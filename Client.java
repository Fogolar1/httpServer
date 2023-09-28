import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final Scanner scanner = new Scanner(System.in);

    public static void start(){
        try(Socket socket = new Socket("127.0.0.1", 3030)){
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            while (true){
                String messagem = scanner.nextLine();
                dataOutputStream.writeUTF(messagem);
                if(messagem.equals("exit")){
                    System.out.println("Closing connection");
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
