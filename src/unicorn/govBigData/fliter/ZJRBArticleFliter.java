package unicorn.govBigData.fliter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import unicorn.govBigData.service.ArticleFliterService;

public class ZJRBArticleFliter implements ArticleFliterService{
	private final boolean DEBUG = false;
	
	@Override
	public String getTime(String url) {
		String newText = "";
		Pattern p = Pattern.compile("[0-9]{4}-[0-9]{2}/[0-9]{2}");
		Matcher m = p.matcher(url);
		while (m.find()) {
			if (DEBUG) {
				System.out.println("Find: " + m.group());
			}
			newText += m.group();
		}
		newText = newText.replaceAll("/", "-");
		newText = newText.replaceAll("<.*>", " ");
		return newText;
	}

	@Override
	public String getContent(String source) {
		String newText = "";
		Pattern p = Pattern.compile("<(P|p)>.*</(P|p)>");
		Matcher m = p.matcher(source);
		while (m.find()) {
			if (DEBUG) {
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

}
