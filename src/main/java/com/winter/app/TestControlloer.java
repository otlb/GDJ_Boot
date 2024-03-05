package com.winter.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TestControlloer {
	
	@GetMapping("/")
	public String test() {
		//trace, debug, info, warn, error
		log.warn("warn message");
		log.error("error message");
		log.info("info message");
		log.debug("debug message");
		log.trace("trace message");
		return "Index";
	}

}
