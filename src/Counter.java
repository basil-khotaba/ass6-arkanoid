/**
 * Author: Basil Khotaba.
 * Counter class
 */
public class Counter {
    private int currentCount = 0;

    /**
     * adds number to current count.
     *
     * @param number : the number to be added to the current count.
     */
    public void increase(int number) {
        this.currentCount += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number : the number to be subtract from the current count.
     */
    public void decrease(int number) {
        this.currentCount -= number;
    }

    /**
     * returns the current count.
     *
     * @return : the current count.
     */

    public int getValue() {
        return this.currentCount;
    }
}