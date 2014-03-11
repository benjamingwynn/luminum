package luminum;

import java.util.Random;

public class LuminumOutput {
	
	public static int sent_message_count;
	
	public void nextMessage() {
		if ( sent_message_count == 0 ) {
			// The first message should be a greeting.
			new LuminumAI().AIMessage("greeting");
			new LuminumInput().handleInput("generic");
		} else {
			// If not the first message:
			int x = new Random().nextInt(1) + 1; // Random 1 through 1
			// If 1 (question)
			if ( x == 1) {
				// give question
				new LuminumAI().AIMessage("question");
				// handle answer
				new LuminumInput().handleInput("question");
			}
		}
		sent_message_count++;
	}
	
	void greetUser(String ai_name, String user_name) {
		
		// Method setup
		LuminumMessageGen msggen = new LuminumMessageGen();
		LuminumInput input = new LuminumInput();
		
		// Print hello message
		String hello = msggen.randomMessage("greeting");
		
		// If the user's name is undefined
		if (user_name != "Undefined") {
			LuminumOutput.friendlyOut(hello + ", " + user_name + ". I'm " + ai_name + ".");
			input.handleInput("generic");
		} else {
			LuminumOutput.friendlyOut(hello + ". I don't seem to know your name.");
			String whoareyou = msggen.randomMessage("askwho");
			LuminumOutput.friendlyOut(whoareyou);
			input.handleInput("name");
		}
	}
	
	void laughterOut() {
		LuminumMessageGen msggen = new LuminumMessageGen();
		LuminumInput input = new LuminumInput();

		String msg = msggen.randomMessage("laugh");
		LuminumOutput.friendlyOut(msg);
		
		input.handleInput("generic");
	}
	
	public static void friendlyOut(String output_text) {
		System.out.println(output_text);
	}
	
	public void systemOut(String output_text) {
		System.out.println("** SYSTEM ** > " + output_text);
	}
	
}
