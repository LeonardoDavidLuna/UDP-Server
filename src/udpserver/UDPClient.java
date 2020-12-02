package udpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import static udpserver.UDPClient.socket;

public class UDPClient
{
    static boolean debug=true;
    public static DatagramSocket socket=null;
    public static DatagramPacket sendPkt, recPkt;
    static void dbg(String str)
    {
        if(debug)
        {
            System.out.println(str);
            System.out.flush();
        }
    }
    public UDPClient()
    {
        try
        {
            socket=new DatagramSocket();
        }catch(SocketException e){}
    }
    public void sendrecv() throws UnknownHostException, IOException
    {
        try{
            for(int j=0; j<3;j++)
            {
                String s[]={"Sending packet 1","Sending packet 2","LAST"};
                byte data[]=s[j].getBytes();
                sendPkt = new DatagramPacket(data,data.length,InetAddress.getLocalHost(),41424);
                socket.send(sendPkt);
                dbg(s[j]);
                byte recdata[]=new byte[100];
                recPkt=  new DatagramPacket(recdata,recdata.length);
                socket.receive(recPkt);
                dbg("Received packet is from host: "+recPkt.getAddress()+" HostPort: "+recPkt.getPort()+" length: "+recPkt.getLength()+" Containing: "+new String(recPkt.getData()));
            }
        }catch(IOException e){}
        }
    
    public static void main(String[]args) throws IOException
    {
        UDPClient c=new UDPClient();
        c.sendrecv();
    }
}

