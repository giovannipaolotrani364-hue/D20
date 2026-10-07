package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.utenteBean;
import model.utenteDAODataSource;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/registrazioneServlet")
public class registrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private utenteDAODataSource utenteDAO;
 
    public registrazioneServlet() {
        super();
        utenteDAO= new utenteDAODataSource();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/registrazione.jsp"); 
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome= request.getParameter("nome");
		String cognome= request.getParameter("cognome");
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		
		if(nome!=null && cognome!=null && email!=null && password!=null && !nome.trim().isEmpty() && !cognome.trim().isEmpty() && !email.trim().isEmpty() && !password.trim().isEmpty()) {
			utenteBean newutente= new utenteBean();
			newutente.setNome(nome.trim());
			newutente.setCognome(cognome.trim());
			newutente.setEmail(email.trim());
			newutente.setPassword(password.trim());
			try {
				utenteDAO.doSave(newutente);
				request.setAttribute("message", "registrazione completata con sucesso");
				RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/view/login.jsp");
				dispatcher.forward(request, response);
				return;
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "email già registrata");
			}
		}else {
			request.setAttribute("error", "tutti i campi sono obligatori");
			
		}
		RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/view/registrazione.jsp");
		dispatcher.forward(request, response);
	}

}
