package pratice.eight;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ls {
	public static void main(String[] args) {
		Path dir = Paths.get("/tmp");
		List<Path> fileList = new ArrayList<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
		    for (Path file: stream) {
		        //System.out.println(file.getFileName());
		    	fileList.add(file);
		    }
		} catch (IOException | DirectoryIteratorException x) {
		    // IOException can never be thrown by the iteration.
		    // In this snippet, it can only be thrown by newDirectoryStream.
		    System.err.println(x);
		}
		for (Path file: fileList) {
			System.out.println(file.getFileName());
		}
		final Comparator<Path> CPM = new Comparator<Path>() {

			@Override
			public int compare(Path o1, Path o2) {
				
				return o1.toString().compareTo(o2.toString());
			}
			
		};
		Collections.sort(fileList,CPM);
		System.out.println("After sort");
		for (Path file: fileList) {
			System.out.println(file.getFileName());
		}
		
	}

}
