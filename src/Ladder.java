public class Ladder {
    int start;
    int end;
    public static String TYPE = "ladder";

    public Ladder(int start, int end) throws IllegalStateException {
        if (end <= start)
            throw new IllegalStateException("Ladder Start cannot be smaller than end [" + start + "] -> [" + end + "]");
        this.start = start;
        this.end = end;
    }
}
