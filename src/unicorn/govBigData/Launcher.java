package unicorn.govBigData;

import java.util.ArrayList;

import unicorn.govBigData.fliter.ListFliter;
import unicorn.govBigData.manager.ArticleManager;
import unicorn.govBigData.po.Article;

public class Launcher {

	public static void main(String[] args) {
		ArrayList<String> l = ListFliter
				.getArticleUrlList("http://xh.xhby.net/newxh/html/2014-03/13/node_1.htm");
		for (String s : l) {
			ArticleManager atcMgr = new ArticleManager();
			Article atc = atcMgr.getArticleFromUrl(s);
			System.out.println(atc);
			atcMgr.writeArticleFile("e:/news", atc);
		}
	}
}
