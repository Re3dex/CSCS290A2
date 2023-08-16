package Assignment2;
import java.io.FileReader;
import java.io.IOException;

public class LexicalAnalyzer {

	    public static void main(String[] args) {
	    	if (args.length < 1) {
	    		System.err.println("Usage: java ClassName <input_file>");
	    		System.exit(1);
	    		}
	    		String filePath = args[0];
	        //String filePath = "out2.txt";
	        
	        try (FileReader fileReader = new FileReader(filePath)) {
	            StringBuilder inputString = new StringBuilder();
	            
	            //Convert to String
	            int character;
	            while ((character = fileReader.read()) != -1) {
	                inputString.append((char) character);
	            }
	            String input = inputString.toString();
	            
	            //Tokenize
	            String[] lexemes = tokenizeLexemes(input);
	            
	            //output
	            int len = lexemes.length;
	            for (int i=0; i<len; i++) {
	            	String lexeme = lexemes[i];
	            	if (lexeme == "$") {
	            		break;
	            	}
	            	else {
		                System.out.println("Lexeme: "+lexeme);
	            	}
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public static String[] tokenizeLexemes(String input) {
	    	//#of lexemes in input string
	        int lexemeCount = countLexemes(input);
	        String[] lexemes = new String[lexemeCount];
	        
	       
	        int index = 0;
	        StringBuilder currentLexeme = new StringBuilder();
	        char[] inputarray = input.toCharArray();
	        int len = input.toCharArray().length;
	        
	        for (int i=0; i<len; i++) {
	        	char c = inputarray[i];
	            if (Character.isWhitespace(c) || isSymbol(c)) {
	                if (currentLexeme.length() > 0) {
	                    lexemes[index++] = currentLexeme.toString();
	                    currentLexeme = new StringBuilder();
	                }
	                if (!Character.isWhitespace(c)) {
	                    lexemes[index++] = String.valueOf(c);
	                }
	            } 
	            else {
	                currentLexeme.append(c);
	            }
	        }
	        
	        if (currentLexeme.length() > 0) {
	            lexemes[index] = currentLexeme.toString();
	        }
	        
	        return lexemes;
	    }
	    
	    
	    public static boolean isSymbol(char c) {
	        return c == '(' || c == ')' || c == '{' || c == '}' || c == '=' || c == ';' || c == ',' || c == '|' || c == '&';
	    }

	    
	    public static int countLexemes(String input) {
	        int count = 0;
	        boolean insideLexeme = false;
	        
	        int l = input.length();
	        char[] inputarray = input.toCharArray();
	        		
	        for (int i=0; i<l; i++) {
	        	char c = inputarray[i];
	            if (c == ' ' || isSymbol(c)) {
	                if (insideLexeme) {
	                    count++;
	                    insideLexeme = false;
	                }
	                if (c != ' ') {
	                    count++;
	                }
	            } 
	            else {
	                insideLexeme = true;
	            }
	        }

	        if (insideLexeme) {
	            count++;
	        }

	        return count;
	    }
	}
