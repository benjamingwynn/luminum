package luminum;

import java.io.IOException;

public class LuminumMain {
	
	// Main Method
	public static void main(String[] args) throws IOException {
		// Load our TestAI script
		new LuminumAI().loadAIScript("testai");
		
		// Loop the following
		while (true) {
			//Print Next message
			new LuminumAI().nextMessage();
			//Input text
			new LuminumInput().handleInput("generic");
		}
	}
}
