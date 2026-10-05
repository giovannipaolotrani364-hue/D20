package model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class utenteDAODataSource {
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
	  
	  public static String toHash(String password) {
		  try {
			MessageDigest digest= MessageDigest.getInstance("SHA-256");
			byte[] hash= digest.digest(password.getBytes(StandardCharsets.UTF_8));
			StringBuilder hexString= new StringBuilder();
			
			for(byte b: hash) {
				String hex= Integer.toHexString(0xff & b);
				if(hex.length()==1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);		}
		  
	  }
	  
	  
	    public synchronized void doSave(utenteBean utente) throws SQLException {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;

	        String insertSQL = "INSERT INTO utente (nome, cognome, email, password, ruolo) VALUESS (?,?,?,?,?)";
	        
	        try {
	            connection = ds.getConnection();
	            preparedStatement = connection.prepareStatement(insertSQL);

	            preparedStatement.setString(1, utente.getNome());
	            preparedStatement.setString(2, utente.getCognome());
	            preparedStatement.setString(3, utente.getEmail());
	            preparedStatement.setString(4, toHash(utente.getPassword()));
	            preparedStatement.setString(5, utente.getRuolo());
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
	  
	  public synchronized utenteBean doRetrieveByCredentials(String email, String password) throws SQLException{
		  Connection connection = null;
	      PreparedStatement preparedStatement = null;
	      utenteBean bean=null;
	      
	      String selectSQL="Select * FROM utente WHERE email= ? AND password= ?";
	      
	      try {
	            connection = ds.getConnection();
	            preparedStatement = connection.prepareStatement(selectSQL);

	            preparedStatement.setString(1, email);
	            preparedStatement.setString(2, toHash(password));
	           
	            ResultSet rs= preparedStatement.executeQuery();
	            
	            if(rs.next()) {
	            	bean= new utenteBean();
	            	bean.setIdutente(rs.getInt("id_utente"));
	            	bean.setNome(rs.getString("nome"));
	            	bean.setCognome(rs.getString("cognome"));
	            	bean.setEmail(rs.getString("email"));
	            	bean.setPassword(rs.getString("password"));
	            	bean.setRuolo(rs.getString("ruolo"));
	            }

	        } finally {
	            try {
	                if (preparedStatement != null)
	                    preparedStatement.close();
	            } finally {
	                if (connection != null)
	                    connection.close(); 
	            }
	        }
	      return bean;
	  }
 
}
