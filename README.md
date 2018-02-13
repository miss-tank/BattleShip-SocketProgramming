# CS342-project4
Authors: Sid Basu, Simar Gadhoke, Ankita Tank

Getting project set up in eclipse:
1. create empty project
2. drop src and res directories in that empty project directory, not the src
    - if you drop into src you will get errors, since project structure will not be maintained
3. To run go to src/battleshipFrontend/BattleShipMain.java and right click run as Java application

Running using the terminal in case eclipse set up doesn't work:
mkdir build
javac src/battleshipBackend/*.java -d build src/battleshipFrontend/*.java
java -cp .:build:**/*.class battleshipFrontend.BattleShipMain

Running application (Brief Overview):
1. Start two applications one for each player
2. On one application start the server.
3. Begin adding ships to both of the applications. Once all the ships have been placed, click submit.
4. Once submit has been clicked from both applications the game will begin.
5. Each player will take turns and the game stats will update accordingly until one player quits the game or wins.

Using interface to play the game
1.  Each player must place his/her ship on his/her field
2.  Each ship has a different size: 
     Aircraft - 5
     Battleship - 4 
     Destroyer - 3 
     Submarine - 3 
     Patrol Boat -2
3.  By default the ship is horzontal, to place a ship vertical, click on the button that says horizontal to toggle vertical mode. then 
    select a ship
4.  To remove a ship press the remove button and click anywhere on the ship to remove it.
5.  Once each player is done building his/her field, the submit button will enable and press submit
6.  Decide which player will be the server and click on network help > start server 
7.  On submit enter in the machine name and port number given from the server. By clicking network help > server info
8.  This has to be the application that the server was started on. If you are the player that did not start the server ask the other        player
9.  Once both players have clicked submit, the game will begin. The player who had submitted first will go first
10. Each player will take turns in attempting each other ships until one player has won.


