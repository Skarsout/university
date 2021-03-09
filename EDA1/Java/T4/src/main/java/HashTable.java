public abstract class HashTable<T> {
    Elemento<T> table[];
    int ocupados=0;

    public HashTable(){
    }

    public HashTable(int n){
        table = new Elemento[procPrimo(n)];
        ocupados=0;
    }
    public int ocupados(){
        return ocupados;
    }

    public float factorCarga(){
        return (ocupados / (float) table.length);
    }

    protected abstract int procPos(T s);

    public void alocarTabela(int dim){
        table = (Elemento<T>[])new Elemento[procPrimo(dim)];
        ocupados = 0;
    }

    public void tornarVazia(){
        alocarTabela(table.length);
    }


    public T procurar(T x){
        int pos = procPos(x);
        if(table[pos]!=null){
            if(table[pos].getElemento().equals(x)){
                return table[pos].getElemento();
            }
        }
        return null;
    }

    public void remove(T x){
        int pos=procPos(x);
        if(table[pos]!=null && !table[pos].apagado){
            table[pos].apagado=true;
            ocupados--;
        }
    }

    public void insere (T x){
        int pos = procPos(x);
        if(table[pos] == null || table[pos].apagado){
            table[pos] = new Elemento<T>(x);
            ocupados++;
        }
        if(factorCarga()>0.5){
            rehash();
        }
    }

    public void rehash(){
        Elemento<T> tempTable[] = table;
        alocarTabela(tempTable.length*2);
        for(int i = 0; i < tempTable.length; i++){
            if(tempTable[i] != null && !tempTable[i].apagado){
                insere(tempTable[i].getElemento());
            }
        }
    }

    public void print(){
        for(int i = 0; i< table.length; i++){
            if(table[i]!= null && !table[i].apagado){
                System.out.println(table[i].getElemento());
            }
        }
    }

    public static boolean isPrime(int n){
        if(n==1 || n % 2 == 0){
            return false;
        }
        if(n == 2 || n == 3){
            return true;
        }
        for(int i = 3; i < n; i += 2){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

    public static int procPrimo(int n) {
        boolean primo = false;
        while(!primo) {
            if (isPrime(n))
                primo = true;
            else
                n++;
        }
        return n;
    }

    protected int hash(T s){
        int valHash = 0;

        for( int i = 0; i < ((String) s).length(); i++)
            valHash = 31 * valHash + ((String) s).charAt(i);

        valHash %= table.length;
        if( valHash < 0 )
            valHash += table.length;
        return valHash;
    }

}
