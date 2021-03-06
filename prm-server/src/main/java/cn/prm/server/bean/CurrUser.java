package cn.prm.server.bean;

/**
 * @Title: CurrUser.java<br>
 * @Package: cn.prm.server.bean<br>
 * @Description: 当前登录的用户 Session中存的此对象<br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:20:31<br>
 * @version v1.0<br>
 */
public class CurrUser {

    private String guid;
    private String name;

    /**
     * @return the guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     * @param guid
     *            the guid to set
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
