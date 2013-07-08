package edu.jxsd.x3510.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;

import edu.jxsd.x3510.bean.Student;

public class FunctionUtilsSchool {
	int i = 0;
	static String regExAll = "<table width=\"160\" bordercolorlight=\"#000000\" bordercolordark=\"#ffffff\" border=\"1\" cellspacing=\"0\">[\\s\\S]*?</table>";
	static String regExNum = "<td>学号：</td>[\\s\\S]*?</td>";
	static String regExName = "<td>姓名：</td>[\\s\\S]*?</td>";
	static String regExClass = "td height=\"40\">班级：</td>[\\s\\S]*?</td>";
	
	static String regExAll2 = "<tr bgcolor=\"White\">[\\s\\S]*?";
	
	public ArrayList<Student> al = new ArrayList<Student>();
	
	public ArrayList<Student> getRegExAll(String context)
			throws ClientProtocolException, IOException {
		Student student = null;
		String summary = null;
		Pattern p = Pattern.compile(regExAll);
		Matcher m = p.matcher(context);
		String res = " ";
		while (m.find()) {
			student = new Student();
			summary = m.group();
			//System.out.println("this is "+i++);
			//System.out.println(res);
			student.setStudentNumber(getRegExNum(summary));
			student.setName(getRegExName(summary));
			student.setClasses(getRegExClass(summary));
			student.setPhotoSchool("http://jwc.jxnu.edu.cn/StudentPhoto/"+student.getStudentNumber()+".jpg");
			al.add(student);
		}
		return al;
	}
	
	public String getRegExNum(String context)
			throws ClientProtocolException, IOException {
		String summary = " ";
		Pattern p = Pattern.compile(regExNum);
		Matcher m = p.matcher(context);
		String res = " ";
		if (m.find()) {
			res = m.group();
			//System.out.println("this is "+i++);
			//System.out.println(res);
		}
		
		p = Pattern.compile("[0-9.]+");
		m = p.matcher(res);
		if (m.find()) {
			summary = m.group();
			//System.out.println(summary);
		}
		return summary;
	}
	
	public String getRegExName(String context)
			throws ClientProtocolException, IOException {
		String summary = null;
		Pattern p = Pattern.compile(regExName);
		Matcher m = p.matcher(context);
		String res = " ";
		while(m.find()) {
			res = m.group();
			String[] result = res.split("<td>");
			summary = result[2].split("</td")[0];
			//System.out.println(summary);
		}
		
		return summary;
	}
	
	public String getRegExClass(String context)
			throws ClientProtocolException, IOException {
		String summary = null;
		Pattern p = Pattern.compile(regExClass);
		Matcher m = p.matcher(context);
		String res = " ";
		while(m.find()) {
			res = m.group();
			String[] result = res.split("<td>");
			summary = result[1].split("</td")[0];
			//System.out.println(summary);
		}
		
		return summary;
	}
	
	public ArrayList<Student> getAllStudent(String url){
		ArrayList<Student> student = null;
		String context = null;
		try {
			context = DownPageFromURL.getContext(url);
			student = this.getRegExAll(context);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}
	
	public static void main(String arg[]) throws ClientProtocolException, IOException{
		String url = "http://jwc.jxnu.edu.cn/MyControl/All_Display.aspx?UserControl=Xfz_Class_student.ascx&bjh=2411696&kch=267167&xq=3/1/2013";

		String context = null;
		ArrayList<Student> al = null;
		context = DownPageFromURL.getContext(url);
		System.out.println("context");
		System.out.println(context);
		System.out.println("--------------------------------------------------------------------------------");
		FunctionUtilsSchool fu = new FunctionUtilsSchool();
		al = fu.getRegExAll(context);
		for(int i=0;i<al.size();i++){
			System.out.println(al.get(i).getName());
			System.out.println(al.get(i).getClasses());
			System.out.println(al.get(i).getStudentNumber());
		}
	}

}
