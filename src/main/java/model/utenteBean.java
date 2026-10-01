package model;

import java.io.Serializable;
import java.security.KeyStore.PrivateKeyEntry;

public class utenteBean implements Serializable{
	private int idutente;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private String ruolo;
	
	public utenteBean() {
		this.idutente=0;
		this.nome="";
		this.cognome="";
		this.email="";
		this.password="";
		this.ruolo="";
	}

	public int getIdutente() {
		return idutente;
	}

	public void setIdutente(int idutente) {
		this.idutente = idutente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	}
