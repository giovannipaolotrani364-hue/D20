package model;

import java.io.Serializable;

public class Prodotto implements Serializable {
	private int id;
	private String nome;
	private String descrizione;
	private String tipologia;
	private float prezzo;
	
	public Prodotto() {
		this.id=0;
		this.nome="";
		this.descrizione="";
		this.tipologia="";
		this.prezzo=0;
	
	}
	
	public int getid() {
		return this.id;
	}
	public void setid( int id) {
		this.id=id;
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

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	

}
