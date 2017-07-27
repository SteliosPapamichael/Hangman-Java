import java.util.Scanner;

public class Prompter { // this class will handle all the I/O
  private Game game;
  private String userName;
  
  public Prompter(Game game , String userName) {
    this.game = game; 
    this.userName = userName;
  }
  
  public boolean promptForGuess() {
    Scanner scanner = new Scanner(System.in); // scanner object instanciation
    boolean isHit = false; // declaring it in the method's scope instead of the try-catch block's scope to avoid compiling errors
    boolean isAcceptable = false;  // using a boolean value to keep track of state in this method
    
    do {
      System.out.print("Enter a letter:   ");
      String guessInput = scanner.nextLine(); // reading the guess -which is a string not a char-
      
      // when calling the applyGuess method, we want to handle any exception that might be thrown
      try {
        isHit = game.applyGuess(guessInput); // calling the Game's applyGuess(String) method to check if the guess is a hit or a miss
        isAcceptable = true; // this won't run if the above line thrown an exception which leads to a new iteration
      } catch(IllegalArgumentException iae) {
        System.out.printf("%s. Please try again \n" , iae.getMessage());
      }
    } while(! isAcceptable);
    return isHit;
  }
  
  public void displayProgress() {
    // %d stands for decimal and represents a number in the string formatter method
    System.out.printf("%s, you have %d tries left to solve:  %s\n", userName , game.getRemainingTries(), game.getCurrentProgress()); 
  }
  
  public void displayOutcome() {
    if (game.isWon()) {
       System.out.printf("Congratulations %s, you won with %d tries remaining!\n", userName , game.getRemainingTries());
    } else {
       System.out.printf("Sorry %s, you lost! The answer was: %s\n" , userName , game.getAnswer()); 
    }
  }
  
}