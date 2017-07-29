public class Hangman {
	
	private static boolean answer = false; // class variables must be declared outside of every function, including  main() .
	
  public static void main(String[] args) {
    // if the user didn't give an answer for the program to use, we exit
    if (args.length == 0) {
      System.out.println("\nUsage: java Hangman <username>");
      System.out.println("A username is required");
      System.exit(1);
    }
    
    do {
        Game game = new Game(Prompter.promptForNewAnswer(args[0]));
        Prompter prompter = new Prompter(game , args[0]);
    	do {
    		  System.out.printf("\nThe current high-score is: %d\n", game.highScore);
              prompter.displayProgress();
              prompter.promptForGuess();
    	} while (game.getRemainingTries() > 0 && !game.isWon());
            prompter.displayOutcome(args[0]);
            try {
                answer = prompter.wantToReplay();
            } catch (IllegalArgumentException iae) {
            	System.out.println("\nExiting the program due to: " + iae.getMessage());
            }
    } while (answer);
    Prompter.displayCredits();
 }
  
}