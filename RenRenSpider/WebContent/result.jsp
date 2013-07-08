<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="edu.jxsd.x3510.bean.Student"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table class="table" width="700" border="0" cellspacing="0"
					cellpadding="5">
					<thead>
						<tr style="background-color: #efeefe;">
							<td align="center" style="font-weight: bold;">姓名：</td>
							<td align="center" style="font-weight: bold;"></td>
							<td align="center" style="font-weight: bold;"></td>
						</tr>
					</thead>
					<tbody>
						<%
							ArrayList<Student> studentList = new ArrayList<Student>();
							studentList = (ArrayList<Student>) session.getAttribute("studentList");
							for (int i = 0; i < studentList.size(); i++) {
							
								Student student = studentList.get(i);
						%>
						<tr>
							<td><%=student.getName() %></td>
							<td><img alt="" src="<%=student.getPhotoSchool()%>"></td>
							<%
								ArrayList<String> renRenPhotoList = student.getPhotoListRenRen();
							if(renRenPhotoList != null)
								for(int j=0;j<renRenPhotoList.size();j++){
							%>
							<td><img alt="" src="<%=renRenPhotoList.get(j)%>"><td>
							<%} %>
						</tr>
					</tbody>
					<%
						}
					%>
				</table>
</body>
</html>