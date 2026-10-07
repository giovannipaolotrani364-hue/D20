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
			request.setAttribute("error", "Devi effettuare il login per completare l'ordine.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp"); 
			dispatcher.forward(request, response);
			return;
		}
		int idutente= utente.getIdutente();
		cart cart = (cart) session.getAttribute("carello");
		String indirizzo= request.getParameter("indirizzo");
		
		if(cart != null && !cart.getItems().isEmpty() && indirizzo != null && !indirizzo.trim().isEmpty()) {
			
			
			ordineBean orderBean = new ordineBean();
			orderBean.setIdutente(idutente);
			orderBean.setIndirizzoSpedizione(indirizzo);
			orderBean.setTotale(cart.getTotale());
			
			try {
				orderDao.doSave(orderBean);
				request.setAttribute("indirizzo", indirizzo);
				request.setAttribute("totalepagato",cart.getTotale());
				cart.svuota();
				
				RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/view/confermaOrdine.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error","Errore durante il salvataggio dell'ordine.");
				RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/view/carello.jsp");
				dispatcher.forward(request, response);
				
			}
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/carrello.jsp"); 
			dispatcher.forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
