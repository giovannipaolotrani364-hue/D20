package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.cart;
import model.ordineBean;
import model.ordineDAODataSource;
import model.utenteBean;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class checkoutServlet
 */
@WebServlet("/checkoutServlet")
public class checkoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private ordineDAODataSource  orderDao;
    
    public checkoutServlet() {
        super();
        orderDao= new ordineDAODataSource();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		utenteBean utente=(utenteBean) session.getAttribute("utente");
		if(utente == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		int idutente= utente.getIdutente();
		cart cart = (cart) session.getAttribute("carello");
		String indirizzo= request.getParameter("idirizzo");
		
		if(cart != null && !cart.getItems().isEmpty() && indirizzo != null && indirizzo.trim().isEmpty()) {
			
			
			ordineBean orderBean = new ordineBean();
			orderBean.setIdutente(idutente);
			orderBean.setIndirizzoSpedizione(indirizzo);
			orderBean.setTotale(cart.getTotale());
			
			try {
				orderDao.doSave(orderBean);
				request.setAttribute("indirizzo", indirizzo);
				request.setAttribute("totalepagato",cart.getTotale());
				cart.svuota();
				
				RequestDispatcher dispatcher= request.getRequestDispatcher("confermaOrdine");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendRedirect("carello.jsp?error1");
			}
		} else {
			response.sendRedirect("carello.jsp");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
