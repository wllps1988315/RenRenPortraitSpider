package edu.jxsd.x3510.dao;

import java.util.ArrayList;
import edu.jxsd.x3510.bean.Student;

public class Test {

	public static void main(String[] args) {
		String address = null;
		ArrayList<Student> studentList;
		ArrayList<String> list=null;
		FunctionUtilsSchool fuSchool = new FunctionUtilsSchool();
		FunctionUtilsRenRen fuRenRen = new FunctionUtilsRenRen();
		String url = "http://jwc.jxnu.edu.cn/MyControl/All_Display.aspx?UserControl=Xfz_Class_student.ascx&bjh=2411696&kch=267167&xq=3/1/2013";
		studentList = fuSchool.getAllStudent(url);
		for(int i=0;i<2;i++){
			System.out.println(studentList.get(i).getName());
			System.out.println(studentList.get(i).getClasses());
			System.out.println(studentList.get(i).getStudentNumber());
			
			address = fuRenRen.getRenRenAddress(studentList.get(i).getName()+"  江西师大");
			if(address != null){
				studentList.get(i).setRenrenAddress(address);
				System.out.println(studentList.get(i).getRenrenAddress());
				address = fuRenRen.getRenRenPhotoAlbumAddress(address);
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
}
