package pl.dele.console;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.dele.Game;
import pl.dele.MessageGenerator;

import java.util.Scanner;

@Slf4j
@AllArgsConstructor
@Component
public class ConsoleNumberGuess{

    // == fields ==
    private final Game game;
    private final MessageGenerator messageGenerator;

    // == events ==
    @EventListener(ContextRefreshedEvent.class)
    public void start() {
        log.info("start() --> Container ready to use.");

        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println(messageGenerator.getMainMessage());
            System.out.println(messageGenerator.getResultMessage());

            // get input from the user
            while(!scanner.hasNextInt()){
                scanner.nextLine();
            }
            int guess = scanner.nextInt();
            scanner.nextLine();

            game.setGuess(guess);
            game.check();

            // result
            if(game.isGameWon() || game.isGameLost()){
                System.out.println(messageGenerator.getResultMessage());
                System.out.println("Play again y/n?");

                String playAgainString = scanner.nextLine().trim();
                if(!playAgainString.equalsIgnoreCase("y")){
                    scanner.close();
                    break;
                }
                game.reset();
            }
        }
    }
}
