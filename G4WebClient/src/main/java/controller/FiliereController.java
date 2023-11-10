package controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.event.FocusEvent;
import java.io.IOException;
import java.util.List;

import dao.IDaoLocal;
import entities.Filiere;
import entities.Student;

/**
 * Servlet implementation class FiliereController
 */
@WebServlet("/FiliereController")
public class FiliereController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(beanName = "FiliereService")
    private IDaoLocal<Filiere> dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FiliereController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 List<Filiere> filieres = dao.findAll();
	     request.setAttribute("filieres", filieres);
	     request.setAttribute("singleFiliere", new Filiere("",""));
	     request.setAttribute("btnType", "ajouter");
	     request.getRequestDispatcher("/filierePage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodBtn = request.getParameter("_method");
		System.out.println("Methode : "+methodBtn);
		if(methodBtn.equals("ajouter")) {
		    String code = request.getParameter("code");
		    String name = request.getParameter("name");
		    dao.create(new Filiere(code,name));
		}else if(methodBtn.equals("delete")) {
			doDelete(request, response);
		}else if(methodBtn.equals("getFiliere")){
			int id=Integer.parseInt(request.getParameter("idGet"));
			Filiere filiere=dao.findById(id);
		    request.setAttribute("singleFiliere", filiere);
		    request.setAttribute("btnType", "modifier");
		    List<Filiere> filieres = dao.findAll();
		    request.setAttribute("filieres", filieres);
		    request.getRequestDispatcher("/filierePage.jsp").forward(request, response);
		}else {
			doPut(request, response);
		}
		doGet(request, response);
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("idDelete"));
		Filiere filiere=dao.findById(id);
		if(!filiere.equals(null))
			dao.delete(filiere);
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("idMod"));
		System.out.println("IDSTUDENT : "+id);
		String code = req.getParameter("code");
	    String name = req.getParameter("name");
		Filiere filiere=new Filiere(code,name);
		filiere.setId(id);
		if(!filiere.equals(null))
			dao.update(filiere);
	}

}
