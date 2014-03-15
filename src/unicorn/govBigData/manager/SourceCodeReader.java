package unicorn.govBigData.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import unicorn.govBigData.fliter.DefaultFliter;

public class SourceCodeReader {
	private final String DEFAULT_CHARSET = "utf-8";
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
		String source = readSourceCodeWithCharset(DEFAULT_CHARSET);
		String charset = DefaultFliter.getCharset(source);
		if (!charset.equals(DEFAULT_CHARSET)) {
			source = readSourceCodeWithCharset(charset);
		}
		return source;
	}

	private String readSourceCodeWithCharset(String charset) {
		BufferedReader bfr = null;
		String source = null;
		try {
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			bfr = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream(), charset));
			String line;
			while ((line = bfr.readLine()) != null) {
				source += line.trim();
			}
			bfr.close();
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
		return source;
	}
}
