package luminum;

public class LuminumAI {
	
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
