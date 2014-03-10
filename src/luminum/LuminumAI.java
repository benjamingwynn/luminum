package luminum;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Random;

public class LuminumAI {
	
	// Add Array Lists and integers:
	public static ArrayList question = new ArrayList();
	public static ArrayList greeting = new ArrayList();
	
	public static int question_count;
	public static int greeting_count;
	
	public void loadAIScript(String script) throws IOException {
		try {
			// Initial variables?
			question_count = 0;
			greeting_count = 0;
			
			// Set path
			String path = "ai/" + script + ".txt";
			
			// Read script
			String[] lines = readFile(path);
			
			// Do stuff with these lines
			int i;
			for ( i=0; i < lines.length; i++ ) {
				//System.out.println( lines[i] ) ; // prints all of the lines in the file
				
				// If the second character isn't a space
				if ( lines[i].indexOf(' ') != 1 && lines[i].startsWith("#") != true) { // counts from 0
					new LuminumOutput().systemOut("Syntax error in AI Script " + script + " on line #" + (i+1) + ". No space after prefix.");
				}
				
				// If the first character is ! then
				if (lines[i].startsWith("#")) {
					// # - File comment, ignore this.
				} else if (lines[i].startsWith("&")) {
					// & - Meta data
					String metatype = "ai_name";
					if ( metatype.equals(lines[i].substring(2, (metatype.length() + 2))) ) {
						new LuminumOutput().systemOut("New AI name set"); // TODO
					}
					metatype = "ai_version";
					if ( metatype.equals(lines[i].substring(2, (metatype.length() + 2))) ) {
						new LuminumOutput().systemOut("New AI version set"); // TODO
					}
				} else if (lines[i].startsWith("$")) {
					String text = lines[i].substring(2, lines[i].length());
					System.out.println(text);
				} else if (lines[i].startsWith("?")) {
					// ? - Asks the user a question, expects an answer.
					question.add(lines[i].substring(2, lines[i].length()));
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
					new LuminumOutput().systemOut("Syntax error in AI Script " + script + " on line #" + (i+1) + ". No prefix given.");
				}
			}
		} catch (IOException e) {
			new LuminumOutput().systemOut("Cannot access AI script. Maybe it doesn't exist? (Java IOException)");
		}
	}
	
	public void nextMessage() {
		int sent_message_count = 0;
		
		if ( sent_message_count == 0 ) {
			// The first message should be a greeting.
			new LuminumAI().AIMessage("greeting");
		}
		
		sent_message_count++;
	}

	public void AIMessage(String type) {
		//new LuminumOutput().systemOut("AI Message");
		LuminumOutput out = new LuminumOutput();
		
		if (type == "greeting") {
			out.friendlyOut(String.valueOf(greeting.get(new Random().nextInt(greeting_count))));
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
	
	void AIInput(String input) {
		if (AIKeywordCheck("how", input) && AIKeywordCheck("you", input) && AIKeywordCheck("feeling", input)) {
			AIProcess("status");
		} else if (AIKeywordCheck("what", input)) {
			if (AIKeywordCheck("weather", input)) {
				AIProcess("weather");
			} else {
				AIProcess("dont_know");
			}
		} else if (AIKeywordCheck("why", input)) {
			if (AIKeywordCheck("did", input) && AIKeywordCheck("chicken", input)) {
				AIProcess("joke_chicken_understand");
			} else {
				AIProcess("dont_know");
			}
		} else if (AIKeywordCheck("who", input)) {
			if (AIKeywordCheck("creator", input)) {
				AIProcess("about");
			} else {
				AIProcess("dont_know");
			}
		} else if (AIKeywordCheck("can", input)) {
			if (AIKeywordCheck("you", input)) {
				AIProcess("cant_do_that");
			} else if (AIKeywordCheck("i", input)) {
				AIProcess("maybe_you_can");
			} else {
				AIProcess("dont_know");
			}
		} else if (AIKeywordCheck("yes", input) || AIKeywordCheck("no", input) || AIKeywordCheck("i'm", input)) {
			AIProcess("okay");
		} else if (AIKeywordCheck("youre", input) || AIKeywordCheck("you're", input) || AIKeywordCheck  ("you", input)) {
			AIProcess("respond_unknown_accusation");
		} else if (AIKeywordCheck("?", input)) {
			AIProcess("dont_know_question");
		} else if (AIKeywordCheck("hello", input) || AIKeywordCheck("hi ", input)) { // this is buggy, TODO change it to absolute wording
				AIProcess("greeting");
		} else {
			AIProcess("unknown");
		}
	}
	
	boolean AIKeywordCheck(String keyword, String input) {
		return (input.toLowerCase().contains(keyword.toLowerCase()));
	}
	
	void AIProcess(String type) {
		if ( type == "respond_unknown_accusation" || type == "dont_know_question" || type == "greeting" || type == "status" || type == "unknown" || type == "cant_do_that" || type == "maybe_you_can" || type == "dont_know") {
			new LuminumOutput().friendlyOut(new LuminumMessageGen().randomMessage(type));
		} else {
			new LuminumOutput().friendlyOut(new LuminumMessageGen().message(type));
		}
		if ( type == "joke_chicken_understand" ) {
			new LuminumInput().handleInput("laugh");
		} else {
			new LuminumInput().handleInput("generic");
		}
	}
}