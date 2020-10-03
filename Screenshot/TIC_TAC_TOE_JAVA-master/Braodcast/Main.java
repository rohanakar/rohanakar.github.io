import java.util.*;
import java.net.*;
import java.io.*;
public class Main{
static public boolean set=true;
static public String msg;
	public static void main(String args[]) throws Exception{
	 long StartTime = System.currentTimeMillis() / 1000;
	msg=args[0];
		new Broad().start();
		Scanner sc=new Scanner(System.in);
		new Interupt(StartTime).start();
	}
}
class Interupt extends Thread{
long StartTime;
	Interupt(long StartTime){
	this.StartTime=StartTime;
	}
public void run(){
		while(Main.set){
		if (10 < ((System.currentTimeMillis() / 1000) - StartTime))
                Main.set=false;
	
		}
System.out.println("Esdds");
}}
class Recv extends Thread{
	int port;
	MulticastSocket socket;
	Recv(int port)throws Exception{
	this.port=port;
	}
	public void run(){	
  
	while(Main.set){
		try{

		socket = new MulticastSocket(port);
		InetAddress address = InetAddress.getByName("230.0.0.0");
		socket.joinGroup(address);
		 byte[] buf = new byte[256];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);
		String received = new String(packet.getData(), 0, packet.getLength());
	        System.out.println(received+" 	send by"+packet.getAddress()+":"+packet.getPort());
		   
            }catch(Exception e){System.out.println(e);}
	}
	socket.close();}
}

class Broad extends Thread{
int my_port;
	 MulticastSocket findfreePort() throws Exception{
		int ports[]={4560,4570,4580,4590,5000};
		for (int port : ports) {
	        	try {
				my_port=port;
		            	return new MulticastSocket(port);
	        	} catch (IOException ex) {
	            		continue; // try next port
        		}
    		}
	throw new IOException("no free port found");
	} 
MulticastSocket socket;
Scanner sc;	
	Broad()	throws Exception{
			 socket=findfreePort();
			socket.setLoopbackMode(true);
			new Recv(socket.getLocalPort()+1).start();
			 sc=new Scanner(System.in);	
			new recieve(socket.getLocalPort()+2).start();	
	}
	public void run(){
			int ports[]={4560,4570,4580,4590,5000};
			DatagramPacket packet=null;
			while(Main.set){
			try{
				String msg=Main.msg;
				byte[] buf = new byte[256];
				buf=msg.getBytes();
				InetAddress group = InetAddress.getByName("230.0.0.0");
				for(int x:ports){
	 				packet = new DatagramPacket(buf, buf.length, group,x+1);
		 	        socket.send(packet);}
            Thread.sleep(1000);
			}
			catch(Exception e){}
			}
			System.out.println("ENTER IP/PORT");
			int port=sc.nextInt();
			String ip=sc.next();
			MAKEGAME(ip,port+2);
			socket.close();
		}
	void MAKEGAME(String ip,int port){
				try{
		new makemove(ip,port).start();
	}catch(Exception e){System.out.println(e);}}

}

class makemove extends Thread{
	String ip;
	int port;
	makemove(String ip,int port) throws Exception{
	this.ip=ip;
	this.port=port;
	}
	public void run(){try{
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
 		for(int i=0; i<5;i++){
            //establish socket connection to server
            socket = new Socket(ip, port);
            //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server"+i);
            if(i==4)oos.writeObject("exit");
            else oos.writeObject(""+i);
            oos.close();
            Thread.sleep(500);
        }}catch(Exception e){System.out.println(e);}
	}
}
class recieve extends Thread{
	
	 private static ServerSocket server;
	private static int port;
	recieve(int port) throws Exception{
	this.port=port;
	server = new ServerSocket(port);	
	}
	public void run(){
        //keep listens indefinitely until receives 'exit' call or program terminates
        try{while(true){
            System.out.println("Waiting for client request");
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            String message = (String) ois.readObject();
            System.out.println("Message Received: " + message);
            //create ObjectOutputStream object
            //close resources
            ois.close();
            socket.close();
            //terminate the server if client sends exit request
            if(message.equalsIgnoreCase("exit")) break;
        }
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();}catch(Exception e){System.out.println(e);}
    }}
    


