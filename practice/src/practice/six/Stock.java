package practice.six;

import java.util.ArrayList;
import java.util.List;

public class Stock {
	List<Loanable> loanStock = new ArrayList<Loanable>();
	List<Reference> refStock = new ArrayList<Reference>();
	List<Loanable> loanedStock = new ArrayList<Loanable>();
	public void lookUp(String s){
		for (Loanable ls : loanStock) {
			if (ls.lookUp(s))
				ls.details();
				
		}
		for (Loanable ls : loanedStock) {
			if (ls.lookUp(s))
				ls.details();
		}
		for (Reference rf : refStock) {
			if (rf.lookUp(s)) {
				rf.details();
			}
		}
	}
	
	
	
	public int freeToLoan() {
		return loanStock.size();
	}
	

}
