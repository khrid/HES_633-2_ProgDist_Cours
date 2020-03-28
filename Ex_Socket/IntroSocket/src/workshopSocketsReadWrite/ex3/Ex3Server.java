package workshopSocketsReadWrite.ex3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Enumeration;
import java.util.Scanner;

public class Ex3Server {
    /*
    *   - Write a java server program that:
        - Wait for a connection on port 45000
        - listen to a message on the console
        - Send the message to the connected client
        - Show the message sent to the client
        - Wait for the answer of the client,
        - Display messages received by the client
        - if the answer of the client is “quit” then close the server
* */

    public static void main(String[] args) {
        ServerSocket listeningSocket; // le socket server
        Socket exchangeSocket;      // le socket d'échange
        String interfaceName = "eth4"; // l'interface cible (wlan, eth)
        InetAddress localAddress = null; // l'adresse qu'on va utiliser (déterminée depuis interfaceName)
        int port = 45000; // port
        int buffer = 5; // buffer
        Scanner scan = new Scanner(System.in);
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
            BufferedReader buffin = new BufferedReader(new InputStreamReader(exchangeSocket.getInputStream()));

            System.out.println("Client connected.");
            String message;

            do {
                System.out.print("Enter message to send : ");
                message = scan.nextLine();

                pout.println(message);
                System.out.println("Sending \"" + message + "\"");
                pout.flush();

                String messageRecu = buffin.readLine();
                System.out.println("Message received : " + messageRecu);
            } while (!message.equals("quit"));


            buffin.close();
            pout.close();
            exchangeSocket.close();
            listeningSocket.close();


        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
