package com.joyance.demo.sharesession;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String login(String username,HttpServletRequest request){
		request.getSession().setAttribute("user", username);
		return "views/helloworld";
	}
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request){
		String username = (String) request.getSession().getAttribute("user");
		System.out.println("username="+username);
		return "views/helloworld";
	}
}
