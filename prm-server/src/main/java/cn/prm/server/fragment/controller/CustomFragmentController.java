/**
 * 
 */
package cn.prm.server.fragment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Title: CustomFragmentController.java<br>
 * @Package: cn.prm.server.fragment.controller<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月4日 下午4:32:59<br>
 * @version v1.0<br>
 */
@Controller
@RequestMapping("/fragment/custom")
public class CustomFragmentController {

    /** 
     * @Title: customAddress<br>
     * @Description: <br>
     * @param custId
     * @return
     */
    @RequestMapping("customAddress")
    public ModelAndView customAddress(String custId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("custom/custom_address");
        mav.addObject("custId", custId);
        return mav;
    }
    
}
