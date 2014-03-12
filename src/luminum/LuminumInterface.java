package luminum;

import java.util.Random;
import java.util.Scanner;

public class LuminumInterface {
	
	public static int sent_message_count;
	
	public void nextMessage() {
		if ( sent_message_count == 0 ) {
			// The first message should be a greeting.
			new LuminumAI().AIMessage("greeting");
			handleInput("generic");
		} else {
			// If not the first message:
			int x = new Random().nextInt(1) + 1; // Random 1 through 1
			// If 1 (question)
			if ( x == 1) {
				// give question
				new LuminumAI().AIMessage("question");
				// handle answer
				handleInput("question");
			}
		}
		sent_message_count++;
	}
	
	public static void friendlyOut(String output_text) {
		System.out.println(output_text);
	}
	
	public void systemOut(String output_text) {
		System.out.println("** SYSTEM ** > " + output_text);
	}
	
	public void handleInput(String type) {
		systemOut("handle input called");
		// Get the text input from the user
		String input = getInput();
		
		if ( type.equals("question") ) {
			int i; for ( i=0; i < LuminumAI.question_count; i++ ) {
				int l; for ( l=0; l < LuminumAI.subline_count; l++ ) {
					System.out.println(l); //okay
					System.out.println(i); //okay
					System.out.println(LuminumAI.q_usr_ans[i][l]); // TODO: Gives null.
					if ( input.matches(LuminumAI.q_usr_ans[i][l]) ) { // [question number] [response number]
						// expected this answer to question
						friendlyOut(LuminumAI.q_ai_resp[i][l]);
						
					} else {
						// answer not coded
						systemOut("answer didnt match");
					}
				}
			}
		} else {
			// Standard processing
		}
		//new LuminumAI().AIInput(input);
	}

	String getInput() {
		System.out.print("> ");
		Scanner input = new Scanner(System.in);
		return input.nextLine();
	}
	
	boolean findWord(String keyword, String input) {
		return (input.toLowerCase().contains(keyword.toLowerCase()));
	}
	
}
