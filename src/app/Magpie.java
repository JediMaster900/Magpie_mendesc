package app;

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
public class Magpie
{
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */
	public String getGreeting()
	{
		return "Hello, let's talk.";
	}
	
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{

		statement = statement.trim().toLowerCase();
		if(statement.length() == 0) return "Hey, where did you go? \n...kids?";
		String response = "";
		if (statement.indexOf("no") >= 0)
		{
			response = "Why so negative?";
		}
		else if (statement.indexOf("mother") >= 0
				|| statement.indexOf("father") >= 0
				|| statement.indexOf("sister") >= 0
				|| statement.indexOf("brother") >= 0)
		{
			response = "Tell me more about your family.";
		}
		else if (statement.indexOf("dog") >= 0
				|| statement.indexOf("cat") >= 0
				|| statement.indexOf("lizard") >= 0
				|| statement.indexOf("snake") >= 0)
		{
			response = "Tell me more about your pets you hecker.";
			// TODO: prevent repeat comment with a toggle
		}
		else if (statement.indexOf("Mr. Adiletta") >= 0
				|| statement.indexOf("Mr. A") >= 0
				|| statement.indexOf("M R dot Ah") >= 0
				|| statement.indexOf("Dan") >= 0)
		{
			response = "I haven't heard from him since we went to work on Glave. That was years ago...";
			// TODO: prevent repeat comment with a toggle
		}
		else if (statement.indexOf("game") >= 0
				|| statement.indexOf("gamer") >= 0
				|| statement.indexOf("play") >= 0
				|| statement.indexOf("controller") >= 0)
		{
			response = "Hey there Gamer.";
			// TODO: prevent repeat comment with a toggle
		}
		else if (statement.indexOf("So, ") >= 0
				|| statement.indexOf("chunks") >= 0
				|| statement.indexOf("huh?") >= 0)
		{
			response = "So, chunks huh?";
			// TODO: prevent repeat comment with a toggle
		}
		else if (statement.indexOf("Fortnite") >= 0
				|| statement.indexOf("building") >= 0
				|| statement.indexOf("battle royale") >= 0)
		{
			response = "Fellow gamers, Ninja needs your help. But the only way to help is to get your mom's credit card. Once you do that, all you need to do is tell me the long number on the front, the name, expieration date, and the 3-digit number on the back. Once that's done, you will have helped Ninja and served your country.";
		}
		else
		{
			response = getRandomResponse();
		}
		return response;
	}
	
	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse()
	{
		final int NUMBER_OF_RESPONSES = 7;
		double r = Math.random();
		int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
		String response = "";
		
		if (whichResponse == 0)
		{
			response = "Interesting, tell me more.";
		}
		else if (whichResponse == 1)
		{
			response = "Hmmm.";
		}
		else if (whichResponse == 2)
		{
			response = "Do you really think so?";
		}
		else if (whichResponse == 3)
		{
			response = "You don't say.";
		}
		else if (whichResponse == 4)
		{
			response = "Amazing.";
		}
		else if (whichResponse == 5)
		{
			response = "If I knew more, I'd be the happiest thing alive.";
		}
		else if (whichResponse == 6)
		{
			response = "Zoo wee momma!";
		}
		
		return response;
	}
}
