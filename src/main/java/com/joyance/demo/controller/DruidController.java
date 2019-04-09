package com.joyance.demo.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyance.demo.mybatis.mapper.ConfigMapper;
import com.joyance.demo.mybatis.persistence.Config;

@Controller
@RequestMapping("/druid_demo")
public class DruidController {

	@Resource
	ConfigMapper configMapper;
	
	@RequestMapping("/getconfig")
	@ResponseBody
	public Config getConfig(Integer id){
		Config config = configMapper.selectById(id);
		return config;
	}
	
}
