package unicorn.govBigData.factory;

import unicorn.govBigData.fliter.XinHuaFliter;
import unicorn.govBigData.fliter.ZheJiangFliter;
import unicorn.govBigData.fliterService.FliterService;

public class FliterFactory {
	public static FliterService createFliter(String url) {
		if (url.contains("xh.xhby.net/")) {
			return new XinHuaFliter();
		} else if (url.contains("zjrb.zjol.com.cn/")) {
			return new ZheJiangFliter();
		} else {
			return null;
		}
	}
}
