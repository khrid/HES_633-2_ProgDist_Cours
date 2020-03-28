package workshopSocketsReadWrite.ex1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Ex1Client {
    public static void main(String[] args) {
        String serverName = "192.168.2.238";
        InetAddress serverAddress;
        Socket exchangeSocket;
        int port = 1992;

        try {
            serverAddress = InetAddress.getByName(serverName);
            exchangeSocket = new Socket(serverAddress, port);

            BufferedReader buffin = new BufferedReader(new InputStreamReader(exchangeSocket.getInputStream()));
            String messageRecu = buffin.readLine();
            System.out.println("Message received : " + messageRecu);
            buffin.close();

            exchangeSocket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
