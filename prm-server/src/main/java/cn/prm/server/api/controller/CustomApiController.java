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
import cn.prm.server.dto.PageDto;
import cn.prm.server.dto.bean.BeanDto;
import cn.prm.server.dto.bean.CustomDto;
import cn.prm.server.exception.BusinessException;
import cn.prm.server.exception.PermissionException;
import cn.prm.server.form.CustomForm;
import cn.prm.server.form.PageBaseForm;
import cn.prm.server.service.CustomService;

/**
 * @Title: CustomApiController.java<br>
 * @Package: cn.prm.server.api.controller<br>
 * @Description: 关于客户的数据接口<br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:19:41<br>
 * @version v1.0<br>
 */
@RestController
@RequestMapping("/api/custom")
public class CustomApiController extends BaseController {

    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(CustomApiController.class);

    @Autowired
    CustomService               customService;

    /**
     * @Title: list<br>
     * @Description: 当前登录账户下的私有客户<br>
     * @param request
     * @param form
     * @return
     */
    @RequestMapping("/list")
    public Object list(HttpServletRequest request, PageBaseForm form) {
        try {
            CurrUser currUser = getCurrUser(request);
            if (currUser == null) {
                return new BaseDto(RESPONSE_CODE.CODE_NEED_LOGIN, "您还未登录");
            }

            // 检查form字段是否合格
            form.checkFields();
            if(form.getLimit()>0){
                PageDto<CustomDto> page = customService.getPrivateCustoms(currUser, form.getSearch(), form.getOrder(),
                        form.getOffset(), form.getLimit());
                page.setCode(RESPONSE_CODE.CODE_SUCCESS);
                return page;
            }else{
                ListDto<CustomDto> list = customService.getPrivateCustoms(currUser, form.getSearch(), form.getOrder());
                list.setCode(RESPONSE_CODE.CODE_SUCCESS);
                return list;
            }
        }
        catch (BusinessException e) {
            e.printStackTrace();
            return new BaseDto(RESPONSE_CODE.CODE_FAILURE, e.getMessage());
        }
    }

    /**
     * @Title: getCustom<br>
     * @Description: 获取客户信息，姓名，联系方式等等(编辑用)<br>
     * @param request
     * @param custId
     * @return
     */
    @RequestMapping("/getCustom")
    public Object getCustom(HttpServletRequest request, String custId) {
        try {
            CurrUser currUser = getCurrUser(request);
            if (currUser == null) {
                return new BaseDto(RESPONSE_CODE.CODE_NEED_LOGIN, "您还未登录");
            }
            CustomDto customDto = customService.getCustom(currUser, custId);
            return new BeanDto<CustomDto>(customDto);
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
     * @Description: 在当前登录的账户下添加私有客户<br>
     * @param request
     * @param form
     * @return
     */
    @RequestMapping("/add")
    public Object add(HttpServletRequest request, CustomForm form) {
        try {
            CurrUser currUser = getCurrUser(request);
            if (currUser == null) {
                return new BaseDto(RESPONSE_CODE.CODE_NEED_LOGIN, "您还未登录");
            }
            // 检查form字段是否合格
            form.checkFields();
            customService.addPrivateCustom(currUser, form);
            return new BaseDto(RESPONSE_CODE.CODE_SUCCESS, "添加成功");
        }
        catch (BusinessException e) {
            e.printStackTrace();
            return new BaseDto(RESPONSE_CODE.CODE_FAILURE, e.getMessage());
        }
    }

    /**
     * @Title: delete<br>
     * @Description: 在当前登录的账户下删除私有客户<br>
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/del")
    public Object delete(HttpServletRequest request, String id) {
        try {
            CurrUser currUser = getCurrUser(request);
            if (currUser == null) {
                return new BaseDto(RESPONSE_CODE.CODE_NEED_LOGIN, "您还未登录");
            }
            customService.delete(currUser, id);
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
     * @Description: 编辑客户<br>
     * @param request
     * @param custId 
     * @param form
     * @return
     */
    @RequestMapping("/modify")
    public Object modify(HttpServletRequest request, String custId, CustomForm form) {
        try{
            CurrUser currUser = getCurrUser(request);
            if (currUser == null) {
                return new BaseDto(RESPONSE_CODE.CODE_NEED_LOGIN, "您还未登录");
            }
            form.checkFields();
            customService.modify(currUser, custId, form);
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
