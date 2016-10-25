package cn.prm.server.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title: ErrorController.java<br>
 * @Package: cn.prm.server.page.controller<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:41:35<br>
 * @version v1.0<br>
 */
@Controller
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

    @Override
    @RequestMapping(value = "/error")
    public String getErrorPath() {
        return "error/404";
    }
}
