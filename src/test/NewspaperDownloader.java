package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class NewspaperDownloader {
	public static void main(String[] args) throws IllegalStateException, IOException {
		String url = "http://acad.cnki.net/kns55/request/SearchHandler.ashx";
		// 创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(RequestConfig.DEFAULT);

		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("action", "undefined"));
		formparams.add(new BasicNameValuePair("NaviCode", "*"));
		formparams.add(new BasicNameValuePair("PageName","ASP.brief_result_aspx"));
		formparams.add(new BasicNameValuePair("DbPrefix", "CCND"));
		formparams.add(new BasicNameValuePair("DbCatalog", "中国重要报纸全文数据库"));
		formparams.add(new BasicNameValuePair("ConfigFile", "CCND.xml"));
		formparams.add(new BasicNameValuePair("db_opt", ""));
		formparams.add(new BasicNameValuePair("db_value", ""));
		formparams.add(new BasicNameValuePair("magazine_special1", "="));
		formparams.add(new BasicNameValuePair("hidMagezineCode", "XHRB"));
		formparams
				.add(new BasicNameValuePair("publishdate_from", "2002-01-01"));
		formparams.add(new BasicNameValuePair("publishdate_to", "2003-01-01"));
		formparams.add(new BasicNameValuePair("au_1_sel", ""));
		formparams.add(new BasicNameValuePair("au_1_special1", "="));
		formparams.add(new BasicNameValuePair("txt_1_sel", ""));
		formparams.add(new BasicNameValuePair("txt_1_relation", "#CNKI_AND"));
		formparams.add(new BasicNameValuePair("txt_1_special1", "="));
		formparams.add(new BasicNameValuePair("his", "0"));
		formparams.add(new BasicNameValuePair("__", "Mon Mar 10 2014 20:27:23 GMT+0800 (中国标准时间)"));

		UrlEncodedFormEntity entity;
		try {
			entity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httpPost.setEntity(entity);
			HttpResponse httpResponse = null;

			httpResponse = closeableHttpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				System.out.println("re: " + EntityUtils.toString(httpEntity));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		HttpGet httpGet = new HttpGet("http://acad.cnki.net/Kns55/brief/brief.aspx?pagename=ASP.brief_result_aspx&dbPrefix=CCND&dbCatalog=%e4%b8%ad%e5%9b%bd%e9%87%8d%e8%a6%81%e6%8a%a5%e7%ba%b8%e5%85%a8%e6%96%87%e6%95%b0%e6%8d%ae%e5%ba%93&ConfigFile=CCND.xml&research=off&t=1394607612586");
		
		HttpResponse response = closeableHttpClient.execute(httpGet);    
        HttpEntity entity2 = response.getEntity();    
        if (entity2 != null) {    
            InputStream instreams = entity2.getContent();    
            String str = convertStreamToString(instreams);  
            System.out.println("Do something");   
            System.out.println(str);  
            // Do not need the rest    
            httpGet.abort();    
        }  
	}
	public static String convertStreamToString(InputStream is) {      
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));      
        StringBuilder sb = new StringBuilder();      
       
        String line = null;      
        try {      
            while ((line = reader.readLine()) != null) {  
                sb.append(line + "\n");      
            }      
        } catch (IOException e) {      
            e.printStackTrace();      
        } finally {      
            try {      
                is.close();      
            } catch (IOException e) {      
               e.printStackTrace();      
            }      
        }      
        return sb.toString();      
    }  
}
