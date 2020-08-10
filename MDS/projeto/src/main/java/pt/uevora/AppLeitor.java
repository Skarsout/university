package pt.uevora;


import org.json.simple.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class AppLeitor {
    public static void main(String[] args) throws IOException {
        abrirLeitor();
    }


    public static void abrirLeitor() throws IOException {
        System.out.println(" 'x' para fechar\n Introduzir cartoes:");
        Scanner input = new Scanner(System.in);
        JSONArray presencas = new JSONArray();
        String cartao;

        do {
            System.out.print("-> ");
            cartao = input.nextLine();

            if (cartao.equals("x")) {
                presencas.add("x");
                Files.write(Paths.get("src/main/resources/presencas.json"), presencas);
                break;
            } else if (cartao.matches("\\d+")) {
                presencas.add(cartao);
            }

            Files.write(Paths.get("src/main/resources/presencas.json"), presencas);
        } while (true);
    }
}
