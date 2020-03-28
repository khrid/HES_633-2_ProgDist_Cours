package workshopSocketsReadWrite.ex3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Ex3Client {
    /*
    - Write a java client program that:
    - Try to connect to the server on port 45000
    - Get a message from the server
    - Display the message received from the server
    - Send back to the server the message received by the server
    - If the message is “quit” then die
    - Handle the error is the server is not reachable
*/
    public static void main(String[] args) {
        String serverName = "192.168.2.238";
        InetAddress serverAddress;
        Socket exchangeSocket;
        int port = 45000;

        try {
            serverAddress = InetAddress.getByName(serverName);
            exchangeSocket = new Socket(serverAddress, port);

            PrintWriter pout = new PrintWriter(exchangeSocket.getOutputStream()); // pour envoyer des données
            BufferedReader buffin = new BufferedReader(new InputStreamReader(exchangeSocket.getInputStream())); // pour en recevoir

            String messageRecu;
            do {
                messageRecu = buffin.readLine();
                System.out.println("Message received : " + messageRecu);

                pout.println(messageRecu);
                System.out.println("Sending back the same message : \"" + messageRecu + "\"");
                pout.flush();
            } while (!messageRecu.equals("quit"));


            pout.close();
            buffin.close();
            exchangeSocket.close();
        } catch (UnknownHostException e) {
            System.out.println("Unkown host. Terminating client.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Server not reachable. Terminating client.");
        }
    }
}
