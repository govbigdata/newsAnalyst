package unicorn.govBigData.po;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Article {
	private String id;
	private String time;
	private String title;
	private String content;
	private String url;

	public Article() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "��ţ�" + id + "\n����ʱ�䣺" + time + "\n��ַ��" + url + "\n���⣺" + title
				+ "\n���ģ�\n" + content;
	}
	
	public File saveFile(String path) {
		File root = new File(path + "/" + getTime());
		if (!root.exists()) {
			root.mkdirs();
		}
		if (!getContent().equals("") && getContent() != null) {
			File atcFile = new File(root + "/" + getId() + "_"
					+ getTitle() + ".txt");
			BufferedWriter bfw = null;
			try {
				bfw = new BufferedWriter(new FileWriter(atcFile));
				bfw.write(getContent().replaceAll("\n", "\r\n"));
				bfw.flush();
				bfw.close();
			} catch (IOException e) {
				System.err.println("####�ļ�д�����####");
				e.printStackTrace();
				try {
					bfw.close();
				} catch (IOException e1) {
				}
			}
			return atcFile;
		}
		return null;
	}
}
