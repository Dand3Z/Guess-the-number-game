package pl.dele;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class GameImpl implements Game {

    // == constants ==
    private static final Logger log = LoggerFactory.getLogger(Game.class);

    // == fields
    @Autowired
    private NumberGenerator numberGenerator;
    // how many times the player can guess the number before the game is over
    private int guessCount = 10;
    // value of the randomly generated number for the player to guess
    private int number;
    // the player's guess
    private int guess;
    // lower range from which the player searches for a number
    private int smallest;
    // upper range from which the player searches for a number
    private int biggest;
    // the number of remaining guesses
    private int remainingGuesses;
    private boolean validNumberRange = true;

    // == init ==
    /**
     * reset / restart game
     */
    @PostConstruct
    @Override
    public void reset() {
        // set the default values
        smallest = 0;
        guess = 0;
        remainingGuesses = guessCount;
        biggest = numberGenerator.getMaxNumber();

        // drew a new number
        number = numberGenerator.next();
        // print that number in our log
        log.debug("the number is {}", number);
    }

    @PreDestroy
    public void preDestroy(){
        log.info("in Game preDestroy()");
    }

    // == public methods ==
    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getGuess() {
        return guess;
    }

    @Override
    public void setGuess(int guess) {
        this.guess = guess;
    }

    @Override
    public int getSmallest() {
        return smallest;
    }

    @Override
    public int getBiggest() {
        return biggest;
    }

    @Override
    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    @Override
    public void check() {
        checkValidNumberRange();

        // if valid change range
        if(validNumberRange) {
            if(guess > number){
                biggest = guess - 1;
            } else if (guess < number){
                smallest = guess + 1;
            }
        }
        --remainingGuesses;
    }

    @Override
    public boolean isValidNumberRange() {
        return validNumberRange;
    }

    // the game is won when the number of guesses is equal to a number
    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses < 1;
    }

    // == private methods ==
    private void checkValidNumberRange(){
        validNumberRange = (guess >= smallest) && (guess <= biggest);
    }
}
