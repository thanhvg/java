package pratice.seven;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Test {
	public static void main(String[] args) {
		//Set<Card> hand = new HashSet<Card>();
		//Set<Card> hand = new TreeSet<Card>();
		List<Card> hand = new ArrayList<Card>();
		hand.add(new Card(Value.A,Suit.S));
		hand.add(new Card(Value.K,Suit.H));
		hand.add(new Card(Value.A,Suit.H));
		hand.add(new Card(Value.V3,Suit.D));
		hand.add(new Card(Value.V3,Suit.S));
		if (hand.size() != 5)	    
			System.out.println("Inital does't hold 5 cards");
		hand.add(new Card(Value.A,Suit.S));
		hand.add(new Card(Value.V3,Suit.D));
		if (hand.size() != 5)
			System.out.println("Initial does't hold 5 cards");

		Collections.sort(hand,new Compare());


		for (Card card : hand) {
			System.out.println(card);
		}
	}
}
