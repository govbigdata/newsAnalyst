package unicorn.govBigData.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SourceCodeReader {
	private static final boolean debug = false;

	private URL url = null;

	public SourceCodeReader(String u) throws MalformedURLException {
		url = new URL(u);
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public String getSourceCode() {
		BufferedReader bfr = null;
		try {
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			bfr = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream(), "utf-8"));
			String pageTxt = "";
			String line;
			while ((line = bfr.readLine()) != null) {
				if (debug) {
					System.out.println(line);
				}
				pageTxt += line;
			}
			bfr.close();
			return pageTxt;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("404 NOT FOUND!");
		} finally {
			try {
				bfr.close();
			} catch (Exception e) {
			}
		}
		return "404";
	}
}
