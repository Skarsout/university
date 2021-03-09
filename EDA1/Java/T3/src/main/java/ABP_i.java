public interface ABP_i<E extends Comparable<? super E>>{
    public boolean contains(E x);
    public E findMax();
    public E findMin();
    public void insere(E x);
    public void remove(E x);
}
