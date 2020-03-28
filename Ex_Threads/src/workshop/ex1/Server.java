package workshop.ex1;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

public class Server {
    public static void main(String[] args) {
        ServerSocket listeningSocket; // le socket server
        Socket exchangeSocket;      // le socket d'échange
        String interfaceName = "eth4"; // l'interface cible (wlan, eth)
        InetAddress localAddress = null; // l'adresse qu'on va utiliser (déterminée depuis interfaceName)
        int port = 1992; // port
        int buffer = 5; // buffer
        int clientNumber = 1;

        // pour récupérer l'adresse IP
        try {
            NetworkInterface ni = NetworkInterface.getByName(interfaceName);
            Enumeration<InetAddress> inetAddresses = ni.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inet = inetAddresses.nextElement();
                if (!inet.isLinkLocalAddress())
                    localAddress = inet;
            }

            listeningSocket = new ServerSocket(port, buffer, localAddress);

            while (true) {
                exchangeSocket = listeningSocket.accept();
                System.out.println("Connection request received, preparing new thread");
                Thread t = new Thread(new ClientHandler(exchangeSocket, clientNumber));
                System.out.println("Starting new thread for client "+clientNumber+".");
                clientNumber++;
                t.start();
            }
        } catch (
                SocketException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
