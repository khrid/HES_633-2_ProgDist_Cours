package workshop;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        String serverName = "10.93.4.59";
        InetAddress serverAddress;
        Socket exchangeSocket;
        int port = 765;

        try {
            serverAddress = InetAddress.getByName(serverName);
            exchangeSocket = new Socket(serverAddress, port);
            System.out.println("I'm connected!");
            exchangeSocket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
