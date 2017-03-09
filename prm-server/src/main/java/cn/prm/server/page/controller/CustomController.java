package cn.prm.server.page.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.prm.server.bean.CurrUser;
import cn.prm.server.commons.BaseController;
import cn.prm.server.commons.MAVHelper;

/**
 * @Title: CustomController.java<br>
 * @Package: cn.prm.server.page.controller<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年10月19日 下午5:41:28<br>
 * @version v1.0<br>
 */
@Controller
@RequestMapping("/custom")
public class CustomController extends BaseController {

    @Autowired
    private MAVHelper mavHelper;

    /**
     * @Title: index<br>
     * @Description: <br>
     * @param request
     * @return
     */
    @RequestMapping("")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        CurrUser currUser = getCurrUser(request);
        if (currUser == null) {
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.setViewName("custom/custom");
        mavHelper.withUserName(mav, currUser.getName());
        return mav;
    }
    
    /** 
     * @Title: customIndex<br>
     * @Description: <br>
     * @return
     */
    @RequestMapping("customIndex")
    public ModelAndView customIndex(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("custom/custom_index");
        return mav;
    }
    
    /** 
     * @Title: passenger<br>
     * @Description: <br>
     * @param request
     * @param custId 
     * @return
     */
    @RequestMapping("passenger")
    public ModelAndView passenger(HttpServletRequest request,@RequestParam(required=true) String custId){
        ModelAndView mav = new ModelAndView();
        CurrUser currUser = getCurrUser(request);
        if (currUser == null) {
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.setViewName("custom/passenger");
        mavHelper.withUserName(mav, currUser.getName());
        return mav;
    }
    
    /** 
     * @Title: passengerIndex<br>
     * @Description: <br>
     * @return
     */
    @RequestMapping("passengerIndex")
    public ModelAndView passengerIndex(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("custom/passenger_index");
        return mav;
    }
    
    /** 
     * @Title: address<br>
     * @Description: <br>
     * @param request
     * @param custId
     * @return
     */
    @RequestMapping("address")
    public ModelAndView address(HttpServletRequest request,@RequestParam(required=true) String custId){
        ModelAndView mav = new ModelAndView();
        CurrUser currUser = getCurrUser(request);
        if (currUser == null) {
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.setViewName("custom/address");
        mavHelper.withUserName(mav, currUser.getName());
        return mav;
    }
    
    /** 
     * @Title: addressIndex<br>
     * @Description: <br>
     * @return
     */
    @RequestMapping("addressIndex")
    public ModelAndView addressIndex(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("custom/address_index");
        return mav;
    }
    
    /** 
     * @Title: detail<br>
     * @Description: <br>
     * @param request
     * @param custId
     * @return
     */
    @RequestMapping("/detail")
    public ModelAndView detail(HttpServletRequest request,@RequestParam(required=true) String custId){
        ModelAndView mav = new ModelAndView();
        CurrUser currUser = getCurrUser(request);
        if (currUser == null) {
            mav.setViewName("redirect:/login");
            return mav;
        }
        mav.setViewName("custom/detail");
        mavHelper.withUserName(mav, currUser.getName());
        return mav;
    }
    
    /** 
     * @Title: detailIndex<br>
     * @Description: <br>
     * @return
     */
    @RequestMapping("/detailIndex")
    public ModelAndView detailIndex(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("custom/detail_index");
        return mav;
    }
    
}
