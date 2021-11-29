package jpabook.jpashop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeContoller {

//	'  @Slf4j'로 대체 가능
//	Logger log = org.slf4j.LoggerFactory.getLogger(getClass());
	
	@RequestMapping
	public String home() {
		log.info("home contoller");
		return "home";
	}
	
	

}
