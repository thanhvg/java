package pratice.seven;

import java.util.ArrayList;
import java.util.List;

public class Player {
	String name;
	List<Card> hand = new ArrayList<Card>();
	
	public Player(String name) {
		super();
		this.name = name;
	}
	
	public Player(String name, List<Card> hand) {
		super();
		this.name = name;
		this.hand = hand;
	}
	
	public void add(Card c) {
		hand.add(c);
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", hand=" + hand + "]";
	}
	
	

}
