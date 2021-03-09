public interface Iterator<T> {
    boolean hasNext();
    boolean hasPrev();
    T next();
    T prev();
    void remove();
}

