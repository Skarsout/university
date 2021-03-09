package org.tweb.steam;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nome;
	private String desc;
	private String preco;
	private String img; //path to img
	private String categoria;

	protected Produto() {}

	public Produto(String nome, String desc, String preco, String img, String categoria) {
		this.nome = nome;
		this.desc = desc;
		this.preco = preco;
		this.img = img;
		this.categoria = categoria;
	}

	public Long getId(){return this.id;}
	public String getNome(){return this.nome;}
	public String getDesc(){return this.desc;}
	public String getPreco(){return this.preco;}
	public String getImg(){return this.img;}
	public String getCategoria(){return this.categoria;}

	@Override
	public String toString() {
		return String.format(
				"Produto[id=%d, nome='%s', desc='%s', preco='%s', img='%s', categoria='%s]",
				id,nome,desc,preco,img,categoria);
	}

	public String getCategoriaFormatada()
	{
		return switch (this.categoria) {
			case "singleplayer" -> "Singleplayer";
			case "multiplayer" -> "Multiplayer";
			case "acao" -> "Ação";
			case "aventura" -> "Aventura";
			case "rpg" -> "RPG";
			case "estrategia" -> "Estratégia";
			case "simulador" -> "Simulador";
			default -> "None";
		};
	}
}