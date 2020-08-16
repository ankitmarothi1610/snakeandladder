public class Ladder {
    int start;
    int end;

    public Ladder(int start, int end) throws IllegalStateException {
        if (start <= end)
            throw new IllegalStateException("Ladder Start cannot be smaller than end [" + start + "] -> [" + end + "]");
        this.start = start;
        this.end = end;
    }
}
