package battleshipFrontend;

/**
 * Clas: BattleShipUtil
 * description: Util class which has some stuff.
 */


public  class BattleShipUtil {
	
	private String machineAddress;
	private int portNum;
	private String serverMachineAddress;
	private int serverPortNum;
	
	public String getServerMachineAddress() {
		return serverMachineAddress;
	}

	/**
     * Method name: setServerMachineAddress
     * Parameters:  char
     * Return:      void
     * Description: init the ship
     */
	public void setServerMachineAddress(String serverMachineAddress) {
		this.serverMachineAddress = serverMachineAddress;
	}

/**
     * Method name: getServerPortNum
     * Parameters:  char
     * Return:      void
     * Description: init the ship
     */
	public int getServerPortNum() {
		return serverPortNum;
	}

	/**
     * Method name: setServerPortNum
     * Parameters:  char
     * Return:      void
     * Description: init the ship
     */
	public void setServerPortNum(int serverPortNum) {
		this.serverPortNum = serverPortNum;
	}
	
	
	BattleShipUtil() {
//		this.machineAddress = "10.75.48.239";
//		this.portNum = 9081;
//		
//		this.serverMachineAddress ="10.75.48.239";
//		this.serverPortNum = 50437;
		this.machineAddress = null;
	}
	
	/**
     * Method name: getMachineAddress
     * Parameters:  char
     * Return:      void
     * Description: init the ship
     */
	public String getMachineAddress() {
		return machineAddress;
	}

	/**
     * Method name: setMachineAddress
     * Parameters:  char
     * Return:      void
     * Description: init the ship
     */
	public void setMachineAddress(String machineAddress) {
		this.machineAddress = machineAddress;
	}

	/**
     * Method name: getPortNum
     * Parameters:  char
     * Return:      void
     * Description: init the ship
     */
	public int getPortNum() {
		return portNum;
	}

	/**
     * Method name: setPortNum
     * Parameters:  char
     * Return:      void
     * Description: init the ship
     */
	public void setPortNum(int portNum) {
		this.portNum = portNum;
	}
	
}
