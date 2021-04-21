package pl.dele.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.dele.GuessCount;
import pl.dele.MaxNumber;

@Configuration
public class GameConfig {

    // == fields ==
    private int maxNumber = 25;

    private int guessCount = 8;

    // == bean methods ==
    @Bean
    @MaxNumber
    public int maxNumber(){
        return maxNumber;
    }

    @Bean
    @GuessCount
    public int guessCount(){
        return guessCount;
    }
}
