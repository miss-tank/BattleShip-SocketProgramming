package battleshipFrontend;

/**
 * Clas: GameView
 * description: Most of the rendering information for the UI.
 * Deals with some battleship actions such as build mode,
 * submit the board, etc. Handles the front end of the program
 * Manages the action listeners on the GUI.
 */

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import battleshipBackend.GameBoard;
import battleshipBackend.Player;
import battleshipBackend.Ship;

public class GameView {
	public static JPanel displayPanel, boardPanel;
	private JPanel  piecesPanel, viewPanel;
	private JFrame frame;
	private JTextArea textArea;
	private GamePieceButton selectedGamePiece;
	private boolean isHorizontal;
	private GameBoard board;
	private int noShips = 5; // change back to 5
	private static final String WATER = "w";
	private static final String REMOVE = "Remove";
	private BattleshipClient client;
	private JButton submit;
	private List<Ship> myship;

    // Tile icons.    
	 private ImageIcon Aircraftv[];
    private ImageIcon Battleshipv[];
    private ImageIcon Destroyerv[];
    private ImageIcon Submarinev[];
    private ImageIcon  Patrol_Boatv[];
       
    private ImageIcon Aircrafth[];
    private ImageIcon Battleshiph[];
    private ImageIcon Destroyerh[];
    private ImageIcon Submarineh[];
    private ImageIcon  Patrol_Boath[];

    private ImageIcon  destroywater;

    private ImageIcon redfrontv;
    private ImageIcon redbackv;
    private ImageIcon redmidv;
    
    private ImageIcon redfronth;
    private ImageIcon redbackh;
    private ImageIcon redmidh;   
    private ImageIcon emptyTileIcon;
   

	private static final int boardSize = 10;
	
	public GameView (JFrame frame) {
		// initializing swing components
		this.frame = frame;
		this.textArea = new JTextArea();
		this.boardPanel = new JPanel(new GridLayout(10, 10));
        this.piecesPanel = new JPanel(new GridLayout(9, 1));
        this.displayPanel = new JPanel();
        this.viewPanel = new JPanel();
        this.submit = new JButton("Submit Board");
        this.submit.setEnabled(false);
               
        // initializing pieces for the game
        this.isHorizontal = true;
		loadImages();

        createInitBoard();
        createGameToolPanel();
        gameBoardLayout();
        
	}
	
	
	/*
	 * Load Images based on the orientation of the ship.
	 * Water
	 * Hit and Miss images icon
	 */
	private void loadImages() {
        // Load tile icons.
        //filledTileIcons = new ImageIcon[9];
        Aircraftv = new ImageIcon[5];
        Battleshipv = new ImageIcon[4];
        Destroyerv = new ImageIcon[3];
        Submarinev = new ImageIcon[3];
        Patrol_Boatv= new ImageIcon[2];
        
        Aircrafth = new ImageIcon[5];
        Battleshiph = new ImageIcon[4];
        Destroyerh = new ImageIcon[3];
        Submarineh = new ImageIcon[3];
        Patrol_Boath= new ImageIcon[2];

        //Aircraftv
        	Aircraftv[0] = new ImageIcon("res/batt" + (6) + ".gif");
        	Aircraftv[1]=new ImageIcon("res/batt" + (7) + ".gif");
        	Aircraftv[2]=new ImageIcon("res/batt" + (8) + ".gif");
        	Aircraftv[3]=new ImageIcon("res/batt" + (9) + ".gif");
        	Aircraftv[4]=new ImageIcon("res/batt" + (10) + ".gif");

        	//Battleshipv
        	Battleshipv[0] = new ImageIcon("res/batt" + (6) + ".gif");
        	Battleshipv[1]=new ImageIcon("res/batt" + (7) + ".gif");
        	Battleshipv[2]=new ImageIcon("res/batt" + (8) + ".gif");
        	Battleshipv[3]=new ImageIcon("res/batt" + (10) + ".gif");
       	
        	//Destroyerv
        	Destroyerv[0] = new ImageIcon("res/batt" + (6) + ".gif");
        	Destroyerv[1]=new ImageIcon("res/batt" + (7) + ".gif");
        	Destroyerv[2]=new ImageIcon("res/batt" + (10) + ".gif");
        
        	//Submarinev
        	Submarinev[0] = new ImageIcon("res/batt" + (6) + ".gif");
        	Submarinev[1]=new ImageIcon("res/batt" + (7) + ".gif");
        	Submarinev[2]=new ImageIcon("res/batt" + (10) + ".gif");
        	
        	//Patrol_Boatv
        	Patrol_Boatv[0] = new ImageIcon("res/batt" + (6) + ".gif");
        	Patrol_Boatv[1]=new ImageIcon("res/batt" + (10) + ".gif");
        	
        	//Aircrafth
        	Aircrafth[0] = new ImageIcon("res/batt" + (1) + ".gif");
        	Aircrafth[1]=new ImageIcon("res/batt" + (2) + ".gif");
        	Aircrafth[2]=new ImageIcon("res/batt" + (3) + ".gif");
        	Aircrafth[3]=new ImageIcon("res/batt" + (4) + ".gif");
        	Aircrafth[4]=new ImageIcon("res/batt" + (5) + ".gif");

        	//Battleshiph
        	Battleshiph[0] = new ImageIcon("res/batt" + (1) + ".gif");
        	Battleshiph[1]=new ImageIcon("res/batt" + (2) + ".gif");
        	Battleshiph[2]=new ImageIcon("res/batt" + (3) + ".gif");
        	Battleshiph[3]=new ImageIcon("res/batt" + (5) + ".gif");
        	
        	//Destroyerh
        	Destroyerh[0] = new ImageIcon("res/batt" + (1) + ".gif");
        	Destroyerh[1]=new ImageIcon("res/batt" + (2) + ".gif");
        	Destroyerh[2]=new ImageIcon("res/batt" + (5) + ".gif");
        	
        	//Submarineh
        	Submarineh[0] = new ImageIcon("res/batt" + (1) + ".gif");
        	Submarineh[1]=new ImageIcon("res/batt" + (2) + ".gif");
        	Submarineh[2]=new ImageIcon("res/batt" + (5) + ".gif");
      
        	//Patrol_Boath
        	Patrol_Boath[0] = new ImageIcon("res/batt" + (1) + ".gif");
        	Patrol_Boath[1]=new ImageIcon("res/batt" + (5) + ".gif");
        	
        emptyTileIcon = new ImageIcon("res/batt102.gif");
        destroywater = new ImageIcon("res/batt103.gif");
        
        redfrontv= new ImageIcon("res/batt206.gif");
        redbackv= new ImageIcon("res/batt204.gif");
        redmidv= new ImageIcon("res/batt205.gif");

        redfronth= new ImageIcon("res/batt203.gif");
        redbackh= new ImageIcon("res/batt201.gif");
        redmidh= new ImageIcon("res/batt202.gif");

    }

		
	/**
     * Method name: createGameBoard
     * Parameters:  none
     * Return:      void
     * Description: creates the game board
     */
	public void createGameBoard () {
		boardPanel.removeAll();
		boardPanel.repaint();
		boardPanel.revalidate();
		this.board = null;
		this.board = new GameBoard();
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				GameCellButton cellBttn = new GameCellButton(null, i, j, WATER.charAt(0));
				cellBttn.setIcon(emptyTileIcon);
				cellBttn.setBorder(new LineBorder(new Color(0, 0, 0)));
				
				cellBttn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							System.out.println("Player: " + client.getPlayer().getPlayerId() + " Turn: " + client.getPlayer().isPlayerTurn());
							//char ret = '#';
							int retVal = client.isHit(cellBttn.getGameCell());
							System.out.println("RET VAL: " + retVal);
							
							// -2 indicates waiting for player to join
							if(retVal == -2) {
								displayMessage("Waiting for other player to join!!!\n" + client.getPlayer().toString());
								JOptionPane.showMessageDialog(null, "Waiting for other player to join");
							}
							// -3 indicates the user tried to hit a cell that they have already hit previously
							else if (retVal == -3) {
								displayMessage(client.getPlayer().toString() + "\nAlready hit in the cell, please choose a different cell");
								JOptionPane.showMessageDialog(null, "Already hit in the cell, please choose a different cell");
							}
							// val != -1 indicates it is the user's turn
							else if (retVal != -1) {
								if (retVal == 1) {
									
									// change the code here
									if (client.getPlayer().getHori()) {
										if (client.getPlayer().getPartOfShip() == 'F') {
											cellBttn.setIcon(redfronth);
										} else if (client.getPlayer().getPartOfShip() == 'K') {
											cellBttn.setIcon(redbackh);											
										} else {
											cellBttn.setIcon(redmidh);
										}
									} else {
										if (client.getPlayer().getPartOfShip() == 'F') {
											cellBttn.setIcon(redfrontv);
										} else if (client.getPlayer().getPartOfShip() == 'K') {
											cellBttn.setIcon(redbackv);											
										} else {
											cellBttn.setIcon(redmidv);
										}
									}
									
									displayMessage(client.getPlayer().toString());
									String destroyedShipName = client.getPlayer().displayShipDestroyed();
									
									if (destroyedShipName != null) {
										displayMessage(client.getPlayer().toString() + "\n" + destroyedShipName + " has been destroyed");
										client.getPlayer().resetshipDestroyedName();
									}
									if (client.getPlayer().isWin()) {
										displayMessage(client.getPlayer().toString() + "\n" + "Player: " + client.getPlayer().getPlayerId() + "wins");
									}
								} else {
									displayMessage(client.getPlayer().toString());
									//destroy water
									// cellBttn.setText("X");
									cellBttn.setIcon(destroywater);
								}	
							}
							else {
								displayMessage("Wait for your turn" + client.getPlayer().toString());
								JOptionPane.showMessageDialog(null, "Wait for other player to move");
							}
							
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "Exception");
							e1.printStackTrace();
						}
					}
				});
				
				boardPanel.add(cellBttn);
			}
		}
	}
		
	/**
     * Method name: gameBoardLayout
     * Parameters:  none
     * Return:      void
     * Description: Uses swing board layout to organize the panels. 
     */
	public void createInitBoard () {
		boardPanel.repaint();
		boardPanel.revalidate();
		
		this.board = new GameBoard();
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				GameCellButton cellBttn = new GameCellButton(null, i, j, WATER.charAt(0));
				cellBttn.setBorder(new LineBorder(new Color(0, 0, 0)));
				cellBttn.setImg(emptyTileIcon);
				cellBttn.setVal('w');
				
				cellBttn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Component[] compList = boardPanel.getComponents();
						
						if (selectedGamePiece != null) {
							if (selectedGamePiece.getText().equals(REMOVE)) {
								removeShipFromBoard(cellBttn, compList);
							} else {
								updateBoard(cellBttn, compList);
							}
						} else {
							displayMessage("No ship selected !!!");
							JOptionPane.showMessageDialog(null, "No ship selected !!!");
						}
					}
				});
				
				boardPanel.add(cellBttn);
			}
		}
	}

	/**
     * Method name: updateBoard
     * Parameters:  GameCellButton, Component[]
     * Return:      void
     * Description: updates the board according to the orientation of the boat.  
     */
	public void updateBoard (GameCellButton cellBttn, Component[] compList) {
		if (isHorizontal) {
			if (canPlaceShipHorizontal(cellBttn, compList)) {
				updateHorizontal(cellBttn, compList);	
				displayMessage("Placed ship successfully");
			} else {
				// place somewhere else
				displayMessage("Can't place ship, please select a different area");
				JOptionPane.showMessageDialog(null, "Can't place ship, please select a different area");
			}
		} else {
			if (canPlaceShipVertical(cellBttn, compList)) {
				updateVertical(cellBttn, compList);	
				displayMessage("Placed ship successfully");
			} else {
				// place somewhere else
				displayMessage("Can't place ship, please select a different area");
				JOptionPane.showMessageDialog(null, "Can't place ship, please select a different area");
			}
		}
		this.selectedGamePiece = null;
		this.noShips--;
		if (this.noShips == 0) {
			this.submit.setEnabled(true);
		}
	}
	
	/**
     * Method name: canRemove
     * Parameters:  GameCellButton, Component[]
     * Return:      boolean
     * Description: Gets the start cell and tries to see from the start
     * position to position+shipSize any other ship is present horizontally. 
     * If not return true else false; 
     */
	private boolean canRemove (GameCellButton cellBttn, Component[] compList) {
		if (cellBttn.getGameCell().getVal() == (WATER.charAt(0))) {
			return false;
		}
		return true;
	}
	
	/**
     * Method name: removeShipFromBoard
     * Parameters:  GameCellButton, Component[]
     * Return:      void
     * Description: removes the ship if the ship is oriented horizontally 
     */
	public void removeShipFromBoard (GameCellButton cellBttn, Component[] compList) {
		if (canRemove(cellBttn, compList)) {
			List<Ship> ships = board.getShipsList();	
			// search the ships
			for (Ship s : ships) {
				if (s.getShipName() == cellBttn.getGameCell().getVal()) 
				{					
					boolean isit = cellBttn.getGameCell().getHori();
					if (isit) 
					{								
						System.out.println("this si the orient hori yes " + s.getShipIsHorizontal() );
						removeShipFromBoardHorizontal (s, cellBttn, compList);

					} else {
						System.out.println("this si the orient hori no " + s.getShipIsHorizontal() );
						removeShipFromBoardVertical(s, cellBttn, compList);
					}
				}
			}
			this.noShips++;
			if (this.noShips > 0) {
				this.submit.setEnabled(false);
			}
		}
	}
	
	/**
     * Method name: removeShipFromBoardHorizontal
     * Parameters:  GameCellButton, Component[]
     * Return:      void
     * Description: removes the ship if the ship is oriented horizontally 
     */
	public void removeShipFromBoardHorizontal (Ship s, GameCellButton cellBttn, Component[] compList) {
		int col = s.getStartPosition().getCol();
		int shipSize = s.getShipSize();
		for (Component c : compList) {
			if (((GameCellButton)c).getGameCell().getRow() == s.getStartPosition().getRow() &&
			((GameCellButton)c).getGameCell().getCol() == col && shipSize != 0) {
				// update value in the board back to W
				((GameCellButton)c).getGameCell().setVal(WATER.charAt(0)); // always one char
				((GameCellButton)c).setIcon(emptyTileIcon);
				((GameCellButton)c).setVal(WATER.charAt(0));
				board.updateBoard(s, ((GameCellButton)c).getGameCell());
				
				col++;
				shipSize--;
				// all parts of ship are on board, no need to continue and disable button
				if (shipSize == 0) {
					s.removeAllLocations();
					
					Component[] pieces = piecesPanel.getComponents();
					for (Component piece : pieces) {
						if (piece instanceof GamePieceButton && !((GamePieceButton) piece).getText().equals(REMOVE)) {
							if(((GamePieceButton) piece).getShip().getShipName() == s.getShipName()) {
								((GamePieceButton)piece).setEnabled(true);
							}
						}
					}
					System.out.println();
					break;
				}
			}
		}
	
	}
	
	/**
     * Method name: removeShipFromBoardVertical
     * Parameters:  GameCellButton, Component[]
     * Return:      void
     * Description: removes the ship if the ship is oriented vertically 
     */
	public void removeShipFromBoardVertical (Ship s, GameCellButton cellBttn, Component[] compList) {
		int row = s.getStartPosition().getRow();
		int shipSize = s.getShipSize();
		for (Component c : compList) {
			if (((GameCellButton)c).getGameCell().getRow() == row &&
			((GameCellButton)c).getGameCell().getCol() == s.getStartPosition().getCol()  && shipSize != 0) {
				// update value in the board back to W
				((GameCellButton)c).getGameCell().setVal(WATER.charAt(0)); // always one char
				((GameCellButton)c).setIcon(emptyTileIcon);
				board.updateBoard(s, ((GameCellButton)c).getGameCell());
				((GameCellButton)c).setVal(WATER.charAt(0));

				// update value in the UI
				((GameCellButton)c).setIcon(emptyTileIcon);
				row++;
				shipSize--;
				// all parts of ship are on board, no need to continue and disable button
				if (shipSize == 0) {
					s.removeAllLocations();
					
					Component[] pieces = piecesPanel.getComponents();
					for (Component piece : pieces) {
						if (piece instanceof GamePieceButton && !((GamePieceButton) piece).getText().equals(REMOVE)) {
							if(((GamePieceButton) piece).getShip().getShipName() == s.getShipName()) {
								((GamePieceButton)piece).setEnabled(true);
							}
						}
					}
					
					break;
				}
			}
		}
	
	}
	
	/**
     * Method name: canPlaceShipHorizontal
     * Parameters:  GameCellButton, Component[]
     * Return:      boolean
     * Description: Gets the start cell and tries to see from the start
     * position to position+shipSize any other ship is present horizontally. 
     * If not return true else false; 
     */
	private boolean canPlaceShipHorizontal (GameCellButton cellBttn, Component[] compList) {
		int col = cellBttn.getGameCell().getCol();
		int count = this.selectedGamePiece.getShip().getShipSize();
		for (Component c : compList) {
			if (((GameCellButton)c).getGameCell().getRow() == cellBttn.getGameCell().getRow() &&
			((GameCellButton)c).getGameCell().getCol() == col && count != 0 && ((GameCellButton)c).getIcon() != null &&
			((GameCellButton)c).getGameCell().getCol() == col && count != 0 && ((GameCellButton)c).getGameCell().getVal()==WATER.charAt(0) )
			{
				col++;
				count--;
				// all parts of ship are on board, no need to continue
				if (count == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
     * Method name: canPlaceShipVertical
     * Parameters:  GameCellButton, Component[]
     * Return:      boolean
     * Description: Gets the start cell and tries to see from the start
     * position to position+shipSize any other ship is present vertically.
     * If not return true else false; 
     */
	public boolean canPlaceShipVertical (GameCellButton cellBttn, Component[] compList) {
		int row = cellBttn.getGameCell().getRow();
		int count = this.selectedGamePiece.getShip().getShipSize();
		for (Component c : compList) {
			if (((GameCellButton)c).getGameCell().getRow() == row &&
			((GameCellButton)c).getGameCell().getCol() == cellBttn.getGameCell().getCol() && count != 0 &&
			((GameCellButton)c).getGameCell().getVal()== WATER.charAt(0) ) {
				row++;
				count--;
				// all parts of ship are on board, no need to continue
				if (count == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
     * Method name: updateHorizontal
     * Parameters:  GameCellButton, Component[]
     * Return:      void
     * Description: updates the horizontal component 
     */
	private void updateHorizontal (GameCellButton cellBttn, Component[] compList) {
		int col = cellBttn.getGameCell().getCol();
		int shipSize = this.selectedGamePiece.getShip().getShipSize();
		int orgShipSize= shipSize;
		System.out.println("ths is my comp lis " + shipSize);
		
		switch(shipSize)
		{
	
		   case 2 :
			   if (col <= boardSize - shipSize) 
				{
					for (Component c : compList) 
					{
						
						if (((GameCellButton)c).getGameCell().getRow() == cellBttn.getGameCell().getRow() &&
						((GameCellButton)c).getGameCell().getCol() == col && shipSize != 0) {
							// update value in the board
							((GameCellButton)c).getGameCell().setVal(this.selectedGamePiece.getShip().getShipName());
							((GameCellButton)c).getGameCell().setHori(true);
							board.updateBoard(this.selectedGamePiece.getShip(), ((GameCellButton)c).getGameCell());
							
							// update value in the UI
							if(shipSize==orgShipSize)
							{
								((GameCellButton)c).setIcon(Patrol_Boath[0]);
							}
							
							if(shipSize==1)
							{
								((GameCellButton)c).setIcon(Patrol_Boath[1]);
							}
							col++;
							shipSize--;
							// all parts of ship are on board, no need to continue and disable button
							if (shipSize == 0) {
								board.updateBoard(this.selectedGamePiece.getShip(), ((GameCellButton)c).getGameCell());
								this.selectedGamePiece.setEnabled(false);
								break;
							}
						}
					}
					displayMessage("Successfully added ship: " + this.selectedGamePiece.getShip().getShipName());
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("Attempting to place ship from cell: ");
					sb.append((col+1)+" to "+ (col+shipSize));
					sb.append(" but size of ship is: "+shipSize);
					sb.append(" and the size of the board is 10X10.");
					displayMessage(sb.toString());
					JOptionPane.showMessageDialog(null, "Ship's size is too big, find a different start cell");
				}
		      break; 
		   
		   case 3 :
			   if (col <= boardSize - shipSize) 
				{
					for (Component c : compList) 
					{	
						if (((GameCellButton)c).getGameCell().getRow() == cellBttn.getGameCell().getRow() &&
						((GameCellButton)c).getGameCell().getCol() == col && shipSize != 0) {
							// update value in the board
							((GameCellButton)c).getGameCell().setVal(this.selectedGamePiece.getShip().getShipName());
							board.updateBoard(this.selectedGamePiece.getShip(), ((GameCellButton)c).getGameCell());
							((GameCellButton)c).getGameCell().setHori(true);

							// update value in the UI
							if(shipSize==orgShipSize)
							{
								((GameCellButton)c).setIcon(Submarineh[0]);
							}
							
							if(orgShipSize > shipSize)
							{
								((GameCellButton)c).setIcon(Submarineh[1]);
							}
							
							if(shipSize==1)
							{
								((GameCellButton)c).setIcon(Submarineh[2]);
							}
							col++;
							shipSize--;
							// all parts of ship are on board, no need to continue and disable button
							if (shipSize == 0) {
								board.updateBoard(this.selectedGamePiece.getShip(), ((GameCellButton)c).getGameCell());
								this.selectedGamePiece.setEnabled(false);
								break;
							}
						}
					}
					displayMessage("Successfully added ship: " + this.selectedGamePiece.getShip().getShipName());
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("Attempting to place ship from cell: ");
					sb.append((col+1)+" to "+ (col+shipSize));
					sb.append(" but size of ship is: "+shipSize);
					sb.append(" and the size of the board is 10X10.");
					displayMessage(sb.toString());
					JOptionPane.showMessageDialog(null, "Ship's size is too big, find a different start cell");
				}
		      break; 
		   
		   case 4 :
			   if (col <= boardSize - shipSize) 
				{
					for (Component c : compList) 
					{
						
						if (((GameCellButton)c).getGameCell().getRow() == cellBttn.getGameCell().getRow() &&
						((GameCellButton)c).getGameCell().getCol() == col && shipSize != 0) {
							// update value in the board
							((GameCellButton)c).getGameCell().setVal(this.selectedGamePiece.getShip().getShipName());
							board.updateBoard(this.selectedGamePiece.getShip(), ((GameCellButton)c).getGameCell());
							((GameCellButton)c).getGameCell().setHori(true);

							// update value in the UI
							if(shipSize==orgShipSize)
							{
								((GameCellButton)c).setIcon(Battleshiph[0]);
							}
							
							if(orgShipSize > shipSize)
							{
								((GameCellButton)c).setIcon(Battleshiph[1]);
							}
							
							if(shipSize==1)
							{
								((GameCellButton)c).setIcon(Battleshiph[3]);
							}
							col++;
							shipSize--;
							// all parts of ship are on board, no need to continue and disable button
							if (shipSize == 0) {
								board.updateBoard(this.selectedGamePiece.getShip(), ((GameCellButton)c).getGameCell());
								this.selectedGamePiece.setEnabled(false);
								break;
							}
						}
					}
					displayMessage("Successfully added ship: " + this.selectedGamePiece.getShip().getShipName());
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("Attempting to place ship from cell: ");
					sb.append((col+1)+" to "+ (col+shipSize));
					sb.append(" but size of ship is: "+shipSize);
					sb.append(" and the size of the board is 10X10.");
					displayMessage(sb.toString());
					JOptionPane.showMessageDialog(null, "Ship's size is too big, find a different start cell");
				}
			      break; 
		   case 5 :
			   if (col <= boardSize - shipSize) 
				{
					for (Component c : compList) 
					{
						if (((GameCellButton)c).getGameCell().getRow() == cellBttn.getGameCell().getRow() &&
						((GameCellButton)c).getGameCell().getCol() == col && shipSize != 0) 
						{
							// update value in the board
							((GameCellButton)c).getGameCell().setVal(this.selectedGamePiece.getShip().getShipName());
							board.updateBoard(this.selectedGamePiece.getShip(), ((GameCellButton)c).getGameCell());
							((GameCellButton)c).getGameCell().setHori(true);

							// update value in the UI
							if(shipSize==orgShipSize)
							{
								((GameCellButton)c).setIcon(Aircrafth[0]);
							}
							
							if(orgShipSize > shipSize)
							{
								((GameCellButton)c).setIcon(Aircrafth[1]);
							}
							
							if(shipSize==1)
							{
								((GameCellButton)c).setIcon(Aircrafth[4]);
							}
							
							col++;
							shipSize--;
							// all parts of ship are on board, no need to continue and disable button
							if (shipSize == 0) {
								board.updateBoard(this.selectedGamePiece.getShip(), ((GameCellButton)c).getGameCell());
								this.selectedGamePiece.setEnabled(false);
								break;
							}
						}
					}
					displayMessage("Successfully added ship: " + this.selectedGamePiece.getShip().getShipName());
				} 
				else {
					StringBuilder sb = new StringBuilder();
					sb.append("Attempting to place ship from cell: ");
					sb.append((col+1)+" to "+ (col+shipSize));
					sb.append(" but size of ship is: "+shipSize);
					sb.append(" and the size of the board is 10X10.");
					displayMessage(sb.toString());
					JOptionPane.showMessageDialog(null, "Ship's size is too big, find a different start cell");
				}
			      break; 
		   default : 
			   System.out.println("N/A");
		}		
	}
	
	/**
     * Method name: changeOrientation
     * Parameters:  GamePieceButton
     * Return:      void
     * Description: Toggle button to change the ship's position from 
     * horizontal to vertical and vice versa. 
     */
	private void updateVertical (GameCellButton cellBttn, Component[] compList) {
		int row = cellBttn.getGameCell().getRow();
		int shipSize = this.selectedGamePiece.getShip().getShipSize();

		int orgShipSize= shipSize;

		/*Add the Icons based on the ship chosen.
		 * 
		 */
		switch(shipSize)
		{
		   case 5 :
				if (row <= boardSize - shipSize) 
				{
					for (Component c : compList) 
					{
						if (((GameCellButton)c).getGameCell().getRow() == row &&
						((GameCellButton)c).getGameCell().getCol() == cellBttn.getGameCell().getCol() && shipSize != 0) 
						{
							// update value in the board
							((GameCellButton)c).getGameCell().setVal(this.selectedGamePiece.getShip().getShipName());
							board.updateBoard(this.selectedGamePiece.getShip(), ((GameCellButton)c).getGameCell());
							((GameCellButton)c).getGameCell().setHori(false);

							// update the value in the UI							
							if(shipSize==orgShipSize)
							{
								((GameCellButton)c).setIcon(Aircraftv[0]);
							}
							
							if(orgShipSize > shipSize)
							{
								((GameCellButton)c).setIcon(Aircraftv[1]);
							}
							
							if(shipSize==1)
							{
								((GameCellButton)c).setIcon(Aircraftv[4]);
							}
							
							row++;
							shipSize--;
							// all parts of ship are on board, no need to continue and disable button
							if (shipSize == 0) 
							{
								board.updateBoard(this.selectedGamePiece.getShip(), ((GameCellButton)c).getGameCell());
								this.selectedGamePiece.setEnabled(false);
								break;
							}
						}
					}
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("Attempting to place ship from cell: ");
					sb.append((row+1)+" to "+ (row+shipSize));
					sb.append(" but size of ship is: "+shipSize);
					sb.append(" and the size of the board is 10X10.");
					displayMessage(sb.toString());
					JOptionPane.showMessageDialog(null, "Ship's size is too big, find a different start cell");
				}
		      break; 
		   
		   case 4 :
			   if (row <= boardSize - shipSize) 
				{
					for (Component c : compList) 
					{
						if (((GameCellButton)c).getGameCell().getRow() == row &&
						((GameCellButton)c).getGameCell().getCol() == cellBttn.getGameCell().getCol() && shipSize != 0) 
						{
							((GameCellButton)c).getGameCell().setHori(false);

							// update value in the board
							((GameCellButton)c).getGameCell().setVal(this.selectedGamePiece.getShip().getShipName());
							board.updateBoard(this.selectedGamePiece.getShip(), ((GameCellButton)c).getGameCell());

							// update the value in the UI							
							if(shipSize==orgShipSize)
							{
								((GameCellButton)c).setIcon(Battleshipv[0]);
							}
							
							if(orgShipSize > shipSize)
							{
								((GameCellButton)c).setIcon(Battleshipv[1]);
							}
							
							if(shipSize==1)
							{
								((GameCellButton)c).setIcon(Battleshipv[3]);
							}
							
							row++;
							shipSize--;
							// all parts of ship are on board, no need to continue and disable button
							if (shipSize == 0) 
							{
								board.updateBoard(this.selectedGamePiece.getShip(), ((GameCellButton)c).getGameCell());
								this.selectedGamePiece.setEnabled(false);
								break;
							}
						}
					}
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("Attempting to place ship from cell: ");
					sb.append((row+1)+" to "+ (row+shipSize));
					sb.append(" but size of ship is: "+shipSize);
					sb.append(" and the size of the board is 10X10.");
					displayMessage(sb.toString());
					JOptionPane.showMessageDialog(null, "Ship's size is too big, find a different start cell");
				}
		      break; // break is optional
		   case 3 :
			   if (row <= boardSize - shipSize) 
				{
					for (Component c : compList) 
					{
						if (((GameCellButton)c).getGameCell().getRow() == row &&
						((GameCellButton)c).getGameCell().getCol() == cellBttn.getGameCell().getCol() && shipSize != 0) 
						{
							// update value in the board
							((GameCellButton)c).getGameCell().setVal(this.selectedGamePiece.getShip().getShipName());
							board.updateBoard(this.selectedGamePiece.getShip(), ((GameCellButton)c).getGameCell());
							((GameCellButton)c).getGameCell().setHori(false);

							// update the value in the UI							
							if(shipSize==orgShipSize)
							{
								((GameCellButton)c).setIcon(Submarinev[0]);
							}
							
							if(orgShipSize > shipSize)
							{
								((GameCellButton)c).setIcon(Submarinev[1]);
							}
							
							if(shipSize==1)
							{
								((GameCellButton)c).setIcon(Submarinev[2]);
							}
							
							row++;
							shipSize--;
							// all parts of ship are on board, no need to continue and disable button
							if (shipSize == 0) 
							{
								board.updateBoard(this.selectedGamePiece.getShip(), ((GameCellButton)c).getGameCell());
								this.selectedGamePiece.setEnabled(false);
								break;
							}
						}
					}
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("Attempting to place ship from cell: ");
					sb.append((row+1)+" to "+ (row+shipSize));
					sb.append(" but size of ship is: "+shipSize);
					sb.append(" and the size of the board is 10X10.");
					displayMessage(sb.toString());
					JOptionPane.showMessageDialog(null, "Ship's size is too big, find a different start cell");
				}
			      
			      break; 
		   case 2 :	   if (row <= boardSize - shipSize) 
			{
				for (Component c : compList) 
				{
					if (((GameCellButton)c).getGameCell().getRow() == row &&
					((GameCellButton)c).getGameCell().getCol() == cellBttn.getGameCell().getCol() && shipSize != 0) 
					{
						// update value in the board
						((GameCellButton)c).getGameCell().setVal(this.selectedGamePiece.getShip().getShipName());
						board.updateBoard(this.selectedGamePiece.getShip(), ((GameCellButton)c).getGameCell());
						((GameCellButton)c).getGameCell().setHori(false);
						
						// update the value in the UI
						//((GameCellButton)c).setText(selectedGamePiece.getShip().getShipName()+"");
						
						if(shipSize==orgShipSize)
						{
							((GameCellButton)c).setIcon(Patrol_Boatv[0]);
						}
						
						if(shipSize==1)
						{
							((GameCellButton)c).setIcon(Patrol_Boatv[1]);
						}
						
						row++;
						shipSize--;
						// all parts of ship are on board, no need to continue and disable button
						if (shipSize == 0) 
						{
							board.updateBoard(this.selectedGamePiece.getShip(), ((GameCellButton)c).getGameCell());
							this.selectedGamePiece.setEnabled(false);
							break;
						}
					}
				}
			} else {
				StringBuilder sb = new StringBuilder();
				sb.append("Attempting to place ship from cell: ");
				sb.append((row+1)+" to "+ (row+shipSize));
				sb.append(" but size of ship is: "+shipSize);
				sb.append(" and the size of the board is 10X10.");
				displayMessage(sb.toString());
				JOptionPane.showMessageDialog(null, "Ship's size is too big, find a different start cell");
			}
			      // Statements
			      break; 

		   default : 
		}
	}
	
	/**
     * Method name: changeOrientation
     * Parameters:  GamePieceButton
     * Return:      void
     * Description: Toggle button to change the ship's position from 
     * horizontal to vertical and vice versa. 
     */
	public void changeOrientation (GamePieceButton piece) {
		if (this.isHorizontal) {
			piece.getShip().setIsHorizontal(false);
		} else {
			piece.getShip().setIsHorizontal(true);
		}
	}
	
	/**
     * Method name: createPiecePanel
     * Parameters:  none
     * Return:      void
     * Description: creates the ship image button panels 
     */
	public void createGameToolPanel () {
		JButton toggleShipOrientation = new JButton("Horizontal");
		GamePieceButton aircraft = new GamePieceButton("Aircraft Carrier (5)", 'A');
		GamePieceButton battleship = new GamePieceButton("Battleship (4)", 'B');
		GamePieceButton destroyer = new GamePieceButton("Destroyer (3)", 'D');
		GamePieceButton submarine = new GamePieceButton("Submarine (3)", 'U');
		GamePieceButton patrol = new GamePieceButton("Patrol Boat (2)", 'P');
		GamePieceButton remove = new GamePieceButton(REMOVE);
		
		board.updateShipList(aircraft.getShip());
		board.updateShipList(battleship.getShip());
		board.updateShipList(destroyer.getShip());
		board.updateShipList(submarine.getShip());
		board.updateShipList(patrol.getShip());
		
		// sets the listener to the button
		toggleShipOrientation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// first need to get the state of the button and change it based on that
				changeOrientation(aircraft);
				changeOrientation(battleship);
				changeOrientation(destroyer);
				changeOrientation(submarine);
				changeOrientation(patrol);
				
				if (isHorizontal) {
					isHorizontal = false;
					toggleShipOrientation.setText("Vertical");
				} else {
					isHorizontal = true;
					toggleShipOrientation.setText("Horizontal");
				}
			}
		});
		
		aircraft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (aircraft.isEnabled()) {
					selectedGamePiece = aircraft;
					displayMessage("selected aircraft size 5");
				} else {
					displayMessage("Aircraft already exists on the board");
				}
			}
		});
		
		battleship.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (battleship.isEnabled()) {
					selectedGamePiece = battleship;
					displayMessage("selected battleship size 4");
				} else {
					displayMessage("Aircraft already exists on the board");
				}
			}
		});
	
		destroyer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (destroyer.isEnabled()) {
					selectedGamePiece = destroyer;
					displayMessage("selected destroyer size 3");
				} else {
					displayMessage("Aircraft already exists on the board");
				}
			}
		});
	
		submarine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (submarine.isEnabled()) {
					selectedGamePiece = submarine;
					displayMessage("selected submarine size 3");
				} else {
					displayMessage("Aircraft already exists on the board");
				}
			}
		});
		
		patrol.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (patrol.isEnabled()) {
					selectedGamePiece = patrol;
					displayMessage("selected patrol boat size 2");
				} else {
					displayMessage("Aircraft already exists on the board");
				}
			}
		});
		
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedGamePiece = remove;
				remove.setEnabled(true);
				displayMessage("To remove a boat from the grid click anywhere there is a ship");
			}
		});
		
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("submitting board");
				
				try {
					String machineName = JOptionPane.showInputDialog(null, "Enter machine name: ");
					String port = JOptionPane.showInputDialog(null, "Enter server port: ");
					int portNum = -1;
					try {
						portNum = Integer.parseInt(port);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Invalid port number please enter integers only");
					}
					
					client = new BattleshipClient(machineName, portNum);
					BattleshipClient.ClientListner clientListener = client.new ClientListner();
					int clientListport = clientListener.getClientPort();
					String clientListmachineName = clientListener.getClientMachineName();
					client.setPlayerInformation(clientListport, clientListmachineName);
					
					BattleShipMain.gameutil.setServerMachineAddress(machineName);
					BattleShipMain.gameutil.setServerPortNum(portNum);
					
					BattleShipMain.gameutil.setMachineAddress(clientListmachineName);
					BattleShipMain.gameutil.setPortNum(clientListport);
					
					clientListener.start();
					client.updateClientBoard(board);
					displayMessage(client.getPlayer().toString() + "\nplayer has set up board");
					createGameBoard();
					submit.setEnabled(false);
					remove.setEnabled(false);
					toggleShipOrientation.setEnabled(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		piecesPanel.add(toggleShipOrientation);
		piecesPanel.add(aircraft);
		piecesPanel.add(battleship);
		piecesPanel.add(destroyer);
		piecesPanel.add(submarine);
		piecesPanel.add(patrol);
		piecesPanel.add(remove);
		piecesPanel.add(submit);	
	}
	
	 /**
     * Method name: gameBoardLayout
     * Parameters:  none
     * Return:      void
     * Description: Uses swing board layout to organize the panels. 
     */
    public void gameBoardLayout () {  
    		int w = 400;
    		int h = 400;
    		GameView.displayPanel.setPreferredSize(new Dimension(w, 200));
    		GameView.boardPanel.setPreferredSize(new Dimension(w, h));
    		this.piecesPanel.setPreferredSize(new Dimension(200, h));
    		
    		BorderLayout gameLayout = new BorderLayout();
    		this.viewPanel.setLayout(gameLayout);
        this.viewPanel.add(boardPanel, BorderLayout.LINE_START);
        this.viewPanel.add(piecesPanel, BorderLayout.LINE_END);
        this.viewPanel.add(displayPanel, BorderLayout.PAGE_END);
    }
    
	
	/**
	 * method name: displayAbout
	 * parameters:  none
	 * return:	    void
	 * description: Displays information in relation to the creators of the 
	 * application.
	 */
	public void displayAbout() {
		StringBuilder sb = new StringBuilder();
        sb.append("CS342 Project 4 - Networked Battleship\n");
        sb.append("Authors: Simar Gadhoke - sgadho2, Ankita Tank - atank2, Siddharth Basu - sbasu9\n");
        displayMessage(sb.toString());
		
	}
	
	/**
	 * method name: displayAbout
	 * parameters:  none
	 * return:	    void
	 * description: Displays information in relation to how to play the game.
	 */
	public void displayHowToPlay() {
		 StringBuilder sb = new StringBuilder();
        sb.append("Networked Battleship is a two player game.\n");
		sb.append("Read the instructions in the read me. Too large to fit\n");
        displayMessage(sb.toString());
		
	}

	/**
	 * method name: displayHowToUseInterface
	 * parameters:  none
	 * return:	    void
	 * description: Displays information in relation to how to use the interface.
	 */
	public void displayHowToUseInterface() {
		StringBuilder sb = new StringBuilder();
        sb.append("Running the Networked Battleship program\n");
        sb.append("First choose who which player will run the server.\n");
        sb.append("Then player 1 and player 2 may proceed and place there battle ships where ever they desire\n");
        sb.append("Once each player has placed ships they may click on the submit button\n");
        sb.append("Once both players have clicked on the submit button the game will begin\n");
        displayMessage(sb.toString());
		
	}
	
	/**
     * Method name: displayMessage
     * Parameters:  String
     * Return:      void
     * Description: Display the message in the display area. 
     */
    public void displayMessage(String msg) {
        this.frame.getContentPane().revalidate();
        this.textArea.setEditable(false);
		// this.textArea.setPreferredSize(new Dimension(500, 200));
        this.textArea.setText(msg);
        displayPanel.add(textArea);
    }
   
	/**
	 * method name: getViewPanel
	 * parameters:  none
	 * return:	    void
	 * description: Displays the about in the display box
	 */
	public JComponent getViewPanel() {
		return this.viewPanel;
	}
}
