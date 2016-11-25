/**
 * 
 */
package cn.prm.server.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.prm.server.commons.Constants.RESPONSE_CODE;
import cn.prm.server.dto.BaseDto;

/**
 * @Title: ErrorApiController.java<br>
 * @Package: cn.prm.server.api.controller<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月4日 下午4:05:03<br>
 * @version v1.0<br>
 */
@Controller
@RequestMapping("/error/api")
public class ErrorApiController {
    
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(ErrorApiController.class);
    
    /** 
     * @Title: noLogin<br>
     * @Description: 未登录信息以json的形式返回<br>
     * @return
     */
    @RequestMapping("/noLogin")
    @ResponseBody
    public Object noLogin(){
        return new BaseDto(RESPONSE_CODE.CODE_NEED_LOGIN, "您还未登录");
    }

}
