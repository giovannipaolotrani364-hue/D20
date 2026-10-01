package model;

import java.io.Serializable;

public class carelloBean implements Serializable {
	private int idutente;
	private int idprodotto;
	private int quantita;
	
	public carelloBean() {
		this.idutente=0;
		this.idprodotto=0;
		this.quantita=0;
	}

	public int getIdutente() {
		return idutente;
	}

	public void setIdutente(int idutente) {
		this.idutente = idutente;
	}

	public int getIdprodotto() {
		return idprodotto;
	}

	public void setIdprodotto(int idprodotto) {
		this.idprodotto = idprodotto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	

}
