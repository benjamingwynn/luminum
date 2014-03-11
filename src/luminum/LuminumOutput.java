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
	
	public static void friendlyOut(String output_text) {
		System.out.println(output_text);
	}
	
	public void systemOut(String output_text) {
		System.out.println("** SYSTEM ** > " + output_text);
	}
	
}
