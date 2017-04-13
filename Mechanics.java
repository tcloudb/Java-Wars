
public class Mechanics {

	private int turn;

	public Mechanics() {
		turn = 1;
	}


	public void battle(Unit a, Unit b) {
		a.damage(b);
		calcDeath(b);
		if (!b.getIsDead()) {
		b.damage(a);
		calcDeath(a);
		}
	}

	private void calcDeath(Unit a) {
		if (a.getHP() == 0) {
			a.setIsDead(true);
		}
	}

	public void endTurn() {
		turn++;
	}

	// returns a number which represents the player who's turn it is. Ex: 0 = player 1, 1 = player 2, etc.
	public int getTurn() {
		return turn%2;
	}

	// returns 0 if nobody has won, 1 if player 1 won, and 2 if player 2 won.
	public int calcVictory() {
		//if player 1 has no units, return 2
		//if player 2 has no units, return 1
		//else return 0
		return 0;
	}






}