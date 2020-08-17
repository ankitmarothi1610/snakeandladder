import java.util.Random;

public class Dice {
    private final int MAX_LIMIT = 6;
    Random random;

    public Dice() {
        random = new Random();
    }

    int rollDice() {
        return random.nextInt(MAX_LIMIT) + 1;
    }

    int roll() {
        int number = rollDice();
        if (number == MAX_LIMIT) {
            System.out.println("Roll produced number " + MAX_LIMIT + "! Rolling again");
            number = number + rollDice();
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
