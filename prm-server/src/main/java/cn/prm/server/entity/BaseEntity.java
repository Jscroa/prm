package cn.prm.server.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Title: BaseEntity.java<br>
 * @Package: cn.prm.server.entity<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:34:58<br>
 * @version v1.0<br>
 */
public class BaseEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6353267447084825951L;

    protected String          guid;
    protected String          stdName;
    protected Integer         stdCode;
    protected Integer         status;
    protected String          memo;
    protected String          createUser;
    protected String          modifyUser;
    protected Timestamp       createTime;
    protected Timestamp       modifyTime;

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
     * @return the stdName
     */
    public String getStdName() {
        return stdName;
    }

    /**
     * @param stdName
     *            the stdName to set
     */
    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    /**
     * @return the stdCode
     */
    public Integer getStdCode() {
        return stdCode;
    }

    /**
     * @param stdCode
     *            the stdCode to set
     */
    public void setStdCode(Integer stdCode) {
        this.stdCode = stdCode;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * @return the createUser
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser
     *            the createUser to set
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * @return the modifyUser
     */
    public String getModifyUser() {
        return modifyUser;
    }

    /**
     * @param modifyUser
     *            the modifyUser to set
     */
    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    /**
     * @return the createTime
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the modifyTime
     */
    public Timestamp getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime
     *            the modifyTime to set
     */
    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }
}
