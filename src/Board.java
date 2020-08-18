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
    private String SNAKE = "snake";
    private String LADDER = "ladder";

    private Board() {
        System.out.println("Creating a instance of type board with size" + SIZE);
        snakes = new HashMap<>(MAX_SHORTCUTS);
        ladders = new HashMap<>(MAX_SHORTCUTS);
        addShortCut(SNAKE);
        addShortCut(LADDER);
        dice = new Dice();
        currentPosition = 0;
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
        if (start >= SIZE || end >= SIZE || start <=0 || end <= 0) {
            System.out.println("Start and end should be > " + 0 + " and < " + (SIZE - 1));
            return false;
        }

        if (((start - 1) / 10) == ((end - 1) / 10)) {
            System.out.println("Start and end cannot be in the same row of the board");
            return false;
        }

        if (type.equalsIgnoreCase(SNAKE)) {
           if (start < end) {
               System.out.println("Start cannot be smaller than end for Snakes");
               return false;
           }
        } else if (type.equalsIgnoreCase(LADDER)) {
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
            return false;
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
            if (type.equalsIgnoreCase(SNAKE)) {
                snakes.put(start, end);
            } else if (type.equalsIgnoreCase(LADDER)) {
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

    public void shouldRepeatGame() {
        System.out.println("\n\n -------------------GAME OVER-------------------\n\n");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Want to play again?");
        String input = scanner.nextLine();
        if (isInputYes(input.strip())) {
            currentPosition = 0;
            dice.setThrowCount(0);
            System.out.println("\n\n -------------------NEW GAME-------------------\n\n");
            play();
        }
    }

    public void play() {
        while(currentPosition != SIZE) {
            int number = 0;
            if (currentPosition == 0) {
                number = dice.rollDice();
                System.out.println("Rolled: " + number);
                if (number == 1 || number == 6) {
                    System.out.println("Game started");
                    currentPosition = 1;
                } else {
                    System.out.println("Need 1 or 6 to start the game");
                }
            }

            number = dice.roll();
            // If current dice throw is greater than board size, roll till
            if (number + currentPosition > SIZE) {
                System.out.println("Looks like you are just there, you need to roll: " + (SIZE - currentPosition));
            } else {
                currentPosition = currentPosition + number;
                // Check if a ladder or snake is reached
                if (ladders.containsKey(currentPosition)) {
                    System.out.println("Woah! You entered a ladder at position " + currentPosition);
                    currentPosition = ladders.get(currentPosition);
                }
                // Check if a snake is reached
                if (snakes.containsKey(currentPosition)) {
                    System.out.println("Oops! Snakebite, at position " + currentPosition);
                    currentPosition = snakes.get(currentPosition);
                }
            }
            System.out.println("CurrentPosition: " + currentPosition + "\n");
        }
        System.out.println("\n Game ended after " + dice.getThrowCount() + " throws");
        shouldRepeatGame();
    }
}
