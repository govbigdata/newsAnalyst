package unicorn.govBigData.fliter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import unicorn.govBigData.manager.SourceCodeManager;

public class ListFliter {
	private static final boolean debug = false;

	public static ArrayList<String> getArticleUrlList(String url) {
		String sourceCode = null;
		ArrayList<String> l = new ArrayList<String>();
		try {
			SourceCodeManager scMgr = new SourceCodeManager(url);
			sourceCode = scMgr.getSourceCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (debug) {
			System.out.println(sourceCode);
		}

		String newText = "";
		Pattern p = Pattern.compile("content_[0-9]{6}.htm");
		Matcher m = p.matcher(sourceCode);
		while (m.find()) {
			if (debug) {
				System.out.println("Find: " + m.group());
			}
			newText = url.replaceAll("node_1.htm", m.group());
			l.add(newText);
		}

		return l;
	}
}
