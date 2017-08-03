import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class Client {


    public static void main(String[] args) throws Exception {

        Socket client= new Socket(); // InetAddress.getByName("127.0.0.1"));

        client.connect(new InetSocketAddress(InetAddress.getByName("localhost"), 5001));

        BufferedReader in= new BufferedReader(new InputStreamReader(client.getInputStream()));

        System.out.println(in.readLine());

        client.close();


    }
}
