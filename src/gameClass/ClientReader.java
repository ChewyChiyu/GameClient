package gameClass;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;

public class ClientReader implements Runnable{
	Thread clientReaderThread;
	DatagramSocket ds;
	ServerSocket s;
	byte[] buf;
	int PORT;
	protected ClientReader(){
		try {
			s = new ServerSocket(0);
			PORT = s.getLocalPort();
		} catch (IOException e) {
			
		}
		clientReaderThread = new Thread(this);
		clientReaderThread.start();
		configure();
	}

	void configure(){
		try{
			ds = new DatagramSocket(PORT);
			buf = new byte[1024];  
		}catch(Exception e){ }
	}
	public void run(){
		while(Client.isRunning){
			try{
				readFromServer();
				Thread.sleep(1);
			}catch(Exception e) { }
		}
	}
	void readFromServer(){
		try{
			DatagramPacket dp = new DatagramPacket(buf, 1024);  
			ds.receive(dp);  
			String str = new String(dp.getData(), 0, dp.getLength());  
			Client.num = Integer.parseInt(str);
		}catch(Exception e){ }
	}
}	
