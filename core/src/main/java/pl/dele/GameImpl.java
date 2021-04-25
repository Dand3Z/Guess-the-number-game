package pl.dele;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Getter
@Component
public class GameImpl implements Game {

    // == fields
    @Getter(AccessLevel.NONE)
    private final NumberGenerator numberGenerator;

    // how many times the player can guess the number before the game is over
    private final int guessCount;

    // value of the randomly generated number for the player to guess
    private int number;
    // lower range from which the player searches for a number
    private int smallest;
    // upper range from which the player searches for a number
    private int biggest;
    // the number of remaining guesses
    private int remainingGuesses;
    private boolean validNumberRange = true;

    // the player's guess
    @Setter
    private int guess;

    // == constructors ==
    @Autowired
    GameImpl(NumberGenerator numberGenerator, @GuessCount int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }


    // == init ==
    /**
     * reset / restart game
     */
    @PostConstruct
    @Override
    public void reset() {
        smallest = numberGenerator.getMinNumber();
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
