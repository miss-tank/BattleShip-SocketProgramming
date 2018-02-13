package battleshipFrontend;

/**
 * Clas: MainMenu
 * description: Has the actions for the main menu such as netowrk help
 * and the help for using the interface.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import battleshipBackend.BattleShipServer;

/**
 * Class: MainMenu
 * Description: Contains functionality for the menu buttons  
 * @author Simar Gadhoke,Ankita Tank, Siddharth Basu 
 */

public class MainMenu {
	private GameView gv;
	private JMenuBar menuBar;
	private JMenu helpMenu, netowrkMenu;
	private BattleShipServer server;
		
	public MainMenu (GameView gv) {
		this.gv = gv;
		this.menuBar = new JMenuBar();
		//this.server = new BattleShipServer();
		createHelpMenuItems();
		networkMenuItem();
	}
	
	public void networkMenuItem () {
		// create the buttons
		this.netowrkMenu = new JMenu("Network Help");
		JMenuItem serverMenu = new JMenuItem("start server");
		JMenuItem serverInfo = new JMenuItem("server info");
		
		// attach their respective action listeners
		serverMenu.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("attempting to start server");
					//Thread server = new Thread(new BattleShipServer());
					server = new BattleShipServer();
					server.start();
					gv.displayMessage("Started server for server info click on network help > server info");
				} catch (Exception e1) {
//					JOptionPane.showMessageDialog(null, "Failure, could not connect to the server");
					e1.printStackTrace();
				}
			}
		});
		
		serverInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = "Error: no running server. Please start server";;
				if (server.getMachineAddress() != null) {
					message = "Starting Server from machine " + server.getMachineAddress() + " on port: " + server.getPortNumber();
				}
				gv.displayMessage(message);
				JOptionPane.showMessageDialog(null, message);
			}
		});
		
		// attach the menu items to the menu
		this.netowrkMenu.add(serverMenu);
		this.netowrkMenu.add(serverInfo);
		
		// attach the menu to the menu bar
		this.menuBar.add(this.netowrkMenu);
	}
	
	/**
     * Method name: createHelpMenuItems
     * Parameters:  none
     * Return:      void
     * Description: renders all the sub menu items in relation to the help main 
     * menu item. 
     */
	public void createHelpMenuItems () {
		helpMenu = new JMenu("Help");
		
		JMenuItem about = new JMenuItem("About");
		JMenuItem guide = new JMenuItem("How to Play");
		JMenuItem quit = new JMenuItem("Quit Game");
		
		
		// listeners
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gv.displayAbout();
			}
		});
		
		guide.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gv.displayHowToPlay();
				
			}
		});
		
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		// add each sub menu item to the help main menu
		helpMenu.add(about);
		helpMenu.add(guide);
		helpMenu.add(quit);
		menuBar.add(helpMenu);
	}
	
	/**
	 * method name: getMenuBar
	 * parameters:  none
	 * return:	    JMenuBar
	 * description: Gets menu bar
	 */
	public JMenuBar getMenuBar () {
		return this.menuBar;
	}
}
