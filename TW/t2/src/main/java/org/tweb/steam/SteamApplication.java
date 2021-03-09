package org.tweb.steam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SteamApplication {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ProdutoRepository repository2;

    public static void main(String[] args) {

        SpringApplication.run(SteamApplication.class, args);

        Carrinho carrinho = new Carrinho(); //de forma a inicializar as variáveis estáticas
    }

}
