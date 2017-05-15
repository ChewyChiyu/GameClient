package gameClass;

public class Client {
	protected static boolean isRunning = true;
	protected static int PORT;
	protected static int numConnected;
	protected Client(){
		new ClientReader();
		new ClientSender();
	}
}
