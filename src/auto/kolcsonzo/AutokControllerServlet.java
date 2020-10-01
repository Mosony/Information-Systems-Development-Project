package auto.kolcsonzo;


import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class AutokControllerServlet
 */
@WebServlet("/AutokControllerServlet")
public class AutokControllerServlet extends HttpServlet{
	
private static final long serialVersionUID = 1L;
	
	private AutokDBUtil autokDBUtil;
		
	@Resource(name="jdbc/beadando")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		
		try {
			
			autokDBUtil = new AutokDBUtil(dataSource);
					
		}
		catch (Exception exc) {
			
			throw new ServletException(exc);
			
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String theCommand = request.getParameter("command1");
			
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			switch (theCommand) {
			
			case "LIST":
				listAutok(request, response);
				break;			
			case "ADD":
				addAutok(request, response);
				break;		
			case"LOAD":
				loadAutok(request, response);
				break;
			case"UPDATE":
				updateAutok(request, response);
				break;
			case"DELETE":
				deleteAutok(request, response);
				break;
				
			default:
				listAutok(request, response);
			}	
			
			listAutok(request, response);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
			
		}
		
	}

	private void deleteAutok(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String theAutokId = request.getParameter("autokId");
		
		autokDBUtil.deleteAutok(theAutokId);
		
		listAutok(request, response);
		
	}

	private void updateAutok(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		int id = Integer.parseInt(request.getParameter("autokId"));
		String marka = request.getParameter("marka");
		String rendSzam = request.getParameter("rendszam");
		String tipus = request.getParameter("tipus");
		String statusz = request.getParameter("statusz");
		String kolcsonzesiDij = request.getParameter("kolcsonzesi_dij");
		
		
		Autok theAutok = new Autok(id, marka, rendSzam, tipus, statusz, kolcsonzesiDij);
		
		
		autokDBUtil.updateAutok(theAutok);
		
		listAutok(request, response);
		
	}

	private void loadAutok(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String theAutokId = request.getParameter("autokId");
		
		Autok theAutok = autokDBUtil.getAutok(theAutokId);
		
		request.setAttribute("THE_AUTOK", theAutok);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/autok-modositasa.jsp");
		
		dispatcher.forward(request, response);
			
	}

	private void addAutok(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String marka = request.getParameter("marka");
		String rendSzam = request.getParameter("rendszam");
		String tipus = request.getParameter("tipus");
		String statusz = request.getParameter("statusz");
		String kolcsonzesiDij = request.getParameter("kolcsonzesi_dij");
		
		
		Autok theAutok = new Autok(marka, rendSzam, tipus, statusz, kolcsonzesiDij);
		
		
		autokDBUtil.addAutok(theAutok);
		
		listAutok(request, response);
		
		
	}

	private void listAutok(HttpServletRequest request, HttpServletResponse response) 
		throws Exception{

		List<Autok> autok = autokDBUtil.getAutok();
		
		request.setAttribute("AUTOK_LIST", autok);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-autok.jsp");
		dispatcher.forward(request, response);
	}
	
	

}
