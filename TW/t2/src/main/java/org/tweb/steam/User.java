package org.tweb.steam;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String username, password, role;
    //@OneToOne
    //private Carrinho carrinho;


    protected User() {}

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        //this.carrinho = new Carrinho();
    }

    public Long getId() {
        return id;
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getRole() { return this.role; }
    //public Carrinho getCarrinho() {
    //    return carrinho;
    //}

    @Override
    public String toString() {
        return String.format(
                "Client[id=%d, Username='%s', Password='%s', Role='%s']",
                id, username, password, role);
    }
}