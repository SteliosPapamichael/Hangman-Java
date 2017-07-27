public class Hangman {

  public static void main(String[] args) {
    // if the user didn't give an answer for the program to use, we exit
    if (args.length == 0 || args.length < 2) {
      System.out.println("Usage: java Hangman <answer> <username>");
      System.out.println("An answer and a username are required");
      System.exit(1);
    }
    
    Game game = new Game(args[0]);
    Prompter prompter = new Prompter(game , args[1]);
    while (game.getRemainingTries() > 0 && !game.isWon()) {
      prompter.displayProgress();
      prompter.promptForGuess();
    } 
    prompter.displayOutcome();
  }
}