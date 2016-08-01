package cn.prm.server.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.prm.server.commons.BaseController;
import cn.prm.server.service.CountryService;

@RestController
@RequestMapping("/api/country")
public class CountryController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(CountryController.class);
	
	@Autowired
	CountryService countryService;
	
}
