package gameClass;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientSender implements Runnable{
	private Thread clientSenderThread;
	private  DatagramSocket ds;
	private  InetAddress ip;
	protected ClientSender(){
		clientSenderThread = new Thread(this);
		configure();
		clientSenderThread.start();
	}
	void configure(){
		try{
			ds = new DatagramSocket();  
			ip = InetAddress.getByName("127.0.0.1");  
		}catch(Exception e) { }
	}
	public void run(){
		while(Client.isRunning){
			try{
				send();
				Thread.sleep(1);
			}catch(Exception e) { }
		}
	}
	void send(){
		try{
			/*
			 * First five characters of data package to server will always be the port
			 * 	commas used for human reading purposes
			 *  xVelo and yVelo are both 1 digits ex :5
			 * ex : PORT,xVelo,yVelo
			 */
			
			String str = ""+Client.PORT +""+ GamePanel.userX +"."+ GamePanel.userY;
			//System.out.println(str);
			
			
			
			DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, 2000);  
			ds.send(dp); 
		}catch(Exception e) { }
	}
}
