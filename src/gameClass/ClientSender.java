package gameClass;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientSender implements Runnable{
	protected Thread clientSenderThread;
	protected  DatagramSocket ds;
	protected  InetAddress ip;
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
			String str = "1";
			DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, 2000);  
			ds.send(dp); 
		}catch(Exception e) { }
	}
}
