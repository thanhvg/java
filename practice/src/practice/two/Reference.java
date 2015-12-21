package practice.two;

public class Reference extends Book{

	public Reference(String tile, String author, double price, int cat,
			int subCat) {
		super(tile, author, price, cat, subCat);		
	}
	
	public String details() {
		return "Ref book" + tile + "Authour" + author + "Category" + cat +"." + subCat + "price $" + price;
	}
	
	public boolean lookUp(String s) {
		if (details().indexOf(s) != -1) return true;
		return false;
	}


}
