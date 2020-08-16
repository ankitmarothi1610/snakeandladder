public class Snake {
    int start;
    int end;
    public static final String TYPE = "SNAKE";

    public Snake(int start, int end) {
        if (end >= start)
            throw new IllegalStateException("Snake End cannot be smaller than start [" + start + "] -> [" + end + "]");
        this.start = start;
        this.end = end;
    }
}
