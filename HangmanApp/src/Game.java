public class Game { // this class will handle the game logic
  public static final int MAX_MISSES = 7;
  private String answer;
  private String hits;
  private String misses;
  
  // Constructor
  public Game(String answer) {
    this.answer = answer.toLowerCase(); 
    hits = "";
    misses = "";
  }
  
  public String getAnswer() {
    return answer; 
  }
  
  private char normalizeGuess(char letter) {
      // if the "char" that was passed in isn't an actual char(eg. if it's a number), throw an exception
    if ( ! Character.isLetter(letter)) {
      throw new IllegalArgumentException("A letter is required!"); 
    }
    // otherwise normalize that value
    letter = Character.toLowerCase(letter);
    // checking if the guess has already been made
    if (misses.indexOf(letter) != -1 || hits.indexOf(letter) != -1) {
       throw new IllegalArgumentException(letter + " has already been guessed!");
    }
    return letter;
  }
  
  public boolean applyGuess(String letters) {
    if(letters.length() == 0) {
      throw new IllegalArgumentException("No letter found"); 
    }
    return applyGuess(letters.charAt(0));
  }
  
  // check if the guess is a hit or not
  public boolean applyGuess(char letter) {
    letter = normalizeGuess(letter);
    boolean isHit = answer.indexOf(letter) != -1; //  if the index is <> -1 , then it's a hit(true), otherwise it's a miss(false)
    if (isHit) {
      hits += letter; 
    } else {
      misses += letter;
    }
    return isHit;
  }
  
  public int getRemainingTries() {
    return MAX_MISSES - misses.length(); 
  }
  
  public String getCurrentProgress() {
    String progress = "";
    // enchanced for loop
    for (char letter : answer.toCharArray()) {
      char display = '_'; // default value-if the letter hasn't been guessed- !!! be careful not to use "" instead of ''
      if (hits.indexOf(letter) != -1) { // if "this" letter has been guessed then : display = letter;
        display = letter; 
      }
      progress += display; 
    }
    return progress;
  }
  
  public boolean isWon() { // getter
     return getCurrentProgress().indexOf('_') == -1; // returns a string so we check if there's an empty space
  }
  
}