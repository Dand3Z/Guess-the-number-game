package pl.dele;

import java.util.Random;

public class NumberGeneratorImpl implements NumberGenerator {

    // == fields ==
    /**
     * Random generator
     */
    private final Random random = new Random();

    private int maxNumber = 100;

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
