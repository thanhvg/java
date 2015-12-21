package practice.six;

import java.util.Date;

public class Loan {
	Person person;
	Loanable loan;
	Date date;
	public Loan(Person person, Loanable l) {
		super();
		this.person = person;
		this.loan = l;
		date = new Date();
		person.borrow(l);
	}	
}
