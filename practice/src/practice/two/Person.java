package practice.two;

import java.util.ArrayList;
import java.util.List;

public class Person {
	String name;
	Membership member;
	List<Loanable> loans = new ArrayList<Loanable>();
	int bookTotal = 0;
	int videoTotal = 0;
	
	
	public int getBookTotal() {
		return bookTotal;
	}

	public int getVideoTotal() {
		return videoTotal;
	}

	public Person(String name, Membership member) {
		super();
		this.name = name;
		this.member = member;
	}
	
	public String details() {
		return name + member.details();
	}
	
	public void borrow(Loanable l) {
		if (member.ableToLoan(this,l)) {
			loans.add(l);
			if (l instanceof Video) videoTotal ++;
			else bookTotal ++ ;			
		} else {
			System.out.printf("Unable to borrow");
		}
		
	}
}
