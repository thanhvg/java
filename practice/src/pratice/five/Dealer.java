package pratice.five;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer {

	private char[] suits = {'S','D','C','H'};
	private char[] values = {'A','K','Q','J','T','2','3','4','5','6','7','8','9'};

	List<Card> deck = new ArrayList<Card>();

	public Dealer() {
		for (char suit: suits) {
			for (char value : values) {
				deck.add(new Card(value,suit));
			}
		}
	}
	
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	public void giveAcard(Player p) {
		Card card = deck.get(deck.size()-1);
		p.add(card);
		deck.remove(deck.size() -1);		
	}

}

