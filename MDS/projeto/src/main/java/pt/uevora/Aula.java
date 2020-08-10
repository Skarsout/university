package pt.uevora;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Aula {

    private int qtd_presencas, num_aula;
    String data, hora;
    int presentes[]; //pronto para 100 alunos + docente

    public Aula(int num_aula, String data, String hora){
        this.num_aula = num_aula;
        this.data = data; //yyyy-mm-dd;
        this.hora = hora; //hh:mm;
        this.presentes = new int[101];

        for(int i = 0; i<101; i++)
            this.presentes[i] = 0;
    }

    int get_qtd_presencas(){ return this.qtd_presencas; }

    void regista_presenca(int cartao) {

        this.presentes[cartao] = 1;
        this.qtd_presencas++;
    }


    void presencas(String fileName) throws IOException {
        File file = new File(fileName);

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String cartao;
        int cartao_aux;
        cartao = reader.readLine();
        while (true){
            if(cartao.equals("x"))
                break;
            cartao_aux = Integer.parseInt(cartao);
            regista_presenca(cartao_aux);
            cartao = reader.readLine();
        }
    }
}
