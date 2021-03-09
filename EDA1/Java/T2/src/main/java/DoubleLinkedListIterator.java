public class DoubleLinkedListIterator<T> implements Iterator<T>{
    public Node<T> current;

    public DoubleLinkedListIterator(Node<T> c) {
        current = c;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public boolean hasPrev() {
        return current != null;
    }

    @Override
    public T next() {
        T nextItem = current.getElemento();
        current = current.getNext();
        return nextItem;
    }

    @Override
    public T prev() {
        T prevItem = current.getElemento();
        current = current.getPrev();
        return prevItem;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}


