package unicorn.govBigData.fliterService;

import java.util.ArrayList;
import java.util.HashMap;

public interface SourceFliterService extends FliterService {
	public String getTitle(String no, String source);

	public String getTime(String origin);

	public String getBody(String origin);

	public HashMap<String, String> getNoTitleMap(String url);
	
	public ArrayList<String> getUrlList(String url);
}
