public class ABP<E extends Comparable<? super E>> implements Iterable<E>, ABP_i<E> {
    NoABP<E> raiz;

    public boolean isEmpty(){
        return raiz.elemento == null;
    }


    public E findMin(){
        if (isEmpty()){
            return null;
        }
        return findMin(raiz);
    }

    private E findMin(NoABP<E> n){
        if(n.esq==null){
            return  n.elemento;
        }else{
            return findMin(n.esq);
        }
    }

    public E findMax(){
        if (isEmpty()){
            return null;
        }
        return findMax(raiz);
    }

    private E findMax(NoABP<E> n){
        if(n.dir==null){
            return  n.elemento;
        }else{
            return findMin(n.dir);
        }
    }

    public boolean contains(E x){
        return contains(x, raiz);
    }

    private boolean contains(E x, NoABP<E> n){
        if(n==null){
            return false;
        }else{
            if(n.elemento.compareTo(x)<0){
                return contains(x,n.dir);
            }else if(n.elemento.compareTo(x)>0) {
                return contains(x, n.esq);
            }
            else{
                return true;
            }
        }
    }

    public void insere(E x){
        raiz=insere(x, raiz);
    }

    private NoABP<E> insere(E x, NoABP<E> n){
        if(n==null){
            n=new NoABP<E>(x, null, null);
        }else if((n.getElemento()).compareTo(x)>0){
            n.setEsq(insere(x, n.getNodeEsq()));
        }else if((n.getElemento()).compareTo(x)<0){
            n.setDir(insere(x, n.getNodeDir()));
        }
        return n;
    }

    public void remove(E x){
        raiz = remove(x, raiz);
    }

    private NoABP<E> remove(E x, NoABP<E> n){
        if(n==null){
            return n;
        }

        if(n.elemento.compareTo(x)<0){
            n.dir=remove(x, n.dir);
        }else if(n.elemento.compareTo(x)>0){
            n.esq=remove(x, n.esq);
        }else if(n.esq!=null && n.dir!=null){
            E min = findMin(n.dir);
            n.elemento = min;
            n.dir=remove(min,n.dir);
        }else if(n.esq==null){
            n=n.dir;
        }else{
            n=n.esq;
        }
        return n;
    }

    public java.util.Iterator<E> iterator(){
        return new Iterable<E>(raiz);
    }


    static class NoABP<E> {
        E elemento;
        NoABP<E> esq;
        NoABP<E> dir;

        NoABP(E e) {
            elemento = e;
            esq = null;
            dir = null;
        }

        NoABP(E e, NoABP<E> esq, NoABP<E> dir) {
            elemento = e;
            this.esq = esq;
            this.dir = dir;
        }

        public E getElemento() {
            return elemento;
        }

        public NoABP<E> getNodeEsq() {
            return esq;
        }

        public NoABP<E> getNodeDir() {
            return dir;
        }

        public void setElemento(E e) {
            elemento = e;
        }

        public void setEsq(NoABP<E> e) {
            this.esq = e;
        }

        public void setDir(NoABP<E> e) {
            this.dir = e;
        }

        public String toString() {
            return elemento.toString();
        }
    }

    public class Iterable<E> implements java.util.Iterator<E> {
        NoABP<E> atual;

        public Iterable(NoABP<E> x){
            atual=x;
        }

        public boolean hasNext(){
            return atual!=null;
        }

        public void remove( ){
            throw new  UnsupportedOperationException();
        }

        public E next(){
            return next(atual);
        }

        private E next(NoABP<E> n) {
            if (!hasNext()){
                throw new java.util.NoSuchElementException();
            }
            E to_return=atual.getElemento();
            n=atual;
            if (atual.getNodeEsq()!=null){
                next(n.getNodeEsq());
            }
            if(atual.getNodeDir()!=null){
                next(n.getNodeDir());
            }
            return to_return;
        }

    }
}