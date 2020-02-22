import java.net.*;
import java.util.Enumeration;
import java.util.Scanner;

public class Exercice2 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // The host IP addresses and name of all connected interfaces.
        getConnectedInterfacesIP();
        // The IP address of the site www.hes-so.ch
        getIpAddress("www.hes-so.ch");
        // The IP address of a given internet address in command line, handle the possible errors.
        System.out.print("Enter internet address : ");
        getIpAddress(scanner.nextLine());
    }

    private static void getIpAddress(String s) {
        try {
            InetAddress ip = InetAddress.getByName(s);
            System.out.println("--------> Public IP of "+s+" : "+ip.getHostAddress());
        } catch (UnknownHostException e) {
            System.out.println("Unkown host ("+s+")");
            System.out.print("Enter internet address : ");
            getIpAddress(scanner.nextLine());
        }
    }

    private static void getConnectedInterfacesIP() {
        System.out.println("--------> All Interfaces");
        Enumeration<NetworkInterface> allni = null;
        try {
            allni = NetworkInterface.getNetworkInterfaces();
            while (allni.hasMoreElements()) {
                NetworkInterface ni = allni.nextElement();
                if (ni.isUp()) {
                    System.out.println("interface name : "+ni.getName()+" - "+ni.getDisplayName());
                    Enumeration<InetAddress> inetAddresses = ni.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inet = inetAddresses.nextElement();
                        if(!inet.isLinkLocalAddress() && !inet.isLoopbackAddress())
                            System.out.println("ip : "+inet.getHostAddress());
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }


    }
}
