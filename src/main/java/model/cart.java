package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class cart implements Serializable  {
	private static final long serialVersionUID= 1L;
	
	public static class CartItem implements Serializable  {
		private static final long serialVersionUID = 1L;
		private ProdottoBean prodotto;
		private int quantita;
		
		
		public CartItem(ProdottoBean prodotto, int quantita) {
			this.prodotto=prodotto;
			this.quantita=quantita;
		}

		public ProdottoBean getProdotto() {
			return prodotto;
		}

		public void setProdotto(ProdottoBean prodotto) {
			this.prodotto = prodotto;
		}

		public int getQuantita() {
			return quantita;
		}

		public void setQuantita(int quantita) {
			this.quantita = quantita;
		}
		
		public float getSubtotale() {
			return prodotto.getPrezzo()* quantita;		}
	}
	private List<CartItem> itemas;
	
	public cart() {
		itemas=new ArrayList<>();
	}
	
	public List<CartItem> getItems(){
		return itemas;
	}
	
	public void addProdotto(ProdottoBean prodotto) {
		for(CartItem item: itemas) {
			if(item.getProdotto().getIdProdotto() == prodotto.getIdProdotto()) {
				item.setQuantita(item.getQuantita()+1);
				return;
			}
		}
		itemas.add(new CartItem(prodotto, 1));
	}
	
	public void remuveProdotto(int idProdotto) {
		itemas.removeIf(item -> item.getProdotto().getIdProdotto() == idProdotto);
		
	}
	
	public float getTotale() {
		float t=0;
		for(CartItem item: itemas) {
			t=t+item.getSubtotale();
		}
		return t;
	}
	
	public void svuota() {
		itemas.clear();
		
	}

}
