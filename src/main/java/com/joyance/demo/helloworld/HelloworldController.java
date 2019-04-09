package com.joyance.demo.helloworld;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class HelloworldController {

	@RequestMapping("/helloworld")
	public ModelAndView hello(){
		ModelAndView mv = new ModelAndView("views/helloworld");
		mv.addObject("helloworld", "hello world!!!");
		return mv;
	}
	
	@RequestMapping(value = "/eat",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String eat(String foodName){
		return "eat "+foodName;
	}
	
	public static void main(String[] args) {
		List<Person> list = new ArrayList<Person>();
		long index = 1;
		while(true){
			Person p = new Person();
			p.setId(index++);
			p.setName("joyance_"+index);
			list.add(p);
		}
	}
	
}
