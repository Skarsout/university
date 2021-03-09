package org.tweb.steam;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Encomenda {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String produtos;
    private String metodoPagamento;

    public Encomenda(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
        this.produtos = Carrinho.carrinhoToString();
    }

    protected Encomenda() { }

    public Long getId(){return this.id;}
    public String getMetodoPagamento(){return this.metodoPagamento;}
    public String getProdutos(){ return this.produtos; }

}