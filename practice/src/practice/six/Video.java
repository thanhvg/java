package practice.six;

public class Video implements Loanable {

	String tile;
	
	

	public Video(String tile) {
		super();
		this.tile = tile;
	}



	@Override
	public boolean canLoan() {
		return true;
	}
	
	public String details() {
		return "Tile" + tile;
	}



	@Override
	public boolean lookUp(String s) {
		if (details().indexOf(s) != -1) return true;
		return false;
	}

}
