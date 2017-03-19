package boggle.model;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtils {

	public static ArrayList<String> readFile(String name) {
		ArrayList<String> lines = new ArrayList<String>();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(name));
		    String line;

		    while ((line = br.readLine()) != null) {
		        lines.add(line);
		    }
		    
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return lines;
	}
	
}
