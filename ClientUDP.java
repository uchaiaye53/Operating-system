
package socket.programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class ClientUDP {
   public static void main(String[] args) throws SocketException, IOException {
        
         BufferedReader clientRead =new BufferedReader(new InputStreamReader(System.in));
      
      InetAddress IP = InetAddress.getByName("192.168.0.104");
      DatagramSocket clientSocket = new DatagramSocket();
      while(true)  
      {
      byte[] sendbuffer = new byte[1024];
      byte[] receivebuffer = new byte[1024];
      
      System.out.print("\nClient: ");
      String clientData = clientRead.readLine();
      sendbuffer = clientData.getBytes();        
      DatagramPacket sendPacket =
      new DatagramPacket(sendbuffer, sendbuffer.length, IP, 9876);
      clientSocket.send(sendPacket);
      if(clientData.equalsIgnoreCase("ok"))
      {
          System.out.println("Connection ended by client");
          break;
      }
      DatagramPacket receivePacket =
      new DatagramPacket(receivebuffer, receivebuffer.length);
      clientSocket.receive(receivePacket);
      String serverData = new String(receivePacket.getData());
      System.out.print("\nServer: " + serverData);
      }
      clientSocket.close();
    }
}
