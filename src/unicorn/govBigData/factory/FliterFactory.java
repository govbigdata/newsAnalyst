package unicorn.govBigData.factory;

import unicorn.govBigData.fliter.XHRBArticleFliter;
import unicorn.govBigData.fliter.XHRBArticleListFliter;
import unicorn.govBigData.fliter.ZJRBArticleFliter;
import unicorn.govBigData.fliter.ZJRBArticleListFliter;
import unicorn.govBigData.service.ArticleFliterService;
import unicorn.govBigData.service.ArticleListFliterService;

public class FliterFactory {
	public static ArticleListFliterService createListFliter(String url) {
		if (url.contains("xh.xhby.net/")) {
			return new XHRBArticleListFliter();
		} else if (url.contains("zjrb.zjol.com.cn/")) {
			return new ZJRBArticleListFliter();
		} else {
			return null;
		}
	}

	public static ArticleFliterService createArticleFliter(String url) {
		if (url.contains("xh.xhby.net/")) {
			return new XHRBArticleFliter();
		} else if (url.contains("zjrb.zjol.com.cn/")) {
			return new ZJRBArticleFliter();
		} else {
			return null;
		}
	}
}
