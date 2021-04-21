package pl.dele;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class MessageGeneratorImpl implements MessageGenerator {

    // == constants ==
    private static final Logger log = LoggerFactory.getLogger(MessageGeneratorImpl.class);

    // == fields ==
    @Autowired
    private Game type;

    private int guessCount = 10;


    @PostConstruct
    public void init(){
        log.info("Game = {}",type);
    }
    // == public methods ==
    @Override
    public String getMainMessage() {
        return "getMainMessage() called";
    }

    @Override
    public String getResultMessage() {
        return "getResultMessage() called";
    }
}
