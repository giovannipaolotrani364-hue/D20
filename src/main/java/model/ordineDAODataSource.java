package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	 
	 public synchronized void doSave(ordineBean order) throws SQLException {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;

	        String insertSQL = "INSERT INTO ordine (id_utente, indirizzo_spedizione, totale VAlUEs(?,?,?)";

	        try {
	            connection = ds.getConnection();
	            preparedStatement = connection.prepareStatement(insertSQL);

	            preparedStatement.setInt(1, order.getIdutente());
	            preparedStatement.setString(2, order.getIndirizzoSpedizione());
	            preparedStatement.setFloat(3, order.getTotale());
	           

	            preparedStatement.executeUpdate();

	        } finally {
	            try {
	                if (preparedStatement != null)
	                    preparedStatement.close();
	            } finally {
	                if (connection != null)
	                    connection.close(); 
	            }
	        }
	    }
}
