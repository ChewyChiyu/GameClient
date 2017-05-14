package gameClass;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {
	protected static Thread game;
	protected GamePanel(){
		game = new Thread(this);
		panel();
		game.start();
	}
	void panel(){
		JFrame frame = new JFrame("DIEP CLIENT");
		frame.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	public void run(){
		while(Client.isRunning){
			update();
			try{
				Thread.sleep(10);
			}catch(Exception e){ }
		}
	}
	void update(){
		repaint();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setFont(new Font("Aerial",Font.BOLD,100));
		g.drawString(""+Client.num, 300, 300);
	}
}
