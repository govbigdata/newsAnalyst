package unicorn.govBigData.po;

public class Article {
	private String no;
	private String time;
	private String title;
	private String body;

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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "编号：" + no + "\n刊登时间：" + time + "\n标题：" + title + "\n正文：\n"
				+ body;
	}

}
