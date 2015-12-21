package practice.two;

public abstract class Book {
	String tile;
	String author;
	double price;
	int cat;
	int subCat;
	
	public Book(String tile, String author, double price, int cat, int subCat) {
		super();
		this.tile = tile;
		this.author = author;
		this.price = price;
		this.cat = cat;
		this.subCat = subCat;
	}

	@Override
	public String toString() {
		return "Book [tile=" + tile + ", author=" + author + ", price=" + price
				+ ", cat=" + cat + ", subCat=" + subCat + "]";
	}
	
	public String details() {
		return tile + "Authour" + author + "Category" + cat +"." + subCat + "price $" + price;
	}
	
//	public boolean canLoan() {
//		return true;		
//	}
//	

}

interface Loanable {
	public boolean canLoan();
	public String details();
	public boolean lookUp(String s);
}