package pt.uevora;

import java.util.*;

public class AppGestao {

    public static void main(String[] args) throws Exception {
	    Gestao gestao = new Gestao();

	    gestao.UserReader();
		gestao.aulaReader();

		gestao.login();
    }
}
