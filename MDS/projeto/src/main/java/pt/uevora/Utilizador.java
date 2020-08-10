package pt.uevora;

public class Utilizador {

    private int cartao, qtd_faltas;
    private String nome, papel, password;
    int[] presencas; //0 representa falta, 1 presença e 2 falta justificada;

    public Utilizador(int cartao, String nome, String papel){
        this.cartao = cartao;
        this.qtd_faltas = 0;
        this.nome = nome;
        this.papel = papel;
        this.presencas = new int[10]; //preparado para 10 aulas

        if(papel.equals("docente")){
            this.password= "password";
        }

        for(int i = 0; i<10; i++)
        {
            presencas[i] = 0;
        }
    }

    void registar_falta(int num_aula) {
        this.presencas[num_aula] = 0;
    }

    void registar_presenca(int num_aula) {
        this.presencas[num_aula] = 1;
    }

    void justificar_falta(int num_aula) {
        this.presencas[num_aula] = 2;
    }

    void inc_faltas(){ this.qtd_faltas++; }

    int get_cartao(){ return this.cartao; }

    String get_password(){ return this.password; }

    String get_nome(){ return this.nome; }

    String get_papel(){ return this.papel; }

    int get_qtd_faltas(){ return this.qtd_faltas; }

    String consultar_faltas(int aula_atual) {
        String a = "";
        for (int i = 0; i<aula_atual; i++) {
            if (this.presencas[i] == 0)
                a = a + "F ";
            else if(this.presencas[i] == 2)
                a = a + "J ";
            else
                a = a + "P ";
        }
        System.out.println(a);
        return a;
    }
}

