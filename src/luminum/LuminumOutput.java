package luminum;

public class LuminumOutput {
	
	void greetUser(String ai_name, String user_name) {
		
		// Method setup
		LuminumOutput output = new LuminumOutput();
		LuminumMessageGen msggen = new LuminumMessageGen();
		LuminumInput input = new LuminumInput();
		
		// Print hello message
		String hello = msggen.randomMessage("greeting");
		
		// If the user's name is undefined
		if (user_name != "Undefined") {
			output.friendlyOut(hello + ", " + user_name + ". I'm " + ai_name + ".");
			input.handleInput("generic");
		} else {
			output.friendlyOut(hello + ". I don't seem to know your name.");
			String whoareyou = msggen.randomMessage("askwho");
			output.friendlyOut(whoareyou);
			input.handleInput("name");
		}
	}
	
	void laughterOut() {
		LuminumOutput output = new LuminumOutput();
		LuminumMessageGen msggen = new LuminumMessageGen();
		LuminumInput input = new LuminumInput();

		String msg = msggen.randomMessage("laugh");
		output.friendlyOut(msg);
		
		input.handleInput("generic");
	}
	
	void friendlyOut(String output_text) {
		System.out.println(output_text);
	}
	
}
