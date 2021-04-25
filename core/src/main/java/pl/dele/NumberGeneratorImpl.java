package pl.dele;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NumberGeneratorImpl implements NumberGenerator {

    // == fields ==
    /**
     * Random generator
     */
    private final Random random = new Random();

    @Getter
    private final int maxNumber;
    @Getter
    private final int minNumber;

    // == constructors ==
    @Autowired
    public NumberGeneratorImpl(@MaxNumber int maxNumber, @MinNumber int minNumber) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }

    // == public methods ==
    /**
     * Generate next value
     * @return
     */
    @Override
    public int next() {
        return minNumber + random.nextInt(maxNumber - minNumber);
    }
}
