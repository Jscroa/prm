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
import cn.prm.server.commons.Constants.DB_STATUS;
import cn.prm.server.commons.UUIDUtil;
import cn.prm.server.dao.IAccToGroupDao;
import cn.prm.server.dao.IAddressDao;
import cn.prm.server.dao.IContactDao;
import cn.prm.server.dao.ICustomDao;
import cn.prm.server.dao.ICustomToAddrDao;
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
    IAccToGroupDao              accToGroupDao;
    @Autowired
    IGroupToCustomDao           groupToCustomDao;
    @Autowired
    IAddressDao                 addressDao;
    @Autowired
    ICustomToAddrDao            customToAddrDao;
    @Autowired
    ICustomDso                  customDso;

    /**
     * @Title: getPrivateCustoms<br>
     * @Description: 查询私有组的所有用户<br>
     * @param currUser
     * @param search
     * @param order
     * @return
     * @throws BusinessException
     */
    public ListDto<CustomDto> getPrivateCustoms(CurrUser currUser, String search, String order)
            throws BusinessException {
        log.info("获取私有客户（所有）");
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
        List<CustomDto> dtos = new ArrayList<>();
        if (search == null || "".equals(search)) {
            dtos = customDso.getCustoms(group.getGuid());
        }
        else {
            dtos = customDso.getCustoms(group.getGuid(), "%" + search + "%");
        }

        ListDto<CustomDto> page = new ListDto<>();
        page.setRows(dtos);
        return page;
    }

    /**
     * @Title: getPrivateCustoms<br>
     * @Description: 分页查询私有组的用户<br>
     * @param currUser
     *            当前登录的用户
     * @param search
     *            查询参数
     * @param order
     *            排序参数
     * @param offset
     *            偏移量
     * @param limit
     *            每页显示数量
     * @return
     * @throws BusinessException
     */
    public PageDto<CustomDto> getPrivateCustoms(CurrUser currUser, String search, String order, int offset, int limit)
            throws BusinessException {
        log.info("获取私有客户（分页）");
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
        List<CustomDto> dtos = new ArrayList<>();
        if (search == null || "".equals(search)) {
            dtos = customDso.getCustoms(group.getGuid(), offset, limit);
        }
        else {
            dtos = customDso.getCustoms(group.getGuid(), "%" + search + "%", offset, limit);
        }
        int total = customDso.getCustomCount();

        PageDto<CustomDto> page = new PageDto<>();
        page.setRows(dtos);
        page.setTotal(total);
        page.setPage(offset / limit + 1);
        return page;
    }

    /**
     * @Title: addPrivateCustom<br>
     * @Description: <br>
     * @param currUser
     *            当前登录的用户
     * @param form
     *            新建客户表单
     * @throws BusinessException
     */
    @Transactional
    public void addPrivateCustom(CurrUser currUser, CustomForm form) throws BusinessException {
        log.info("添加私有客户");
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

        Contact contact = new Contact();
        contact.setGuid(UUIDUtil.randomUUID());
        contact.setCustomId(customId);
        contact.setPhone(form.getPhone());
        contact.setEmail(form.getEmail());
        contact.setQq(form.getQq());
        contact.setWeixin(form.getWeixin());
        contact.setStatus(DB_STATUS.STATUS_ACTIVE);
        contact.setCreateTime(now);
        contact.setModifyTime(now);
        contact.setCreateUser(currUser.getGuid());
        contact.setModifyUser(currUser.getGuid());
        contactDao.add(contact);

    }

    /**
     * @Title: delete<br>
     * @Description: <br>
     * @param currUser
     *            当前登录的用户
     * @param id
     *            客户id
     * @throws BusinessException
     * @throws PermissionException
     */
    public void delete(CurrUser currUser, String id) throws BusinessException, PermissionException {
        log.info("删除客户");
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
    public void modify(CurrUser currUser, String custId, CustomForm form)
            throws PermissionException, BusinessException {
        log.info("编辑客户");
        if (form == null) {
            throw new BusinessException("参数不完整");
        }
        pCheckCustomOwnner(currUser, custId);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Custom custom = customDao.get(custId);
        custom.setStdName(form.getName());
        custom.setSex(form.getSex());
        // custom.setBirthday(form.getBirthday());
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
        List<Contact> contacts = contactDao.getByCustomId(custId);
        if (contacts.isEmpty() || contacts.size() != 1) {
            throw new BusinessException("该客户数据有误，建议删除后重新添加。");
        }
        Contact contact = contacts.get(0);
        contact.setPhone(form.getPhone());
        contact.setEmail(form.getEmail());
        contact.setQq(form.getQq());
        contact.setWeixin(form.getWeixin());
        contact.setModifyTime(now);
        contact.setModifyUser(currUser.getGuid());
        contactDao.modify(contact);
    }

    /**
     * @Title: pCheckCustomOwnner<br>
     * @Description: 客户所有权权限检查<br>
     * @param currUser
     *            当前登录的用户
     * @param id
     *            客户id
     * @throws BusinessException
     * @throws PermissionException
     */
    public void pCheckCustomOwnner(CurrUser currUser, String id) throws BusinessException, PermissionException {
        log.info("检查客户权限");
        if (id == null || "".equals(id)) {
            throw new BusinessException("未指定客户");
        }
        Custom custom = customDao.get(id);
        if (custom == null) {
            throw new BusinessException("没有此客户");
        }
        if (custom.getStatus() != DB_STATUS.STATUS_ACTIVE) {
            throw new BusinessException("此客户已经被删除");
        }
        List<String> ids = customDso.checkCustomOwn(currUser.getGuid(), id);
        if (ids == null || ids.size() == 0) {
            throw new PermissionException("您无权操作此客户");
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
    public CustomDto getCustom(CurrUser currUser, String custId) throws PermissionException, BusinessException {
        log.info("获取客户");
        if (custId == null || "".equals(custId)) {
            throw new BusinessException("参数不完整");
        }
        pCheckCustomOwnner(currUser, custId);

        Custom custom = customDao.get(custId);
        CustomDto customDto = new CustomDto();
        customDto.setId(custId);
        customDto.setName(custom.getStdName());
        customDto.setSex(custom.getSex());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        if (custom.getBirthday() != null) {
            customDto.setBirthday(sdf.format(custom.getBirthday()));
        }
        List<Contact> contacts = contactDao.getByCustomId(custId);
        if (contacts.isEmpty() || contacts.size() != 1) {
            throw new BusinessException("该客户数据有误，建议删除后重新添加。");
        }
        Contact contact = contacts.get(0);
        customDto.setPhone(contact.getPhone());
        customDto.setEmail(contact.getEmail());
        customDto.setQq(contact.getQq());
        customDto.setWeixin(contact.getWeixin());
        return customDto;
    }

}
