package edu.jxsd.x3510.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;

import edu.jxsd.x3510.bean.Student;

public class FunctionUtilsRenRen {
	int i = 0;
	static String regExRenRenAddress = "http://www.renren.com/[0-9]+";
	static String regExRenRenAlbumPhoto = "http://photo.renren.com/getalbumprofile.do\\?owner=[0-9]+";
	static String regExRenRenPhoto = "http://hdn.xnimg.cn/photos/hdn[0-9/]+h_main[_a-zA-Z0-9]+\\.jpg";
	//http://hdn.xnimg.cn/photos/hdn321/20120809/2330/h_main_BuSl_7de90000027f1376.jpg
	
	RenRen renren = new RenRen();
	
	public void a(){
		Pattern p = Pattern.compile(regExRenRenPhoto);
		String result = null;
		Matcher m = p.matcher("http://hdn.xnimg.cn/photos/hdn321/20120809/2330/h_main_BuSl_7de90000027f1376.jpg");
		if(m.find()){
		   result = m.group();
		}
		System.out.println(result);
	}
	
	public String getRenRenAddress(String searchKey){
		String context = null;
		String url = null;
		Pattern p = Pattern.compile(regExRenRenAddress);
		
		url = renren.searchUrl(searchKey);
		String res = null;
		context = renren.getText(url);
		Matcher m = p.matcher(context);
		if (m.find()) {
			res = m.group();
		}
		return res;
	}
	
	//
	public String getRenRenPhotoAlbumAddress(String url){
		String photoAddress = null;
		Pattern p = Pattern.compile(regExRenRenAlbumPhoto);
		String context = renren.getText(url);
		Matcher m = p.matcher(context);
		if (m.find()) {
			photoAddress = m.group();
		}
		return photoAddress;
	}
	
	public ArrayList<String> getRenRenPhotoAddress(String url){
		ArrayList<String> photoList = null;
		String context = null;
		context = renren.getText(url);
		Pattern p = Pattern.compile(regExRenRenPhoto);
		Matcher m = p.matcher(context);
		if(m.find()){
			photoList = new ArrayList<String>();
			String photo = null;
			photo = m.group();
			photoList.add(photo);
		}
		while(m.find()) {
			String photo = null;
			photo = m.group();
			photoList.add(photo);
		}
		return photoList;
	}
	
	public static void main(String arg[]) throws ClientProtocolException, IOException{
		FunctionUtilsRenRen frr = new FunctionUtilsRenRen();
		ArrayList<String> photoList = new ArrayList<String>();
		String address = frr.getRenRenAddress("方立义  江西师大");
		System.out.println(address);
		
		address = frr.getRenRenPhotoAlbumAddress(address);
		System.out.println(address);
		
		photoList = frr.getRenRenPhotoAddress(address);
		for(int i=0;i<photoList.size();i++){
			System.out.println(photoList.get(i));
		}
		//frr.a();		
	}

}
