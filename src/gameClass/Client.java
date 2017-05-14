package gameClass;

public class Client {
	protected static boolean isRunning = true;
	protected static int num = 0;

	protected Client(){
		new ClientReader();
		new ClientSender();
	}
}
