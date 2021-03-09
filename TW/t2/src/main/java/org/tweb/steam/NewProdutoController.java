package org.tweb.steam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class NewProdutoController {

	private static final Logger log = LoggerFactory.getLogger(NewProdutoController.class);

    @Autowired
    private ProdutoRepository repository2;
    

}
