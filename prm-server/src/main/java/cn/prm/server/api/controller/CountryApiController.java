package cn.prm.server.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.prm.server.commons.BaseController;
import cn.prm.server.commons.Constants.RESPONSE_CODE;
import cn.prm.server.dto.CountryDto;
import cn.prm.server.dto.ListDto;
import cn.prm.server.service.CountryService;

@RestController
@RequestMapping("/api/country")
public class CountryApiController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(CountryApiController.class);
	
	@Autowired
	CountryService countryService;
	
	@RequestMapping("/list")
	public ListDto<CountryDto> list(){
		ListDto<CountryDto> list = countryService.getAll();
		list.setCode(RESPONSE_CODE.CODE_SUCCESS);
		return list;
	}
}
