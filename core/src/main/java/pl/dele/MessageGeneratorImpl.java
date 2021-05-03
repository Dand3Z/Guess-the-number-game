package pl.dele;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator {

    // == constants ==
    public static final String MAIN_MESSAGE = "game.main.message";
    public static final String WIN = "game.win";
    public static final String LOSE = "game.lose";
    public static final String INVALID_RANGE = "game.invalid.range";
    public static final String FIRST_GUESS = "game.first.guess";
    public static final String HIGHER = "game.higher";
    public static final String LOWER = "game.lower";
    public static final String REMAINING = "game.remaining";


    // == fields ==
    private final Game game;
    private final MessageSource messageSource;

    // == constructors ==
    public MessageGeneratorImpl(Game game, MessageSource messageSource) {
        this.game = game;
        this.messageSource = messageSource;
    }

    // == init ==
    @PostConstruct
    public void init(){
        log.info("Game = {}", game);
    }
    // == public methods ==
    @Override
    public String getMainMessage() {
        return getMessage(MAIN_MESSAGE, game.getSmallest(), game.getBiggest());
    }

    @Override
    public String getResultMessage() {
        if(game.isGameWon()){
            return getMessage(WIN, game.getNumber());
        } else if (game.isGameLost()) {
            return getMessage(LOSE, game.getNumber());
        } else if (!game.isValidNumberRange()) {
            return getMessage(INVALID_RANGE);
        } else if(game.getRemainingGuesses() == game.getGuessCount()){
            return getMessage(FIRST_GUESS);
        } else {
            String direction = getMessage(LOWER);

            if(game.getGuess() < game.getNumber()){
                direction = getMessage(HIGHER);
            }

            return getMessage(REMAINING, direction, game.getRemainingGuesses());
        }
    }

    // == private methods ==
    /*
        code - the property key e.g. game.main.message
        args - variable number of arguments
        getLocale() - specific locale
     */
    private String getMessage(String code, Object... args){
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
