package unicorn.govBigData.po;

public class Article {
	private String no;
	private String time;
	private String title;
	private String content;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
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

	@Override
	public String toString() {
		return "��ţ�" + no + "\n����ʱ�䣺" + time + "\n���⣺" + title + "\n���ģ�\n"
				+ content;
	}

}
