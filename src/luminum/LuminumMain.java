package luminum;

import java.io.IOException;

public class LuminumMain {
	
	// Main Method
	public static void main(String[] args) throws IOException {
		// Load our TestAI script
		new LuminumAI().loadAIScript("testai");
		
		System.out.println(LuminumAI.q_usr_ans[0][0]);
		
		// set as 0
		LuminumOutput.sent_message_count = 0;
		
		// Loop the following
		while (true) {
			new LuminumOutput().nextMessage();
		}
	}
}
