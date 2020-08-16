public class Snake {
    int start;
    int end;
    public Snake(int start, int end) {
        if (start >= end) {
            throw new IllegalStateException("Snake End cannot be smaller than start [" + start + "] -> [" + end + "]");
        }
        this.start = start;
        this.end = end;
    }
}
