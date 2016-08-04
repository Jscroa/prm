package cn.prm.server.dao;

import java.util.List;

import cn.prm.server.entity.Contact;
import cn.prm.server.entity.CustomToContact;

public interface ICustomToContactDao extends IBaseDao<CustomToContact> {

	public List<Contact> getContacts(String customId);
	
}
