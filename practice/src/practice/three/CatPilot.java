package practice.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class CatPilot {
	BufferedReader in;
	
	public CatPilot (String name) throws SubCatException {
		try {
			URI uri = new URI(name);
			in = new BufferedReader(new InputStreamReader(uri.toURL().openStream()));
		} catch (URISyntaxException e) {
			throw new SubCatException("Problem with opening", e);
		} catch (MalformedURLException e) {
			throw new SubCatException("Problem with opening", e);
		} catch (IOException e) {
			throw new SubCatException("Problem with opening", e);
		}
	}
	
	public void copy(PrintWriter out) throws SubCatException, SubCatExceptionPrint {
		String line;
		
		try {
			while ((line = in.readLine()) != null) {
				out.println(line);
				if (out.checkError()){
					throw new SubCatExceptionPrint("Print API problem");
				}
			}
			
		} catch (IOException e) {
			throw new SubCatException("Copy problem", e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void close() {
		try {
			in.close();
		} catch (IOException e) {
			//Doing nothing here to swallow/hide the exception 
		}
	}


}
