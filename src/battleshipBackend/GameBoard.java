package battleshipBackend;

/**
 * Clas: GameBoard
 * description: The gameboard class is checks for the battleship stuff.
 * Such as if the ship has been hit, the part of the ship, and updating
 * the ships accordingly.
 */

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class GameBoard implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int boardSize = 10;
	private GameCell gameBoard[][];
	private List<Ship> ships;
	private boolean allShipsDestroyed;
	
	// constructor
	public GameBoard () {
		this.ships = new ArrayList<Ship>(5);
		initBoard();
		
	}

	/**
	* Method name: getGameBoard
	* Parameters:  none
	* Return:      GameCell[][]
	* Description: Gets the game board
	*/
	public GameCell[][] getGameBoard () {
		return this.gameBoard;
	}
	
	/**
	 * Method:      initBoard
	 * Parameters:  none
	 * Return:      void
	 * Description: initalizes an empty board
	 */
	public void initBoard () {
		this.gameBoard = new GameCell[boardSize][boardSize];
		char letter = 'A';
		for (int n = 0; n < boardSize; n++) {
			for (int l = 0; l < boardSize; l++) {
				this.gameBoard[n][l] = new GameCell(n, letter, 'w', false);
			}
			letter++;
		}
	}
	
	/**
	 * Method:      displayBoard
	 * Parameters:  none
	 * Return:      void
	 * Description: displays the board
	 */
	public void displayBoard () {
		GameCell cell = null;
		System.out.printf("\n------------- Displaying Board --------------\n");
		for (int n = 0; n < boardSize; n++) {
			for (int l = 0; l < boardSize; l++) {
				cell = this.gameBoard[n][l];
				System.out.print(cell.getVal() + " ");
//				System.out.println(cell.toString());
			}
			System.out.println();
		}
	}

	/**
	 * Method:      isShipHit
	 * Parameters:  GameCell
	 * Return:      boolean
	 * Description: Checks if the cell has been hit or not.
	 */
	public boolean isShipHit (GameCell incomingCell) {
		boolean isHit = false;
		
		GameCell cell = gameBoard[incomingCell.getRow()][incomingCell.getCol()];
		
		if (!cell.isWater()) {
			isHit = true;
			
			for (Ship s : ships) {
				if (s.getShipName() == cell.getVal()) {
					s.destroyedOnePartOfShip();
					break;
				}
			}
		}
		return isHit;
	}
	
	/**
	 * Method:      isShipHit
	 * Parameters:  GameCell
	 * Return:      boolean
	 * Description: Checks if the cell has been hit or not.
	 */
	public boolean checkHort (GameCell incomingCell) {
		GameCell cell = gameBoard[incomingCell.getRow()][incomingCell.getCol()];
		for (Ship s : ships) {
			if (s.getShipName() == cell.getVal() && cell.getHori()) {
				return true;
			}
		}
		return false;
	}

	/**
	* Method name: isStart
	* Parameters:  InetAddress
	* Return:      void
	* Description: prints the information.
	*/
	public boolean isStart (GameCell incomingCell) {
		int inc_c = incomingCell.getCol();
		int inc_r = incomingCell.getRow();
		
//		if (int_c) {
//			
//		}
		
		return false;
	}
	
	/**
	* Method name: printInfo
	* Parameters:  InetAddress
	* Return:      void
	* Description: prints the information.
	*/
	public boolean getOrienH(GameCell incomingcell)
	 {		
		 for (Ship s : ships) {
			 if (s.getShipIsHorizontal() == true) {
			 	return false;
	 		}
	 	}
	 	return true;
	 }
	
	/**
	* Method name: printInfo
	* Parameters:  InetAddress
	* Return:      void
	* Description: prints the information.
	*/
	public char shipSize  (GameCell incomingCell) {
		GameCell cell = gameBoard[incomingCell.getRow()][incomingCell.getCol()];
		return cell.getVal();
	}
	
	/**
	* Method name: printInfo
	* Parameters:  InetAddress
	* Return:      void
	* Description: prints the information.
	*/
	public GameCell getCell(GameCell cellWithPosition) {
		return this.gameBoard[cellWithPosition.getRow()][cellWithPosition.getCol()];
	}
	
	/**
	* Method name: printInfo
	* Parameters:  InetAddress
	* Return:      void
	* Description: prints the information.
	*/
	public char isShipDestroyed (GameCell incomingCell) {
		GameCell cell = gameBoard[incomingCell.getRow()][incomingCell.getCol()];
		for (Ship s : ships) {
			if (s.getShipName() == cell.getVal()) {
				System.out.println("SIZE: " + s.getShipSize());
				if (s.isShipDestroyed()) {
					return s.getShipName();
				}
			}
		}
		return 'X';
	}
	
	/**
	 * Method:      destroyedShipName
	 * Parameters:  char
	 * Return:      String
	 * Description: Takes in a char and gives the nice string rep of the 
	 * ship's name.
	 */
	public String destroyedShipName (char shipChar) {
		String shipName = "";
		switch (shipChar) {
			case 'A':
				shipName = "Aircraft Carrier";
				break;
			case 'B':
				shipName = "Battleship";
				break;
			case 'D':
				shipName = "Destroyer";
				break;
			case 'U':
				shipName = "Submarine";
				break;
			case 'P':
				shipName = "Patrol Boat";
				break;
			default:
				shipName = "";
				break;
		}
		
		return shipName;
	}
	
	/**
	* Method name: printInfo
	* Parameters:  InetAddress
	* Return:      void
	* Description: prints the information.
	*/
	public void updateBoard (Ship ship, GameCell cell) {
		gameBoard[cell.getRow()][cell.getCol()] = cell;
		ship.updateShipLocation(cell);
	}
	
	/**
	* Method name: printInfo
	* Parameters:  InetAddress
	* Return:      void
	* Description: prints the information.
	*/
	public boolean hasAlreadyHitCell (GameCell incomingCell) {
		GameCell cell = gameBoard[incomingCell.getRow()][incomingCell.getCol()];
		if (cell.getIsCellHit()) {
			return true;
		}
		return false;
	}
	
	/**
	* Method name: printInfo
	* Parameters:  InetAddress
	* Return:      void
	* Description: prints the information.
	*/
	public void updateGameCell (GameCell cell) {
		System.out.println("cell hit set to true");
		gameBoard[cell.getRow()][cell.getCol()].isCellHit(true);
	}
	
	/**
	* Method name: printInfo
	* Parameters:  InetAddress
	* Return:      void
	* Description: prints the information.
	*/
	public void updateShipList(Ship ship) {
		this.ships.add(ship);
	}

	/**
	* Method name: printInfo
	* Parameters:  InetAddress
	* Return:      void
	* Description: prints the information.
	*/
	public List<Ship> getShipsList () {
		return this.ships;
		
	}
	
	/**
	* Method name: printInfo
	* Parameters:  InetAddress
	* Return:      void
	* Description: prints the information.
	*/
	public char partOfSHipHit (GameCell incomingCell) {
		char partChar = 'M';
		GameCell cell = gameBoard[incomingCell.getRow()][incomingCell.getCol()];
		
		for (Ship s : ships) {
			if (s.getShipName() == cell.getVal()) {
				if (s.isFront(cell)) {
					partChar = 'F';
				} else if (s.isBack(cell)) {
					partChar = 'K';
				} 
				break;
			}
		}
		
		return partChar;
	}
	
}
