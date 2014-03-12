package luminum;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Random;

public class LuminumAI {
	
	// Add Array Lists, arrays and integers:
	public static ArrayList<String> question = new ArrayList<String>();
	public static ArrayList<String> greeting = new ArrayList<String>();
	
	public static int question_count;
	public static int greeting_count;
	
	public static String[][] q_usr_ans = new String[999][999]; // TODO: define accurate max size for these arrays
	public static String[][] q_ai_resp = new String[999][999];

	public static int subline_count;
	public static int question_number;
	
	public void loadAIScript(String script) throws IOException {
		try {
			// Initial variables
			question_count = 0;
			greeting_count = 0;

			subline_count = 0;
			question_number = 0;
			
			// Set path
			String path = "ai/" + script + ".txt";
			
			// Read script
			String[] lines = readFile(path);
			
			// Define array variables
			//String[][] q_usr_ans = new String[lines.length][lines.length];
			//String[][] q_ai_resp = new String[lines.length][lines.length];
			
			// Set up boolean
			boolean expect_subline = false;
			
			// Do stuff with these lines
			int i;for ( i=0; i < lines.length; i++ ) {
				//System.out.println( lines[i] ) ; // prints all of the lines in the file
				
				// If the second character isn't a space
				if ( lines[i].indexOf(' ') != 1 && lines[i].startsWith("#") != true) { // counts from 0
					new LuminumInterface().systemOut("Syntax error in AI Script " + script + " on line #" + (i+1) + ". No space after prefix.");
				}
				
				// If the first character is ! then
				if (lines[i].startsWith("#")) {
					// # - File comment, ignore this.
				} else if (lines[i].startsWith("L")) {
					// L - sub line (expanded information from previous line)
					if (expect_subline) {
						System.out.println("expect_subline is true");
						// Expected the sub line prefix
						String text = lines[i].substring(2, lines[i].length());
						System.out.println(text.substring(0,(lines[i].indexOf(':') - 2)));
						System.out.println(question_count);
						System.out.println(subline_count);
						
						q_usr_ans[question_count][subline_count] = (text.substring(0,(lines[i].indexOf(':') - 2)));
						
						q_ai_resp[question_count][subline_count] = text.substring(lines[i].indexOf(':'),text.length());
						System.out.println(q_usr_ans[question_count][subline_count]); // TODO: the variable can't be accessed out of this *loop*.
						subline_count++;
					} else {
						// Didn't expect the sub line
						new LuminumInterface().systemOut("Syntax error in AI Script " + script + " on line #" + (i+1) + ". Didn't expect 'L' prefix.");
						subline_count = 0;
					}
				} else if (lines[i].startsWith("&")) {
					// & - Meta data
					String metatype = "ai_name";
					if ( metatype.equals(lines[i].substring(2, (metatype.length() + 2))) ) {
						new LuminumInterface().systemOut("New AI name set"); // TODO
					}
					metatype = "ai_version";
					if ( metatype.equals(lines[i].substring(2, (metatype.length() + 2))) ) {
						new LuminumInterface().systemOut("New AI version set"); // TODO
					}
				} else if (lines[i].startsWith("$")) {
					String text = lines[i].substring(2, lines[i].length());
					System.out.println(text);
				} else if (lines[i].startsWith("?")) {
					// ? - Asks the user a question, expects an answer.
					subline_count = 0;
					question.add(lines[i].substring(2, lines[i].length()));
					expect_subline = true;
					question_count++;
				} else if (lines[i].startsWith("@")) {
					// @ - Unhappy response.
				} else if (lines[i].startsWith("~")) {
					// ~ - Happy response.
				} else if (lines[i].startsWith("*")) {
					// * - Neutral response.
				} else if (lines[i].startsWith("G")) {
					// G - Greeting
					greeting.add(lines[i].substring(2, lines[i].length()));
					greeting_count++;
				} else {
					new LuminumInterface().systemOut("Syntax error in AI Script " + script + " on line #" + (i+1) + ". No known prefix given.");
				}
			}
			System.out.println(q_usr_ans[0][0]);
		} catch (IOException e) {
			new LuminumInterface().systemOut("Cannot access AI script. Maybe it doesn't exist? (Java IOException)");
		}
	}

	public void AIMessage(String type) {
		//new LuminumInterface().systemOut("AI Message");
		
		if (type == "greeting") {
			LuminumInterface.friendlyOut(String.valueOf(greeting.get(new Random().nextInt(greeting_count))));
		} else if (type == "question") {
			question_number = new Random().nextInt(question_count);
			LuminumInterface.friendlyOut(String.valueOf(question.get(question_number)));
		}
	}
	
	
	String[] readFile(String path) throws IOException {
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		
		// Create array for the text file
		int numberOfLines = readLines(path);
		String[] textData = new String[numberOfLines];
		int i;

		// Put the contents of the text file in the array
		for (i=0; i < numberOfLines; i++) {
			textData[i] = textReader.readLine();
		}
		
		// Close the memory buffer and return our array
		textReader.close( );
		return textData;
	}
	
	int readLines(String path) throws IOException { // Reads amount of lines in a text file.
		
		BufferedReader bf = new BufferedReader(new FileReader(path));
		
		String aLine;
		int numberOfLines = 0;
	
		while ( (aLine = bf.readLine()) != null ) {
			numberOfLines++;
		}
		bf.close();
		
		return numberOfLines;
	}
	
	boolean AIKeywordCheck(String keyword, String input) {
		return (input.toLowerCase().contains(keyword.toLowerCase()));
	}
}