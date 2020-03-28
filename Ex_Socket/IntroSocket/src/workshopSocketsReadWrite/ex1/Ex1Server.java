package workshopSocketsReadWrite.ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Enumeration;

public class Ex1Server {
    public static void main(String[] args) {

        ServerSocket listeningSocket; // le socket server
        Socket exchangeSocket;      // le socket d'échange
        String interfaceName = "eth4"; // l'interface cible (wlan, eth)
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
            exchangeSocket = listeningSocket.accept();

            PrintWriter pout = new PrintWriter(exchangeSocket.getOutputStream());
            String toSend = localAddress.getHostName() + " - " + localAddress.getHostAddress();
            pout.println(toSend);
            System.out.println("Sending \""+toSend+"\"");
            pout.flush();

            exchangeSocket.close();
            listeningSocket.close();

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
