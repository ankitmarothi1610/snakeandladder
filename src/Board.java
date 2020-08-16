import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {
    private final static int SIZE = 100;
    private final static int MAX_SHORTCUTS = 10;
    private final String _YES_STR = "[Y/yes - Yes, any other string for - No]";
    private static Board instance;
    private List<Ladder> ladders;
    private List<Snake> snakes;
    private Dice dice;

    private Board() {
        System.out.println("Creating a instance of type board with size" + SIZE);
        snakes = new ArrayList<>(MAX_SHORTCUTS);
        ladders = new ArrayList<>(MAX_SHORTCUTS);
        addShortCut(Snake.TYPE);
        addShortCut(Ladder.TYPE);
        dice = new Dice();
    }

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    private boolean isLimitCrossed() {
        if (snakes != null && snakes.size() >= MAX_SHORTCUTS) {
            System.out.println("Max number of snakes already added");
            return true;
        }
        return false;
    }

    private boolean isInputYes(String input) {
        return ("y".equalsIgnoreCase(input) || "yes".equalsIgnoreCase(input));
    }

    private void readStartAndEnd(Scanner scanner, String type) {
        int start;
        int end;

        System.out.println("Enter start value: ");
        start = scanner.nextInt();

        System.out.println("Enter end value: ");
        end = scanner.nextInt();

        if (type.equalsIgnoreCase(Snake.TYPE)) {
            Snake snake = new Snake(start, end);
            snakes.add(snake);
        } else if (type.equalsIgnoreCase(Ladder.TYPE)) {
            Ladder ladder = new Ladder(start, end);
            ladders.add(ladder);
        } else {
            throw new IllegalArgumentException("Type not found" + type);
        }
    }

    private void addShortCut(String type) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to add " + type + " (Max 10) " + _YES_STR);
        String input = scanner.nextLine();
        while (isInputYes(input.strip()) && !isLimitCrossed()) {
            try {
                readStartAndEnd(scanner, type);
                System.out.println("Want to add more? " + _YES_STR);
                input = scanner.next();
            } catch (IllegalStateException ise) {
                ise.printStackTrace();
            }
        }
    }

    private void play() {

    }
}
