package unicorn.govBigData.fliter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import unicorn.govBigData.fliterService.SourceFliterService;
import unicorn.govBigData.fliterService.UrlFliterService;
import unicorn.govBigData.manager.SourceCodeReader;

public class ZheJiangFliter implements SourceFliterService, UrlFliterService {
	private static final boolean debug = false;

	@Override
	public String getNo(String url) {
		String newText = "";
		Pattern p = Pattern.compile("content_[0-9]{7}.htm");
		Matcher m = p.matcher(url);
		while (m.find()) {
			if (debug) {
				System.out.println("Find: " + m.group());
			}
			newText += m.group();
		}
		newText = newText.replaceAll("content_", "");
		newText = newText.replaceAll(".htm", "");
		newText = newText.replaceAll("<.*>", " ");
		return newText;
	}

	@Override
	public String getTitle(String no, String source) {
		String newText = "";
		// <a href=content_2568352.htm?div=-1>浙江代表有话说</a>
		//
		//
		//
		//
		Pattern p = Pattern.compile("<a href=content_" + no + "^(?=(</a>))");
		Matcher m = p.matcher(source);
		while (m.find()) {
			if (debug) {
				System.out.println("Find: " + m.group());
			}
			newText += m.group();
		}
		newText = newText.replaceAll("&nbsp", "");
		newText = newText.replaceAll("<a href=.*" + no + ".*>", "");
		newText = newText.replaceAll("</a>", "");
		newText = newText.replaceAll("<.*>", " ");
		return newText;
	}

	@Override
	public String getTime(String url) {
		String newText = "";
		Pattern p = Pattern.compile("[0-9]{4}-[0-9]{2}/[0-9]{2}");
		Matcher m = p.matcher(url);
		while (m.find()) {
			if (debug) {
				System.out.println("Find: " + m.group());
			}
			newText += m.group();
		}
		newText = newText.replaceAll("/", "-");
		newText = newText.replaceAll("<.*>", " ");
		return newText;
	}

	@Override
	public String getBody(String origin) {
		String newText = "";
		Pattern p = Pattern.compile("<(P|p)>.*</(P|p)>");
		Matcher m = p.matcher(origin);
		while (m.find()) {
			if (debug) {
				System.out.println("Find: " + m.group());
			}
			newText += m.group();
		}
		newText = newText.replaceAll("&nbsp", "");
		newText = newText.replaceAll("<(P|p)>", "");
		newText = newText.replaceAll("</(P|p)>", "\n");
		newText = newText.replaceAll("<.*>", "");
		newText = newText.replaceAll(";", "");
		return newText;
	}

	@Override
	public HashMap<String, String> getNoTitleMap(String url) {
		String sourceCode = null;
		ArrayList<String> list = new ArrayList<String>();
		HashMap<String, String> map = new HashMap<String, String>();

		try {
			SourceCodeReader scMgr = new SourceCodeReader(url);
			sourceCode = scMgr.getSourceCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (debug) {
			System.out.println(sourceCode);
		}

		if (!sourceCode.equals("404")) {
			String[] tmp = sourceCode.split("<a href=");
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
			String no = "null";
			String title = "null";
			Pattern noptn = Pattern.compile("content_[0-9]{7}");
			Pattern titlePtn = Pattern.compile(">.*");
			Matcher nomc = noptn.matcher(s);
			Matcher titlemc = titlePtn.matcher(s);
			if (nomc.find()) {
				no = nomc.group();
				no = no.replaceAll("content_", "");
			}
			if (titlemc.find()) {
				title = titlemc.group();
				title = title.replaceAll("<.*>"," ").replaceAll(">", "");
			}
			if (debug) {
				System.out.println(s);
				System.out.println(no + "----" + title);
			}
			map.put(no, title);
		}
		return map;
	}

	@Override
	public ArrayList<String> getUrlList(String url) {
		String sourceCode = null;
		ArrayList<String> list = new ArrayList<String>();

		try {
			SourceCodeReader scMgr = new SourceCodeReader(url);
			sourceCode = scMgr.getSourceCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (debug) {
			System.out.println(sourceCode);
		}
		if (!sourceCode.equals("404")) {
			Pattern noptn = Pattern.compile("content_[0-9]{7}");
			Matcher nomc = noptn.matcher(sourceCode);
			while (nomc.find()) {
				list.add(url.replaceAll("zjrbindex", nomc.group()));
			}
			return list;
		}
		return null;
	}
}
