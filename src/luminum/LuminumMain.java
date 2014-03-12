package luminum;

import java.io.IOException;

public class LuminumMain {
	
	// Main Method
	public static void main(String[] args) throws IOException {
		// Load our TestAI script
		new LuminumAI().loadAIScript("testai");
		
		// Set as 0
		LuminumInterface.sent_message_count = 0;
		
		// Loop the following
		while (true) {
			new LuminumInterface().nextMessage();
		}
	}
}
