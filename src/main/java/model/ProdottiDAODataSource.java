package model;

import javax.naming.InitialContext;
import javax.sql.DataSource;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProdottiDAODataSource implements IBeanDAO<ProdottoBean> {

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

    private static final String TABLE_NAME = "prodotto";

    @Override
    public synchronized void doSave(ProdottoBean product) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " + ProdottiDAODataSource.TABLE_NAME
                + " (titolo, descrizione, prezzo, quantita_disponibile, immagine_path, "
                + "numero_giocatori_min, numero_giocatori_max, eta_minima, durata_minuti, id_categoria) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);

            preparedStatement.setString(1, product.getTitolo());
            preparedStatement.setString(2, product.getDescrizione());
            preparedStatement.setFloat(3, product.getPrezzo());
            preparedStatement.setInt(4, product.getQuantitàDisponibile());
            preparedStatement.setString(5, product.getImagine());
            preparedStatement.setInt(6, product.getNumeroGiocatoriMin());
            preparedStatement.setInt(7, product.getNumeroGiocatoriMax());
            preparedStatement.setInt(8, product.getEtaMinima());
            preparedStatement.setInt(9, product.getDurataMinuti());
            preparedStatement.setInt(10, product.getIdCategoria());

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

    @Override
    public synchronized boolean doDelete(int code) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = 0;

        String deleteSQL = "DELETE FROM " + ProdottiDAODataSource.TABLE_NAME + " WHERE id_prodotto = ?";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, code);

            result = preparedStatement.executeUpdate();

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }
        return (result != 0);
    }

    @Override
    public synchronized ProdottoBean doRetrieveByKey(int code) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ProdottoBean bean = new ProdottoBean();

        String selectSQL = "SELECT * FROM " + ProdottiDAODataSource.TABLE_NAME + " WHERE id_prodotto = ?";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, code);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bean.setIdProdotto(rs.getInt("id_prodotto"));
                bean.setTitolo(rs.getString("titolo"));
                bean.setDescrizione(rs.getString("descrizione"));
                bean.setPrezzo(rs.getFloat("prezzo"));
                bean.setQuantitàDisponibile((rs.getInt("quantita_disponibile")));;
                bean.setImagine(rs.getString("immagine_path"));
                bean.setNumeroGiocatoriMin(rs.getInt("numero_giocatori_min"));
                bean.setNumeroGiocatoriMax(rs.getInt("numero_giocatori_max"));
                bean.setEtaMinima(rs.getInt("eta_minima"));
                bean.setDurataMinuti(rs.getInt("durata_minuti"));
                bean.setIdCategoria(rs.getInt("id_categoria"));
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

    @Override
    public synchronized Collection<ProdottoBean> doRetrieveAll(String order) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Collection<ProdottoBean> products = new LinkedList<ProdottoBean>();

        String selectSQL = "SELECT * FROM " + ProdottiDAODataSource.TABLE_NAME;

        if (order != null && !order.trim().equals("")) {
            selectSQL += " ORDER BY " + order;
        }

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	ProdottoBean bean = new ProdottoBean();

                bean.setIdProdotto(rs.getInt("id_prodotto"));
                bean.setTitolo(rs.getString("titolo"));
                bean.setDescrizione(rs.getString("descrizione"));
                bean.setPrezzo(rs.getFloat("prezzo"));
                bean.setQuantitàDisponibile(rs.getInt("quantita_disponibile"));
                bean.setImagine(rs.getString("immagine_path"));
                bean.setNumeroGiocatoriMin(rs.getInt("numero_giocatori_min"));
                bean.setNumeroGiocatoriMax(rs.getInt("numero_giocatori_max"));
                bean.setEtaMinima(rs.getInt("eta_minima"));
                bean.setDurataMinuti(rs.getInt("durata_minuti"));
                bean.setIdCategoria(rs.getInt("id_categoria"));

                products.add(bean);
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
        return products;
    }
}

