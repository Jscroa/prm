package cn.prm.server.entity;

import java.io.Serializable;

/**
 * @Title: AccountToMessage.java<br>
 * @Package: cn.prm.server.entity<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:34:15<br>
 * @version v1.0<br>
 */
public class AccountToMessage extends BaseEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8737896935501840635L;

    private String            accId;
    private String            msgType;
    private String            content;
    private Boolean           isRead;

    /**
     * @return the accId
     */
    public String getAccId() {
        return accId;
    }

    /**
     * @param accId
     *            the accId to set
     */
    public void setAccId(String accId) {
        this.accId = accId;
    }

    /**
     * @return the msgType
     */
    public String getMsgType() {
        return msgType;
    }

    /**
     * @param msgType
     *            the msgType to set
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the isRead
     */
    public Boolean getIsRead() {
        return isRead;
    }

    /**
     * @param isRead
     *            the isRead to set
     */
    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

}
