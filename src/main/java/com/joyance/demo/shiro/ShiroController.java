package com.joyance.demo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/shiro")
public class ShiroController {
	
	
	@RequestMapping("/index")
	public ModelAndView index(String userName){
		ModelAndView mv = new ModelAndView("views/shiro/index");
		mv.addObject("username", userName);
		return mv;
	}
	
	@RequestMapping(value = "/login")
	public ModelAndView login(){
		ModelAndView mv = new ModelAndView("views/shiro/login");
		return mv;
	}

	@RequiresPermissions("user:query")
	@RequestMapping(value = "/query")
	public ModelAndView query(){
		ModelAndView mv = new ModelAndView("views/shiro/index");
		return mv;
	}
	
    @RequestMapping("/rlogin")
    @ResponseBody
    public ReturnDto login(String userName, String passwd) {
    	ReturnDto dto = new ReturnDto();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, passwd);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
        }
        return dto;
    }
	
}
