public class Node<T> {
    T elemento;
    Node<T> next;
    Node<T> prev;

    public Node(T x){
        elemento=x;
        next=null;
        prev=null;
    }

    public Node(){
        this(null);
    }

    public Node(T x, Node<T> n, Node<T> p){
        elemento=x;
        next=n;
        prev=p;
    }

    public Node<T> getNext(){
        return next;
    }

    public Node<T> getPrev(){
        return prev;
    }

    public T getElemento(){
        return elemento;
    }

    public void setElemento(T x){
        this.elemento=x;
    }

    public void setNext(Node<T> n){
        next=n;
    }

    public void setPrev(Node<T> p){
        prev=p;
    }

    public String getStringElemento(){
        String x = String.valueOf(elemento);
        return x;
    }
}
