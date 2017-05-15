package gameClass;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {
	private static Thread game;
	protected static int screenW = Toolkit.getDefaultToolkit().getScreenSize().width;
	protected static int screenH = Toolkit.getDefaultToolkit().getScreenSize().height;
	protected static ArrayList<GameObject> sprites = new ArrayList<GameObject>();


	protected static int userDX = 0;
	protected static int userDY = 0;
	protected static int userX = 500;
	protected static int userY = 500;


	protected GamePanel(){
		game = new Thread(this);
		panel();
		keys();
		game.start();
	}

	void keys(){
		getInputMap().put(KeyStroke.getKeyStroke("A"), "A");
		getInputMap().put(KeyStroke.getKeyStroke("S"), "S");
		getInputMap().put(KeyStroke.getKeyStroke("D"), "D");
		getInputMap().put(KeyStroke.getKeyStroke("W"), "W");

		getInputMap().put(KeyStroke.getKeyStroke("released A"), "rA");
		getInputMap().put(KeyStroke.getKeyStroke("released S"), "rS");
		getInputMap().put(KeyStroke.getKeyStroke("released D"), "rD");
		getInputMap().put(KeyStroke.getKeyStroke("released W"), "rW");


		getActionMap().put("A", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				userDX = -5;
			}

		});
		getActionMap().put("S", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				userDY = 5;
			}

		});
		getActionMap().put("D", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				userDX = 5;
			}

		});
		getActionMap().put("W", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				userDY = -5;
			}

		});
		getActionMap().put("rW", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				userDY = 0;
			}

		});

		getActionMap().put("rS", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				userDY = 0;
			}

		});

		getActionMap().put("rA", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				userDX = 0;
			}

		});
		getActionMap().put("rD", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				userDX = 0;
			}

		});
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
		moveGO();
		repaint();
	}
	void moveGO(){
		userX += userDX;
		userY += userDY;

	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	
		drawSprites(g);
	}
	void drawSprites(Graphics g){
		g.setFont(new Font("Aerial",Font.BOLD,50));
		g.drawString("Sprites # " + sprites.size(), 300, 300);
		int yBuffer = 400;
		for(int index = 0; index < sprites.size(); index++){
			GameObject o = sprites.get(index);
			g.drawString("X : " + index + "-"+ o.getX() + " Y : " + index+ "-" + o.getY(),0,yBuffer);
			o.draw(g);
			yBuffer+=100;
		}
	}

}
