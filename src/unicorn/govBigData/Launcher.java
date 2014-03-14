package unicorn.govBigData;

import java.util.ArrayList;
import java.util.HashMap;

import unicorn.govBigData.factory.FliterFactory;
import unicorn.govBigData.fliterService.SourceFliterService;
import unicorn.govBigData.manager.ArticleManager;
import unicorn.govBigData.po.Article;

public class Launcher {
	public static void main(String[] args) {
		String url = "http://zjrb.zjol.com.cn/html/2014-03/12/zjrbindex.htm";
		SourceFliterService sfs = (SourceFliterService) FliterFactory
				.createFliter(url);
		HashMap<String, String> map = sfs.getNoTitleMap(url);
		ArrayList<String> l = sfs.getUrlList(url);
		ArticleManager atcMgr = new ArticleManager(map);
		if (l != null) {
			for (String s : l) {
				Article atc = atcMgr.getArticleFromUrl(s);
				System.out.println(atc);
				atcMgr.writeArticleFile("e:/zjnews", atc);
			}
		} else {
			System.err.println("¡¥Ω””–ŒÛ£°");
		}
	}
}
