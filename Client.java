import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final Scanner scanner = new Scanner(System.in);

    public static void start(){
        try(Socket socket = new Socket("127.0.0.1", 3030)){
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            int fileNameLength = dataInputStream.readInt();
            if(fileNameLength > 0){
                byte[] fileNameBuffer = new byte[fileNameLength];
                dataInputStream.readFully(fileNameBuffer, 0, fileNameBuffer.length);
                String fileName = new String(fileNameBuffer);
                System.out.println("File name: " + fileName);

                int fileContentLength = dataInputStream.readInt();
                if(fileContentLength > 0){
                    byte[] fileContentBytes = new byte[fileContentLength];
                    dataInputStream.readFully(fileContentBytes, 0, fileContentLength);

                    File download = new File("C:\\Users\\ENZO.KF\\" + fileName);
                    System.out.println(download.getAbsolutePath());
                    FileOutputStream fileOutputStream = new FileOutputStream(download);
                    fileOutputStream.write(fileContentBytes);
                    fileOutputStream.close();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
