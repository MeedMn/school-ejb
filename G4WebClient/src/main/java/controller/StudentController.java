package controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import dao.IDaoLocal;
import entities.Filiere;
import entities.Student;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB(beanName = "StudentService")
    private IDaoLocal<Student> dao;
	@EJB(beanName = "FiliereService")
    private IDaoLocal<Filiere> daoFil;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 List<Student> students = dao.findAll();
	     request.setAttribute("students", students);
	     request.setAttribute("singleStudent", new Student("","","","",""));
	     request.setAttribute("btnType", "ajouter");
	     request.setAttribute("filieresList", daoFil.findAll());
	     request.getRequestDispatcher("/studentPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodBtn = request.getParameter("_method");
		System.out.println("Methode : "+methodBtn);
		if(methodBtn.equals("ajouter")) {
		    String firstName = request.getParameter("firstName");
		    String lastName = request.getParameter("lastName");
		    String telephone = request.getParameter("telephone");
		    String email = request.getParameter("email");
		    String password = request.getParameter("password");
		    Filiere filiere = daoFil.findById(Integer.parseInt(request.getParameter("filiere")));
		    dao.create(new Student(email,password,firstName,lastName,telephone,filiere));
		}else if(methodBtn.equals("delete")) {
			doDelete(request, response);
		}else if(methodBtn.equals("getStudent")){
			int id=Integer.parseInt(request.getParameter("idGet"));
			Student student=dao.findById(id);
		    request.setAttribute("singleStudent", student);
		    request.setAttribute("btnType", "modifier");
		    List<Student> students = dao.findAll();
		    request.setAttribute("students", students);
		    request.setAttribute("filieresList", daoFil.findAll());
		    request.getRequestDispatcher("/studentPage.jsp").forward(request, response);
		}else {
			doPut(request, response);
		}
		doGet(request, response);
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("idDelete"));
		Student student=dao.findById(id);
		if(!student.equals(null))
			dao.delete(student);
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("idMod"));
		System.out.println("IDSTUDENT : "+id);
		String firstName = req.getParameter("firstName");
	    String lastName = req.getParameter("lastName");
	    String telephone = req.getParameter("telephone");
	    String email = req.getParameter("email");
	    String password = req.getParameter("password");
	    Filiere filiere = daoFil.findById(Integer.parseInt(req.getParameter("filiere")));
		Student student=new Student(email,password,firstName,lastName,telephone,filiere);
		student.setId(id);
		if(!student.equals(null))
			dao.update(student);
	}
	

}
