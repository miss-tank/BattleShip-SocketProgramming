package battleshipBackend;


/**
 * Clas: GameCell
 * description: This is used for each cell. Has a row, col, val, and 
 * orintation. This is used to check for the ships being hit as well 
 * as the water.
 */


import java.io.Serializable;

import javax.swing.ImageIcon;

public class GameCell implements Serializable {

	private static final long serialVersionUID = 1L;
	private int number;
	private int letter;
	private char val;
	private static final char WATER = 'w';  
	private boolean orien;
	private boolean isCellHit; 
	
	public GameCell (int number, int letter, char val,boolean orien) {
		this.setLetter(letter);
		this.setNumber(number);
		this.setVal(val);
		this.setHori(orien);
		this.isCellHit = false;
	}
	
	public GameCell () {}

	/**
	 * Method:      isShipHit
	 * Parameters:  GameCell
	 * Return:      boolean
	 * Description: Checks if the cell has been hit or not.
	 */
	public void isCellHit (boolean isCellHit) {
		this.isCellHit = isCellHit;
	}
	
	/**
	 * Method:      isShipHit
	 * Parameters:  GameCell
	 * Return:      boolean
	 * Description: Checks if the cell has been hit or not.
	 */
	public boolean getIsCellHit () {
		return this.isCellHit;
	}
	
	/**
	 * Method:      getLetter
	 * Parameters:  none
	 * Return:      int
	 * Description: Gets the letter.
	 */
	public int getCol() {
		return letter;
	}
	
	/**
	 * Method:      isShipHit
	 * Parameters:  GameCell
	 * Return:      boolean
	 * Description: Checks if the cell has been hit or not.
	 */
	public void setHori(boolean hori)
	{
		this.orien=hori;
	}

	/**
	 * Method:      isShipHit
	 * Parameters:  GameCell
	 * Return:      boolean
	 * Description: Checks if the cell has been hit or not.
	 */
	public boolean getHori()
	{
		return orien;
	}

	
	/**
	 * Method:      setLetter
	 * Parameters:  letter
	 * Return:      void
	 * Description: sets the letter attribute
	 */
	public void setLetter(int letter) {
		this.letter = letter;
	}
	
	/**
	 * Method:      isShipHit
	 * Parameters:  GameCell
	 * Return:      boolean
	 * Description: Checks if the cell has been hit or not.
	 */
	public void setImg(ImageIcon icon)
	{
		this.setImg(icon);
	}
	
	/**
	 * Method:      getNumber
	 * Parameters:  none
	 * Return:      int
	 * Description: Gets the number.
	 */
	public int getRow() {
		return number;
	}

	/**
	 * Method:      setNumber
	 * Parameters:  int
	 * Return:      void
	 * Description: sets the number attribute
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * Method:      getVal
	 * Parameters:  none
	 * Return:      char
	 * Description: sets the number attribute
	 */
	public char getVal() {
		return val;
	}

	/**
	 * Method:      setVal
	 * Parameters:  char
	 * Return:      void
	 * Description: Sets the value attribute to either 'w' or 's'.
	 * 'w' indicates water and 's' indicates ship.
	 */
	public void setVal(char val) {
		this.val = val;
	}
	
	/**
	 * Method:      toString
	 * Parameters:  none
	 * Return:      String
	 * Description: returns the overrided toString format of the GameCell object.
	 */
	@Override
	public String toString () {
		return "<" + this.number + " ," + this.letter + ", " + this.val + ">";
	}

	/**
	 * Method:      isWater
	 * Parameters:  none
	 * Return:      boolean
	 * Description: checks if the cell's value is water
	 */
	public boolean isWater() {
		return val == WATER ? true: false;
	}
}
