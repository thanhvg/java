package pratice.five;

import java.util.Comparator;

public class Card implements Comparable<Card> {
	char suit;
	char value;
	
	private char[] suits = {'S','D','C','H'};
	private char[] values = {'A','K','Q','J','T','2','3','4','5','6','7','8','9'};
	
	private boolean isSuit(char ch) {
		for (char s: suits){
			if (s == ch) return true;
		}
		return false;
	}
	
	private boolean isValue(char ch) {
		for (char v : values) {
			if (v == ch) return true;
		}
		return false;
	}
	
	private int getSuitIndex() {
		for (int i = 0; i < suits.length; i++) {
			if (suits[i] == suit)
				return i;
		}
		return -1; //ugly
	}
	
	private int getValueIndex() {
		for (int i = 0; i < values.length; i++) {
			if (values[i] == value) {
				return i;
			}
		}
		return -1; // ugly
	}
	
	
	public Card(char value, char suit) {
		super();
		this.suit = suit;
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + suit;
		//result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Card))
			return false;
		Card other = (Card) obj;
		if (suit != other.suit)
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public int compareTo(Card o) {
		int cmp = this.getValueIndex() - o.getValueIndex();
		return (cmp != 0 ? cmp : this.getSuitIndex() - o.getSuitIndex());
				
	}

	@Override
	public String toString() {
		return "Card" + value + "-" + suit + "[value=" + value + ", suit=" + suit + "]";
	}
}

class Compare implements Comparator<Card> {

	@Override
	public int compare(Card o1, Card o2) {
		return o1.compareTo(o2);
	}
	
}
