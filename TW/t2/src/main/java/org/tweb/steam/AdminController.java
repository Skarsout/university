package org.tweb.steam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class AdminController {

    @Autowired
    private ProdutoRepository repository2;

    @Autowired
    private UserRepository repository;

    @GetMapping("/admin")
    public String adminMain(
            Model model)
    {
        return "admin";
    }

    @GetMapping("/admin/add-produto")
    public String adminAdd(
            Model model)
    {
        return "new-produto";
    }

    @GetMapping("/admin/rem-produto")
    public String adminRem(
            Model model)
    {
        return "remove-produto";
    }

    @GetMapping("/admin/list-users")
    public String listUsers(
            @RequestParam(name="username", required=false, defaultValue="Hello") String username,
            @RequestParam(name="password", required=false, defaultValue="World") String password,
            @RequestParam(name="role", required=false, defaultValue="ROLE_USER") String role,
            Model model)
    {
        List<User> userList = (List<User>) repository.findAll();

        model.addAttribute("userList", userList);
        return "list-user-view";
    }

    @PostMapping("/admin/rem-produto/new")
    public String removeProduto(
            @RequestParam(name="nome", required = true) String nome,
            Model model
    )
    {
        Produto produto = repository2.findByNome(nome);

        if(produto == null)
            model.addAttribute("nome", "Não existe um produto com este nome");
        else {
            repository2.delete(produto);
            model.addAttribute("nome", "Foi removido o produto " + nome);
        }

        return "removido-view";
    }

    @PostMapping("/admin/add-produto/new")
    public String newProduto(
            @RequestParam(name="nome", required=false) String nome,
            @RequestParam(name="desc", required=false) String desc,
            @RequestParam(name="preco", required=false) String preco,
            @RequestParam(name="img", required=false) String img,
            @RequestParam(name="categoria", required=false) String categoria,
            Model model)
    {

        repository2.save(new Produto(nome,desc,preco,img,categoria));

        model.addAttribute("nome", nome);
        model.addAttribute("desc", desc);
        model.addAttribute("preco", preco);
        model.addAttribute("img", img);
        model.addAttribute("categoria",categoria);

        return "adicionado-view";
    }
}
