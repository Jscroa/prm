package cn.prm.server.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.prm.server.bean.CurrUser;
import cn.prm.server.commons.Constants;
import cn.prm.server.commons.Constants.CONTACT_TYPE;
import cn.prm.server.commons.Constants.DB_STATUS;
import cn.prm.server.commons.UUIDUtil;
import cn.prm.server.dao.IAccToGroupDao;
import cn.prm.server.dao.IAddressDao;
import cn.prm.server.dao.IContactDao;
import cn.prm.server.dao.ICustomDao;
import cn.prm.server.dao.ICustomToAddrDao;
import cn.prm.server.dao.ICustomToContactDao;
import cn.prm.server.dao.IGroupToCustomDao;
import cn.prm.server.dso.ICustomDso;
import cn.prm.server.dto.ListDto;
import cn.prm.server.dto.PageDto;
import cn.prm.server.dto.bean.AddressDto;
import cn.prm.server.dto.bean.CustomDto;
import cn.prm.server.entity.AccGroup;
import cn.prm.server.entity.Address;
import cn.prm.server.entity.Contact;
import cn.prm.server.entity.Custom;
import cn.prm.server.entity.CustomToAddr;
import cn.prm.server.entity.CustomToContact;
import cn.prm.server.entity.GroupToCustom;
import cn.prm.server.exception.BusinessException;
import cn.prm.server.exception.PermissionException;
import cn.prm.server.form.CustomForm;

/**
 * @Title: CustomService.java<br>
 * @Package: cn.prm.server.service<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:40:39<br>
 * @version v1.0<br>
 */
@Service(value = "customService")
public class CustomService {

    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(CustomService.class);

    @Autowired
    ICustomDao                  customDao;
    @Autowired
    IContactDao                 contactDao;
    @Autowired
    ICustomToContactDao         customToContactDao;
    @Autowired
    IAccToGroupDao              accToGroupDao;
    @Autowired
    IGroupToCustomDao           groupToCustomDao;
    @Autowired
    IAddressDao                 addressDao;
    @Autowired
    ICustomToAddrDao            customToAddrDao;
    @Autowired
    ICustomDso customDso;

    /**
     * @Title: getPrivateCustoms<br>
     * @Description: <br>
     * @param currUser 当前登录的用户
     * @param search 查询参数
     * @param order 排序参数
     * @param offset 偏移量
     * @param limit 每页显示数量
     * @return
     * @throws BusinessException
     */
    public PageDto<CustomDto> getPrivateCustoms(CurrUser currUser, String search, String order, int offset, int limit)
            throws BusinessException {
        // 获取当前登录账号的用户组
        List<AccGroup> groups = accToGroupDao.getGroups(currUser.getGuid());
        if (groups == null || groups.size() == 0) {
            throw new BusinessException("数据错误");
        }
        // 获取私有用户组
        AccGroup group = null;
        for (AccGroup accGroup : groups) {
            if (accGroup.getIsPrivate()) {
                group = accGroup;
                break;
            }
        }
        if (group == null) {
            throw new BusinessException("数据错误");
        }
        // 私有用户组的客户
        List<Custom> customs = groupToCustomDao.getCustoms(group.getGuid(), offset, limit);
        int total = groupToCustomDao.getCustomCount();
        List<CustomDto> dtos = new ArrayList<>();
        for (Custom custom : customs) {
            CustomDto dto = new CustomDto();
            dto.setId(custom.getGuid());
            dto.setName(custom.getStdName());
            // 获取联系方式列表
            List<Contact> contacts = customToContactDao.getContacts(custom.getGuid());

            if (contacts != null && contacts.size() > 0) {
                for (Contact contact : contacts) {
                    if (contact.getStdCode() == CONTACT_TYPE.Phone.getCode()) {
                        dto.setPhone(contact.getStdName());
                    }
                    else if (contact.getStdCode() == CONTACT_TYPE.Email.getCode()) {
                        dto.setEmail(contact.getStdName());
                    }
                    else if (contact.getStdCode() == CONTACT_TYPE.WeiXin.getCode()) {
                        dto.setWeixin(contact.getStdName());
                    }
                    else if (contact.getStdCode() == CONTACT_TYPE.QQ.getCode()) {
                        dto.setQq(contact.getStdName());
                    }
                }
            }

            dtos.add(dto);
        }

        PageDto<CustomDto> page = new PageDto<>();
        page.setRows(dtos);
        page.setTotal(total);
        page.setPage(offset / limit + 1);
        return page;
    }

    /**
     * @Title: addPrivateCustom<br>
     * @Description: <br>
     * @param currUser 当前登录的用户
     * @param form 新建客户表单
     * @throws BusinessException
     */
    @Transactional
    public void addPrivateCustom(CurrUser currUser, CustomForm form) throws BusinessException {

        if (form.getName() == null || "".equals(form.getName())) {
            throw new BusinessException("请输入姓名");
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());
        // 客户表
        String customId = UUIDUtil.randomUUID();
        Custom custom = new Custom();
        custom.setGuid(customId);
        custom.setStdName(form.getName());
        custom.setSex(form.getSex());

        Date birthday = null;
        String birthdayStr = form.getBirthday();
        if (birthdayStr != null && !"".equals(birthdayStr)) {
            try {
                java.util.Date bir = new SimpleDateFormat("yyyy年MM月dd日").parse(form.getBirthday());
                birthday = new Date(bir.getTime());
            }
            catch (ParseException e) {
                throw new BusinessException("出生年月格式错误");
            }
        }
        custom.setBirthday(birthday);
        custom.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
        custom.setCreateTime(now);
        custom.setModifyTime(now);
        custom.setCreateUser(currUser.getGuid());
        custom.setModifyUser(currUser.getGuid());
        customDao.add(custom);

        // 用户组与客户关联表
        List<AccGroup> groups = accToGroupDao.getGroups(currUser.getGuid());
        if (groups == null || groups.size() == 0) {
            throw new BusinessException("数据错误");
        }
        AccGroup privateGroup = null;
        for (AccGroup accGroup : groups) {
            if (accGroup.getIsPrivate()) {
                privateGroup = accGroup;
                break;
            }
        }
        if (privateGroup == null) {
            throw new BusinessException("数据错误");
        }
        GroupToCustom groupToCustom = new GroupToCustom();
        groupToCustom.setGuid(UUIDUtil.randomUUID());
        groupToCustom.setCustomId(customId);
        groupToCustom.setGroupId(privateGroup.getGuid());
        groupToCustom.setStatus(DB_STATUS.STATUS_ACTIVE);
        groupToCustom.setCreateTime(now);
        groupToCustom.setModifyTime(now);
        groupToCustom.setCreateUser(currUser.getGuid());
        groupToCustom.setModifyUser(currUser.getGuid());
        groupToCustomDao.add(groupToCustom);

        // 关联联系方式(手机)
        if (form.getPhone() != null && !"".equals(form.getPhone())) {
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

        // 关联联系方式(邮箱)
        if (form.getEmail() != null && !"".equals(form.getEmail())) {
            String email = form.getEmail();
            if (!email.contains("@")) {
                throw new BusinessException("不是正确的邮箱");
            }
            String contactId = UUIDUtil.randomUUID();
            Contact contact = new Contact();
            contact.setGuid(contactId);
            contact.setStdName(email);
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

        // 关联联系方式(QQ)
        if (form.getQq() != null && !"".equals(form.getQq())) {
            String qq = form.getQq();

            String contactId = UUIDUtil.randomUUID();
            Contact contact = new Contact();
            contact.setGuid(contactId);
            contact.setStdName(qq);
            contact.setStdCode(CONTACT_TYPE.QQ.getCode());
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

        // 关联联系方式(微信)
        if (form.getWeixin() != null && !"".equals(form.getWeixin())) {
            String weixin = form.getWeixin();

            String contactId = UUIDUtil.randomUUID();
            Contact contact = new Contact();
            contact.setGuid(contactId);
            contact.setStdName(weixin);
            contact.setStdCode(CONTACT_TYPE.WeiXin.getCode());
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

        // 关联地址
//        if (form.getAddr() != null && !"".equals(form.getAddr())) {
//            String addr = form.getAddr();
//            String addrId = UUIDUtil.randomUUID();
//            Address address = new Address();
//            address.setGuid(addrId);
//            address.setStdName(addr);
//            address.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
//            address.setCreateTime(now);
//            address.setModifyTime(now);
//            address.setCreateUser(currUser.getGuid());
//            address.setModifyUser(currUser.getGuid());
//            addressDao.add(address);
//
//            CustomToAddr customToAddr = new CustomToAddr();
//            customToAddr.setGuid(UUIDUtil.randomUUID());
//            customToAddr.setCustomId(customId);
//            customToAddr.setAddrId(addrId);
//            customToAddr.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
//            customToAddr.setCreateTime(now);
//            customToAddr.setModifyTime(now);
//            customToAddr.setCreateUser(currUser.getGuid());
//            customToAddr.setModifyUser(currUser.getGuid());
//            customToAddrDao.add(customToAddr);
//        }
    }

    /**
     * @Title: delete<br>
     * @Description: <br>
     * @param currUser 当前登录的用户
     * @param id 客户id
     * @throws BusinessException
     * @throws PermissionException 
     */
    public void delete(CurrUser currUser, String id) throws BusinessException, PermissionException {
        if (id == null || "".equals(id)) {
            throw new BusinessException("未指定要删除的客户");
        }
        
        pCheckCustomOwnner(currUser, id);
        Custom custom = customDao.get(id);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        custom.setStatus(DB_STATUS.STATUS_INACTIVE);
        custom.setModifyTime(now);
        custom.setModifyUser(currUser.getGuid());
        customDao.modify(custom);
    }

    /** 
     * @Title: modify<br>
     * @Description: <br>
     * @param currUser
     * @param custId 
     * @param form
     * @throws PermissionException 
     * @throws BusinessException 
     */
    @Transactional
    public void modify(CurrUser currUser, String custId, CustomForm form) throws PermissionException,BusinessException{
        if(form==null) {
            throw new BusinessException("参数不完整");
        }
        pCheckCustomOwnner(currUser,custId);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Custom custom = customDao.get(custId);
        custom.setStdName(form.getName());
        custom.setSex(form.getSex());
        //custom.setBirthday(form.getBirthday());
        Date birthday = null;
        String birthdayStr = form.getBirthday();
        if (birthdayStr != null && !"".equals(birthdayStr)) {
            try {
                java.util.Date bir = new SimpleDateFormat("yyyy年MM月dd日").parse(form.getBirthday());
                birthday = new Date(bir.getTime());
            }
            catch (ParseException e) {
                throw new BusinessException("出生年月格式错误");
            }
        }
        custom.setBirthday(birthday);
        custom.setModifyTime(now);
        custom.setModifyUser(currUser.getGuid());
        customDao.modify(custom);
        // 以下 联系方式修改
        List<Contact> contacts = customToContactDao.getContacts(custId);
        // 修改phone
        {
            String phone = form.getPhone();
            boolean flag = false; // 标记原数据库有无phone记录
            for (Contact contact : contacts) {
                if(contact.getStdCode()==CONTACT_TYPE.Phone.getCode()){
                    flag = true;
                    // 有记录，修改
                    contact.setStdName(phone);
                    contact.setModifyTime(now);
                    contact.setModifyUser(currUser.getGuid());
                    contactDao.modify(contact);
                    break;
                }
            }
            if(!flag){ // 原记录中没有phone，新增
                if (phone != null && !"".equals(phone)) {
                    // 联系方式表
                    String contactId = UUIDUtil.randomUUID();
                    Contact contact = new Contact();
                    contact.setGuid(contactId);
                    contact.setStdName(phone);
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
                    customToContact.setCustomId(custId);
                    customToContact.setContactId(contactId);
                    customToContact.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
                    customToContact.setCreateTime(now);
                    customToContact.setModifyTime(now);
                    customToContact.setCreateUser(currUser.getGuid());
                    customToContact.setModifyUser(currUser.getGuid());
                    customToContactDao.add(customToContact);
                }
            }
        }
        
        {
            String email = form.getEmail();
            boolean flag = false; // 标记原数据库有无phone记录
            for (Contact contact : contacts) {
                if(contact.getStdCode()==CONTACT_TYPE.Email.getCode()){
                    flag = true;
                    // 有记录，修改
                    contact.setStdName(email);
                    contact.setModifyTime(now);
                    contact.setModifyUser(currUser.getGuid());
                    contactDao.modify(contact);
                    break;
                }
            }
            if(!flag){ // 原记录中没有phone，新增
                if (email != null && !"".equals(email)) {
                    if (!email.contains("@")) {
                        throw new BusinessException("不是正确的邮箱");
                    }
                    String contactId = UUIDUtil.randomUUID();
                    Contact contact = new Contact();
                    contact.setGuid(contactId);
                    contact.setStdName(email);
                    contact.setStdCode(CONTACT_TYPE.Email.getCode());
                    contact.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
                    contact.setCreateTime(now);
                    contact.setModifyTime(now);
                    contact.setCreateUser(currUser.getGuid());
                    contact.setModifyUser(currUser.getGuid());
                    contactDao.add(contact);

                    CustomToContact customToContact = new CustomToContact();
                    customToContact.setGuid(UUIDUtil.randomUUID());
                    customToContact.setCustomId(custId);
                    customToContact.setContactId(contactId);
                    customToContact.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
                    customToContact.setCreateTime(now);
                    customToContact.setModifyTime(now);
                    customToContact.setCreateUser(currUser.getGuid());
                    customToContact.setModifyUser(currUser.getGuid());
                    customToContactDao.add(customToContact);
                }
            }
        }
        
        {
            String qq = form.getQq();
            boolean flag = false; // 标记原数据库有无phone记录
            for (Contact contact : contacts) {
                if(contact.getStdCode()==CONTACT_TYPE.QQ.getCode()){
                    flag = true;
                    // 有记录，修改
                    contact.setStdName(qq);
                    contact.setModifyTime(now);
                    contact.setModifyUser(currUser.getGuid());
                    contactDao.modify(contact);
                    break;
                }
            }
            if(!flag){ // 原记录中没有phone，新增
                if (form.getQq() != null && !"".equals(form.getQq())) {
                    String contactId = UUIDUtil.randomUUID();
                    Contact contact = new Contact();
                    contact.setGuid(contactId);
                    contact.setStdName(qq);
                    contact.setStdCode(CONTACT_TYPE.QQ.getCode());
                    contact.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
                    contact.setCreateTime(now);
                    contact.setModifyTime(now);
                    contact.setCreateUser(currUser.getGuid());
                    contact.setModifyUser(currUser.getGuid());
                    contactDao.add(contact);

                    CustomToContact customToContact = new CustomToContact();
                    customToContact.setGuid(UUIDUtil.randomUUID());
                    customToContact.setCustomId(custId);
                    customToContact.setContactId(contactId);
                    customToContact.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
                    customToContact.setCreateTime(now);
                    customToContact.setModifyTime(now);
                    customToContact.setCreateUser(currUser.getGuid());
                    customToContact.setModifyUser(currUser.getGuid());
                    customToContactDao.add(customToContact);
                }
            }
        }
        
        {
            String weixin = form.getWeixin();
            boolean flag = false; // 标记原数据库有无phone记录
            for (Contact contact : contacts) {
                if(contact.getStdCode()==CONTACT_TYPE.WeiXin.getCode()){
                    flag = true;
                    // 有记录，修改
                    contact.setStdName(weixin);
                    contact.setModifyTime(now);
                    contact.setModifyUser(currUser.getGuid());
                    contactDao.modify(contact);
                    break;
                }
            }
            if(!flag){ // 原记录中没有phone，新增
                if (form.getWeixin() != null && !"".equals(form.getWeixin())) {
                    String contactId = UUIDUtil.randomUUID();
                    Contact contact = new Contact();
                    contact.setGuid(contactId);
                    contact.setStdName(weixin);
                    contact.setStdCode(CONTACT_TYPE.WeiXin.getCode());
                    contact.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
                    contact.setCreateTime(now);
                    contact.setModifyTime(now);
                    contact.setCreateUser(currUser.getGuid());
                    contact.setModifyUser(currUser.getGuid());
                    contactDao.add(contact);

                    CustomToContact customToContact = new CustomToContact();
                    customToContact.setGuid(UUIDUtil.randomUUID());
                    customToContact.setCustomId(custId);
                    customToContact.setContactId(contactId);
                    customToContact.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
                    customToContact.setCreateTime(now);
                    customToContact.setModifyTime(now);
                    customToContact.setCreateUser(currUser.getGuid());
                    customToContact.setModifyUser(currUser.getGuid());
                    customToContactDao.add(customToContact);
                }
            }
        }
    }
    
    /** 
     * @Title: pCheckCustomOwnner<br>
     * @Description: 客户所有权权限检查<br>
     * @param currUser 当前登录的用户
     * @param id 客户id
     * @throws BusinessException 
     * @throws PermissionException 
     */
    public void pCheckCustomOwnner(CurrUser currUser, String id) throws BusinessException, PermissionException{
        if (id == null || "".equals(id)) {
            throw new BusinessException("未指定客户");
        }
        Custom custom = customDao.get(id);
        if(custom==null){
            throw new BusinessException("没有此客户");
        }
        if(custom.getStatus()!=DB_STATUS.STATUS_ACTIVE){
            throw new BusinessException("此客户已经被删除");
        }
        List<String> ids = customDso.checkCustomOwn(currUser.getGuid(), id);
        if(ids==null || ids.size()==0){
            throw new PermissionException("您无权操作此客户");
        }
    }
    
    /** 
     * @Title: pCheckAddressOwnner<br>
     * @Description: <br>
     * @param currUser
     * @param id
     * @throws BusinessException
     * @throws PermissionException 
     */
    public void pCheckAddressOwnner(CurrUser currUser, String id) throws BusinessException, PermissionException{
        if (id == null || "".equals(id)) {
            throw new BusinessException("未指定地址");
        }
        Address address = addressDao.get(id);
        if(address==null){
            throw new BusinessException("没有此地址");
        }
        if(address.getStatus()!=DB_STATUS.STATUS_ACTIVE){
            throw new BusinessException("此地址已经被删除");
        }
        List<String> ids = customDso.checkAddressOwn(currUser.getGuid(), id);
        if(ids==null || ids.size()==0){
            throw new PermissionException("您无权操作此客户");
        }
    }
    
    /** 
     * @Title: getCustomAddrs<br>
     * @Description: <br>
     * @param currUser
     * @param id
     * @return 
     * @throws PermissionException
     * @throws BusinessException
     */
    public ListDto<AddressDto> getCustomAddrs(CurrUser currUser, String id) throws PermissionException,BusinessException{
        pCheckCustomOwnner(currUser, id);
        List<Address> addrs = customToAddrDao.getAddresses(id);
        List<AddressDto> dtos = new ArrayList<>();
        for(Address addr: addrs){
            AddressDto dto = new AddressDto();
            dto.setId(addr.getGuid());
            dto.setTip(addr.getStdName());
            dto.setAddr(addr.getMemo());
            dtos.add(dto);
        }
        ListDto<AddressDto> list = new ListDto<>();
        list.setRows(dtos);
        return list;
    }
    
    /** 
     * @Title: addAddress<br>
     * @Description: <br>
     * @param currUser
     * @param customId 
     * @param tip
     * @param addr
     * @throws PermissionException 
     * @throws BusinessException
     */
    public void addAddress(CurrUser currUser,String customId, String tip,String addr) throws PermissionException,BusinessException {
        // 先检查编辑客户的权限才能在该客户下添加地址
        pCheckCustomOwnner(currUser, customId);
        
        Timestamp now = new Timestamp(System.currentTimeMillis());

        String addrId = UUIDUtil.randomUUID();
        Address address = new Address();
        address.setGuid(addrId);
        address.setStdName(tip);
        address.setMemo(addr);
        address.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
        address.setCreateTime(now);
        address.setModifyTime(now);
        address.setCreateUser(currUser.getGuid());
        address.setModifyUser(currUser.getGuid());
        addressDao.add(address);

        CustomToAddr customToAddr = new CustomToAddr();
        customToAddr.setGuid(UUIDUtil.randomUUID());
        customToAddr.setCustomId(customId);
        customToAddr.setAddrId(addrId);
        customToAddr.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
        customToAddr.setCreateTime(now);
        customToAddr.setModifyTime(now);
        customToAddr.setCreateUser(currUser.getGuid());
        customToAddr.setModifyUser(currUser.getGuid());
        customToAddrDao.add(customToAddr);
    }
    
    /** 
     * @Title: delAddress<br>
     * @Description: <br>
     * @param currUser
     * @param addrId
     * @throws PermissionException 
     * @throws BusinessException 
     */
    public void delAddress(CurrUser currUser, String addrId) throws BusinessException, PermissionException{
     // 先检查地址的权限才能删除地址
        pCheckAddressOwnner(currUser, addrId);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Address address = addressDao.get(addrId);
        if(address==null){
            throw new BusinessException("该地址不存在");
        }
        address.setStatus(DB_STATUS.STATUS_INACTIVE);
        address.setModifyTime(now);
        address.setModifyUser(currUser.getGuid());
        addressDao.modify(address);
        List<CustomToAddr> customToAddrs = customToAddrDao.getbyAddrId(addrId);
        if(customToAddrs!=null){
            for (CustomToAddr customToAddr : customToAddrs) {
                customToAddr.setStatus(DB_STATUS.STATUS_INACTIVE);
                customToAddr.setModifyTime(now);
                customToAddr.setModifyUser(currUser.getGuid());
                customToAddrDao.modify(customToAddr);
            }
        }
    }
    
    /** 
     * @Title: getCustom<br>
     * @Description: <br>
     * @param currUser
     * @param custId
     * @return 
     * @throws PermissionException 
     * @throws BusinessException 
     */
    public CustomDto getCustom(CurrUser currUser,String custId) throws PermissionException,BusinessException{
        if(custId==null || "".equals(custId)){
            throw new BusinessException("参数不完整");
        }
        pCheckCustomOwnner(currUser,custId);
        
        Custom custom = customDao.get(custId);
        CustomDto customDto = new CustomDto();
        customDto.setId(custId);
        customDto.setName(custom.getStdName());
        customDto.setSex(custom.getSex());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        if(custom.getBirthday()!=null){
            customDto.setBirthday(sdf.format(custom.getBirthday()));
        }
        List<Contact> contacts = customToContactDao.getContacts(custId);
        if(contacts!=null){
            for (Contact contact : contacts) {
                if(contact.getStatus()==DB_STATUS.STATUS_ACTIVE){
                    if(contact.getStdCode()==CONTACT_TYPE.Phone.getCode()){
                        customDto.setPhone(contact.getStdName());
                    }
                    if(contact.getStdCode()==CONTACT_TYPE.Email.getCode()){
                        customDto.setEmail(contact.getStdName());
                    }
                    if(contact.getStdCode()==CONTACT_TYPE.WeiXin.getCode()){
                        customDto.setWeixin(contact.getStdName());
                    }
                    if(contact.getStdCode()==CONTACT_TYPE.QQ.getCode()){
                        customDto.setQq(contact.getStdName());
                    }
                }
            }
        }
        return customDto;
    }
    
}
