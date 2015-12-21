package practice.six;

import java.util.Calendar;

public enum Membership {
	JUNIOR {
		@Override
		public boolean ableToLoan(Person p, Loanable l){
			if (l instanceof Video) return false;
			if (p.getBookTotal() < 1) return true;
			return false;
		}

		@Override
		public String details() {
			// TODO Auto-generated method stub
			return "Junior membership";
		}
	},
	STANDARD {
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
	},
	OAPS {
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
			if (weekendCheck()) return true;
			return false;
		}

	};

	public abstract boolean ableToLoan(Person person, Loanable l) ;

	public abstract String details() ;

}
