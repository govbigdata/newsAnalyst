package unicorn.govBigData.manager;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import unicorn.govBigData.factory.FliterFactory;
import unicorn.govBigData.po.Article;
import unicorn.govBigData.service.ArticleFliterService;
import unicorn.govBigData.service.ArticleListFliterService;

public class Downloader {
	private String name = null;
	private GregorianCalendar startTime = new GregorianCalendar();
	private GregorianCalendar currentTime = new GregorianCalendar();
	private GregorianCalendar endTime = new GregorianCalendar();

	public Downloader(String name, int startYear, int endYear) {
		this.name = name;
		startTime.set(startYear, 0, 1);
		currentTime.set(startYear, 0, 1);
		endTime.set(endYear, 11, 31);
	}

	public void startDownload(String path) throws MalformedURLException {
		do {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM/dd");
			System.out.print(sdf.format(currentTime.getTime()));
			String url = getListUrl(name, sdf.format(currentTime.getTime()));
			ArticleListFliterService alfs = FliterFactory.createListFliter(url);
			ArticleFliterService afs = FliterFactory.createArticleFliter(url);
			SourceCodeReader scr = new SourceCodeReader(url);
			String listSource = scr.getSourceCode();
			HashMap<String, Article> map = alfs
					.getIdArticleMap(url, listSource);
			ArrayList<Article> list = new ArrayList<Article>(map.values());
			for (Article a : list) {
				System.out.print(".");
				a.setTime(afs.getTime(url));
				SourceCodeReader newsScr = new SourceCodeReader(a.getUrl());
				a.setContent(afs.getContent(newsScr.getSourceCode()));
				a.saveFile(path);
			}
			// Ê±¼ä¼Ó1
			currentTime.add(GregorianCalendar.DATE, 1);
			System.out.println();
		} while (!currentTime.equals(endTime));
	}

	private String getListUrl(String name, String time) {
		if (name.equals("ZJRB")) {
			return "http://zjrb.zjol.com.cn/html/" + time + "/zjrbindex.htm";
		} else if (name.equals("XHRB")) {
			return "http://xh.xhby.net/newxh/html/" + time + "/node_1.htm";
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		Downloader d = new Downloader("ZJRB", 2011, 2012);
		try {
			d.startDownload("e:/News");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
