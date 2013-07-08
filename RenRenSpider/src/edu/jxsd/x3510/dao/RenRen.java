package edu.jxsd.x3510.dao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import edu.jxsd.x3510.bean.Student;

public class RenRen {

	/*private static String userName = "gewen0607@163.com";
	private static String password = "genwen0607gewen";*/
	/*private static String userName = "18770091747@sina.com";
	private static String password = "18770091747";*/
	private static String userName = "1396750185@qq.com";
	private static String password = "husangen";
	private static String renRenLoginURL = "http://www.renren.com/PLogin.do";
	private static String redirectURL = "http://browse.renren.com/s/all?from=opensearch&q=";
	
	//private static String redirectURL = "http://www.renren.com/home";
	String tmpcookies = null;
	List<Cookie> cookies = null;
	private HttpResponse response;

	private DefaultHttpClient httpclient = new DefaultHttpClient();
	
	public RenRen(){
		this.login(renRenLoginURL);
		this.init(this.getRedirectLocation());	
	}

	private boolean login(String url) {
		
		HttpPost httpost = new HttpPost(renRenLoginURL);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("origURL", url));
		nvps.add(new BasicNameValuePair("domain", "renren.com"));
		nvps.add(new BasicNameValuePair("isplogin", "true"));
		nvps.add(new BasicNameValuePair("formName", ""));
		nvps.add(new BasicNameValuePair("method", ""));
		nvps.add(new BasicNameValuePair("submit", "登录"));
		nvps.add(new BasicNameValuePair("email", userName));
		nvps.add(new BasicNameValuePair("password", password));
		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			response = httpclient.execute(httpost);
			cookies = httpclient.getCookieStore().getCookies();
			
			 if (cookies.isEmpty()) {
	                System.out.println("None");
	            } else {
	                for (int i = 0; i < cookies.size(); i++) {
	                    System.out.println("- " + cookies.get(i).toString());
	                    tmpcookies += cookies.get(i).toString();
	                }
	            }
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			httpost.abort();
		}
		return true;
	}

	
	
	 public void init(String redirectLocation) {  
	        HttpGet httpget = new HttpGet(redirectLocation);  
	        ResponseHandler<String> responseHandler = new BasicResponseHandler();  
	        try {  
	            httpclient.execute(httpget, responseHandler);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            httpget.abort();  
	           // httpClient.getConnectionManager().shutdown();  
	        }    
	    } 
	 
	 public String getText(String url) {  
		 String context = null;	
		 if(url != null){
		        HttpGet httpget = new HttpGet(url);  
		        ResponseHandler<String> responseHandler = new BasicResponseHandler();  
		        
		        try {  
		            context = httpclient.execute(httpget, responseHandler);  
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        } finally {  
		            httpget.abort();  
		           // httpClient.getConnectionManager().shutdown();  
		        }    
		 	}
	        return context;
	    } 

	private String getRedirectLocation() {
		Header locationHeader = response.getFirstHeader("Location");
		if (locationHeader == null) {
			return null;
		}
		return locationHeader.getValue();
	}
	
	public String searchUrl(String searchKey){
		String encoderUrl = this.redirectURL;
		String encoderSearchKey = null;
		try {
			encoderSearchKey = URLEncoder.encode(searchKey, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		encoderUrl = encoderUrl + encoderSearchKey;
		return encoderUrl;
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException{
		RenRen renren = new RenRen();
		/*renren.searchUrl("金海浪 江西师大");
		System.out.println(renren.redirectURL);
		renren.login();
		String redirectLocation = renren.getRedirectLocation();
		System.out.println(redirectLocation);
		System.out.println(renren.getTextGet(redirectLocation));
		String urlCoure = "http://jwc.jxnu.edu.cn/MyControl/All_Display.aspx?UserControl=Xfz_Class_student.ascx&bjh=2411696&kch=267167&xq=3/1/2013";
		ArrayList<Student> al = null;
		String content = null;
		FunctionUtilsSchool fus = new FunctionUtilsSchool();
		content = DownPageFromURL.getContext(urlCoure);
		al = fus.getRegExAll(content);
		for(int i=0;i<al.size();i++){
			System.out.println(al.get(i).getName());
			System.out.println(al.get(i).getClasses());
			System.out.println(al.get(i).getStudentNumber());
		}*/
		String a = null;
		String context = renren.getText("http://www.renren.com/429033117");
		Pattern p = Pattern.compile("http://photo.renren.com/getalbumprofile.do\\?owner=");
		Matcher m = p.matcher(context);
		System.out.println(context);
		if (m.find()) {
			 a = m.group();
		}
		System.out.println(a);
	}
}
