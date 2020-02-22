package test;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

public class ServerConnection {
    public static void main(String[] args) {
        ServerSocket listeningSocket; // le socket server
        Socket exchangeSocket;      // le socket d'échange
        String interfaceName = "wlan0"; // l'interface cible (wlan, eth)
        InetAddress localAddress = null; // l'adresse qu'on va utiliser (déterminée depuis interfaceName)
        int port = 1992; // port
        int buffer = 5; // buffer

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
            System.out.println("Default timeout : " + listeningSocket.getSoTimeout());
            System.out.println("Used IP address : " + listeningSocket.getInetAddress().getHostAddress());
            System.out.println("Listening port  : " + listeningSocket.getLocalPort());

            listeningSocket.setSoTimeout(30000);
            exchangeSocket = listeningSocket.accept();
            System.out.println("One client connected");
            exchangeSocket.close();
            listeningSocket.close();

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
