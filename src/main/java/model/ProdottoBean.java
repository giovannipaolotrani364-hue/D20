package model;

import java.io.Serializable;

public class ProdottoBean implements Serializable{
	private int idProdotto;
	private String titolo;
	private String descrizione;
	private float prezzo;
	private int idCategoria;
	private int quantitàDisponibile;
	private String imagine;
	private int numeroGiocatoriMin; 
	private int numeroGiocatoriMax; 
	private int etaMinima; 
	private int durataMinuti;
	
	 public ProdottoBean() {
		this.idProdotto=0;
		this.titolo="";
		this.descrizione="";
		this.prezzo=0;
		this.idCategoria=0;
		this.quantitàDisponibile=0;
		this.imagine="";
		this.numeroGiocatoriMin=0;
		this.numeroGiocatoriMax=0;
		this.etaMinima=0;
		this.etaMinima=0;
		
	}

	 public int getIdProdotto() {
		 return idProdotto;
	 }

	 public void setIdProdotto(int idProdotto) {
		 this.idProdotto = idProdotto;
	 }

	 public String getTitolo() {
		 return titolo;
	 }

	 public void setTitolo(String titolo) {
		 this.titolo = titolo;
	 }

	 public String getDescrizione() {
		 return descrizione;
	 }

	 public void setDescrizione(String descrizione) {
		 this.descrizione = descrizione;
	 }

	 public float getPrezzo() {
		 return prezzo;
	 }

	 public void setPrezzo(float prezzo) {
		 this.prezzo = prezzo;
	 }

	 public int getIdCategoria() {
		 return idCategoria;
	 }

	 public void setIdCategoria(int idCategoria) {
		 this.idCategoria = idCategoria;
	 }

	 public int getQuantitàDisponibile() {
		 return quantitàDisponibile;
	 }

	 public void setQuantitàDisponibile(int quantitàDisponibile) {
		 this.quantitàDisponibile = quantitàDisponibile;
	 }

	 public String getImagine() {
		 return imagine;
	 }

	 public void setImagine(String imagine) {
		 this.imagine = imagine;
	 }

	 public int getNumeroGiocatoriMin() {
		 return numeroGiocatoriMin;
	 }

	 public void setNumeroGiocatoriMin(int numeroGiocatoriMin) {
		 this.numeroGiocatoriMin = numeroGiocatoriMin;
	 }

	 public int getNumeroGiocatoriMax() {
		 return numeroGiocatoriMax;
	 }

	 public void setNumeroGiocatoriMax(int numeroGiocatoriMax) {
		 this.numeroGiocatoriMax = numeroGiocatoriMax;
	 }

	 public int getEtaMinima() {
		 return etaMinima;
	 }

	 public void setEtaMinima(int etaMinima) {
		 this.etaMinima = etaMinima;
	 }

	 public int getDurataMinuti() {
		 return durataMinuti;
	 }

	 public void setDurataMinuti(int durataMinuti) {
		 this.durataMinuti = durataMinuti;
	 } 
	 
	 
	 
}

