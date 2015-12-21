package practice.two;

public class Library {
	Book b1 = new LoanBook("Going nut","David Young",2.20,12,123);
	LoanBook b2 = new LoanBook("Be calm", "Peter Olde", 13.13, 134, 234);
	Standard standard;
	
	Person pObama = new Person("Barack Obama", standard);
	Loan l1 = new Loan(pObama,b2);
	

}
