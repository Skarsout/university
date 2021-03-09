package org.tweb.steam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private static Double total;
    private static int quant;
    private static List<String> carrinho;

    public Carrinho() {
        total = 0.0;
        quant = 0;
        carrinho = new ArrayList<>();
    }

    public static Double getTotal(){return Math.round(total*100.0)/100.0;}
    public static int getQuant(){return quant;}
    public static List<String> getCarrinho(){return carrinho;}

    @Override
    public String toString() {
        return String.format(
                "Produto[total='%f', quantidade='%d']",
                total,quant);
    }

    public static void addCarrinho(String nome){ carrinho.add(nome); }
    public static void remCarrinho(String nome){ carrinho.remove(nome); }
    public static void addTotal(Double preco){ total += preco; }
    public static void subTotal(Double preco){ total -= preco; }
    public static void incQuant(){ quant++; }
    public static void decQuant(){ quant--; }

    public static void clearCarrinho(){
        carrinho.clear();
        total = 0.0;
        quant = 0;
    }

    public static String carrinhoToString(){

        String temp = "Produtos: ";
        for(String produto : carrinho)
            temp += produto + ", ";

        temp += "Método de pagamento: ";

        return temp;
    }

    public static boolean isEmpty(){
        return carrinho.isEmpty();
    }
}