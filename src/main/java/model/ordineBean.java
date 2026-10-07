package model;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ordineBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int idordine;
	private int idutente;
	private String indirizzoSpedizione;
	private float totale;
	private Timestamp dataOrdine;
	private List<dettaglioOrdineBean> articoli;
	
	public ordineBean() {
		this.idordine=0;
		this.idutente=0;
		this.dataOrdine=null;
		this.totale=0;
		this.indirizzoSpedizione="";
		this.setArticoli(new ArrayList<>());
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

	public List<dettaglioOrdineBean> getArticoli() {
		return articoli;
	}

	public void setArticoli(List<dettaglioOrdineBean> articoli) {
		this.articoli = articoli;
	}
	

}
