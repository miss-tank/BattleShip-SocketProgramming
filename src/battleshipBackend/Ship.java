package battleshipBackend;

/**
 * Clas: Ship
 * description: Has attributes for the ship such as the size,
 orientation, list of locatio, ship name.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Ship implements Serializable {
	private int shipSize;
	private int officialShipSize;
	private char shipName;
	private List<GameCell> location;
	private boolean isHorizontal;
	
	// constructor
	public Ship () {}
	
	public Ship (char shipType) {
		this.shipName = shipType;
		this.isHorizontal = true;
		this.setShipSize(shipType);
		this.location = new ArrayList<GameCell>();
	}

	/**
	 * Method:      getShipSize
	 * Parameters:  none
	 * Return:      int
	 * Description: Gets the ship's size.
	 */
	public int getShipSize() {
		return shipSize;
	}

	/**
	 * Method:      setShipSize
	 * Parameters:  int
	 * Return:      void
	 * Description: Sets the ships size attribute.
	 */
	public void setShipSize(char shipType) {
		switch (shipType) {
			case 'A':
				this.shipSize = 5;
				break;
			case 'B':
				this.shipSize = 4;
				break;
			case 'D':
				this.shipSize = 3;
				break;
			case 'U':
				this.shipSize = 3;
				break;
			case 'P':
				this.shipSize = 2;
				break;
			default:
				this.shipSize = 0;
		}
		this.officialShipSize = this.shipSize;
	}
	
	public void updateShipLocation (GameCell cell) {
		this.location.add(cell);
	}
	
	/**
	 * Method:      setShipSize
	 * Parameters:  int
	 * Return:      void
	 * Description: Sets the ships size attribute.
	 */
	public void setShipLocation (char shipType, GameCell startCell) {
		this.location = new ArrayList<GameCell>();
		// is the ship horizontally or vertically located
		if (this.isHorizontal) {
			for (int c = startCell.getCol(); c < this.shipSize; c++) {
				
			}
		} else {
			for (int r = startCell.getRow(); r < this.shipSize; r++) {
				
			}
		}
	}
	
	/**
	 * Method:      setShipSize
	 * Parameters:  int
	 * Return:      List<GameCell>
	 * Description: Sets the ships size attribute.
	 */
	public List<GameCell> getShipLocations () {
		return this.location;
	}
	
	/**
	 * Method:      setShipSize
	 * Parameters:  int
	 * Return:      void
	 * Description: Sets the ships size attribute.
	 */
	public void setIsHorizontal (boolean flag) {
		this.isHorizontal = flag;
	}
	
	/**
	 * Method:      setShipSize
	 * Parameters:  int
	 * Return:      boolean
	 * Description: Sets the ships size attribute.
	 */
	public boolean getShipIsHorizontal () {
		return this.isHorizontal;
	}
	
	/**
	 * Method:      isShipHit
	 * Parameters:  GameCell
	 * Return:      boolean
	 * Description: Checks if the cell has been hit or not.
	 */
	public char getShipName () {
		return this.shipName;
	}

	/**
	 * Method:      isShipHit
	 * Parameters:  GameCell
	 * Return:      boolean
	 * Description: Checks if the cell has been hit or not.
	 */
	public GameCell getStartPosition() {
		return this.location.get(0);
	}

	/**
	 * Method:      isShipHit
	 * Parameters:  GameCell
	 * Return:      boolean
	 * Description: Checks if the cell has been hit or not.
	 */
	public void removeAllLocations () {
		this.location.clear();
	}
	
	/**
	 * Method:      isShipHit
	 * Parameters:  GameCell
	 * Return:      boolean
	 * Description: Checks if the cell has been hit or not.
	 */
	public void destroyedOnePartOfShip () {
		System.out.println("Destroyed ship");
		this.shipSize--;
	}
	
	/**
	 * Method:      isShipHit
	 * Parameters:  GameCell
	 * Return:      boolean
	 * Description: Checks if the cell has been hit or not.
	 */
	public boolean isShipDestroyed () {
		return this.shipSize == 0 ? true : false;
	}
	
	/**
	 * Method:      isShipHit
	 * Parameters:  GameCell
	 * Return:      boolean
	 * Description: Checks if the cell has been hit or not.
	 */
	public boolean isBack (GameCell cell) {
		int firstIndex = 0;
		if (location.get(firstIndex).getRow() == cell.getRow() && location.get(firstIndex).getCol() == cell.getCol()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method:      isShipHit
	 * Parameters:  GameCell
	 * Return:      boolean
	 * Description: Checks if the cell has been hit or not.
	 */
	public boolean isFront (GameCell cell) {
		int lastIndex = this.officialShipSize-1;
		if (location.get(lastIndex).getRow() == cell.getRow() && location.get(lastIndex).getCol() == cell.getCol()) {
			return true;
		}
		return false;
	}
}
