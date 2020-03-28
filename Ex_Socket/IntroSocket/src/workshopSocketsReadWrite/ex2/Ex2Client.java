package workshopSocketsReadWrite.ex2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/*
2) Exercise
Write a java server program that will read the client hostname, and a client program
that will send its hostname to the server.
*/

public class Ex2Client {
    public static void main(String[] args) {
        String serverName = "192.168.2.238";
        InetAddress serverAddress;
        Socket exchangeSocket;
        int port = 1992;

        try {
            serverAddress = InetAddress.getByName(serverName);
            exchangeSocket = new Socket(serverAddress, port);

            PrintWriter pout = new PrintWriter(exchangeSocket.getOutputStream());
            String toSend = InetAddress.getLocalHost().getHostName();
            pout.println(toSend);
            System.out.println("Sending \""+toSend+"\"");
            pout.flush();

            exchangeSocket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
