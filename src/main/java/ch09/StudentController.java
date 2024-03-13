package ch09;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/studentControl")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	StudentDAO dao;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new StudentDAO();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		String view = "";
		
		if(action == null) {
			getServletContext().getRequestDispatcher("/studentControl?action=list").forward(request, response);
		} else {
			switch(action) {
			case "list": view = list(request, response);break;
			case "insert": view = insert(request, response);break;
			case "delete": view = delete(request, response);break;
			case "select": view = select(request, response);break;
			case "update": view = update(request, response);break;
			}
			getServletContext().getRequestDispatcher("/ch09/"+view).forward(request, response);		
		}
	}
	
	public String list(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("students", dao.getAll());
		String studentsJson = new Gson().toJson(dao.getAll());
		request.setAttribute("studentsJson", studentsJson);
		return "studentInfo.jsp";		
	}
	
	public String insert(HttpServletRequest request, HttpServletResponse response) {
		Student s = new Student();
		try {
			BeanUtils.populate(s, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao.insert(s);
		return list(request, response);
	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao.delete(id,username);
		return list(request, response);
	}
	
	public String select(HttpServletRequest request, HttpServletResponse response) {
		int id;
		if(request.getParameter("id").equals("")){
			id = 0;
		}else {
			id = Integer.parseInt(request.getParameter("id"));
		}
		
		String username = request.getParameter("username");
		String univ = request.getParameter("univ");
		request.setAttribute("students", dao.select(id,username,univ));
		String studentsJson = new Gson().toJson(dao.select(id,username,univ));
		request.setAttribute("studentsJson", studentsJson);
		return "studentInfo.jsp";		
	}
	
	public String update(HttpServletRequest request, HttpServletResponse response) {
		Student s = new Student();
		try {
			BeanUtils.populate(s, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao.update(s);
		return list(request, response);
	}
}