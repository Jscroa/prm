package cn.prm.server.service;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import cn.prm.server.dao.IAccToGroupDao;
import cn.prm.server.dao.IContactDao;
import cn.prm.server.dao.ICustomDao;
import cn.prm.server.dao.ICustomToContactDao;
import cn.prm.server.dao.IGroupToCustomDao;
import cn.prm.server.dto.CustomDto;
import cn.prm.server.dto.PageDto;
import cn.prm.server.entity.AccGroup;
import cn.prm.server.entity.Contact;
import cn.prm.server.entity.Custom;
import cn.prm.server.entity.CustomToContact;
import cn.prm.server.entity.GroupToCustom;
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
	@Autowired
	IAccToGroupDao accToGroupDao;
	@Autowired
	IGroupToCustomDao groupToCustomDao;
	
	public PageDto<CustomDto> getPrivateCustoms(CurrUser currUser, String search, String order, int offset, int limit) throws BusinessException{
		// 获取当前登录账号的用户组
		List<AccGroup> groups = accToGroupDao.getGroups(currUser.getGuid());
		if(groups==null || groups.size()==0){
			throw new BusinessException("数据错误");
		}
		// 获取私有用户组
		AccGroup group = null;
		for (AccGroup accGroup : groups) {
			if(accGroup.getIsPrivate()){
				group = accGroup;
				break;
			}
		}
		if(group == null){
			throw new BusinessException("数据错误");
		}
		// 私有用户组的客户
		List<Custom> customs = groupToCustomDao.getCustoms(group.getGuid(),search,offset,limit);
		int total = groupToCustomDao.getCustomCount();
		List<CustomDto> dtos = new ArrayList<>();
		for (Custom custom : customs) {
			CustomDto dto = new CustomDto();
			dto.setId(custom.getGuid());
			dto.setName(custom.getStdName());
			List<Contact> contacts = customToContactDao.getContacts(custom.getGuid());
			
			if(contacts!=null && contacts.size()>0){
				for (Contact contact : contacts) {
					if(contact.getStdCode() == CONTACT_TYPE.Phone.getCode()){
						dto.setPhone(contact.getStdName());
					}else if(contact.getStdCode() == CONTACT_TYPE.Email.getCode()){
						dto.setEmail(contact.getStdName());
					}else if(contact.getStdCode() == CONTACT_TYPE.WeiXin.getCode()){
						dto.setWeixin(contact.getStdName());
					}else if(contact.getStdCode() == CONTACT_TYPE.QQ.getCode()){
						dto.setQq(contact.getStdName());
					}
				}
			}
			dtos.add(dto);
		}
		
		PageDto<CustomDto> page = new PageDto<>();
		page.setRows(dtos);
		page.setTotal(total);
		page.setPage(offset/limit +1);
		return page;
	}
	
	public void addPrivateCustom(CurrUser currUser, CustomForm form) throws BusinessException{
		
		if(form.getName()==null || "".equals(form.getName())){
			throw new BusinessException("请输入客户名字");
		}
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		// 客户表
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

		// 用户组与客户关联表
		List<AccGroup> groups = accToGroupDao.getGroups(currUser.getGuid());
		if(groups==null || groups.size()==0){
			throw new BusinessException("数据错误");
		}
		AccGroup group = null;
		for (AccGroup accGroup : groups) {
			if(accGroup.getIsPrivate()){
				group = accGroup;
				break;
			}
		}
		if(group == null){
			throw new BusinessException("数据错误");
		}
		GroupToCustom groupToCustom = new GroupToCustom();
		groupToCustom.setGuid(UUIDUtil.randomUUID());
		groupToCustom.setCustomId(customId);
		groupToCustom.setGroupId(group.getGuid());
		groupToCustom.setStatus(DB_STATUS.STATUS_ACTIVE);
		groupToCustom.setCreateTime(now);
		groupToCustom.setModifyTime(now);
		groupToCustom.setCreateUser(currUser.getGuid());
		groupToCustom.setModifyUser(currUser.getGuid());
		groupToCustomDao.add(groupToCustom);
		
		// 关联联系方式
		if(form.getPhone()!=null && !"".equals(form.getPhone())) {
			// 联系方式表
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
			
			// 客户与联系方式关联表
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
		if(custom.getStatus()==DB_STATUS.STATUS_INACTIVE){
			throw new BusinessException("该客户已删除");
		}
		Timestamp now = new Timestamp(System.currentTimeMillis());
		custom.setStatus(DB_STATUS.STATUS_INACTIVE);
		custom.setModifyTime(now);
		custom.setModifyUser(currUser.getGuid());
		customDao.modify(custom);
	}
	
}
