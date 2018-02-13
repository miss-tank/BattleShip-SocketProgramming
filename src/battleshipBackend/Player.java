package battleshipBackend;

/**
 * class: player
 * description: The player class has attibutes that are required for the player.
 * This class will have information on player turn, ship hit or not, how the ship
 was hit. Port and client information.
 */


import java.io.Serializable;

public class Player implements Serializable {
	private static final long serialVersionUID = 1L;
	private GameBoard board;
	private int noHits, noMisses, port, playerId, noShipsDestroyed; 
	private boolean isTurn, isHit, isHitHort;
	private String destroyedShipName, machineAddress, message;
	private char partOfShip;
	
	public Player (int playerId, GameBoard board, String mahineAddress, int port) {
		this.playerId = playerId;
		this.board = board;
		this.isTurn = false;
		this.destroyedShipName = null;
		this.noShipsDestroyed = 0; 
		this.setMachineAddress(mahineAddress);
		this.setPort(port);
		this.message = null;
		this.setPartOfShip('M');
	}
	
	/**
     * Method name: setHori
     * Parameters:  boolean
     * Return:      void
     * Description: sets the hit ship position
     */
	public void setHori(boolean hori)
	 {
	 		this.isHitHort=hori;
	 }
	 
	/**
     * Method name: getHori
     * Parameters:  none
     * Return:      boolean
     * Description: Gets what the ship's position horizontal or vertical
     */
	public boolean getHori()
	{
		return isHitHort;
	}
	
	/**
     * Method name: setGameMessage
     * Parameters:  String
     * Return:      void
     * Description: sets the message such as player turn or player wins.
     */
	public void setGameMessage (String message) {
		this.message = message;
	}
	
	/**
     * Method name: getGameMessage
     * Parameters:  none
     * Return:      String
     * Description: Gets the player message
     */
	public String getGameMessage () {
		return this.message;
	}
	
	/**
     * Method name: isWin
     * Parameters:  none
     * Return:      boolean
     * Description: Checks if the player has won
     */
	public boolean isWin () {
		return noShipsDestroyed == 5 ? true : false;
	}

	/**
     * Method name: setHitHor
     * Parameters:  boolean
     * Return:      void
     * Description: sets the hit horizontal property
     */
	public void setHitHor(boolean set)
	{
		this.isHitHort = set;
	}
	
	/**
     * Method name: getHitHor
     * Parameters:  none
     * Return:      boolean
     * Description: Gets the hit horizontal property
     */
	public boolean getHitHor()
	{
		return isHitHort;
	}
	
	/**
     * Method name: setTurn
     * Parameters:  boolean
     * Return:      void
     * Description: Sets the turn of the player
     */
	public void setTurn (boolean turn) {
		this.isTurn = turn;
	}
	
	/**
     * Method name: isPlayerTurn
     * Parameters:  none
     * Return:      boolean
     * Description: Gets the player turn
     */
	public boolean isPlayerTurn() {
		return this.isTurn; 
	}	

	/**
     * Method name: updateDestroyedShip
     * Parameters:  none
     * Return:      String
     * Description: Gets the player id. 
     */
	public void setBoard (GameBoard board) {
		this.board = board;
	}
	
	/**
     * Method name: setDestroyedShip
     * Parameters:  char
     * Return:      void
     * Description: sets the destroyed ship name
     */
	public void setDestroyedShip (char shipChar) {
		this.destroyedShipName = this.board.destroyedShipName(shipChar);
	}
	
	/**
     * Method name: updateDestroyedShip
     * Parameters:  none
     * Return:      void
     * Description: resets the ship name
     */
	public void resetshipDestroyedName () {
		this.destroyedShipName = null;
	}

	/**
     * Method name: displayShipDestroyed
     * Parameters:  none
     * Return:      String
     * Description: gets the ship that was destroyed
     */
	public String displayShipDestroyed () {
		return this.destroyedShipName;
	}
	
	/**
     * Method name: updateDestroyedShip
     * Parameters:  none
     * Return:      void
     * Description: increases the number of ships destroyed
     */
	public void updateDestroyedShip () {
		this.noShipsDestroyed++;
	}
	
	/**
     * Method name: getPlayerId
     * Parameters:  none
     * Return:      String
     * Description: Gets the player id. 
     */
	public int getPlayerId () {
		return this.playerId;
	}
	
	/**
     * Method name: getGameBoard
     * Parameters:  none
     * Return:      GameBoard
     * Description: Gets the player's board 
     */
	public GameBoard getGameBoard () {
		return this.board;
	}
	
	/**
     * Method name: toString
     * Parameters:  none
     * Return:      String
     * Description: overrided method to get the player attributes
     */
	public String toString () {
		return "PlayerName: " + playerId + "\nHits: " + noHits + "\nMisses: " + noMisses + "\nShips Destroyed: " + this.noShipsDestroyed + "\n";
	}

	/**
     * Method name: getNoHits
     * Parameters:  none
     * Return:      GameBoard
     * Description: Gets the number of hits
     */
	public int getNoHits() {
		return noHits;
	}

	/**
     * Method name: updateNoHits
     * Parameters:  none
     * Return:      void
     * Description: update the number of hits.
     */
	public void updateNoHits() {
		this.noHits++;
	}

	/**
     * Method name: getNoMisses
     * Parameters:  none
     * Return:      int
     * Description: Gets number of misses
     */
	public int getNoMisses() {
		return noMisses;
	}

	/**
     * Method name: updateNoMisses
     * Parameters:  none
     * Return:      void
     * Description: increaments the nubmer of misses
     */
	public void updateNoMisses() {
		this.noMisses++;
	}

	/**
     * Method name: setPlayerId
     * Parameters:  int
     * Return:      void
     * Description: sets the player id
     */
	public void setPlayerId(int playerId2) {
		this.playerId = playerId2;
	}
	
	/**
     * Method name: setIsHit
     * Parameters:  boolean
     * Return:      void
     * Description: sets if the player has hit a cell. This is used to check if
	 * the player tried to hit a scell they have already previously hit.
     */
	public void setIsHit (boolean isHit) {
		this.isHit = isHit;
	}
	
	/**
     * Method name: getIsHit
     * Parameters:  none
     * Return:      boolean
     * Description: gets if player has hit a cell. This is used to check if
	 * the player has already hit a cell. (not the ship)
     */
	public boolean getIsHit () {
		return this.isHit;
	}
	
	/**
     * Method name: getMachineAddress
     * Parameters:  none
     * Return:      String
     * Description: Gets player machien name
     */
	public String getMachineAddress() {
		return machineAddress;
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
     * Method name: getPort
     * Parameters:  none
     * Return:      int
     * Description: Gets the port
     */
	public int getPort() {
		return port;
	}

	/**
     * Method name: setPort
     * Parameters:  int
     * Return:      void
     * Description: sets the port number
     */
	public void setPort(int port) {
		this.port = port;
	}

	/**
     * Method name: getGameBoard
     * Parameters:  none
     * Return:      GameBoard
     * Description: Gets the part of ship destroeyd F, M, K.
	 * (front, mid, back)
     */
	public char getPartOfShip() {
		return partOfShip;
	}

	/**
     * Method name: setPartOfShip
     * Parameters:  char
     * Return:      void
     * Description: sets the part of the ship that is destroyed front, mid, back
     */
	public void setPartOfShip(char partOfShip) {
		this.partOfShip = partOfShip;
	}

	public class PlayerHelper implements Serializable{
		private static final long serialVersionUID = 1L;
		private int playerHelperId;
		private GameCell cell;
		
		public PlayerHelper (GameCell cell) {
			this.setPlayerHelperId(getPlayerId());
			this.setCell(cell);
		}

		/**
		* Method name: getPlayerHelperId
		* Parameters:  none
		* Return:      int
		* Description: gets the player id
		*/
		public int getPlayerHelperId() {
			return playerHelperId;
		}

		/**
		* Method name: setPlayerHelperId
		* Parameters:  none
		* Return:      GameBoard
		* Description: sets player id
		*/
		public void setPlayerHelperId(int playerId) {
			this.playerHelperId = playerId;
		}

		/**
		* Method name: getCell
		* Parameters:  none
		* Return:      GameBoard
		* Description: Gets cell
		*/
		public GameCell getCell() {
			return cell;
		}

		/**
		* Method name: getGameBoard
		* Parameters:  none
		* Return:      void
		* Description: sets game cell
		*/
		public void setCell(GameCell cell) {
			this.cell = cell;
		}
		
		
	}
	
}
