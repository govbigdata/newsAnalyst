package unicorn.govBigData.fliter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlFliter {
	private static final boolean debug = false;

	public static String getNo(String url) {
		String newText = "";
		Pattern p = Pattern.compile("content_[0-9]{6}.htm");
		Matcher m = p.matcher(url);
		while (m.find()) {
			if (debug) {
				System.out.println("Find: " + m.group());
			}
			newText += m.group();
		}
		newText = newText.replaceAll("content_", "");
		newText = newText.replaceAll(".htm", "");
		newText = newText.replaceAll("<.*>", " ");
		return newText;
	}

	public static String getTime(String url) {
		String newText = "";
		Pattern p = Pattern.compile("[0-9]{4}-[0-9]{2}/[0-9]{2}");
		Matcher m = p.matcher(url);
		while (m.find()) {
			if (debug) {
				System.out.println("Find: " + m.group());
			}
			newText += m.group();
		}
		newText = newText.replaceAll("/", "-");
		newText = newText.replaceAll("<.*>", " ");
		return newText;
	}
}
