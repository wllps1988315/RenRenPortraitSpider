package edu.jxsd.x3510.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;

import edu.jxsd.x3510.bean.Student;
import edu.jxsd.x3510.dao.DownPageFromURL;
import edu.jxsd.x3510.dao.FunctionUtilsSchool;

 class SearchRenRenaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchRenRenaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String actionType;
		actionType = request.getParameter("actionType");
		if (actionType.equals("getAllStudent")) {
			this.getAllStudent(request, response);
		}
	}

	public void getAllStudent(HttpServletRequest request,
			HttpServletResponse response) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		String urlCoure = request.getParameter("course_link");
		ArrayList<Student> al = null;
		String content = null;
		FunctionUtilsSchool fus = new FunctionUtilsSchool();
		content = DownPageFromURL.getContext(urlCoure);
		al = fus.getRegExAll(content);
		for(int i=0;i<al.size();i++){
			System.out.println(al.get(i).getName());
			System.out.println(al.get(i).getClasses());
			System.out.println(al.get(i).getStudentNumber());
		}
	}

}
