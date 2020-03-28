package echangeMessage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnection {
    public static void main(String[] args) {
        String serverName = "192.168.2.238";
        InetAddress serverAddress;
        Socket exchangeSocket;
        int port = 1992;

        try {
            serverAddress = InetAddress.getByName(serverName);
            exchangeSocket = new Socket(serverAddress, port);
            System.out.println("I'm connected!");

            PrintWriter pout = new PrintWriter(exchangeSocket.getOutputStream());
            pout.println("I am David");
            System.out.println("Sending \"I am David\"");
            pout.flush();
            pout.close();

            exchangeSocket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
