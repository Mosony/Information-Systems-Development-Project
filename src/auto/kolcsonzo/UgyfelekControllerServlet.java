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
 * Servlet implementation class UgyfelekControllerServlet
 */
@WebServlet("/UgyfelekControllerServlet")
public class UgyfelekControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UgyfelekDBUtil ugyfelekDBUtil;
		
	@Resource(name="jdbc/beadando")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		
		try {
			
			ugyfelekDBUtil = new UgyfelekDBUtil(dataSource);
					
		}
		catch (Exception exc) {
			
			throw new ServletException(exc);
			
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String theCommand = request.getParameter("command");
			
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			switch (theCommand) {
			
			case "LIST":
				listUgyfelek(request, response);
				break;			
			case "ADD":
				addUgyfelek(request, response);
				break;		
			case"LOAD":
				loadUgyfelek(request, response);
				break;
			case"UPDATE":
				updateUgyfelek(request, response);
				break;
			case"DELETE":
				deleteUgyfelek(request, response);
				break;
				
			default:
				listUgyfelek(request, response);
			}	
			
			listUgyfelek(request, response);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
			
		}
		
	}

	private void deleteUgyfelek(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String theUgyfelekId = request.getParameter("ugyfelekId");
		
		ugyfelekDBUtil.deleteUgyfelek(theUgyfelekId);
		
		listUgyfelek(request, response);
		
	}

	private void updateUgyfelek(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id = Integer.parseInt(request.getParameter("ugyfelekId"));
		String igazolvanySzam = request.getParameter("igazolvanySzam");
		String vezetekNev = request.getParameter("vezetekNev");
		String keresztNev = request.getParameter("keresztNev");	
		String telefonSzam = request.getParameter("telefonSzam");	
		String email = request.getParameter("email");	
		
	
		Ugyfelek theUgyfelek = new Ugyfelek(id, igazolvanySzam, vezetekNev, keresztNev, telefonSzam, email);
		
		ugyfelekDBUtil.updateUgyfelek(theUgyfelek);
		
		listUgyfelek(request, response);
		
	}

	private void loadUgyfelek(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String theUgyfelekId = request.getParameter("ugyfelekId");
		
		Ugyfelek theUgyfelek = ugyfelekDBUtil.getUgyfelek(theUgyfelekId);
		
		request.setAttribute("THE_UGYFELEK", theUgyfelek);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ugyfel-modositasa.jsp");
		
		dispatcher.forward(request, response);
			
	}

	private void addUgyfelek(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String igazolvanySzam = request.getParameter("igazolvanySzam");
		String vezetekNev = request.getParameter("vezetekNev");
		String keresztNev = request.getParameter("keresztNev");	
		String telefonSzam = request.getParameter("telefonSzam");	
		String email = request.getParameter("email");	
		
		Ugyfelek theUgyfelek = new Ugyfelek(igazolvanySzam, vezetekNev, keresztNev, telefonSzam, email);
		
		ugyfelekDBUtil.addUgyfelek(theUgyfelek);
		
		listUgyfelek(request, response);
		
	}

	private void listUgyfelek(HttpServletRequest request, HttpServletResponse response) 
		throws Exception{

		List<Ugyfelek> ugyfelek = ugyfelekDBUtil.getUgyfelek();
		
		request.setAttribute("UGYFELEK_LIST", ugyfelek);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-ugyfelek.jsp");
		dispatcher.forward(request, response);
	}

}
