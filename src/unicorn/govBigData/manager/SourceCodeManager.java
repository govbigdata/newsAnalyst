package unicorn.govBigData.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SourceCodeManager {
	private static final boolean debug = false;

	private URL url = null;

	public SourceCodeManager(String u) throws MalformedURLException {
		url = new URL(u);
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public String getSourceCode() throws IOException {
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		BufferedReader bfr = new BufferedReader(new InputStreamReader(
				urlConnection.getInputStream(), "utf-8"));
		String pageTxt = "";
		String line;
		while ((line = bfr.readLine()) != null) {
			pageTxt += line;
		}
		bfr.close();
		return pageTxt;
	}
}
