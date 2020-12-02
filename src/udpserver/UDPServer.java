package udpserver;

import java.io.*;
import java.net.*;

public class UDPServer
{
        static boolean debug=true;
        static void dbg(String str)
        {
            {
                if(debug)
                {
                    System.out.println(str);
                    System.out.flush();
                }
            }
        }
        
    public static void main(String[] args) throws SocketException
    {
        DatagramSocket socket=null;
        DatagramPacket sendPkt, recPkt;
        String recStr="       ";
        try{
            socket=new DatagramSocket(41424);
            while(true)
            {
                dbg("Starting Server on port: "+socket.getLocalPort());//Puerto Local
                byte buf[]=new byte[100];
                recPkt=new DatagramPacket(buf,100);
                socket.receive(recPkt);
                recStr=new String(recPkt.getData());
                dbg("Received from InetAddress: "+recPkt.getAddress()+"port number: "+recPkt.getPort()+"lenght: "+recPkt.getLength()+"Containing: "+recStr);
                sendPkt=new DatagramPacket(recPkt.getData(),recPkt.getLength(),recPkt.getAddress(),recPkt.getPort());
                socket.send(sendPkt);
            }
        }catch(IOException e){}
    }
}