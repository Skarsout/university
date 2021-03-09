package org.tweb.steam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class NewUserController {

    private static final Logger log = LoggerFactory.getLogger(NewUserController.class);

    @Autowired
    private UserRepository repository;

    @GetMapping("/new-user")
    public String criarUser(
            Model model)
    {
        return "new-user-view";
    }

    @PostMapping("/new-user/criar")
    public String newUser(
            @RequestParam(name="username", required=false) String username,
            @RequestParam(name="password", required=false) String password,
            Model model)
    {
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        repository.save(new User(username,encodedPassword, "ROLE_USER"));

        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("role", "ROLE_USER");

        return "registado-view";
    }
}