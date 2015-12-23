package com.mwh.demo.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mwh.demo.common.ResponseJSONHelp;

@Controller("/test")
public class TestController {

	@RequestMapping("/helloworld")
	public String initMonitorJGroup(ModelMap modelMap) {
		return "helloworld";
	}

	@RequestMapping("/async/helloworld")
	@ResponseBody
	public ResponseEntity<String> getCacheMonitorTree() {
		String result = "hello world";
		return ResponseJSONHelp.buildResponseEntity(result);
	}
}
