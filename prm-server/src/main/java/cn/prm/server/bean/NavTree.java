package cn.prm.server.bean;

import java.util.List;

/**
 * @Title: NavTree.java<br>
 * @Package: cn.prm.server.bean<br>
 * @Description: 导航栏树结构<br>
 * @author yyao<br>
 * @date 2016年10月19日 下午6:19:11<br>
 * @version v1.0<br>
 */
public class NavTree {

    private String        title;
    private String        url;
    private List<NavTree> sub;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the sub
     */
    public List<NavTree> getSub() {
        return sub;
    }

    /**
     * @param sub
     *            the sub to set
     */
    public void setSub(List<NavTree> sub) {
        this.sub = sub;
    }

}
