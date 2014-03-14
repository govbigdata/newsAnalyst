package unicorn.govBigData.manager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import unicorn.govBigData.factory.FliterFactory;
import unicorn.govBigData.fliterService.SourceFliterService;
import unicorn.govBigData.fliterService.UrlFliterService;
import unicorn.govBigData.po.Article;

public class ArticleManager {
	private static final boolean debug = false;
	private SourceFliterService sfs = null;
	private UrlFliterService ufs = null;
	private HashMap<String, String> map = null;

	public ArticleManager(HashMap<String, String> map) {
		this.map = map;
	}

	private String getArticleListUrl(String url) {
		ufs = (UrlFliterService) FliterFactory.createFliter(url);

		if (url.contains("xh.xhby.net/")) {
			return "http://xh.xhby.net/newxh/html/"
					+ ufs.getTime(url).replaceAll("-", "/")
							.replaceFirst("/", "-") + "/node_1.htm";
		} else if (url.contains("zjrb.zjol.com.cn/")) {
			return "http://zjrb.zjol.com.cn/html/"
					+ ufs.getTime(url).replaceAll("-", "/")
							.replaceFirst("/", "-") + "/zjrbindex.htm";
		} else {
			return null;
		}
	}

	public Article getArticleFromUrl(String url) {
		sfs = (SourceFliterService) FliterFactory.createFliter(url);
		ufs = (UrlFliterService) FliterFactory.createFliter(url);

		Article atc = new Article();
		String no = null;
		String time = null;
		String title = null;
		String body = null;
		String content = null;
		try {
			SourceCodeReader scm = new SourceCodeReader(url);
			body = scm.getSourceCode();
			if (debug) {
				System.out.println(body);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		no = ufs.getNo(url);
		time = ufs.getTime(url);
		title = map.get(no);
		content = sfs.getBody(body);

		atc.setNo(no);
		atc.setTime(time);
		atc.setTitle(title);
		atc.setContent(content);

		return atc;
	}

	public File writeArticleFile(String rootPath, Article atc) {
		File root = new File(rootPath + "/" + atc.getTime());
		if (!root.exists()) {
			root.mkdirs();
		}

		File atcFile = new File(root + "/" + atc.getNo() + "_" + atc.getTitle()
				+ ".txt");
		BufferedWriter bfw = null;
		try {
			bfw = new BufferedWriter(new FileWriter(atcFile));
			bfw.write(atc.getContent().replaceAll("\n", "\r\n"));
			bfw.flush();
			bfw.close();
		} catch (IOException e) {
			System.err.println("####ÎÄ¼þÐ´Èë´íÎó####");
			e.printStackTrace();
			try {
				bfw.close();
			} catch (IOException e1) {
			}
		}
		return atcFile;
	}
}
