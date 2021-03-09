package org.tweb.steam;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProdutosController {

	private static final Logger log = LoggerFactory.getLogger(ProdutosController.class);

    @Autowired
    private ProdutoRepository repository2;
    
	@GetMapping("/produtos")
	public String listProdutos(
			Model model) 
	{		
		List<Produto> produtosList = (List<Produto>) repository2.findAll();
		
		model.addAttribute("produtosList", produtosList);
		return "produtos-view";
	}

	@GetMapping("/produtos/search")
	public String searchProduto(
			@RequestParam(name="nome", required=true, defaultValue = "tf2") String nome,
			Model model)
	{
		Produto produto = repository2.findByNome(nome);

		if(produto == null)
		{
			List<Produto> produtosList = (List<Produto>) repository2.findAll();
			model.addAttribute("produtosList", produtosList);
			return "produtos-view";
		}

		model.addAttribute("produto", produto);

		return "produtos-search";
	}

	@GetMapping("/produtos/detalhado")
	public String detalhadoProduto(
			@RequestParam(name="nome", required = true) String nome,
			Model model)
	{
		Produto produto = repository2.findByNome(nome);

		model.addAttribute("produto", produto);

		return "produtos-detalhado";
	}

	@GetMapping("/produtos/categoria")
	public String categoriaProduto(
			@RequestParam(name="categoria") String categoria,
			Model model)
	{
		List<Produto> produtosList = repository2.findByCategoria(categoria);

		model.addAttribute("produtosList", produtosList);

		return "produtos-view";
	}

	@GetMapping("/produtos/advanced-search")
	public String advancedProdutos(
			@RequestParam(name="categoria",required = false, defaultValue = "notDefined") String categoria,
			@RequestParam(name="min", required = false, defaultValue = "0.00") String min,
			@RequestParam(name="max", required = false, defaultValue = "1000.00") String max,
			Model model
	)
	{

		List<Produto> produtosList;

		if(categoria.equals("notDefined"))
			produtosList = (List<Produto>) repository2.findAll();
		else
			produtosList = repository2.findByCategoria(categoria);

		Double tempMin = Math.round(Double.parseDouble(min)*100.0)/100.0;
		Double tempMax = Math.round(Double.parseDouble(max)*100.0)/100.0;

		double tempPreco;

		List<Produto> produtosTemp = new ArrayList<>();

		for(Produto produto : produtosList) {
			tempPreco = Math.round(Double.parseDouble(produto.getPreco())*100.0)/100.0;
			if (tempPreco >= tempMin && tempPreco <= tempMax)
				produtosTemp.add(produto);
		}

		model.addAttribute("produtosList", produtosTemp);

		return "produtos-view";
	}

}
