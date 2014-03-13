package unicorn.govBigData.fliter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SourceCodeFliter {
	private static final boolean debug = false;

	public static String getBody(String origin) {
		String newText = "";
		Pattern p = Pattern.compile("<(P|p)>.*</(P|p)>");
		Matcher m = p.matcher(origin);
		while (m.find()) {
			if (debug) {
				System.out.println("Find: " + m.group());
			}
			newText += m.group();
		}
		newText = newText.replaceAll("&nbsp", "");
		newText = newText.replaceAll("<(P|p)>", "");
		newText = newText.replaceAll("</(P|p)>", "\n");
		newText = newText.replaceAll("<.*>", "");
		newText = newText.replaceAll(";", "");
		return newText;
	}
	
	public static String getTitle(String origin) {
		String newText = "";
		Pattern p = Pattern.compile("<title>.*--.{4}</title>");
		Matcher m = p.matcher(origin);
		while (m.find()) {
			if (debug) {
				System.out.println("Find: " + m.group());
			}
			newText += m.group();
		}
		newText = newText.replaceAll("&nbsp", "");
		newText = newText.replaceAll("<(title|TITLE)>", "");
		newText = newText.replaceAll("</(title|TITLE)>", "");
		newText = newText.replaceAll("<.*>", " ");
		return newText;
	}
}
