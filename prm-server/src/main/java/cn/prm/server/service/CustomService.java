package cn.prm.server.service;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.prm.server.bean.CurrUser;
import cn.prm.server.commons.Constants;
import cn.prm.server.commons.Constants.CONTACT_TYPE;
import cn.prm.server.commons.Constants.DB_STATUS;
import cn.prm.server.commons.UUIDUtil;
import cn.prm.server.dao.IContactDao;
import cn.prm.server.dao.ICustomDao;
import cn.prm.server.dao.ICustomToContactDao;
import cn.prm.server.dto.CustomDto;
import cn.prm.server.dto.PageDto;
import cn.prm.server.entity.Contact;
import cn.prm.server.entity.Custom;
import cn.prm.server.entity.CustomToContact;
import cn.prm.server.exception.BusinessException;
import cn.prm.server.form.CustomForm;

@Service(value = "customService")
public class CustomService {
	
	private static final Logger log = LoggerFactory.getLogger(CustomService.class);
	
	@Autowired
	ICustomDao customDao;
	@Autowired
	IContactDao contactDao;
	@Autowired
	ICustomToContactDao customToContactDao;
	
	public PageDto<CustomDto> page(String order,int offset,int limit) throws BusinessException{
		//TODO page
		throw new BusinessException("");
//		return null;
	}
	
	public void add(CurrUser currUser, CustomForm form) throws BusinessException{
		//TODO 非空验证
		if(form.getName()==null || "".equals(form.getName())){
			throw new BusinessException("请输入客户名字");
		}
		
		// bean转化
		Timestamp now = new Timestamp(System.currentTimeMillis());
		String customId = UUIDUtil.randomUUID();
		Custom custom = new Custom();
		custom.setGuid(customId);
		custom.setStdName(form.getName());
		custom.setSex(form.getSex());
		custom.setAge(form.getAge());
		custom.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
		custom.setCreateTime(now);
		custom.setModifyTime(now);
		custom.setCreateUser(currUser.getGuid());
		custom.setModifyUser(currUser.getGuid());
		customDao.add(custom);

		if(form.getPhone()!=null && !"".equals(form.getPhone())) {
			String contactId = UUIDUtil.randomUUID();
			Contact contact = new Contact();
			contact.setGuid(contactId);
			contact.setStdName(form.getPhone());
			contact.setStdCode(CONTACT_TYPE.Phone.getCode());
			contact.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
			contact.setCreateTime(now);
			contact.setModifyTime(now);
			contact.setCreateUser(currUser.getGuid());
			contact.setModifyUser(currUser.getGuid());
			contactDao.add(contact);
			
			CustomToContact customToContact = new CustomToContact();
			customToContact.setGuid(UUIDUtil.randomUUID());
			customToContact.setCustomId(customId);
			customToContact.setContactId(contactId);
			customToContact.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
			customToContact.setCreateTime(now);
			customToContact.setModifyTime(now);
			customToContact.setCreateUser(currUser.getGuid());
			customToContact.setModifyUser(currUser.getGuid());
			customToContactDao.add(customToContact);
		}
		
		if(form.getEmail()!=null && !"".equals(form.getEmail())) {
			String email = form.getEmail();
			if(!email.contains("@")){
				throw new BusinessException("不是正确的邮箱");
			}
			String contactId = UUIDUtil.randomUUID();
			Contact contact = new Contact();
			contact.setGuid(contactId);
			contact.setStdName(form.getEmail());
			contact.setStdCode(CONTACT_TYPE.Email.getCode());
			contact.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
			contact.setCreateTime(now);
			contact.setModifyTime(now);
			contact.setCreateUser(currUser.getGuid());
			contact.setModifyUser(currUser.getGuid());
			contactDao.add(contact);
			
			CustomToContact customToContact = new CustomToContact();
			customToContact.setGuid(UUIDUtil.randomUUID());
			customToContact.setCustomId(customId);
			customToContact.setContactId(contactId);
			customToContact.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
			customToContact.setCreateTime(now);
			customToContact.setModifyTime(now);
			customToContact.setCreateUser(currUser.getGuid());
			customToContact.setModifyUser(currUser.getGuid());
			customToContactDao.add(customToContact);
		}
	}
	
	public void delete(CurrUser currUser,String id) throws BusinessException{
		if(id==null || "".equals(id)){
			throw new BusinessException("未指定要删除的客户");
		}
		Custom custom = customDao.get(id);
		if(custom==null){
			throw new BusinessException("没有此客户");
		}
		Timestamp now = new Timestamp(System.currentTimeMillis());
		custom.setStatus(DB_STATUS.STATUS_INACTIVE);
		custom.setModifyTime(now);
		custom.setModifyUser(currUser.getGuid());
	}
	
}
