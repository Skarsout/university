package pt.uevora;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Scanner;

public class Gestao {
    int aula_atual;
    Aula[] aulas;
    Utilizador[] inscritos;


    public Gestao(){

        this.aula_atual = 0;
        this.aulas = new Aula[10];
        this.inscritos = new Utilizador[101];
    }

    void nova_aula(String data, String hora) {
        aulas[this.aula_atual] = new Aula(this.aula_atual,data,hora);
        inc_aula_atual();
    }

    void novo_utilizador(int cartao, String nome, String papel) {
        this.inscritos[cartao] = new Utilizador(cartao,nome,papel);
    }

    String consultar_faltas_por_aluno(int cartao) {

        String a = "";
        System.out.print(cartao);
        System.out.print(" ");
        System.out.print(inscritos[cartao].get_nome());
        System.out.print(" | ");

        if(inscritos[cartao]!=null)
            a = inscritos[cartao].consultar_faltas(this.aula_atual);

        return a;
    }

    void pres_por_aula(){

        int temp;

        for(int i = 0; i<this.aula_atual; i++){
            System.out.print("aula ");
            System.out.print(i+1);
            System.out.print(" -> ");
            if(aulas[i] == null)
                break;
            temp = aulas[i].get_qtd_presencas();
            for (int j = 0; j<temp; j++) {
                System.out.print("|");
            }
            System.out.println();
        }
    }

    void inc_aula_atual() {
        this.aula_atual++;
    }

    private void parseUserObject(JSONObject user) {
        String name = (String) user.get("nome");
        int card = Integer.parseInt(String.valueOf(user.get("cartao")));
        String role = (String) user.get("papel");

        novo_utilizador(card, name, role);
    }

    public void UserReader() throws Exception
    {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/resources/dados.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray userList = (JSONArray) obj;

            //Iterate over users array
            userList.forEach( user -> parseUserObject( (JSONObject) user));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void parseAulaObject(JSONObject aula) {
        String data = (String) aula.get("data");
        String hora = (String) aula.get("hora");

        nova_aula(data, hora);
    }

    public void aulaReader() throws Exception
    {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/resources/aulas.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray aulaList = (JSONArray) obj;

            //Iterate over users array
            aulaList.forEach( aula -> parseAulaObject( (JSONObject) aula));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.aula_atual = 0;
    }

    public boolean menu() throws IOException {
        String action, cartao, temp;

        while(true) {

            System.out.println("Insira um número correspondente à ação que pretende realizar");
            System.out.println("1 - justificar faltas");
            System.out.println("2 - Marcar faltas");
            System.out.println("3 - Marcar presença");
            System.out.println("4 - Preparar próxima aula");
            System.out.println("5 - consultar faltas de um aluno");
            System.out.println("6 - Relatório de presenças");
            System.out.println("x - terminar sessão");

            Scanner scanner = new Scanner(System.in);
            action = scanner.nextLine();

            if(action.equals("1")) { //justifica faltas
                System.out.println("insira o número do aluno que pretende justificar a falta");
                scanner = new Scanner(System.in);
                cartao = scanner.nextLine();
                this.inscritos[Integer.parseInt(cartao)].justificar_falta(this.aula_atual);
            }
            else if(action.equals("2")) { //marcar falta a um aluno
                System.out.println("insira o número do aluno que pretende marcar falta");
                scanner = new Scanner(System.in);
                cartao = scanner.nextLine();
                this.inscritos[Integer.parseInt(cartao)].registar_falta(this.aula_atual);
            }
            else if(action.equals("3")) { //marcar presença a um aluno
                System.out.println("insira o número do aluno que pretende marcar presença");
                scanner = new Scanner(System.in);
                cartao = scanner.nextLine();
                this.inscritos[Integer.parseInt(cartao)].registar_presenca(this.aula_atual);
            }
            else if(action.equals("4")) { //próxima aula
                System.out.println("atenção certifique-se que foram registadas novas presenças antes continuar, pretende continuar? y/n");

                scanner = new Scanner(System.in);
                temp = scanner.nextLine();

                boolean aux = false;

                if(temp.equals("y")) {
                    this.aulas[this.aula_atual].presencas("src/main/resources/presencas.json");

                    for(int i = 1; i<101; i++) {
                        if(this.inscritos[i].get_papel().equals("docente") && this.aulas[this.aula_atual].presentes[i] == 0) {
                            for (int j = 1; j < 101; j++)
                                if (this.inscritos[j].get_papel().equals("aluno")) {
                                    this.inscritos[j].justificar_falta(this.aula_atual);
                                }

                            aux = true;
                        }
                    }

                    if(aux) {
                        this.inc_aula_atual();
                        continue;
                    }

                    for(int i = 1; i<101; i++) {
                        if(this.aulas[this.aula_atual].presentes[i] == 0)
                            this.inscritos[i].inc_faltas();
                        this.inscritos[i].presencas[this.aula_atual] = this.aulas[this.aula_atual].presentes[i];
                    }

                    this.inc_aula_atual();
                }
                else {
                    System.out.println("retornando ao menu inicial");
                }

            }
            else if(action.equals("5")) { //consultar faltas de um aluno
                System.out.println("insira o número do aluno que pretende consultar");
                scanner = new Scanner(System.in);
                cartao = scanner.nextLine();
                this.consultar_faltas_por_aluno(Integer.parseInt(cartao));
            }
            else if(action.equals("6")) { //gráfico presenças
                this.pres_por_aula();
                for(int i = 1; i<101; i++){
                    if(this.inscritos[i].get_papel().equals("aluno"))
                        this.consultar_faltas_por_aluno(i);
                }
            }
            else if(action.equals("x")){
                System.out.println("Sessão encerrada");
                return true;
            }
            else
                return false;
        }



    }

    public void login() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String id, inputPassword;

        while(true){
            System.out.println("Introduza ID: ");
            id = scanner.nextLine();
            System.out.println("Introduza password:");
            inputPassword = scanner.nextLine();

            boolean check = false;
            for(int i = 1; i<101; i++) {
                if (id.equals(String.valueOf(this.inscritos[i].get_cartao())) && inputPassword.equals(this.inscritos[i].get_password())) {
                    check = menu();
                }
            }
            if(check){
                break;
            }

            System.out.println("ID ou password invalidos, tente outra vez.");
            login();

        }
    }
}