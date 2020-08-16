public class Board {
    final static int SIZE = 100;
    private static Board instance;
    private int[] board;

    private Board() {
        System.out.println("Creating a instance of type board with size" + SIZE);
        board = new int[SIZE];
    }

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }
}
