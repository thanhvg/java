package practice.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class Cat {
	BufferedReader in;

	public Cat(String name) throws URISyntaxException, MalformedURLException, IOException {
		URI uri = new URI(name);
		in = new BufferedReader(new InputStreamReader(uri.toURL().openStream()));
	}

	public void copy(PrintWriter out) throws IOException {
		String line;
		while ((line = in.readLine()) != null) {
			out.println(line);
		}
		in.close();
	}

	public void close() throws IOException {
		in.close();
	}

}
