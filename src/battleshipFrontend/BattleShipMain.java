package battleshipFrontend;


/**
 * Clas: BattleShipMain
 * description: where the program runs
 */

import java.awt.Container;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BattleShipMain 
{
	
	public static BattleShipUtil gameutil = new BattleShipUtil();
	
	public void startGame () {
		JFrame frame = new JFrame("CS342-project4: Battleship");
		GameView gameView = new GameView(frame);
		MainMenu menu = new MainMenu(gameView);
		
        frame.getContentPane().add(gameView.getViewPanel()); // add view here
		
		Container container = frame.getContentPane();
		frame.setJMenuBar(menu.getMenuBar());
		frame.setSize(600, 600);
		container.validate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frame, 
		            "Are you sure to close this window?", "Really Closing?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
			        	if (gameutil.getServerMachineAddress() == null) {
			        		System.exit(0);
			        	} else {
			        		sendServerPlayerQuit();
		        	 		System.exit(0);
			        	}
		        }
		    }
		});
		
	}
	
		
	public static void main (String[] args) {
		System.out.println("Start");
		BattleShipMain game = new BattleShipMain ();
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				game.startGame()	;
			}
	    });
	}
	
	private void sendServerPlayerQuit () {
		Socket socket = null;
		try {
				socket = new Socket(gameutil.getServerMachineAddress(), gameutil.getServerPortNum());
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject("PlayerQuit::"+gameutil.getPortNum());
				out.flush();
				socket.close();
			
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

}
