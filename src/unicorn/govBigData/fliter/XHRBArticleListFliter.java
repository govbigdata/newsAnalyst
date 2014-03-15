package unicorn.govBigData.fliter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import unicorn.govBigData.po.Article;
import unicorn.govBigData.service.ArticleListFliterService;

public class XHRBArticleListFliter implements ArticleListFliterService {
	private final boolean DEBUG = false;

	@Override
	public HashMap<String, Article> getIdArticleMap(String url, String source) {
		ArrayList<String> list = new ArrayList<String>();
		HashMap<String, Article> map = new HashMap<String, Article>();

		if (source != null) {
			String[] tmp = source.split("<a href=");
			for (int i = 0; i < tmp.length; i++) {
				tmp[i] = tmp[i].split("</a>")[0];
				if (tmp[i].contains("content")) {
					tmp[i] = tmp[i].replaceAll(" ", "");
					tmp[i] = tmp[i].replaceAll("\t", "");
					list.add(tmp[i].trim());
				}
			}
		}

		for (String s : list) {
			String id = "null";
			String title = "null";
			Pattern idPattern = Pattern.compile("content_[0-9]*");
			Pattern tPattern = Pattern.compile(">.*");
			Matcher idMatcher = idPattern.matcher(s);
			Matcher tMatcher = tPattern.matcher(s);
			Article article = new Article();
			if (idMatcher.find()) {
				id = idMatcher.group();
				article.setId(id.replaceAll("content_", ""));
				article.setUrl(url.replace("node_1", id));
			}
			if (tMatcher.find()) {
				title = tMatcher.group();
				title = title.replaceAll("<.*>", " ");
				title = title.replace(">", "");
				title = title.replace("<", "");
				title = title.replace("\"", "¡°");
				title = title.replace("?", "£¿");
				title = title.replace("/", " ");
				title = title.replace("\\", " ");
				title = title.replace("*", " ");
				title = title.replace(":", " ");
				title = title.replace("|", " ");
				article.setTitle(title);
			}

			if (DEBUG) {
				System.out.println(s);
				System.out.println(id + "----" + title);
			}
			if (id != null && !id.contains("null")) {
				map.put(id, article);
			}
		}
		return map;
	}
}
