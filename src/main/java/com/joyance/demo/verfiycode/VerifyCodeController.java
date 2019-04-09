package com.joyance.demo.verfiycode;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/verifycode")
public class VerifyCodeController {

	private static Random r = new Random();
    private static char[] chs = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
	private static final int NUMBER_OF_CHS = 4;
	private static final int IMG_WIDTH = 65;
	private static final int IMG_HEIGHT = 25;
	
	@RequestMapping("/init")
	public String init(){
		return "views/verifycode";
	}
	
	@RequestMapping("/generate")
	public void generate(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 BufferedImage image = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);    // 实例化BufferedImage
		 Graphics g = image.getGraphics();
		 Color c = new Color(200, 200, 255);                                             // 验证码图片的背景颜色                                        
		 g.setColor(c);
		 g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);                                        // 图片的边框
		 
		 StringBuffer sb = new StringBuffer();                                           // 用于保存验证码字符串
		 int index;                                                                      // 数组的下标
		 for (int i = 0; i < NUMBER_OF_CHS; i++) {
		     index = r.nextInt(chs.length);                                              // 随机一个下标
		     g.setColor(new Color(r.nextInt(88), r.nextInt(210), r.nextInt(150)));       // 随机一个颜色
		     g.drawString(chs[index] + "", 15 * i + 3, 18);                              // 画出字符
		     sb.append(chs[index]);                                                      // 验证码字符串
		 }
		 request.getSession().setAttribute("piccode", sb.toString());                    // 将验证码字符串保存到session中
		 ImageIO.write(image, "jpg", response.getOutputStream());                        // 向页面输出图像
	}
	
	@RequestMapping("/verify")
	public String verify(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	     response.setContentType("text/html;charset=utf-8");
	     String picString = (String) request.getSession().getAttribute("piccode");
	     String checkCode = request.getParameter("verifyCode");
	     PrintWriter out = response.getWriter();
	     if (picString.toUpperCase().equals(checkCode.toUpperCase()))
	         out.println("验证码正确");
	     else
	         out.print("验证码错误！");
	     out.flush();
	     out.close();
	    return "views/verifycode";
    }
}
