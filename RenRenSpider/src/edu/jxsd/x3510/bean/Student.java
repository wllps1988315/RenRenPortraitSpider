package edu.jxsd.x3510.bean;

import java.util.ArrayList;

public class Student {
	private String school;
	private String classes;
	private String studentNumber;
	private String name;
	private String sex;
	private String renrenAddress;
	private String qqFriendAddress;
	private String photoSchool;
	
	private ArrayList<String> photoListRenRen;
	private ArrayList<String> photoListQQFriend; 
	
	public ArrayList<String> getPhotoListRenRen() {
		return photoListRenRen;
	}
	public void setPhotoListRenRen(ArrayList<String> photoListRenRen) {
		this.photoListRenRen = photoListRenRen;
	}
	public ArrayList<String> getPhotoListQQFriend() {
		return photoListQQFriend;
	}
	public void setPhotoListQQFriend(ArrayList<String> photoListQQFriend) {
		this.photoListQQFriend = photoListQQFriend;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRenrenAddress() {
		return renrenAddress;
	}
	public void setRenrenAddress(String renrenAddress) {
		this.renrenAddress = renrenAddress;
	}
	public String getQqFriendAddress() {
		return qqFriendAddress;
	}
	public void setQqFriendAddress(String qqFriendAddress) {
		this.qqFriendAddress = qqFriendAddress;
	}
	public String getPhotoSchool() {
		return photoSchool;
	}
	public void setPhotoSchool(String photoSchool) {
		this.photoSchool = photoSchool;
	}
	
	
	
}
