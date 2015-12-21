package practice.six;

public class LoanBook extends Book implements Loanable {

	public LoanBook(String tile, String author, double price, int cat,
			int subCat) {
		super(tile, author, price, cat, subCat);		
	}

	@Override
	public boolean canLoan() {
		return true;
	}
	
	@Override
	public String details() {
		return "Loanable book:" + super.details();
	}

	@Override
	public boolean lookUp(String s) {
		if (details().indexOf(s) != -1) return true;
		return false;
	}

}
