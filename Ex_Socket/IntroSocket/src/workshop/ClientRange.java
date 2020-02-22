package workshop;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientRange {
    public static void main(String[] args) {
        String serverName = "192.168.43.90";
        InetAddress serverAddress = null;
        Socket exchangeSocket;
        int max = 45010, min = 44980;

        try {
            serverAddress = InetAddress.getByName(serverName);

        } catch (IOException e) {
        }

        for (int i = min; i <= max; i++) {
            try {
                exchangeSocket = new Socket(serverAddress, i);
                System.out.println("I'm connected to port " + i + "!");
                exchangeSocket.close();
            } catch (IOException e) {
                System.out.println("Port "+i+" not listening !");
            }
        }
    }
}
