package com.duiya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.duiya.mq.Producer;

@Controller
@RequestMapping("test")
public class TestController {
	
	@Autowired
	private Producer producer;
	@RequestMapping(value="sendMess", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject sendMessage() {
		producer.sendMessage("测试邮件");
		return null;
	}

}
