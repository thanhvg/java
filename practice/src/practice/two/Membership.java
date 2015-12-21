package practice.two;

import java.util.Calendar;

public abstract class Membership {
	public abstract String details() ;
	int maxBooks;
	public int getMaxBooks() {
		return maxBooks;
	}
	public abstract boolean ableToLoan(Person person, Loanable l); 
}

class Junior extends Membership {
	int maxBooks = 1;
	public String details() {
		return "Junior membership";
		
	}
	
	public boolean ableToLoan(Person person, Loanable l) {
		if (l instanceof Video) return false;
		if (person.getBookTotal() < maxBooks) return true;
		return false;
	}

}

class Standard extends Membership {
	int maxBooks = 6;
	public String details() {
		return "Standard membership";
	}
	@Override
	public boolean ableToLoan(Person person, Loanable l) {
		if (l instanceof Video) {
			if (person.getVideoTotal() > 0) return false;
			return true;
		}
		if (person.getBookTotal() < maxBooks) return true;
		return false;
	}
}

class Oaps extends Membership {
	int maxBooks = 10000;
	
	public String details () {
		return "AOPS membership";
	}
	
	boolean weekendCheck() {
		Calendar rightNow = Calendar.getInstance();
		int day = rightNow.get(Calendar.DAY_OF_WEEK);
		if ((day == Calendar.SUNDAY) || (day == Calendar.SATURDAY)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean ableToLoan(Person person, Loanable l) {
		if (l instanceof Video) {
			if (person.getVideoTotal() > 0) return false;
			return true;
		}
		if (person.getBookTotal() < maxBooks && weekendCheck()) return true;
		return false;
	}
	
	
	
}