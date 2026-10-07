package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ordineDAODataSource {
	 private static DataSource ds;
	 
	 static {
	        try {
	            Context initCtx = new InitialContext();
	            Context envCtx = (Context) initCtx.lookup("java:comp/env");
	            ds = (DataSource) envCtx.lookup("jdbc/ecommerce_giochi"); 
	        } catch (NamingException e) {
	            System.out.println("Error:" + e.getMessage());
	        }
	    }
	 
	 public synchronized void doSave(ordineBean order, cart cart) throws SQLException {
	        Connection connection = null;
	        PreparedStatement psOrder = null;
	        PreparedStatement psDetail= null;

	        String insertOrderSQL = "INSERT INTO ordine (id_utente, indirizzo_spedizione, totale VAlUEs(?,?,?)";
	        String insertDetailSQLString="INSERT INTO dettaglio_ordine (id_ordine, id_prodotto, quantita, prezzo_unitario) VALUES (?, ?, ?, ?)";
	        try {
	            connection = ds.getConnection();
	            connection.setAutoCommit(false);
	            psOrder = connection.prepareStatement(insertOrderSQL);

	            psOrder.setInt(1, order.getIdutente());
	            psOrder.setString(2, order.getIndirizzoSpedizione());
	            psOrder.setFloat(3, order.getTotale());
	            psOrder.executeUpdate();
	            
	            ResultSet rskeyResultSet=psOrder.getGeneratedKeys();
	            int idordinegenerato =0;
	            if(rskeyResultSet.next()) {
	            	idordinegenerato= rskeyResultSet.getInt(1);
	            }
	            
	            psDetail=connection.prepareStatement(insertDetailSQLString);
	            for(cart.CartItem item : cart.getItems()) {
	            	psDetail.setInt(1, idordinegenerato);
	            	psDetail.setInt(2, item.getProdotto().getIdProdotto());
	            	psDetail.setInt(3, item.getQuantita());
	            	psDetail.setFloat(4, item.getSubtotale());
	            }
	            connection.commit();
	        } catch (SQLException e) {
				if(connection != null) {
					connection.rollback();
				}
				throw e;	
			} finally {
				if(connection != null) {
					connection.setAutoCommit(true);
					connection.close();
					
				}
			}
	    }
	 public synchronized Collection<ordineBean> doRetrieveByUtente(int idutente) throws SQLException  {
		 Connection connection= null;
		 PreparedStatement psOrder = null;
	     PreparedStatement psDetail= null;		
	     List<ordineBean> ordini=new ArrayList<>();
	     
	     String selectOrdersSQL = "SELECT * FROM ordine WHERE id_utente = ? ORDER BY data_ordine DESC";
	     String selectDetailsSQL = "SELECT d.*, p.titolo FROM dettaglio_ordine d " + "LEFT JOIN prodotto p ON d.id_prodotto = p.id_prodotto " + "WHERE d.id_ordine = ?";
	     
	     try {
	    	 connection=ds.getConnection();
				psOrder=connection.prepareStatement(selectOrdersSQL);
				psOrder.setInt(1, idutente);
				ResultSet rsOrders= psOrder.executeQuery();
				
				while (rsOrders.next()) {
					ordineBean order=new ordineBean();
					order.setIdordine(rsOrders.getInt("id_utente"));
					order.setIdutente(rsOrders.getInt("id_utente"));
					order.setIndirizzoSpedizione(rsOrders.getString("indirizzo_spedizione"));
					order.setTotale(rsOrders.getFloat("totale"));
					order.setDataOrdine(rsOrders.getTimestamp("data_ordine"));
					
					psDetail=connection.prepareStatement(selectDetailsSQL);
					ResultSet rsDetail= psDetail.executeQuery();
					
					while (rsDetail.next()) {
						dettaglioOrdineBean dettaglio= new dettaglioOrdineBean();
						dettaglio.setIdDettaglio(rsDetail.getInt("id_dettaglio"));
						dettaglio.setIdOrdine(rsDetail.getInt("id_ordine"));
						dettaglio.setIdProdotto(rsDetail.getInt("id_podotto"));
						String titolo= rsDetail.getString("titolo");
						dettaglio.setTitoloProdotto(titolo != null ? titolo: "prodotto non disponibile");
						dettaglio.setQuantita(rsDetail.getInt("quantita"));
						dettaglio.setPrezzounitario(rsDetail.getFloat("prezzo_unitarrio"));
						order.getArticoli().add(dettaglio);
					}
					psDetail.close();
					ordini.add(order);
				}
		} finally {
			if (connection != null) connection.close(); } 
	     return ordini;
		}
	 }

