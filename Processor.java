package Assignment2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Processor {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Usage: java ClassName <input_file>");
			System.exit(1);
			}
			String input_file = args[0];
		//String input_file = "out1.java";
		
		try (FileReader reader = new FileReader(input_file);
	             FileWriter writer = new FileWriter("out2.txt")) {
			
			int character;
			
			while ((character = reader.read())!= -1){
				if (character == '\n') {
					continue;
				}
				else {
					writer.write(character);
				}
			
			}
			writer.write('$');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
