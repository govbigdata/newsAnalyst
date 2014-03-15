package unicorn.govBigData.service;

import java.util.HashMap;

import unicorn.govBigData.po.Article;

public interface ArticleListFliterService {
	/**
	 * Get the list of Id-Article map
	 * 
	 * @param url
	 *            URL of a web page
	 * @param source
	 *            Source Code of a web page
	 * @return the list of Id-Article map
	 */
	public HashMap<String, Article> getIdArticleMap(String url, String source);

}
