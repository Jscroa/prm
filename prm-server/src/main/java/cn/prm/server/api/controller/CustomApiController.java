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
import cn.prm.server.dto.CustomDto;
import cn.prm.server.dto.PageDto;
import cn.prm.server.exception.BusinessException;
import cn.prm.server.form.CustomForm;
import cn.prm.server.form.PageBaseForm;
import cn.prm.server.service.CustomService;

@RestController
@RequestMapping("/api/custom")
public class CustomApiController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(CustomApiController.class);

	@Autowired
	CustomService customService;

	@RequestMapping("/list")
	public Object list(HttpServletRequest request,PageBaseForm form){
		try{
			CurrUser currUser = getCurrUser(request);
			if (currUser == null) {
				return new BaseDto(RESPONSE_CODE.CODE_NEED_LOGIN, "您还未登录");
			}
			PageDto<CustomDto> page = customService.getPrivateCustoms(currUser, form.getSearch(), form.getOrder(), form.getOffset(), form.getLimit());
			page.setCode(RESPONSE_CODE.CODE_SUCCESS);
			return page;
		}catch (BusinessException e) {
			e.printStackTrace();
			return new BaseDto(RESPONSE_CODE.CODE_FAILURE, e.getMessage());
		}
	}
	
	@RequestMapping("/add")
	public Object add(HttpServletRequest request, CustomForm form) {
		form.setAge(1);
		try {
			CurrUser currUser = getCurrUser(request);
			if (currUser == null) {
				return new BaseDto(RESPONSE_CODE.CODE_NEED_LOGIN, "您还未登录");
			}
			customService.addPrivateCustom(currUser, form);
			return new BaseDto(RESPONSE_CODE.CODE_SUCCESS, "添加成功");
		} catch (BusinessException e) {
			e.printStackTrace();
			return new BaseDto(RESPONSE_CODE.CODE_FAILURE, e.getMessage());
		}
	}

	@RequestMapping("/del")
	public Object delete(HttpServletRequest request, String id) {
		try {
			CurrUser currUser = getCurrUser(request);
			if (currUser == null) {
				return new BaseDto(RESPONSE_CODE.CODE_NEED_LOGIN, "您还未登录");
			}
			customService.delete(currUser, id);
			return new BaseDto(RESPONSE_CODE.CODE_SUCCESS, "删除成功");
		} catch (BusinessException e) {
			e.printStackTrace();
			return new BaseDto(RESPONSE_CODE.CODE_FAILURE, e.getMessage());
		}
	}
	
}
