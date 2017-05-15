package gameClass;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ClientReader implements Runnable{
	private Thread clientReaderThread;
	private DatagramSocket ds;
	private ServerSocket s;
	byte[] buf;
	
	protected ClientReader(){
		try {
			/*
			 * A Port is assigned to each client, random and new each launch
			 *
			 */
			s = new ServerSocket(0);
			Client.PORT = s.getLocalPort();
		} catch (IOException e) {
			
		}
		clientReaderThread = new Thread(this);
		clientReaderThread.start();
		configure();
	}

	void configure(){
		try{
			ds = new DatagramSocket(Client.PORT);
			buf = new byte[1024];  
		}catch(Exception e){ }
	}
	public void run(){
		while(Client.isRunning){
			try{
				readFromServer();
				Thread.sleep(1);
			}catch(Exception e) { e.printStackTrace();}
		}
	}
	void readFromServer(){
		try{
			DatagramPacket dp = new DatagramPacket(buf, 1024);  
			ds.receive(dp);  
			String str = new String(dp.getData(), 0, dp.getLength()); 
			
			ArrayList<GameObject> tempGOA = new ArrayList<GameObject>();
			while(str.indexOf("|")!=-1){
				int x = Integer.parseInt(str.substring(0, str.indexOf(".")));
				int y = Integer.parseInt(str.substring(str.indexOf(".")+1,str.indexOf("|")));
				GameObject o = new GameObject(x,y);
				tempGOA.add(o);
				//System.out.println(str);
				str = str.substring(str.indexOf("|")+1);
			}
			GamePanel.sprites = tempGOA;
			
		}catch(Exception e){ }
	}
}	
