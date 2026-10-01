package model;

import java.io.Serializable;

public class ordineBean implements Serializable {
	private int idordine;
	private int idutente;
	private String dataOrdine;
	private float totale;
	private String indirizzoSpedizione;
	
	public ordineBean() {
		this.idordine=0;
		this.idutente=0;
		this.dataOrdine="";
		this.totale=0;
		this.indirizzoSpedizione="";
	}

	public int getIdordine() {
		return idordine;
	}

	public void setIdordine(int idordine) {
		this.idordine = idordine;
	}

	public int getIdutente() {
		return idutente;
	}

	public void setIdutente(int idutente) {
		this.idutente = idutente;
	}

	public String getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(String dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public float getTotale() {
		return totale;
	}

	public void setTotale(float totale) {
		this.totale = totale;
	}

	public String getIndirizzoSpedizione() {
		return indirizzoSpedizione;
	}

	public void setIndirizzoSpedizione(String indirizzoSpedizione) {
		this.indirizzoSpedizione = indirizzoSpedizione;
	}
	

}
