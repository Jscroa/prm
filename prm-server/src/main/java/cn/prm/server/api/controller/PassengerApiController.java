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
import cn.prm.server.dto.bean.BeanDto;
import cn.prm.server.dto.bean.PassengerDto;
import cn.prm.server.exception.BusinessException;
import cn.prm.server.exception.PermissionException;
import cn.prm.server.form.PassengerForm;
import cn.prm.server.service.PassengerService;

/**
 * @Title: PassengerApiController.java<br>
 * @Package: cn.prm.server.api.controller<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2017年3月8日 上午11:22:26<br>
 * @version v1.0<br>
 */
@RestController
@RequestMapping("/api/passenger")
public class PassengerApiController extends BaseController {

    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(PassengerApiController.class);

    @Autowired
    PassengerService            passengerService;

    /**
     * @Title: list<br>
     * @Description: <br>
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
            ListDto<PassengerDto> list = passengerService.getCustomPsgs(currUser, custId);
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
     * @Title: getPassenger<br>
     * @Description: <br>
     * @param request
     * @param psgId
     * @return
     */
    @RequestMapping("/getPassenger")
    public Object getPassenger(HttpServletRequest request, String psgId) {
        try {
            CurrUser currUser = getCurrUser(request);
            if (currUser == null) {
                return new BaseDto(RESPONSE_CODE.CODE_NEED_LOGIN, "您还未登录");
            }
            PassengerDto passengerDto = passengerService.getPassenger(currUser, psgId);
            return new BeanDto<PassengerDto>(passengerDto);
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
    public Object add(HttpServletRequest request, PassengerForm form) {
        try {
            CurrUser currUser = getCurrUser(request);
            if (currUser == null) {
                return new BaseDto(RESPONSE_CODE.CODE_NEED_LOGIN, "您还未登录");
            }
            form.checkFields();
            passengerService.add(currUser, form);
            return new BaseDto(RESPONSE_CODE.CODE_SUCCESS, "添加成功");
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
     * @Description: <br>
     * @param request
     * @param psgId
     * @return
     */
    @RequestMapping("/del")
    public Object del(HttpServletRequest request, String psgId) {
        try {
            CurrUser currUser = getCurrUser(request);
            if (currUser == null) {
                return new BaseDto(RESPONSE_CODE.CODE_NEED_LOGIN, "您还未登录");
            }
            if (psgId == null || "".equals(psgId)) {
                return new BaseDto(RESPONSE_CODE.CODE_FAILURE, "参数不完整");
            }
            passengerService.delPassenger(currUser, psgId);
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
     * @param psgId
     * @param form
     * @return
     */
    @RequestMapping("/modify")
    public Object modify(HttpServletRequest request, String psgId, PassengerForm form) {
        try {
            CurrUser currUser = getCurrUser(request);
            if (currUser == null) {
                return new BaseDto(RESPONSE_CODE.CODE_NEED_LOGIN, "您还未登录");
            }
            form.checkFields();
            passengerService.modify(currUser, psgId, form);
            return new BaseDto(RESPONSE_CODE.CODE_SUCCESS, "修改成功");
        }
        catch (PermissionException e) {
            e.printStackTrace();
            return new BaseDto(RESPONSE_CODE.CODE_PERMISSION_DENIED, e.getMessage());
        }
        catch (BusinessException e) {
            e.printStackTrace();
            return new BaseDto(RESPONSE_CODE.CODE_FAILURE, e.getMessage());
        }
    }

}
