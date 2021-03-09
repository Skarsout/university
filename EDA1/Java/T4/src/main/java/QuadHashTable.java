public class QuadHashTable<T> extends HashTable<T>{
    public QuadHashTable(){
        super();
    }

    public QuadHashTable(int n){
        super(n);
    }

    public int procPos(T s){
        int colisao = 1;
        int pos = hash(s);
        while(table[pos] != null && !table[pos].elemento.equals(s)){
            pos += colisao*colisao;
            colisao += 1;
            if(pos >= table.length)
                pos = pos % table.length;
        }
        return pos;
    }
}
