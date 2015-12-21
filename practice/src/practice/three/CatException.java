package practice.three;

public abstract class CatException extends Exception {
	public CatException(String message) {
		super(message);
	}

	public CatException(String message, Throwable e) {
		super(message, e);
	}
}

class SubCatException extends CatException {

	public SubCatException(String message) {
		super(message);
	}

	public SubCatException(String message, Throwable e) {
		super(message, e);

	}

}

class SubCatExceptionPrint extends CatException {

	public SubCatExceptionPrint(String message, Throwable e) {
		super(message, e);
	}

	public SubCatExceptionPrint(String message) {
		super(message);
	}
	
}
