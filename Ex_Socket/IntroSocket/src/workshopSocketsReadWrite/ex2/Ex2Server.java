package workshopSocketsReadWrite.ex2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;

/*
2) Exercise
Write a java server program that will read the client hostname, and a client program
that will send its hostname to the server.
*/

public class Ex2Server {
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

            BufferedReader buffin = new BufferedReader(new InputStreamReader(exchangeSocket.getInputStream()));
            String messageRecu = buffin.readLine();
            System.out.println("Message received : " + messageRecu);
            buffin.close();

            exchangeSocket.close();
            listeningSocket.close();

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
