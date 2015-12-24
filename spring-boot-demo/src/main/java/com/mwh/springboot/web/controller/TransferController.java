package com.mwh.springboot.web.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mwh.springboot.common.utils.TypeConvertUtil;

@Controller("/test")
public class TransferController {
	@PostConstruct
	public void init() {
		// TODO INIT
	}

	/**
	 * test
	 * 
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/helloworld", method = RequestMethod.GET)
	@ResponseBody
	public String helloworld(HttpServletResponse response, @RequestBody String requestData) throws Exception {
		return requestData;
	}

}
