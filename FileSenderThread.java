import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;

public class FileSenderThread extends Thread {

    Socket client = null;

    FileSenderThread(Socket client){
        this.client = client;
    }

    public void run(){
        try(ServerSocket socket = new ServerSocket(0)){
            File file = createHtmlFile(client.getRemoteSocketAddress().toString(), socket.getLocalPort() + "");
            FileInputStream fileInputStream = new FileInputStream(file);
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());

            String fileName = file.getName();
            byte[] bufferName = fileName.getBytes();
            byte[] bufferContent = new byte[(int) file.length()];
            fileInputStream.read(bufferContent);

            dataOutputStream.writeInt(bufferName.length);
            dataOutputStream.write(bufferName);

            dataOutputStream.writeInt(bufferContent.length);
            dataOutputStream.write(bufferContent);
        }catch(IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public File createHtmlFile(String ip, String port) throws IOException {
        File file = new File("C:\\Users\\ENZO.KF\\IdeaProjects\\httpServer\\template.html");
        String content  = new String(Files.readAllBytes(file.toPath()));
        String html = content
                .replace("{{ip}}", ip)
                .replace("{{porta}}", port);
        File htmlFile = new File("C:\\Users\\ENZO.KF\\IdeaProjects\\httpServer\\index.html");
        BufferedWriter writer = new BufferedWriter(new FileWriter(htmlFile));
        writer.write(html);
        writer.close();

        return htmlFile;
    }
}


