package com.loki2302.dummy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dummy")
public class DummyWebAppController {	
	@RequestMapping
	public String index() {
		return "dummy";
	}
}
