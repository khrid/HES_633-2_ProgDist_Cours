package test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Intro {
    public static void main(String[] args) throws SocketException {

        Enumeration<NetworkInterface> allni = NetworkInterface.getNetworkInterfaces();
        System.out.println("--------> All Interfaces");

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
    }
}
