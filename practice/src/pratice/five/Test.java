package pratice.five;

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
		hand.add(new Card('A','S'));
		hand.add(new Card('K','H'));
		hand.add(new Card('A','H'));
		hand.add(new Card('3','D'));
		hand.add(new Card('3','S'));
		if (hand.size() != 5)
			System.out.println("Initial does't hold 5 cards");
		hand.add(new Card('A','S'));
		hand.add(new Card('3','D'));
		if (hand.size() != 5)
			System.out.println("Initial does't hold 5 cards");
		
		Collections.sort(hand,new Compare());


		for (Card card : hand) {
			System.out.println(card);
		}
	}
}
