import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server {

    public static void main(String[] args) throws Exception {
        int port= 5001;

        ServerSocket listener= new ServerSocket(port);

        listener.setSoTimeout(30);

        System.out.println("Listening...");

        boolean running= true;
        Socket client= null;
        while (running) {
            try {
                if ((client = listener.accept()) != null) {
                    // got a new client connection, service the request
                    handle(client);
                }
            } catch (SocketTimeoutException ignore) { }
        }


    }

    private static void handle(Socket client) throws Exception {

        PrintWriter out= new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

        out.println("HELLO");

        out.close();

        client.close();
    }

}
