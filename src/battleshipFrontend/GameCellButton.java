package battleshipFrontend;


/**
 * Clas: GameCellButton
 * description: extends JButton and uses propertis of the GameCell.
 */

import javax.swing.ImageIcon;
import javax.swing.JButton;
import battleshipBackend.GameCell;

@SuppressWarnings("serial")
public class GameCellButton extends JButton {
	private GameCell cell;
	private char mval;
	
	public GameCellButton (String text, int row, int col, char val) {
		super(text);
		this.cell = new GameCell(row, col, val, false);
	}
		
	/**
     * Method name: getGameCell
     * Parameters:  none
     * Return:      GameCell
     * Description: Gets the Game cell attribute. 
     */
	public GameCell getGameCell () {
		return this.cell;
	}
	
	/**
     * Method name: setImg
     * Parameters:  none
     * Return:      GameCell
     * Description: sets the image 
     */
	public void setImg(ImageIcon icon)
	{
		this.setIcon(icon);
	}
	
	/**
     * Method name: setVal
     * Parameters:  none
     * Return:      GameCell
     * Description: Gets the Game cell attribute. 
     */
	public void setVal (char val) {
		this.cell.setVal(val);
		mval=val;
	}
	
	/**
     * Method name: getVal
     * Parameters:  none
     * Return:      GameCell
     * Description: Gets the Game cell attribute. 
     */
	public char getVal () {
		 return mval;
	}
	
	/**
     * Method name: updateCell
     * Parameters:  none
     * Return:      GameCell
     * Description: Gets the Game cell attribute. 
     */
	public void updateCell (String text, char val) {
		this.setText(text);
		this.cell.setVal(val);
		mval=val;
	}
}
