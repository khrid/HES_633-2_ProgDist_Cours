package workshop;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServerSocket listeningSocket; // le socket server
        Socket exchangeSocket;      // le socket d'échange
        String interfaceName = "wlan0"; // l'interface cible (wlan, eth)
        InetAddress localAddress = null; // l'adresse qu'on va utiliser (déterminée depuis interfaceName)
        int port = -1; // port
        int max = 45010, min = 44980;
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

            do {
                System.out.print("Select server listening port ("+min+"-"+max+") : ");
                port = scanner.nextInt();
            } while (port < min || port > max);

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
