package app;

//import com.sun.org.apache.xpath.internal.compiler.Keywords;

/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 * 		    Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie {
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */
	public String getGreeting() {
		return "Hello, let's talk.";
	}

	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement) {

		statement = statement.trim().toLowerCase();
		if (statement.length() == 0) return "Hey, where did you go? \n...kids?";
		String response = "";
		if (findKeyword(statement, "no") >= 0) {
			response = "Why so negative?";
		} else if (findKeyword(statement, "mother") >= 0 ||
			findKeyword(statement, "father") >= 0 ||
			findKeyword(statement, "sister") >= 0 ||
			findKeyword(statement, "brother") >= 0) {
			response = "Tell me more about your family.";
		} else if (findKeyword(statement, "dog") >= 0 ||
			findKeyword(statement, "cat") >= 0 ||
			findKeyword(statement, "lizard") >= 0 ||
			findKeyword(statement, "snake") >= 0) {
			response = "Tell me more about your pets you hecker.";
			// TODO: prevent repeat comment with a toggle
		} else if (findKeyword(statement, "Mr. Adiletta") >= 0 ||
			findKeyword(statement, "Mr. A") >= 0 ||
			findKeyword(statement, "M R dot Ah") >= 0 ||
			findKeyword(statement, "Dan") >= 0) {
			response = "I haven't heard from him since we went to work on Glave. That was years ago...";
			// TODO: prevent repeat comment with a toggle
		} else if (findKeyword(statement, "game") >= 0 ||
			findKeyword(statement, "gamer") >= 0 ||
			findKeyword(statement, "play") >= 0 ||
			findKeyword(statement, "controller") >= 0) {
			response = "Hey there Gamer.";
			// TODO: prevent repeat comment with a toggle
		} else if (findKeyword(statement, "So, ") >= 0 ||
			findKeyword(statement, "chunks") >= 0 ||
			findKeyword(statement, "huh?") >= 0) {
			response = "So, chunks huh?";
			// TODO: prevent repeat comment with a toggle
		} else if (findKeyword(statement, "Fortnite") >= 0 ||
			findKeyword(statement, "building") >= 0 ||
			findKeyword(statement, "battle royale") >= 0) {
			response = "Fellow gamers, Ninja needs your help. But the only way to help is to get your mom's credit card. Once you do that, all you need to do is tell me the long number on the front, the name, expieration date, and the 3-digit number on the back. Once that's done, you will have helped Ninja and served your country.";
		}

		// Responses which require transformations
		else if (findKeyword(statement, "I want to", 0) >= 0) {
			response = transformIWantToStatement(statement);
		} 
		else if (findKeyword(statement, "I want", 0) >= 0) {
			response = transformIWantStatement(statement);
		}
		else {
			// Look for a two word (you <something> me)
			// pattern
			int psn = findKeyword(statement, "you", 0);

			if (psn >= 0 &&
				findKeyword(statement, "me", psn) >= 0) {
				response = transformYouMeStatement(statement);
				}
			else if(psn >= 2 && findKeyword(statement, "I") >= 0){
				response = transformIBlankYouStatement(statement);
			} else {
				response = getRandomResponse();
			}
		}

		return response;
	}

	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse() {
		final int NUMBER_OF_RESPONSES = 7;
		double r = Math.random();
		int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
		String response = "";

		if (whichResponse == 0) {
			response = "Interesting, tell me more.";
		} else if (whichResponse == 1) {
			response = "Hmmm.";
		} else if (whichResponse == 2) {
			response = "Do you really think so?";
		} else if (whichResponse == 3) {
			response = "You don't say.";
		} else if (whichResponse == 4) {
			response = "Amazing.";
		} else if (whichResponse == 5) {
			response = "If I knew more, I'd be the happiest thing alive.";
		} else if (whichResponse == 6) {
			response = "Zoo wee momma!";
		}

		return response;
	}

	/**
	 * Search for one word in phrase. The search is not case
	 * sensitive. This method will check that the given goal
	 * is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no").
	 *
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @param startPos the character of the string to begin the search at
	 * @return the index of the first occurrence of goal in
	 *         statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal,
		int startPos) {
		String phrase = statement.trim().toLowerCase();
		goal = goal.toLowerCase();

		// The only change to incorporate the startPos is in
		// the line below
		int psn = phrase.indexOf(goal, startPos);

		// Refinement--make sure the goal isn't part of a
		// word
		while (psn >= 0) {
			// Find the string of length 1 before and after
			// the word
			String before = " ", after = " ";
			if (psn > 0) {
				before = phrase.substring(psn - 1, psn);
			}
			if (psn + goal.length() < phrase.length()) {
				after = phrase.substring(
					psn + goal.length(),
					psn + goal.length() + 1);
			}

			// If before and after aren't letters, we've
			// found the word
			if (((before.compareTo("a") < 0) || (before
					.compareTo("z") > 0)) // before is not a
				// letter
				&&
				((after.compareTo("a") < 0) || (after
					.compareTo("z") > 0))) {
				return psn;
			}

			// The last position didn't work, so let's find
			// the next, if there is one.
			psn = phrase.indexOf(goal, psn + 1);

		}

		return -1;
	}

	/**
	 * Search for one word in phrase. The search is not case
	 * sensitive. This method will check that the given goal
	 * is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no"). The search
	 * begins at the beginning of the string.
	 * 
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @return the index of the first occurrence of goal in
	 *         statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal) {
		return findKeyword(statement, goal, 0);
	}

	/**
	 * Take a statement with "I want to <something>." and transform it into 
	 * "What would it mean to <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement) {
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
			.length() - 1);
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement
				.length() - 1);
		}
		int psn = findKeyword(statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "What would it mean to " + restOfStatement + "?";
	}

	private String transformIWantStatement(String statement) {
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
			.length() - 1);
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement
				.length() - 1);
		}
		int psn = findKeyword(statement, "I want", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "Would you really love it if you had " + restOfStatement + " right now?";
	}

	private String transformIBlankYouStatement(String statement) {
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
			.length() - 1);
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement
				.length() - 1);
		}

		int psnOfI = findKeyword(statement, "I", 0);
		int psnOfYou = findKeyword(statement, "you", psnOfI + 2);

		String restOfStatement = statement.substring(psnOfI + 2, psnOfYou).trim();
		return "Why do you " + restOfStatement + " me?";
	}



	/**
	 * Take a statement with "you <something> me" and transform it into 
	 * "What makes you think that I <something> you?"
	 * @param statement the user statement, assumed to contain "you" followed by "me"
	 * @return the transformed statement
	 */
	private String transformYouMeStatement(String statement) {
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
			.length() - 1);
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement
				.length() - 1);
		}

		int psnOfYou = findKeyword(statement, "you", 0);
		int psnOfMe = findKeyword(statement, "me", psnOfYou + 3);

		String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
		return "What makes you think that I " + restOfStatement + " you?";
	}



} // closes Magpie class