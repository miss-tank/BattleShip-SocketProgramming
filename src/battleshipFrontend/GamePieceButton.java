package battleshipFrontend;


/**
 * Clas: GameView
 * description: Most of the rendering information for the UI.
 * Deals with some battleship actions such as build mode,
 * submit the board, etc.
 */

import javax.swing.JButton;


import battleshipBackend.Ship;

@SuppressWarnings("serial")
public class GamePieceButton extends JButton {
	private Ship ship;
	public GamePieceButton () {
		super();
	}
	
	// constructor
	public GamePieceButton (String text) {
		super(text);
	}
	
	// constructor
	public GamePieceButton (String text, char shipType) {
		super(text);
		this.ship = new Ship(shipType);
	}
	
	/**
     * Method name: initShip
     * Parameters:  char
     * Return:      void
     * Description: init the ship
     */
	public void initShip (char shipType) {
		this.ship = new Ship(shipType);
	}
	
	/**
     * Method name: getShip
     * Parameters:  none
     * Return:      Ship
     * Description: Gets the Ship cell attribute. 
     */
	public Ship getShip() {
		return this.ship;
	}

}
