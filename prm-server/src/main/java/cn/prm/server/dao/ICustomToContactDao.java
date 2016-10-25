package cn.prm.server.dao;

import java.util.List;

import cn.prm.server.entity.Contact;
import cn.prm.server.entity.CustomToContact;

/**
 * @Title: ICustomToContactDao.java<br>
 * @Package: cn.prm.server.dao<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:31:39<br>
 * @version v1.0<br>
 */
public interface ICustomToContactDao extends IBaseDao<CustomToContact> {

    /**
     * @Title: getContacts<br>
     * @Description: <br>
     * @param customId
     * @return
     */
    public List<Contact> getContacts(String customId);

}
