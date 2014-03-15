package unicorn.govBigData.fliter;

import java.util.ArrayList;

public class DefaultFliter {
	public static String getCharset(String source) {
		String charset = "utf-8";
		String[] spstr = source.split("<");
		for (int i = 0; i < spstr.length; i++) {
			spstr[i] = spstr[i].toLowerCase();
			if (spstr[i].contains("meta") && spstr[i].contains("charset")) {
				spstr[i] = spstr[i].split("charset")[1];
				spstr[i] = spstr[i].replace("\"", "");
				spstr[i] = spstr[i].replace("=", "");
				spstr[i] = spstr[i].replace(">", "");
				spstr[i] = spstr[i].trim();
				charset = spstr[i];
				break;
			}
		}
		return charset;
	}
}
