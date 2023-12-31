import java.io.*;
import java.net.Socket;

public class Client {

    private final static String PATH = "C:\\Users\\ENZO.KF\\Desktop\\";

    public static void start(){
        try(Socket socket = new Socket("127.0.0.1", 3030)){
            requestFile(socket, "html");
            requestFile(socket, "img");
            requestFile(socket, "exit");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void requestFile(Socket socket, String type) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(type);
        if(type.equals("exit"))
            return;
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

                File download = new File(PATH  +  fileName);
                System.out.println(download.getAbsolutePath());
                FileOutputStream fileOutputStream = new FileOutputStream(download);
                fileOutputStream.write(fileContentBytes);
                fileOutputStream.close();
            }
        }
    }
}
