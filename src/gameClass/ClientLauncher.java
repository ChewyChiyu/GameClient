package gameClass;

public class ClientLauncher {
	public static void main(String[] args){
		new ClientLauncher();
	}
	ClientLauncher(){
		new GamePanel(); 
		new Client();
		
	}
}
