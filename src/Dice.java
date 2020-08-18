import java.util.Random;

public class Dice {
    private final int MAX_LIMIT = 6;
    private Random random;
    private int rolls;
    public Dice() {
        random = new Random();
        rolls = 0;
    }

    public int getThrowCount() { return rolls; }

    public void setThrowCount(int count) { rolls = count; }
    public int rollDice() {
        rolls++;
        return random.nextInt(MAX_LIMIT) + 1;
    }

    public int roll() {
        int number = rollDice();
        System.out.println("Rolled: " + number);
        if (number == MAX_LIMIT) {
            System.out.println("Roll produced number " + MAX_LIMIT + "! Rolling again");
            number = number + roll();
            if (number >= 3 * MAX_LIMIT) {
                // TODO: Return on third throw of 6 instead of a 4th throw
                System.out.println("Three " + MAX_LIMIT + " rolled, current throw will be counted as 0");
                number = 0;
            }
        }
        return number;
    }

    public static void main(String[] args) {
        Dice dice = new Dice();
        while(true) {
            try {
                System.out.println("This roll produced a total of " + dice.roll());
                Thread.sleep(500);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
