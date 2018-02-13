package battleshipBackend;

public class GameActions {
	private static final char WATER = 'w';
	
	public GameActions () {}

	public boolean isHit (GameCell cell) {
		if (WATER == cell.getVal()) {
			return false;
		}
		return true;
	}
	
}
