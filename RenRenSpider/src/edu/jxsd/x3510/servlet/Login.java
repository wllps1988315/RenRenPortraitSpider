package edu.jxsd.x3510.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.jxsd.x3510.bean.Student;
import edu.jxsd.x3510.dao.FunctionUtilsRenRen;
import edu.jxsd.x3510.dao.FunctionUtilsSchool;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String address = null;
		ArrayList<Student> studentList;
		ArrayList<String> list=null;
		FunctionUtilsSchool fuSchool = new FunctionUtilsSchool();
		FunctionUtilsRenRen fuRenRen = new FunctionUtilsRenRen();
		String url = request.getParameter("url");
		studentList = fuSchool.getAllStudent(url);
		for(int i=0;i<studentList.size();i++){
			System.out.println(studentList.get(i).getName());
			System.out.println(studentList.get(i).getClasses());
			System.out.println(studentList.get(i).getStudentNumber());
			
			address = fuRenRen.getRenRenAddress(studentList.get(i).getName()+"  江西师大");
			if(address != null){
					studentList.get(i).setRenrenAddress(address);
					System.out.println(studentList.get(i).getRenrenAddress());
					address = fuRenRen.getRenRenPhotoAlbumAddress(address);
					if(address!=null){
					list = fuRenRen.getRenRenPhotoAddress(address);
					if(list != null){
						studentList.get(i).setPhotoListRenRen(list);
						
						for(int j=0;j<list.size();j++){
							System.out.println(list.get(j));
						}
					}				
				}
			}
		}
		HttpSession session = request.getSession();
		session.setAttribute("studentList", studentList);
		response.sendRedirect("/RenRenSpider/result.jsp");
	}

}
