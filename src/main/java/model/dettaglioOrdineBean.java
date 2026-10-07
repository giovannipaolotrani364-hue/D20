package model;

import java.io.Serializable;

public class dettaglioOrdineBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int idDettaglio;
	private int idOrdine;
	private int idProdotto;
	private String titoloProdotto;
	private int quantita;
	private float prezzounitario;
	
	public dettaglioOrdineBean() {
		this.idDettaglio=0;
		this.idOrdine=0;
		this.idProdotto=0;
		this.titoloProdotto="";
		this.quantita=0;
		this.prezzounitario=0;
	}

	public int getIdDettaglio() {
		return idDettaglio;
	}

	public void setIdDettaglio(int idDettaglio) {
		this.idDettaglio = idDettaglio;
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public String getTitoloProdotto() {
		return titoloProdotto;
	}

	public void setTitoloProdotto(String titoloProdotto) {
		this.titoloProdotto = titoloProdotto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public float getPrezzounitario() {
		return prezzounitario;
	}

	public void setPrezzounitario(float prezzounitario) {
		this.prezzounitario = prezzounitario;
	}
	

}
