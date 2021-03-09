package org.tweb.steam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.lang.Long;

import java.util.List;


@Controller
public class EncomendaController {

    @Autowired
    private EncomendaRepository repository3;

    @GetMapping("/admin/listar-encomenda")
    public String listarEncomenda(Model model)
    {
        List<Encomenda> encomendaList = (List<Encomenda>) repository3.findAll();

        model.addAttribute("encomendaList", encomendaList);

        return "encomendas-view";
    }

    @PostMapping("/admin/processar-encomenda")
    public String processarEncomenda(
            @RequestParam(name="id",defaultValue = "0") String id,
            Model model
    )
    {

        Encomenda encomenda = repository3.findById(Long.parseLong(id));

        repository3.delete(encomenda);

        List<Encomenda> encomendaList = (List<Encomenda>) repository3.findAll();

        model.addAttribute("encomendaList", encomendaList);

        return "encomendas-view";
    }

    @PostMapping("/criar-encomenda")
    public String criarEncomenda(
            @RequestParam(name="metodoPagamento", defaultValue = "dinheiro") String metodoPagamento,
            Model model)
    {
        if(!Carrinho.isEmpty())
            repository3.save(new Encomenda(metodoPagamento));

        Carrinho.clearCarrinho();

        model.addAttribute("quant", 0);
        model.addAttribute("total", 0.0);

        return "carrinho-view";
    }
}