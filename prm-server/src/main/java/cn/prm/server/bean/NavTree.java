package cn.prm.server.bean;

import java.util.List;

/**
 * 导航栏树结构
 * 
 * @author yyao
 *
 */
public class NavTree {

	private String title;
	private String url;
	private List<NavTree> sub;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<NavTree> getSub() {
		return sub;
	}

	public void setSub(List<NavTree> sub) {
		this.sub = sub;
	}

}
