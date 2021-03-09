public class Elemento<T> {
    T elemento;
    boolean apagado;

    public Elemento(T t){
        elemento = t;
        apagado = false;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public String toString(){
        return elemento.toString();
    }
}
