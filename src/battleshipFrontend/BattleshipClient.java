package battleshipFrontend;


/**
 * Clas: BattleshipClient
 * description: Acts like a client, and does some stuff.
 */

import java.awt.Color;

import java.awt.Component;
import java.awt.Font;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import battleshipBackend.GameBoard;
import battleshipBackend.GameCell;
import battleshipBackend.Player;


public class BattleshipClient {
	private String machineName, playerMachineName;
	private int port, battleShipClientport;
	Player player;
	
	// constructor
	public BattleshipClient (String machineName, int port) {
		this.machineName = machineName;
		this.port = port;			
	}
	
	/**
	* Method name: setPlayerInformation
	* Parameters:  none
	* Return:      void
	* Description: creates the game board
	*/
	public void setPlayerInformation (int clientPort, String machineName) {
		this.battleShipClientport = clientPort;
		this.playerMachineName = machineName;
	}
	
	/**
	* Method name: isHit
	* Parameters:  none
	* Return:      void
	* Description: creates the game board
	*/
	public synchronized int isHit (GameCell cell) throws IOException {
//		JOptionPane.showMessageDialog(null, "Current Player: " + player.getPlayerId());
		// init connection for the client
        Socket communicationSocket = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        int retFlag = -1;
        try {
        		communicationSocket = new Socket(machineName, port);
        		out = new ObjectOutputStream(communicationSocket.getOutputStream());
        		Player.PlayerHelper helper = this.player.new PlayerHelper(cell);
        		out.writeObject(helper);
        		out.flush();
        		
        		in = new ObjectInputStream(communicationSocket.getInputStream());
        		try {
        			Object retObj = in.readObject();
        			if (retObj instanceof Player) {
        				this.player = (Player)retObj;
        				System.out.println("Player " + player.getPlayerId() + " destroyed? " + this.player.displayShipDestroyed());
        				retFlag = player.getIsHit() ? 1 : 0;
        			} else if (retObj instanceof String && retObj.equals("NotStarted")) {
        				retFlag = -2;
        			} else if (retObj instanceof String && ((String) retObj).contains("already")) {
        				retFlag = -3;
        			}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + machineName);
            JOptionPane.showMessageDialog(null, "Connection failed please try different machine name");
        } catch (IOException e) {
        	e.printStackTrace();
            System.err.println("Couldn't get I/O for " + "the connection to: " + machineName);
            JOptionPane.showMessageDialog(null, "Connection failed please try different machine name");
        }
        out.close();
        in.close();
		 communicationSocket.close();
		 return retFlag;
	}
	
	/**
	* Method name: updateClientBoard
	* Parameters:  none
	* Return:      void
	* Description: creates the game board
	*/
	public synchronized void updateClientBoard (GameBoard board) throws IOException {
		this.player = new Player(-1, board, this.playerMachineName, battleShipClientport);
		// init connection for the client
        Socket communicationSocket = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        try {
        		communicationSocket = new Socket(machineName, port);
        		out = new ObjectOutputStream(communicationSocket.getOutputStream());
        		in = new ObjectInputStream(communicationSocket.getInputStream());
        		try {
					out.writeObject(player);
					out.flush();
					System.out.println("*");
					this.player = (Player)in.readObject();
					System.out.println("**Client Player " + player.getPlayerId() + " Turn: " + player.isPlayerTurn());
				} catch (Exception e) {
					e.printStackTrace();
				}
        		
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + machineName);
            JOptionPane.showMessageDialog(null, "Connection failed please try different machine name");
        } catch (IOException e) {
        	e.printStackTrace();
            System.err.println("Couldn't get I/O for " + "the connection to: " + machineName);
            JOptionPane.showMessageDialog(null, "Connection failed please try different machine name");
        }
        out.close();
        in.close();

		 communicationSocket.close();
	}
	
	
	public synchronized Player getPlayer () {
		return player;
	}
	
	public class ClientListner extends Thread{
		
		private int clientPort;
		private ServerSocket clientListener;
		private String clientMachineName;
		private JTextArea text;
		
		public ClientListner () {
			text = new JTextArea();
			try {
				InetAddress addrs = InetAddress.getLocalHost();
				this.clientMachineName = new String(addrs.getHostAddress());
				clientListener = new ServerSocket(0);
				this.clientPort = clientListener.getLocalPort();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		* Method name: getClientPort
		* Parameters:  none
		* Return:      void
		* Description: creates the game board
		*/
		public int getClientPort () {
			return this.clientPort;
		}
		
		/**
		* Method name: getClientMachineName
		* Parameters:  none
		* Return:      void
		* Description: creates the game board
		*/
		public String getClientMachineName() {
			return this.clientMachineName;
		}
		
		/**
		* Method name: run
		* Parameters:  none
		* Return:      void
		* Description: creates the game board
		*/
		public void run () {
			Socket communicationSocket = null;
			
			try { 
				 
				 while (true) {
					 
					  try { 
					       communicationSocket = clientListener.accept();
					       ObjectInputStream in = new ObjectInputStream(communicationSocket.getInputStream());
					       Object retObj = (Player)in.readObject();
					       
				    	   if ( (retObj instanceof Player) && ((Player)retObj).getGameMessage().contains("Game Over. Player:")) {
		        				
				    		   text.setText(((Player)retObj).getGameMessage());
						       Font font = new Font("Verdana", Font.BOLD, 12);
						       text.setFont(font);
						       text.setForeground(Color.RED);
						       GameView.displayPanel.add(text);
						       GameView.displayPanel.revalidate();
						       GameView.displayPanel.repaint();
						       
						       Component[] compList = GameView.boardPanel.getComponents();
								for (Component c : compList) {
									((JButton)c).setEnabled(false);
								}
								GameView.boardPanel.revalidate();
								GameView.boardPanel.repaint();
						       break;
				    		   
		        			} else if ( (retObj instanceof Player) && ((Player)retObj).getGameMessage().contains("Game Started. Player:")) {			        				
		        				text.setText(((Player)retObj).getGameMessage());
		        				Font font = new Font("Verdana", Font.BOLD, 12);
		        				text.setFont(font);
		        				text.setForeground(Color.MAGENTA);
		        				GameView.displayPanel.add(text);
		        				GameView.displayPanel.revalidate();
		        				GameView.displayPanel.repaint();
		        				
		        			} else if ( (retObj instanceof Player) && ((Player)retObj).getGameMessage().contains("Current Player:")) {
		        				text.setText(((Player)retObj).getGameMessage());
		        				Font font = new Font("Verdana", Font.BOLD, 12);
		        				text.setFont(font);
		        				text.setForeground(Color.BLUE);
		        				GameView.displayPanel.add(text);
		        				GameView.displayPanel.revalidate();
		        				GameView.displayPanel.repaint();
		        			} else if ( (retObj instanceof Player) && ((Player)retObj).getGameMessage().contains("Opponent Quit!!!")) {
		        				text.setText(((Player)retObj).getGameMessage());
		        				Font font = new Font("Verdana", Font.BOLD, 12);
		        				text.setFont(font);
		        				text.setForeground(Color.BLUE);
		        				GameView.displayPanel.add(text);
		        				GameView.displayPanel.revalidate();
		        				GameView.displayPanel.repaint();
		        			}
		        				
					  } catch (Exception e) {
						  e.printStackTrace();
					  }
				 }
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					communicationSocket.close();
					this.clientListener.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
