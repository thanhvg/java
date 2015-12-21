package practice.three;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class Main {

	public static void main(String[] args) {
		Cat cat = null;
		try {
			for (int i = 0; i < args.length; i ++) {
				cat = new Cat("file:"+args[i]);
				cat.copy(new PrintWriter(System.out));
			}			
		} catch (URISyntaxException e){
			System.err.println(e);				
		} catch (MalformedURLException e) {
			System.err.println(e);
		} catch (FileNotFoundException e) {
			System.err.println("Failed to open file" + e);
		} catch (IOException e) {
			System.err.println("IO error" + e);
		} finally { 
			try {
				cat.close();
			} catch (IOException e) {
				System.err.println(e);
			} 
		}

	}

}
