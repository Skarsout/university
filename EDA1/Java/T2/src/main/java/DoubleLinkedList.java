public class DoubleLinkedList<T> implements Lista<T> {
    Node<T> header;
    Node<T> footer;
    int theSize;

    public Iterator<T> iterator() {
        return new DoubleLinkedListIterator<T>(header.getNext());
    }

    public DoubleLinkedList(){
        header = new Node<T>();
        footer = new Node<T>();
        header.setNext(footer);
        footer.setPrev(header);
        theSize = 0;
    }

    @Override
    public void add(T x) {
        Node<T> newNode = new Node<T>(x, null, null);

        newNode.setPrev(footer.getPrev());
        footer.setPrev(newNode);
        newNode.setNext(footer);
        newNode.getPrev().setNext(newNode);
        theSize++;
    }

    @Override
    public void add(int i, T x) {
        Node<T> newNode = new Node<T>(x);

        if(i == theSize + 1){
            add(x);
        }else{
            if(i > theSize){
                System.out.println("Não pode inserir nessa posição");
            }else{
                Node<T> nodeNext = getPos(i);
                Node<T> nodePrev = getPos(i).getPrev();

                nodePrev.setNext(newNode);
                newNode.setPrev(nodePrev);
                newNode.setNext(nodeNext);
                nodeNext.setPrev(newNode);
                theSize++;
            }
        }
    }

    public Node getPos(int pos){
        int counter = 0;
        Node<T> current = header;
        if(pos == 0) {
            System.out.println("Cant Insert on header.");
        }else{
            while(counter != pos) {
                current = current.getNext();
                counter++;
            }
        }
        return current;
    }

    @Override
    public void set(int i, T x) {
        getNode(i).setElemento(x);
    }

    void remove(Node<T> prev){
        prev.setNext(prev.getNext().getNext());
        theSize--;
    }

    @Override
    public T remove(int i) {
        remove(getNode(i-1));
        return getNode(i-1).getElemento();
    }

    @Override
    public void remove(T x) {
        try{
            remove(findPrev(x));
        }
        catch(java.util.NoSuchElementException e){}
    }

    Node<T> findPrev(T x) {
        Node<T> prev = header;
        int s = size();
        int counter = 0;
        String v = null;
        v = prev.next.getStringElemento();

        while (counter < s)

            if (v.equals(x))
                return prev;
            else {
                prev = prev.getNext();
                counter++;
                v = prev.next.getStringElemento();
            }
        throw new java.util.NoSuchElementException( );
    }

    @Override
    public void clear() {
        header=null;
        footer=null;
    }

    @Override
    public T get(int i) {
        Node current = header;
        int p = 0;
        while (p != i){
            current = current.next;
            p++;
        }
        return (T) current.getElemento();
    }

    @Override
    public int size() {
        return theSize;
    }

    @Override
    public String toString(){
        Node<T> temp = header.getNext();
        String lista = "";
        while (temp!=footer){
            lista +=temp.getElemento();
            temp = temp.getNext();
        }
        return lista;
    }

    Node<T> getNode(int i){
        int index = -1;
        Node<T> s = header;
        while(index++ < i)
            s = s.getNext();
        return s;
    }
}
