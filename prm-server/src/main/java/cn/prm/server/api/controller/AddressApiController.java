/**
 * 
 */
package cn.prm.server.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.prm.server.bean.CurrUser;
import cn.prm.server.commons.BaseController;
import cn.prm.server.commons.Constants.RESPONSE_CODE;
import cn.prm.server.dto.BaseDto;
import cn.prm.server.dto.ListDto;
import cn.prm.server.dto.bean.AddressDto;
import cn.prm.server.dto.bean.BeanDto;
import cn.prm.server.exception.BusinessException;
import cn.prm.server.exception.PermissionException;
import cn.prm.server.form.AddressForm;
import cn.prm.server.form.CustomForm;
import cn.prm.server.service.AddressService;

/**
 * @Title: AddressApiController.java<br>
 * @Package: cn.prm.server.api.controller<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年12月2日 上午11:24:29<br>
 * @version v1.0<br>
 */
@RestController
@RequestMapping("/api/address")
public class AddressApiController extends BaseController{

    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(AddressApiController.class);
    
    @Autowired
    AddressService addressService;

    /**
     * @Title: list<br>
     * @Description: 某客户的地址列表<br>
     * @param request
     * @param custId
     * @return
     */
    @RequestMapping("/list")
    public Object list(HttpServletRequest request, String custId) {

        try {
            CurrUser currUser = getCurrUser(request);
            if (currUser == null) {
                return new BaseDto(RESPONSE_CODE.CODE_NEED_LOGIN, "您还未登录");
            }
            ListDto<AddressDto> list = addressService.getCustomAddrs(currUser, custId);
            list.setCode(RESPONSE_CODE.CODE_SUCCESS);
            return list;
        }
        catch (BusinessException e) {
            e.printStackTrace();
            return new BaseDto(RESPONSE_CODE.CODE_FAILURE, e.getMessage());
        }
        catch (PermissionException e) {
            e.printStackTrace();
            return new BaseDto(RESPONSE_CODE.CODE_PERMISSION_DENIED, e.getMessage());
        }
    }
    
    /** 
     * @Title: getAddress<br>
     * @Description: <br>
     * @param request
     * @param addrId
     * @return
     */
    @RequestMapping("/getAddress")
    public Object getAddress(HttpServletRequest request,String addrId) {
        try{
        CurrUser currUser = getCurrUser(request);
        if(currUser == null){
            return new BaseDto(RESPONSE_CODE.CODE_NEED_LOGIN,"您还未登录");
        }
        AddressDto addressDto = addressService.getAddress(currUser, addrId);
        return new BeanDto<AddressDto>(addressDto);
        }
        catch (BusinessException e) {
            e.printStackTrace();
            return new BaseDto(RESPONSE_CODE.CODE_FAILURE, e.getMessage());
        }
        catch (PermissionException e) {
            e.printStackTrace();
            return new BaseDto(RESPONSE_CODE.CODE_PERMISSION_DENIED, e.getMessage());
        }
    }
    
    /** 
     * @Title: add<br>
     * @Description: <br>
     * @param request
     * @param form
     * @return
     */
    @RequestMapping("/add")
    public Object add(HttpServletRequest request, AddressForm form){
        try{
            CurrUser currUser = getCurrUser(request);
            if(currUser==null){ return new BaseDto(RESPONSE_CODE.CODE_NEED_LOGIN,"您还未登录"); }
            form.checkFields();
            addressService.AddAddress(currUser, form);
            return new BaseDto(RESPONSE_CODE.CODE_SUCCESS,"添加成功");
        }
        catch (BusinessException e) {
            e.printStackTrace();
            return new BaseDto(RESPONSE_CODE.CODE_FAILURE, e.getMessage());
        }
        catch (PermissionException e) {
            e.printStackTrace();
            return new BaseDto(RESPONSE_CODE.CODE_PERMISSION_DENIED, e.getMessage());
        }
    }
    
    /** 
     * @Title: del<br>
     * @Description: 删除地址<br>
     * @param request
     * @param addrId
     * @return
     */
    @RequestMapping("/del")
    public Object del(HttpServletRequest request, String addrId){
        try {
            CurrUser currUser = getCurrUser(request);
            if (currUser == null) {
                return new BaseDto(RESPONSE_CODE.CODE_NEED_LOGIN, "您还未登录");
            }
            if (addrId==null || "".equals(addrId)) {
                return new BaseDto(RESPONSE_CODE.CODE_FAILURE, "参数不完整");
            }
            addressService.delAddress(currUser, addrId);
            return new BaseDto(RESPONSE_CODE.CODE_SUCCESS, "删除成功");
        }
        catch (BusinessException e) {
            e.printStackTrace();
            return new BaseDto(RESPONSE_CODE.CODE_FAILURE, e.getMessage());
        }
        catch (PermissionException e) {
            e.printStackTrace();
            return new BaseDto(RESPONSE_CODE.CODE_PERMISSION_DENIED, e.getMessage());
        }
    }

    /** 
     * @Title: modify<br>
     * @Description: <br>
     * @param request
     * @param addrId
     * @param form
     * @return
     */
    @RequestMapping("/modify")
    public Object modify(HttpServletRequest request, String addrId, AddressForm form) {
        try{
            CurrUser currUser = getCurrUser(request);
            if (currUser == null) {
                return new BaseDto(RESPONSE_CODE.CODE_NEED_LOGIN, "您还未登录");
            }
            form.checkFields();
            addressService.modify(currUser, addrId, form);
            return new BaseDto(RESPONSE_CODE.CODE_SUCCESS,"修改成功");
        }catch (PermissionException e) {
            e.printStackTrace();
            return new BaseDto(RESPONSE_CODE.CODE_PERMISSION_DENIED, e.getMessage());
        }
        catch (BusinessException e) {
            e.printStackTrace();
            return new BaseDto(RESPONSE_CODE.CODE_FAILURE, e.getMessage());
        }
    }
    
}
