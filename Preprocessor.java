package Assignment2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Preprocessor {
	public static void main(String[] args) {
		System.out.println("Working Directory: " + System.getProperty("user.dir"));
		if (args.length < 1) {
			System.err.println("Usage: java ClassName <input_file>");
			System.exit(1);
			}
			String input_file = args[0];
			
		//String input_file = "in1.java";
		
		try (FileReader reader = new FileReader(input_file);
	             FileWriter writer = new FileWriter("out1.java")) {
			
			int character;
			int prevchar = -1;
			boolean prevblank = false;
			boolean insideString = false;
			boolean insideComment = false;
			boolean emptyline = true; //ensures that if we have '\n' line at the start of reading, then it has to be an empty line.
			
			while ((character = reader.read()) != -1) {
				//import statements
	            if (character == 'i') {
	            	int nextchar = reader.read();
	            	if (nextchar == 'm') {
	            		while ((character = reader.read()) != -1 & character != '\n') {
                            	//skip whole line
                        }
	            	}
	            	else if (nextchar == '\n') {
	            		continue;
	            	}
	            	else { //if its not import statement write to file
	            		writer.write(character);
	            		writer.write(nextchar);
	            		prevchar = nextchar;
	            		continue;
	            	}
	            }
				//Empty Lines
	            if (character == '\n') { //Check for empty line
                    if (emptyline) {
                        emptyline = true;
                        prevchar = character;
                        continue;  // Skip empty lines
                    } else {
                        emptyline = false;
                    }
				}
				
				
				//Enclosed Strings
				if (character == '"') { //Start/End of string
					insideString = !insideString;
				}
				
				//Comments
				if (!insideString) {
					if (character == '/') {
						int nextchar = reader.read();
						if (nextchar == '/') {
							while ((character = reader.read()) != -1 & character != '\n') {
								//Skip Comment
							}

							emptyline = true;
						}
						else if (nextchar == '*') {
							insideComment = true;
							while (insideComment) {
								//skip multi-comment
								character = reader.read();
								if (character == '*') {
									int endslash = reader.read();
									if (endslash == '/') {
										insideComment = false;
									}
								}
							}
							emptyline = true;
						}
						else if (nextchar == '\n') {
							continue;
						}
						else {
							//Write character to  file	
							writer.write(character);
							if (nextchar != -1) {
								writer.write(nextchar);
							}
							emptyline = false;
						}
					}
				else if (character == ' ' & character!= '\n') {
					//SINGLE SPACE CASE?
					if (prevchar!=' ' & prevchar!='\n') {
						writer.write(' ');
					}
					prevchar = character;
					continue;
				}
				
				else {
					if (prevchar==10 & character ==10) {
							continue;
						}
					//blank space passes through here
					
					writer.write(character);
					emptyline = false;
					prevchar = character;
				}
			}
		}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	

	}
}
