package com.demo.springlogging.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/gbt")
public class SpringDatabaseController {
    Logger logger = LogManager.getLogger(SpringDatabaseController.class);

	@GetMapping("/test")
	public String test() {
		System.out.println("In controller");
		logger.debug("In Controller test() : called");
		logger.info("In Controller test(info ) : called");
		return "Helloo ... !";
	}

}
