package model;

import java.io.Serializable;
import java.security.Timestamp;

public class ordineBean implements Serializable {
	private int idordine;
	private int idutente;
	private Timestamp dataOrdine;
	private float totale;
	private String indirizzoSpedizione;
	
	public ordineBean() {
		this.idordine=0;
		this.idutente=0;
		this.dataOrdine=null;
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

	public Timestamp getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(Timestamp dataOrdine) {
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
