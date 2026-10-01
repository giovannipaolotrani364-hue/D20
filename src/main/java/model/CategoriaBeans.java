package model;

import java.io.Serializable;

public class CategoriaBeans implements Serializable {
	private int id_categoria; 	
	private String nome;
	private String descrizione;
	
	public CategoriaBeans() {
		this.id_categoria=-1;
		this.nome="";
		this.descrizione="";
	}

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


}
