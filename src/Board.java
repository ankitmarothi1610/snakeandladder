import java.util.*;

public class Board {
    private final static int SIZE = 100;
    private final static int MAX_SHORTCUTS = 10;
    private final String _YES_STR = "[Y/yes - Yes, any other string for - No]";
    private static Board instance;
    private Map<Integer, Integer> ladders;
    private Map<Integer, Integer> snakes;
    private Dice dice;
    int currentPosition;

    private Board() {
        System.out.println("Creating a instance of type board with size" + SIZE);
        snakes = new HashMap<>(MAX_SHORTCUTS);
        ladders = new HashMap<>(MAX_SHORTCUTS);
        addShortCut(Snake.TYPE);
        addShortCut(Ladder.TYPE);
        dice = new Dice();
        currentPosition = 1;
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

    private boolean isValidShortut(String type, int start, int end) {
        if (start == 100 || end == 100) {
            System.out.println("Neither start or end can be the final position");
        }

        if ((start % 10) == (end % 10)) {
            System.out.println("Start and end cannot be in the same row of the board");
        }

        if (type.equalsIgnoreCase(Snake.TYPE)) {
           if (start < end) {
               System.out.println("Start cannot be smaller than end for Snakes");
               return false;
           }
        } else if (type.equalsIgnoreCase(Ladder.TYPE)) {
            if (start == 1) {
                System.out.println("Start of the board cannot contain a ladder");
            }
            if (end < start) {
                System.out.println("End cannot be smaller than start for Ladders");
                return false;
            }
        }
        if (snakes.containsKey(start) || ladders.containsKey(end)) {
            System.out.println("Either a snake or a ladder for the position exists");
        }
        return true;
    }

    private void readStartAndEnd(Scanner scanner, String type) {
        int start;
        int end;

        System.out.println("Enter start value: ");
        start = scanner.nextInt();

        System.out.println("Enter end value: ");
        end = scanner.nextInt();

        if (isValidShortut(type, start, end)) {
            if (type.equalsIgnoreCase(Snake.TYPE)) {
                snakes.put(start, end);
            } else if (type.equalsIgnoreCase(Ladder.TYPE)) {
                ladders.put(start, end);
            } else {
                throw new IllegalArgumentException("Type not found" + type);
            }
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

    public void play() {
        while(currentPosition == SIZE) {
            int number = dice.roll();
            if (currentPosition == 1) {
                if (number != 1 || number != 6) {
                    System.out.println("Need 1 or 6 to start the game");
                }
            }
            // If current dice throw is greater than board size, roll till
            if (number + currentPosition > SIZE) {
                System.out.println("Looks like you are just there, you need to roll: " + (SIZE - currentPosition));
            } else {
                currentPosition = currentPosition + number;
                // Check if a ladder or snake is reached
                if (ladders.containsKey(currentPosition)) {
                    currentPosition = ladders.get(currentPosition);
                }
                // Check if a snake is reached
                if (snakes.containsKey(currentPosition)) {
                    currentPosition = snakes.get(currentPosition);
                }
            }
        }
    }
}
