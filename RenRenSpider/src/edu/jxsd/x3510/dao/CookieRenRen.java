package edu.jxsd.x3510.dao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class CookieRenRen {

	// The configuration items  
    private static String userName = "549262189@qq.com";  
    private static String password = "xixiha451198435";  
    private static String redirectURL = "http://blog.renren.com/blog/304317577/449470467";  
  
    // Don't change the following URL  
    private static String renRenLoginURL = "http://www.renren.com/PLogin.do";  
  
    private DefaultHttpClient httpClient = new DefaultHttpClient();
    private HttpResponse response;
    private List<Cookie> cookies = null;
    String tmpcookies= "";
    
    private boolean login(){
    	HttpPost httpost = new HttpPost(renRenLoginURL);
    	List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("origURL", redirectURL));
		nvps.add(new BasicNameValuePair("domain", "renren.com"));
		nvps.add(new BasicNameValuePair("isplogin", "true"));
		nvps.add(new BasicNameValuePair("formName", ""));
		nvps.add(new BasicNameValuePair("method", ""));
		nvps.add(new BasicNameValuePair("submit", "登录"));
		nvps.add(new BasicNameValuePair("email", userName));
		nvps.add(new BasicNameValuePair("password", password));
		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			response = httpClient.execute(httpost);
			cookies=httpClient.getCookieStore().getCookies();
			for(Cookie c:cookies){
                tmpcookies += c.toString()+";";
            }
			System.out.println(tmpcookies);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			httpost.abort();
		}
		return true;	
    }
    
    private String getRedirectLocation() {
		Header locationHeader = response.getFirstHeader("Location");
		if (locationHeader == null) {
			return null;
		}
		return locationHeader.getValue();
	}
    
    private void init(String redirectLocation) {  
        HttpGet httpget = new HttpGet(redirectLocation);  
        ResponseHandler<String> responseHandler = new BasicResponseHandler();  
        try {  
            httpClient.execute(httpget, responseHandler);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            httpget.abort();  
           // httpClient.getConnectionManager().shutdown();  
        }    
    } 
  
    private String getText(String url) {  
        HttpGet httpget = new HttpGet(url);  
        ResponseHandler<String> responseHandler = new BasicResponseHandler();  
        String context = null;
        try {  
            context = httpClient.execute(httpget, responseHandler);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            httpget.abort();  
           // httpClient.getConnectionManager().shutdown();  
        }    
        return context;
    } 
    
    public static void main(String[] args) {  
        CookieRenRen renRen = new CookieRenRen();  
        renRen.login();      
        renRen.init(renRen.getRedirectLocation());
        String context = renRen.getText("http://blog.renren.com/blog/304317577/449470467");
        context = renRen.getText("http://www.renren.com/home");
        System.out.println(context);
    }   
}
