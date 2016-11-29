package cn.prm.server.dao;

import java.util.List;

import cn.prm.server.entity.Contact;

/**
 * @Title: IContactDao.java<br>
 * @Package: cn.prm.server.dao<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:31:04<br>
 * @version v1.0<br>
 */
public interface IContactDao extends IBaseDao<Contact> {

    /** 
     * @Title: getByCustomId<br>
     * @Description: <br>
     * @param customId
     * @return
     */
    List<Contact> getByCustomId(String customId);
    
}
