package luminum;

import java.util.Scanner;

public class LuminumInput {
	
	public void handleInput(String type) {
		new LuminumOutput().systemOut("handle input called");
		// Get the text input from the user
		String input = getInput();
		
		if ( type.equals("question") ) {
			new LuminumOutput().systemOut("question detected");
			int i; for ( i=0; i < LuminumAI.question_count; i++ ) {
				int l; for ( l=0; l < LuminumAI.subline_count; l++ ) {
					System.out.println(l); //okay
					System.out.println(i); //okay
					System.out.println(LuminumAI.q_usr_ans[i][l]); // TODO: Gives null.
					if ( input.matches(LuminumAI.q_usr_ans[i][l]) ) { // [question number] [response number]
						// expected this answer to question
						LuminumOutput.friendlyOut(LuminumAI.q_ai_resp[i][l]);
						
					} else {
						// answer not coded
						new LuminumOutput().systemOut("answer didnt match");
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
