import java.util.Scanner;

public class Agenda {
    static class Contact implements Comparable<Contact>{
        String name, num1, num2;

        public Contact(String name, String n1){
            this.name = name;
            this.num1 = n1;
            this.num2 = " ";
        }
        public Contact(String name, String n1, String n2){
            this.name = name;
            this.num1 = n1;
            this.num2 = n2;
        }

        public String getName(){
            return name;
        }

        public String getNum1() {
            return num1;
        }

        public String getNum2() {
            return num2;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setNum1(String num1) {
            this.num1 = num1;
        }

        public void setNum2(String num2) {
            this.num2 = num2;
        }

        public int compareTo(Contact contacto) {
            if(getName().equals(contacto.getName())) {
                return 0;
            }else if (getName().compareTo(contacto.getName()) < 0) {
                return -1;
            }else {
                return 1;
            }
        }
    }

    ABP<Contact> tree;
    int unknown;
    ABP.NoABP<Contact> temp;

    public Agenda() {
        this.tree = new ABP<Contact>();
    }

    public void adicionar(String id, String num){
        Contact contact = new Contact(id, num);
        tree.insere(contact);
    }
    public void editar(String edit) {
        Scanner input = new Scanner(System.in);
        String answer, newEdit;

        editThis(tree.raiz, edit);
        Contact n = temp.getElemento();

        System.out.println(n.getName()+"\nNumero 1: "+n.getNum1()+"\nNumero 2: "+n.getNum2()+"\n");
        System.out.println("Editar: \n(1)Nome\n(2)Numero 1\n(3)Numero 2");

        answer = input.nextLine();
        switch(answer){
            case "1":
                newEdit = input.nextLine();
                n.setName(newEdit);
                break;
            case "2":
                newEdit = input.nextLine();
                n.setNum1(newEdit);
                break;
            case "3":
                newEdit = input.nextLine();
                n.setNum2(newEdit);
                break;
            default:
                System.out.println("Invalid input");
                break;

        }

    }

    private void editThis(ABP.NoABP<Contact> n, String edit){
        if(n==null){
            return;
        }
        if(n.getElemento().getName().equals(edit) || n.getElemento().getNum1().equals(edit) || n.getElemento().getNum2().equals(edit)){
            temp=n;
        }else{
            if(n.getNodeEsq()!=null){
                editThis(n.getNodeEsq(), edit);
            }
            if(n.getNodeDir()!=null) {
                editThis(n.getNodeDir(), edit);
            }
        }
    }

    public void remover(String input){
        remover(tree.raiz, input);
    }

    private void remover(ABP.NoABP<Contact> n, String input){
        if(n.getElemento().getName().equals(input) || n.getElemento().getNum1().equals(input) || n.getElemento().getNum2().equals(input)){
            tree.remove(n.getElemento());
        }else{
            if(n.getNodeEsq()!=null){
                remover(n.getNodeEsq(), input);
            }
            if(n.getNodeDir()!=null) {
                remover(n.getNodeDir(), input);
            }
        }
    }

    public void listar() {
        listar(tree.raiz);
    }

    private void listar(ABP.NoABP<Contact> n) {
        if (n != null) {
            listar(n.getNodeEsq());
            System.out.println(n.getElemento().getName()+" "+n.getElemento().getNum1()+" "+n.getElemento().getNum2());
            listar(n.getNodeDir());
        }
        if (tree.raiz == null){
            System.out.println("Nenhum contacto!");
        }
    }

    public void chamador(String num){
        unknown=0;
        chamador(tree.raiz, num);
        if(unknown==0){
            System.out.println("DESCONHECIDO");
        }
    }

    private void chamador(ABP.NoABP<Contact> x, String num){
        if(x.getElemento().num1.equals(num) || x.getElemento().num2.equals(num)){
            System.out.println(x.getElemento().name);
            unknown+=1;
            return;
        }else {
            if (x.getNodeEsq() != null) {
                chamador(x.getNodeEsq(), num);
            }
            if (x.getNodeDir() != null) {
                chamador(x.getNodeDir(), num);
            }
        }
        return;
    }

    public static void main(String Args[]){
        //TESTS
        Agenda agenda = new Agenda();

        agenda.adicionar("Bernardo", "12345");
        agenda.adicionar("Ana", "93821");
        agenda.adicionar("Margarida", "29384");
        agenda.adicionar("Daniel", "98731");
        agenda.adicionar("Joao", "92222");
        agenda.adicionar("Maria", "11111");
        agenda.adicionar("Afonso", "55555");

        //agenda.remover("Bernardo");
        //agenda.remover("92222");

        //agenda.listar();

        //agenda.chamador("55555");

        //agenda.editar("Ana");

        agenda.listar();

    }
}
