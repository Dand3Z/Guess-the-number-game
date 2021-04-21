package pl.dele;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class NumberGeneratorImpl implements NumberGenerator {

    // == fields ==
    /**
     * Random generator
     */
    private final Random random = new Random();

    @Autowired
    @MaxNumber
    private int maxNumber;

    // == public methods ==
    /**
     * Generate next value
     * @return
     */
    @Override
    public int next() {
        return random.nextInt(maxNumber);
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }
}
