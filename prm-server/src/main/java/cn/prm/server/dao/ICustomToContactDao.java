package cn.prm.server.dao;

import java.util.List;

import cn.prm.server.entity.Contact;
import cn.prm.server.entity.CustomToContact;

/**
 * @Title: ICustomToContactDao.java
 * @Package: cn.prm.server.dao
 * @Description:
 * @author yyao
 * @date 2016年10月19日 下午5:31:39
 * @version v1.0
 */
public interface ICustomToContactDao extends IBaseDao<CustomToContact> {

	/** 
	 * @Title: getContacts 
	 * @Description: 
	 * @param customId
	 * @return
	 * @throws 
	 */
	public List<Contact> getContacts(String customId);
	
}
