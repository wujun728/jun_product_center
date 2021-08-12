package com.oracle.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrameHandler {

	@RequestMapping("/frame/{path}")
	public String frame(@PathVariable("path") String path) {		
		return "frame/"+path;
	}
	
}
