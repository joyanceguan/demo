package com.joyance.demo.textparse;

import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class XmlDemo {

//	<Configuration>
//	   <project id="admin">
//	       <firstlevel desc="内容管理" >
//	           <secondlevel desc="文章管理" url="/admin/atcp/articlescrapList"></secondlevel> 
//	           <secondlevel desc="轮播图管理" url="/admin/cul/carouselList"></secondlevel>
//	           <secondlevel desc="推荐管理" url="/admin/rcma/recommedArticlescrapList"></secondlevel>
//	           <secondlevel desc="频道管理" url="/admin/channel/list"></secondlevel>
//	           <secondlevel desc="首页配置管理" url="/admin/midxcfg/list"></secondlevel>
//	           <secondlevel desc="文章审核管理" url="/admin/pding/docms"></secondlevel>   
//	       </firstlevel>
//	       <firstlevel desc="广告管理" >
//	           <secondlevel desc="广告内容管理" url="/admin/ad/adList"></secondlevel> 
//	       </firstlevel>
//	       <firstlevel desc="反馈管理" >
//	           <secondlevel desc="前台反馈管理" url="/admin/ffb/feedBackList"></secondlevel> 
//	           <secondlevel desc="后台反馈管理" url="/admin/afb/feedBackList"></secondlevel> 
//	       </firstlevel>
//	       <firstlevel desc="后台用户管理" >
//	           <secondlevel desc="权限管理" url="/admin/admin/athrtyList"></secondlevel> 
//	           <secondlevel desc="角色管理" url="/admin/admin/roleList"></secondlevel>
//	           <secondlevel desc="用户管理" url="/admin/adnur/adminList"></secondlevel>
//	           <secondlevel desc="操作日志" url="/admin/oprtlg/list"></secondlevel>
//	           <secondlevel desc="组织管理" url="/admin/ognztn/list"></secondlevel>
//	       </firstlevel>
//	       <firstlevel desc="公告管理" >
//	           <secondlevel desc="公告内容管理" url="/admin/announce/list"></secondlevel> 
//	       </firstlevel>
//	       <firstlevel desc="配置管理" >
//	           <secondlevel desc="参数配置管理" url="/admin/config/list"></secondlevel> 
//	       </firstlevel>
//	   </project>
//	   <project id="cps">
//	       <firstlevel desc="写文章" url="/cps/dft/wdoc" css="fa fa-desktop" page_name="dft/wdoc"></firstlevel> 
//	       <firstlevel desc="文章管理" url="/cps/pding/docms" css="fa fa-dashboard" page_name="pding/docms"></firstlevel>
//	       <firstlevel desc="流量统计" url="/cps/flwststcs/list" css="fa fa-yelp" page_name="flwststcs/list"></firstlevel>
//	       <firstlevel desc="素材管理" url="/cps/userPics/list" css="fa fa-flash" page_name="userPics/source"></firstlevel>
//	       <firstlevel desc="个人信息" url="/cps/user/psoninf" css="fa fa-anchor" page_name="user/psoninf"></firstlevel>
//	       <firstlevel desc="草稿箱" url="/cps/dft/draft" css="fa fa-yelp" page_name="dft/draft,dft/audit"></firstlevel>   
//	       <firstlevel desc="反馈" url="/cps/fb/feedback" css="fa fa-square-o" page_name="fb/feedback"></firstlevel>
//	       <firstlevel desc="成员管理" url="/cps/childUser/list" css="fa fa-sitemap" page_name="childUser/list"></firstlevel>
//	       <firstlevel desc="推荐码管理" url="/cps/ivtatncd/list" css="fa fa-coffee" page_name="ivtatncd/list"></firstlevel>
//	   </project>
//	</Configuration>
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws DocumentException {
		InputStream is = XmlDemo.class.getClassLoader().getResourceAsStream("menu.xml");
		SAXReader reader = new SAXReader(); // 解析的xml文档
		Document doc = reader.read(is);
		Element root = doc.getRootElement(); // 获取根节点
		Iterator<Element> it = root.elementIterator("project");
		while (it.hasNext()) {
			Element project = it.next();
			String name = project.attribute("id").getText();
			if("admin".equals(name)){
				Iterator<Element> firstlevelIt = project.elementIterator("firstlevel");
				while(firstlevelIt.hasNext()){
					Element firstlevel = firstlevelIt.next();
					String desc = firstlevel.attribute("desc").getText();
					if("内容管理".equals(desc)){
						Iterator<Element> secondlevelIt=firstlevel.elementIterator("secondlevel");
						while(secondlevelIt.hasNext()){
							Element secondlevel = secondlevelIt.next();
							String url = secondlevel.attribute("url").getText();
							String sdesc = secondlevel.attribute("desc").getText();
							System.out.println("admin->内容管理 "+"url="+url+",desc="+sdesc);
						}
					}
				}
			}
		}
	}
}
