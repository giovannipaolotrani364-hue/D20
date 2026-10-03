package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProdottiDAODataSource;
import model.ProdottoBean;

@WebServlet("/catalogoServlet")
public class catalogoServlet extends HttpServlet {
	private static final long serialVersionUID= 1L;
	private ProdottiDAODataSource prodottoDao;
	
	public catalogoServlet() {
		super();
		prodottoDao=new ProdottiDAODataSource();	
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
		try {
			Collection<ProdottoBean> prodotti= prodottoDao.doRetrieveAll("titolo");
			
			request.setAttribute("prodotti", prodotti);
		} catch ( SQLException e) {
			request.setAttribute("error", "errore nel caricamento dl catalogo");
		}
		
		RequestDispatcher dispatcher= request.getRequestDispatcher("catalogo.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
		doGet(request, response);
	}
}
