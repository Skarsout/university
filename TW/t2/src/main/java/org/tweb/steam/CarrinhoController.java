package org.tweb.steam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CarrinhoController {

    private static final Logger log = LoggerFactory.getLogger(CarrinhoController.class);

    @Autowired
    private ProdutoRepository repository2;

    @PostMapping("/carrinho/adicionar")
    public String addProduto(
            @RequestParam(name = "nome") String nome,
            Model model) {
        Carrinho.addCarrinho(nome);

        String precoTemp = repository2.findByNome(nome).getPreco();
        Carrinho.addTotal(Double.parseDouble(precoTemp));

        Carrinho.incQuant();

        updateModel(model);

        return "carrinho-view";
    }

    @PostMapping("/carrinho/remover")
    public String removeProduto(
            @RequestParam(name = "nome") String nome,
            Model model)
    {
        Carrinho.remCarrinho(nome);

        String precoTemp = repository2.findByNome(nome).getPreco();
        Carrinho.subTotal(Double.parseDouble(precoTemp));

        Carrinho.decQuant();

        updateModel(model);

        return "carrinho-view";
    }

    @PostMapping("/carrinho/limpar")
    public String limparCarrinho(Model model){
        Carrinho.clearCarrinho();

        updateModel(model);

        return "carrinho-view";
    }

    @GetMapping("/carrinho")
    public String listCarrinho(Model model)
    {
        updateModel(model);

        return "carrinho-view";
    }

    private void updateModel(Model model)
    {
        model.addAttribute("total", Carrinho.getTotal());
        model.addAttribute("quant", Carrinho.getQuant());
        model.addAttribute("carrinho", Carrinho.getCarrinho());
    }

}