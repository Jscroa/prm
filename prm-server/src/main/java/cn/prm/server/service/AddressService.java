/**
 * 
 */
package cn.prm.server.service;

import java.sql.Timestamp;
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
import cn.prm.server.dao.IAddressDao;
import cn.prm.server.dao.ICustomDao;
import cn.prm.server.dao.ICustomToAddrDao;
import cn.prm.server.dso.ICustomDso;
import cn.prm.server.dto.ListDto;
import cn.prm.server.dto.bean.AddressDto;
import cn.prm.server.entity.Address;
import cn.prm.server.entity.CustomToAddr;
import cn.prm.server.exception.BusinessException;
import cn.prm.server.exception.PermissionException;
import cn.prm.server.form.AddressForm;

/**
 * @Title: AddressService.java<br>
 * @Package: cn.prm.server.service<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年12月2日 上午11:25:29<br>
 * @version v1.0<br>
 */
@Service(value = "addressService")
public class AddressService {

    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(AddressService.class);

    @Autowired
    IAddressDao addressDao;
    @Autowired
    ICustomDao customDao;
    @Autowired
    ICustomDso customDso;
    @Autowired
    ICustomToAddrDao customToAddrDao;
    
    @Autowired
    CustomService customService;

    /**
     * @Title: pCheckAddressOwnner<br>
     * @Description: <br>
     * @param currUser
     * @param id
     * @throws BusinessException
     * @throws PermissionException
     */
    public void pCheckAddressOwnner(CurrUser currUser, String id) throws BusinessException, PermissionException {
        log.info("S检查地址的权限 ->user:"+currUser.getGuid()+" addr:"+id);
        if (id == null || "".equals(id)) {
            throw new BusinessException("未指定地址");
        }
        Address address = addressDao.get(id);
        if (address == null) {
            throw new BusinessException("没有此地址");
        }
        if (address.getStatus() != DB_STATUS.STATUS_ACTIVE) {
            throw new BusinessException("此地址已经被删除");
        }
        List<String> ids = customDso.checkAddressOwn(currUser.getGuid(), id);
        if (ids == null || ids.size() == 0) {
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
    public ListDto<AddressDto> getCustomAddrs(CurrUser currUser, String id)
            throws PermissionException, BusinessException {
        log.info("S获取客户地址");
        customService.pCheckCustomOwnner(currUser, id);
        List<Address> addrs = customToAddrDao.getAddresses(id);
        List<AddressDto> dtos = new ArrayList<>();
        for (Address addr : addrs) {
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
     * @Title: AddAddress<br>
     * @Description: <br>
     * @param currUser
     * @param form
     * @throws PermissionException 
     * @throws BusinessException 
     */
    @Transactional
    public void addAddress(CurrUser currUser,AddressForm form) throws PermissionException, BusinessException{
        log.info("S添加地址");
        // 先检查编辑客户的权限才能在该客户下添加地址
        customService.pCheckCustomOwnner(currUser, form.getCustomId());

        Timestamp now = new Timestamp(System.currentTimeMillis());

        String addrId = UUIDUtil.randomUUID();
        Address address = new Address();
        address.setGuid(addrId);
        address.setStdName(form.getTip());
        address.setMemo(form.getAddr());
        address.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
        address.setCreateTime(now);
        address.setModifyTime(now);
        address.setCreateUser(currUser.getGuid());
        address.setModifyUser(currUser.getGuid());
        addressDao.add(address);

        CustomToAddr customToAddr = new CustomToAddr();
        customToAddr.setGuid(UUIDUtil.randomUUID());
        customToAddr.setCustomId(form.getCustomId());
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
    public void delAddress(CurrUser currUser, String addrId) throws BusinessException, PermissionException {
        log.info("S删除地址");
        // 先检查地址的权限才能删除地址
        pCheckAddressOwnner(currUser, addrId);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Address address = addressDao.get(addrId);
        if (address == null) {
            throw new BusinessException("该地址不存在");
        }
        address.setStatus(DB_STATUS.STATUS_INACTIVE);
        address.setModifyTime(now);
        address.setModifyUser(currUser.getGuid());
        addressDao.modify(address);
        List<CustomToAddr> customToAddrs = customToAddrDao.getbyAddrId(addrId);
        if (customToAddrs != null) {
            for (CustomToAddr customToAddr : customToAddrs) {
                customToAddr.setStatus(DB_STATUS.STATUS_INACTIVE);
                customToAddr.setModifyTime(now);
                customToAddr.setModifyUser(currUser.getGuid());
                customToAddrDao.modify(customToAddr);
            }
        }
    }

    /** 
     * @Title: getAddress<br>
     * @Description: <br>
     * @param currUser
     * @param addrId
     * @return
     * @throws PermissionException
     * @throws BusinessException
     */
    public AddressDto getAddress(CurrUser currUser, String addrId) throws PermissionException, BusinessException {
        log.info("S获取地址");
        if (addrId == null || "".equals(addrId)) {
            throw new BusinessException("参数不完整");
        }
        pCheckAddressOwnner(currUser, addrId);
        Address address = addressDao.get(addrId);
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getGuid());
        addressDto.setTip(address.getStdName());
        addressDto.setAddr(address.getMemo());
        return addressDto;
    }
    
    /** 
     * @Title: modify<br>
     * @Description: <br>
     * @param currUser
     * @param addrId
     * @param form
     * @throws PermissionException
     * @throws BusinessException
     */
    public void modify(CurrUser currUser, String addrId, AddressForm form)
            throws PermissionException, BusinessException {
        log.info("S编辑地址");
        if (form == null) {
            throw new BusinessException("参数不完整");
        }
        pCheckAddressOwnner(currUser, addrId);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Address address = addressDao.get(addrId);
        address.setStdName(form.getTip());
        address.setMemo(form.getAddr());
        address.setModifyTime(now);
        address.setModifyUser(currUser.getGuid());
        addressDao.modify(address);
    }
    
}
