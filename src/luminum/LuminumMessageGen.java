package luminum;

import java.util.Random;

public class LuminumMessageGen {
	
	String randomMessage(String message_type) {
		Random ran = new Random();
		String message_return = getRandomMessage(ran.nextInt(3) + 0, message_type); // Random 0 through 3
		return message_return;
	}
	
	String message(String message_type) {
		if (message_type == "weather" ) { return "I'm not yet able to tell the weather. Try your nearest window."; }
		if (message_type == "okay" ) { return "Okay."; }
		if (message_type == "about" ) { return "I'm an AI coded with Luminium, an AI base created by Benjamin Gwynn."; }
		if (message_type == "joke_chicken_understand" ) { return "I don't know, why /did/ the chicken cross the road?"; }
		return "E: Unknown message_type called. (message)";
	}
	
	String getRandomMessage(int random_number, String message_type) {
		if ( message_type == "greeting" ) {
			String[] msg={"Hello", "Hi", "Holla", "Hey"}; // note: tables count from 0
			return msg[random_number];
		} else if (message_type == "askwho") {
			String[] msg={"Who are you?", "What's your name?", "What should I call you?", "What're you called?"};
			return msg[random_number];
		} else if (message_type == "laugh") {
			String[] msg={"Haha!", "lol", "Hahahaha!", "That joke had me in tears!"};
			return msg[random_number];
		} else if (message_type == "unknown") {
			String[] msg={"I don't understand.","Sorry, what?","What are you trying to say?","Huh?"};
			return msg[random_number];
		} else if (message_type == "maybe_you_can") {
			String[] msg={"Maybe... Maybe not.","Try it and find out.","Probably not, try it.","Probably I guess, try it out."};
			return msg[random_number];
		} else if (message_type == "cant_do_that") {
			String[] msg={"No.","I don't think I can.","Sorry, no.","Not today."};
			return msg[random_number];
		} else if (message_type == "dont_know") {
			String[] msg={"I don't know the answer to everything.","Who knows? Not me.","That's up to you to find out.","I don't know."};
			return msg[random_number];
		} else if (message_type == "dont_know_question") {
			String[] msg={"That's a strange question.","What kind of question is that?","Um... yes??","I don't know what you're trying to ask here."};
			return msg[random_number];
		} else if (message_type == "respond_unknown_accusation") {
			String[] msg={"Thanks... I guess.","Erm... I guess that's a good thing in it's own way.","I can live with that.","Are you trying to be mean?"};
			return msg[random_number];
		}
		return "E: Unknown message_type called. (random message)";
	}

}
