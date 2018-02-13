package battleshipBackend;

/**
 * Clas: BattleshipServer
 * description: the battle ship server is used to maintain each player's board.
 * This class is used to determine whose turn it is, whether teh player is hit
 * the ship or not, hit a perviously hit spot. 
 */


import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class BattleShipServer extends Thread {
	
	private int port;
	private String machineAddress;
	//private Player player;
	private boolean runServer;
	private List<Player> players;
	private int playerId;
	private boolean gameStarted;
	public boolean serverStarted;
	
	// constructor
	public BattleShipServer() {
		this.serverStarted = false;
		this.gameStarted = false;
		InetAddress addr;
		ServerSocket socket = null;
		// get the socket information dynamically
		try {
			socket = new ServerSocket(0);
			addr = InetAddress.getLocalHost();
			this.machineAddress = addr.getHostAddress();
			this.port = socket.getLocalPort();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		 
		runServer = true;
		playerId = 100;
		this.players = new ArrayList<Player>();
	}
	
	/**
	* Method name: setMachineAddress
	* Parameters:  String
	* Return:      void
	* Description: sets the machine name
	*/
	public void setMachineAddress(String machineAddress) {
		this.machineAddress = machineAddress;
	}
	
	/**
		* Method name: sendFinalGameStatus
		* Parameters:  int
		* Return:      void
		* Description: sets the final game status
		*/
	private void sendFinalGameStatus (int playerId) {
		Socket socket = null;
		
		try {
			for (Player p: players) {
				socket = new Socket(p.getMachineAddress(), p.getPort());
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				p.setTurn(false);
				p.setGameMessage("\n\nGame Over. Player: " + playerId + " Wins");
				out.writeObject(p);
				out.flush();
				socket.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	* Method name: gameStarted
	* Parameters:  int
	* Return:      void
	* Description: game shas started, send to the intial players turn
	*/
	private void gameStarted (int playerId) {
		Socket socket = null;
		try {
			for (Player p: players) {
				socket = new Socket(p.getMachineAddress(), p.getPort());
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				p.setGameMessage("\n\nGame Started. Player: " + playerId + " goes first.\n\n");
				out.writeObject(p);
				out.flush();
				socket.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Method name: currGamePlayer
	* Parameters:  int
	* Return:      void
	* Description: who is the current player, send to both players
	*/
	private void currGamePlayer (int playerId) {
		Socket socket = null;
		try {
			for (Player p: players) {
				socket = new Socket(p.getMachineAddress(), p.getPort());
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				p.setGameMessage("\n\nCurrent Player: " + playerId);
				out.writeObject(p);
				out.flush();
				socket.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Method name: checkChickenLeft
	* Parameters:  none
	* Return:      String
	* Description: checks on which person left.
	*/
	private void checkChickenLeft (final String chickenIp) {
		Socket socket = null;
		try {
			for (Player p: players) {
				if (p.getPort() != ( (new Integer(chickenIp)).intValue()) ){
				
					socket = new Socket(p.getMachineAddress(), p.getPort());
					ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
					p.setGameMessage("Opponent Quit!!!");
					out.writeObject(p);
					out.flush();
					socket.close();
					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	* Method name: run
	* Parameters:  none
	* Return:      void
	* Description: establishing connection
	*/
	@Override
	public void run() {
	    ServerSocket connectionSocket = null;
	    try { 
	    	InetAddress addr = InetAddress.getLocalHost();
			 this.machineAddress = new String(addr.getHostAddress());
			// make a connection 
			 connectionSocket = new ServerSocket(port); 
			 this.port = connectionSocket.getLocalPort();
			 printInfo(addr);
	    
			 while (runServer) {
			  Socket communicationSocket = null; 
			
			  try { 
			       communicationSocket = connectionSocket.accept();
			       
			       ObjectOutputStream out = new ObjectOutputStream(communicationSocket.getOutputStream());
			       ObjectInputStream in = new ObjectInputStream(communicationSocket.getInputStream());
			       
			       
			       if(this.gameStarted) {
			    	   		try {
			    	   			// reading the object
			    	   			Object obj = in.readObject();
			    	   			Player.PlayerHelper helper = null;
			    	   			
			    	   			if (obj instanceof String &&  ((String)obj).contains("PlayerQuit")) {
			    	   				checkChickenLeft(((String)obj).split("::")[1]);
			    	   				System.out.println("Scarry Cat Quits!!!");
			    	   				return;
			    	   			}
			    	   			
			    	   			/**
			    	   			 * checks if player 1 or player 2 has a turn, has not already hit in the same cell.
			    	   			 * Then will check whether the ship has been hit or not, and will send and update 
			    	   			 * the response accordingly.
			    	   			 * Else will not move if the turn hasn't been made, or
			    	   			 * if the cell hasn't been correctly hit.
			    	   			 */
			    	   			if (obj instanceof Player.PlayerHelper) {
			    	   				helper = (Player.PlayerHelper) obj;
			    	   				char shipChar;
			    	   				// player 1 turn
			    	   				if (players.get(0).getPlayerId() == helper.getPlayerHelperId() && players.get(0).isPlayerTurn()) {
//			    	   					1st check if cell has already been hit
			    	   					System.out.println("*** " + players.get(1).getGameBoard().hasAlreadyHitCell(helper.getCell()));
			    	   					if (players.get(1).getGameBoard().hasAlreadyHitCell(helper.getCell())) {
			    	   						 out.writeObject("already hit");
			    	   					} else {
//			    	   						if cell has not been then check whether the ship has been hit and update the board accordingly
				    	   					if (players.get(1).getGameBoard().isShipHit(helper.getCell())) {
				    	   						players.get(0).updateNoHits();
				    	   						players.get(0).setIsHit(true);
				    	   						shipChar = players.get(1).getGameBoard().isShipDestroyed(helper.getCell());
				    	   						players.get(0).setHitHor(players.get(1).getGameBoard().checkHort(helper.getCell()));
											players.get(0).setPartOfShip(players.get(1).getGameBoard().partOfSHipHit(helper.getCell()));
				    	   						if (shipChar!= 'X') {
				    	   							players.get(0).updateDestroyedShip();
				    	   							players.get(0).setDestroyedShip(shipChar);
				    	   							if (players.get(0).isWin()) {
				    	   								sendFinalGameStatus(helper.getPlayerHelperId());
				    	   							}
				    	   						}
				    	   						
					    	   				} else {
				    	   						players.get(0).updateNoMisses();
				    	   						players.get(0).setIsHit(false);
				    	   					}
				    	   					players.get(1).getGameBoard().updateGameCell(helper.getCell());
				    	   					players.get(1).setTurn(true);
				    	   					players.get(0).setTurn(false);
				    	   					currGamePlayer(players.get(1).getPlayerId());
				    	   					out.writeObject(players.get(0));
			    	   					}
			    	   				} 
			    	   				// player's 2 turn
			    	   				else if(players.get(1).getPlayerId() == helper.getPlayerHelperId() && players.get(1).isPlayerTurn()){
			    	   					System.out.println("*** " + players.get(1).getGameBoard().hasAlreadyHitCell(helper.getCell()));
			    	   					// 1st check if cell has already been hit
			    	   					if (players.get(0).getGameBoard().hasAlreadyHitCell(helper.getCell())) {
			    	   						 out.writeObject("already hit");
			    	   					} 
			    	   					else {
			    	   					// if cell has not been then check whether the ship has been hit and update the board accordingly
				    	   					if (players.get(0).getGameBoard().isShipHit(helper.getCell())) {
				    	   						players.get(1).updateNoHits();
				    	   						players.get(1).setIsHit(true);
				    	   						shipChar = players.get(0).getGameBoard().isShipDestroyed(helper.getCell());
				    	   						players.get(1).setHitHor(players.get(0).getGameBoard().checkHort(helper.getCell()));
				    	   						players.get(1).setPartOfShip(players.get(0).getGameBoard().partOfSHipHit(helper.getCell()));
				    	   						
				    	   						if (shipChar!= 'X') {
				    	   							players.get(1).updateDestroyedShip();
				    	   							players.get(1).setDestroyedShip(shipChar);
				    	   							if (players.get(1).isWin()) {
				    	   								sendFinalGameStatus(helper.getPlayerHelperId());
				    	   							}
				    	   							
				    	   						}
					    	   				} else {
					    	   					players.get(1).updateNoMisses();
					    	   					players.get(1).setIsHit(false);
					    	   				}
				    	   					players.get(0).getGameBoard().updateGameCell(helper.getCell());
				    	   					players.get(0).setTurn(true);
				    	   					players.get(1).setTurn(false);
				    	   					currGamePlayer(players.get(0).getPlayerId());
										out.writeObject(players.get(1)); 
			    	   						
			    	   					}
			    	   				} else {
			    	   					out.writeObject("noTurn");
			    	   				}
			    	   				
			    	   				
			    	   				out.flush();
			    	   				
			    	   			} else {
			    	   				JOptionPane.showMessageDialog(null, "Something sent wrong");
			    	   			}
			    	   			// write back the result
			    	   			
					   } catch (ClassNotFoundException e1) {
							e1.printStackTrace();
					   }
				       
			       } else {
			    	   	   Player player = null;
				       try {
				    	   		Object obj = in.readObject();
				    	   		System.out.println("...."+obj.getClass());
				    	   		if (obj instanceof Player) {
				    	   			System.out.println("****Player: " + ((Player)obj).getPlayerId());
				    	   			player = (Player)obj;
				    	   		} else {
				    	   			//JOptionPane.showMessageDialog(null, "not an instance of player");
				    	   			out.writeObject("NotStarted");
				    	   			System.out.println("not an instance of player");
				    	   		}
				       } catch (Exception e) {
				    	   		e.printStackTrace();
				       }
				       
				       // player hasn't been assigned an id and 
				       if (player != null && player.getPlayerId() == -1 ) {
				    	   	   playerId++;
					       player.setPlayerId(playerId);
					       players.add(player);
							if (players.size() == 1) {
								player.setTurn(true);
							}
							
							if (players.size() == 2) {
			    	   			this.gameStarted = true;
			    	   			gameStarted(players.get(0).getPlayerId());
					    	} 
					       out.writeObject(player);
					       out.flush();
				       }
			       }
			       
			       in.close();
			       out.close(); 
			       System.out.println("Connection Established");
			       
			  } catch (IOException e) { 
			       System.err.println("Accept failed.");
			  } 
			  communicationSocket.close(); 
			}
			connectionSocket.close(); 
		
	    } catch (IOException e) { 
		     System.err.println("Could not listen on port: " + this.port); 
		     JOptionPane.showMessageDialog(null, "Could not listen to port: " + this.port);
		} 
	}
	
	/**
	* Method name: getPortNumber
	* Parameters:  int
	* Return:      void
	* Description: gets the port number that the server is running onw
	*/
	public int getPortNumber () {
		return this.port;
	}
	
	/**
	* Method name: getMachineAddress
	* Parameters:  none
	* Return:      String
	* Description: gets the machine name that the server is running on.
	*/
	public String getMachineAddress () {
		return this.machineAddress;
	}
	
	/**
	* Method name: printInfo
	* Parameters:  InetAddress
	* Return:      void
	* Description: prints the information.
	*/
	public void printInfo (InetAddress addr) {
		System.out.println("PORT: " + port);
		System.out.println("Java InetAddress localHost info: " + addr);
        System.out.println("Local Host Name: " + addr.getHostName());
        System.out.println("Local Host Address: " + this.machineAddress);
	}

}
