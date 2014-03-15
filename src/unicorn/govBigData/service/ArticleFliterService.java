package unicorn.govBigData.service;

public interface ArticleFliterService {

	/**
	 * Get publishing time of a news
	 * 
	 * @param source
	 *            Source Code of a web page
	 * @return Publishing time
	 */
	public String getTime(String url);

	/**
	 * Get content of a news
	 * 
	 * @param source
	 *            Source Code of a web page
	 * @return Content
	 */
	public String getContent(String source);
}
