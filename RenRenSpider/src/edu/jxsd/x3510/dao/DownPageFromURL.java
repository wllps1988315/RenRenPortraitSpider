package edu.jxsd.x3510.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class DownPageFromURL {
	public static String getContext(String url) throws ClientProtocolException, IOException {
		String context = null;
		HttpClient httpclient = new DefaultHttpClient();
		InputStream instream = null;
		BufferedReader reader;
		try {
			HttpGet httpget = new HttpGet(url);
			System.out.println("executing request " + httpget.getURI());
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			System.out.println("----------------------------------------");
			System.out.println(response.getStatusLine());
			if (entity != null) {
				System.out.println("Response content length: "
						+ entity.getContentLength());
				instream = entity.getContent();
				/*
				 * byte[] b = new byte[(int) entity.getContentLength()];
				 * instream.read(b);
				 */
				/*reader = new BufferedReader(new InputStreamReader(instream,
						entity.getContentType().equals(
								"text-html; charset=gb2312") ? "gb2312"
								: "UTF-8"));*/
				reader = new BufferedReader(new InputStreamReader(instream,"GB2312"));
				StringBuffer temp = new StringBuffer();
				String str;
				while ((str = reader.readLine()) != null) {
					temp.append(str + "\n");
				}
				context = new String(temp);
				//context = new String(context.getBytes("utf-8"), "GB2312");
			}
			;
			System.out.println("----------------------------------------");
			httpget.abort();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return context;
	}
	
	public static void main(String arg[]) throws ClientProtocolException, IOException{
		//String url = "http://jwc.jxnu.edu.cn/MyControl/All_Display.aspx?UserControl=Xfz_Class_student.ascx&bjh=2411696&kch=267167&xq=3/1/2013";
		String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng=28.6866439,116.0246427&sensor=true&language=zh_cn";
		String context = null;
		context = DownPageFromURL.getContext(url);
		System.out.println(context);
	}
}
