package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.utenteBean;
import model.utenteDAODataSource;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private utenteDAODataSource utenteDAO;

    public loginServlet() {
        super();
        utenteDAO=new utenteDAODataSource();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action= request.getParameter("action");
		if("logout".equals(action)) {
			HttpSession session=request.getSession(false);
			if(session!=null) {
				session.invalidate();
			}
			response.sendRedirect("catalogoServlet");
		}else {
			response.sendRedirect("login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		
		if(email != null && password !=null && !email.trim().isEmpty() && !password.trim().isEmpty()) {
		try {
			utenteBean utente= utenteDAO.doRetrieveByCredentials(email.trim(), password.trim());
			
			if(utente !=null ) {
				HttpSession session= request.getSession();
				session.setAttribute("utente", utente);
				
				response.sendRedirect("catalogoServelt");
				return;
			}else {
				request.setAttribute("error", "email o password non validi");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "errore del server duante l'autenticazione");
		}
	}else {
		request.setAttribute("error", "insersci sia l'email ed la password");
	}
		RequestDispatcher dispatcher= request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}
}
