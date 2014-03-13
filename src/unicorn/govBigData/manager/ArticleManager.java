package unicorn.govBigData.manager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import unicorn.govBigData.fliter.SourceCodeFliter;
import unicorn.govBigData.fliter.UrlFliter;
import unicorn.govBigData.po.Article;

public class ArticleManager {
	private static final boolean debug = false;

	public Article getArticleFromUrl(String url) {
		Article atc = new Article();
		String no = null;
		String time = null;
		String title = null;
		String body = null;
		try {
			SourceCodeManager scm = new SourceCodeManager(url);
			body = scm.getSourceCode();
			if (debug) {
				System.out.println(body);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		no = UrlFliter.getNo(url);
		time = UrlFliter.getTime(url);
		title = SourceCodeFliter.getTitle(body);
		body = SourceCodeFliter.getBody(body);

		atc.setNo(no);
		atc.setTime(time);
		atc.setTitle(title);
		atc.setBody(body);

		return atc;
	}

	public File writeArticleFile(String rootPath, Article atc) {
		File root = new File(rootPath + "/" + atc.getTime());
		if (!root.exists()) {
			root.mkdirs();
		}

		File atcFile = new File(root + "/" + atc.getNo() + "_" + atc.getTitle() + ".txt");
		BufferedWriter bfw = null;
		try {
			bfw = new BufferedWriter(new FileWriter(atcFile));
			bfw.write(atc.getBody().replaceAll("\n", "\r\n"));
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
