package luminum;

import java.util.Scanner;

public class LuminumInput {

	void handleInput(String type) {
		
		// Get the text input from the user
		String input = getInput();
		
		// Different tasks for different types of input
		if ( type == "name" ) {
			new LuminumOutput().greetUser(new LuminumMeta().AIName(), input);
		} else if (type == "laugh") {
			new LuminumOutput().laughterOut();
		} else if (type == "generic") {
			new LuminumAI().AIInput(input);
		}
	}

	String getInput() {
		System.out.print("> ");
		Scanner input = new Scanner(System.in);
		return input.nextLine();
	}

}
