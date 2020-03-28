package workshop.ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler implements Runnable  {

    private Socket socket;
    private int clientNumber = 1;

    public ClientHandler(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
    }

    @Override
    public void run() {
        System.out.println("(from thread) new client : " + this.clientNumber);
        PrintWriter pout = null;
        try {
            pout = new PrintWriter(socket.getOutputStream());
            pout.println(clientNumber);
            System.out.println("(from thread) sending \""+clientNumber+"\"");
            pout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
