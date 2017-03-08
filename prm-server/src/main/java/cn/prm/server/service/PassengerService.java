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
import cn.prm.server.dao.ICountryDao;
import cn.prm.server.dao.ICustomDao;
import cn.prm.server.dao.ICustomToPassengerDao;
import cn.prm.server.dao.IPassengerDao;
import cn.prm.server.dso.ICustomDso;
import cn.prm.server.dto.ListDto;
import cn.prm.server.dto.bean.PassengerDto;
import cn.prm.server.entity.Country;
import cn.prm.server.entity.CustomToPassenger;
import cn.prm.server.entity.Passenger;
import cn.prm.server.exception.BusinessException;
import cn.prm.server.exception.PermissionException;
import cn.prm.server.form.PassengerForm;

/**
 * @Title: PassengerService.java<br>
 * @Package: cn.prm.server.service<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2017年3月8日 上午11:24:03<br>
 * @version v1.0<br>
 */
@Service(value = "passengerService")
public class PassengerService {

    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(PassengerService.class);
    
    @Autowired
    IPassengerDao passengerDao;
    @Autowired
    ICustomDao customDao;
    @Autowired
    ICustomDso customDso;
    @Autowired
    ICountryDao countryDao;
    @Autowired
    ICustomToPassengerDao customToPassengerDao;

    @Autowired
    CustomService customService;
    
    /** 
     * @Title: pCheckPassengerOwnner<br>
     * @Description: <br>
     * @param currUser
     * @param id
     * @throws BusinessException
     * @throws PermissionException
     */
    public void pCheckPassengerOwnner(CurrUser currUser, String id) throws BusinessException, PermissionException {
        log.info("S检查乘客权限");
        if (id == null || "".equals(id)) {
            throw new BusinessException("未指定乘客");
        }
        Passenger passenger = passengerDao.get(id);
        if (passenger == null) {
            throw new BusinessException("没有此乘客");
        }
        if (passenger.getStatus() != DB_STATUS.STATUS_ACTIVE) {
            throw new BusinessException("此乘客已经被删除");
        }
//        List<String> ids = customDso.checkAddressOwn(currUser.getGuid(), id);
        List<String> ids = customDso.checkPassengerOwn(currUser.getGuid(), id);
        if (ids == null || ids.size() == 0) {
            throw new PermissionException("您无权操作此乘客");
        }
    }
    
    /** 
     * @Title: getCustomPsgs<br>
     * @Description: <br>
     * @param currUser
     * @param id
     * @return
     * @throws PermissionException
     * @throws BusinessException
     */
    public ListDto<PassengerDto> getCustomPsgs(CurrUser currUser, String id)
            throws PermissionException, BusinessException {
        log.info("获取乘客");
        customService.pCheckCustomOwnner(currUser, id);
        List<Passenger> psgs = customToPassengerDao.getPassengers(id);
        List<PassengerDto> dtos = new ArrayList<>();
        for(Passenger psg : psgs){
            PassengerDto dto = new PassengerDto();
            dto.setId(psg.getGuid());
            dto.setName(psg.getStdName());
            dto.setIdCard(psg.getIdCard());
            dto.setSex(psg.getSex());
            String countryId = psg.getCountryId();
            dto.setCountryId(countryId);
            if(countryId!=null && !"".equals(countryId)){
                Country country = countryDao.get(psg.getCountryId());
                if(country!=null){
                    dto.setCountryCn(country.getCnName());
                    dto.setCountryEn(country.getEnName());
                    dto.setCountryCode(country.getStdName());
                }
            }
            dtos.add(dto);
        }
        ListDto<PassengerDto> list = new ListDto<>();
        list.setRows(dtos);
        return list;
    }
    
    /** 
     * @Title: AddPassenger<br>
     * @Description: <br>
     * @param currUser
     * @param form
     * @throws PermissionException 
     * @throws BusinessException 
     */
    @Transactional
    public void add(CurrUser currUser,PassengerForm form) throws PermissionException, BusinessException{
        log.info("S添加乘客");
        // 先检查编辑客户的权限才能在该客户下添加地址
        customService.pCheckCustomOwnner(currUser, form.getCustomId());

        Timestamp now = new Timestamp(System.currentTimeMillis());

        String psgId = UUIDUtil.randomUUID();
        
        Passenger passenger = new Passenger();
        passenger.setGuid(psgId);
        passenger.setStdName(form.getName());
        passenger.setIdCard(form.getIdCard());
        passenger.setSex(form.getSex());
        passenger.setCountryId(form.getCountryId());
        passenger.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
        passenger.setCreateTime(now);
        passenger.setModifyTime(now);
        passenger.setCreateUser(currUser.getGuid());
        passenger.setModifyUser(currUser.getGuid());
        passengerDao.add(passenger);
        
        CustomToPassenger customToPassenger = new CustomToPassenger();
        customToPassenger.setGuid(UUIDUtil.randomUUID());
        customToPassenger.setCustomId(form.getCustomId());
        customToPassenger.setPassengerId(psgId);
        customToPassenger.setStatus(Constants.DB_STATUS.STATUS_ACTIVE);
        customToPassenger.setCreateTime(now);
        customToPassenger.setModifyTime(now);
        customToPassenger.setCreateUser(currUser.getGuid());
        customToPassenger.setModifyUser(currUser.getGuid());
        customToPassengerDao.add(customToPassenger);
    }

    /**
     * @Title: delPassenger<br>
     * @Description: <br>
     * @param currUser
     * @param psgId
     * @throws PermissionException
     * @throws BusinessException
     */
    @Transactional
    public void delPassenger(CurrUser currUser, String psgId) throws BusinessException, PermissionException {
        log.info("S删除乘客");
        // 先检查地址的权限才能删除地址
        pCheckPassengerOwnner(currUser, psgId);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Passenger passenger = passengerDao.get(psgId);
        if (passenger == null) {
            throw new BusinessException("该乘客不存在");
        }
        passenger.setStatus(DB_STATUS.STATUS_INACTIVE);
        passenger.setModifyTime(now);
        passenger.setModifyUser(currUser.getGuid());
        passengerDao.modify(passenger);
        List<CustomToPassenger> customToPassengers = customToPassengerDao.getByPsgId(psgId);
                
        if (customToPassengers != null) {
            for (CustomToPassenger customToPassenger : customToPassengers) {
                customToPassenger.setStatus(DB_STATUS.STATUS_INACTIVE);
                customToPassenger.setModifyTime(now);
                customToPassenger.setModifyUser(currUser.getGuid());
                customToPassengerDao.modify(customToPassenger);
            }
        }
    }

    /** 
     * @Title: getPassenger<br>
     * @Description: <br>
     * @param currUser
     * @param psgId
     * @return
     * @throws PermissionException
     * @throws BusinessException
     */
    public PassengerDto getPassenger(CurrUser currUser, String psgId) throws PermissionException, BusinessException {
        log.info("S获取乘客");
        if (psgId == null || "".equals(psgId)) {
            throw new BusinessException("参数不完整");
        }
        pCheckPassengerOwnner(currUser, psgId);
        Passenger passenger = passengerDao.get(psgId);
        PassengerDto passengerDto = new PassengerDto();
        passengerDto.setId(passenger.getGuid());
        passengerDto.setName(passenger.getStdName());
        passengerDto.setIdCard(passenger.getIdCard());
        passengerDto.setSex(passenger.getSex());
        
        String countryId = passenger.getCountryId();
        passengerDto.setCountryId(countryId);
        if(countryId!=null && !"".equals(countryId)){
            Country country = countryDao.get(passenger.getCountryId());
            if(country!=null){
                passengerDto.setCountryCn(country.getCnName());
                passengerDto.setCountryEn(country.getEnName());
                passengerDto.setCountryCode(country.getStdName());
            }
        }
        return passengerDto;
    }
    
    /** 
     * @Title: modify<br>
     * @Description: <br>
     * @param currUser
     * @param pasId
     * @param form
     * @throws PermissionException
     * @throws BusinessException
     */
    public void modify(CurrUser currUser, String pasId, PassengerForm form)
            throws PermissionException, BusinessException {
        log.info("S编辑乘客");
        if (form == null) {
            throw new BusinessException("参数不完整");
        }
        pCheckPassengerOwnner(currUser, pasId);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Passenger passenger = passengerDao.get(pasId);
        passenger.setStdName(form.getName());
        passenger.setIdCard(form.getIdCard());
        passenger.setSex(form.getSex());
        passenger.setCountryId(form.getCountryId());
        passenger.setModifyTime(now);
        passenger.setModifyUser(currUser.getGuid());
        passengerDao.modify(passenger);
    }
    
}
