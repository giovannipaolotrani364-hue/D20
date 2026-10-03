package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ProdottiDAODataSource;
import model.ProdottoBean;
import model.cart;
import model.cart;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class carelloServlet
 */
@WebServlet("/carelloServlet")
public class carelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdottiDAODataSource prodottoDao;
     
	
    public carelloServlet() {
        super();
        prodottoDao=new ProdottiDAODataSource();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		cart cart=(cart) session.getAttribute("carello");
		if(cart== null) {
			cart = new cart();
			session.setAttribute("carello", cart);
		}
		String azione= request.getParameter("azione");
		
		try {
			if(azione != null) {
				if(azione.equals("add")) {
					int idProdotto= Integer.parseInt(request.getParameter("idProdotto"));
					ProdottoBean prodotto=prodottoDao.doRetrieveByKey(idProdotto);
					if(prodotto !=null && prodotto.getIdProdotto() !=0) {}
					cart.addProdotto(prodotto);
				}
			}else if(azione.equalsIgnoreCase("delete")) {
				int idProdotto=Integer.parseInt(request.getParameter("idProdotto"));
				cart.remuveProdotto(idProdotto);
			}else if (azione.equals("clear")) {
				cart.svuota();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("carello.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
