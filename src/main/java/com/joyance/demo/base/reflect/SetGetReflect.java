package com.joyance.demo.base.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class SetGetReflect {
	
	public static class Person{
		private int id;
		private String name;
		private Date time;
		private boolean valid;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Date getTime() {
			return time;
		}
		public void setTime(Date time) {
			this.time = time;
		}
		public boolean isValid() {
			return valid;
		}
		public void setValid(boolean valid) {
			this.valid = valid;
		}
		
	}

	public static Object mapToBean(Class<?> beanClass,Map<String,Object> map) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		Object bean = beanClass.newInstance();
		for(Method method : beanClass.getMethods()){
			 String methodName = method.getName();
			 if (methodName.length() > 3 && methodName.startsWith("set")
	                    && Modifier.isPublic(method.getModifiers())
	                    && method.getParameterTypes().length == 1) {
	              String propertyName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
	              Object value = map.get(propertyName);
	              method.invoke(bean, value);
			 }
		}
		return bean;
	}
	
	public static Map<String,Object> beanToMap(Object bean) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class<?> beanClass = bean.getClass();
		Map<String,Object> map = new HashMap<String,Object>();
		Object[] empty = new Object[0];
		for(Method method : beanClass.getMethods()){
			String methodName = method.getName();
			if (methodName.length() > 3 && methodName.startsWith("set")
                    && Modifier.isPublic(method.getModifiers())
                    && method.getParameterTypes().length == 1) {
			  Method getter = null;
			  String propertyName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
              try {
            	   getter = beanClass.getMethod("get" + methodName.substring(3), new Class<?>[0]);
              } catch (NoSuchMethodException e) {
                  try {
                      getter = beanClass.getMethod("is" + methodName.substring(3), new Class<?>[0]);
                  } catch (NoSuchMethodException e2) {}
              }
              Object value = getter.invoke(bean, empty);
              map.put(propertyName, value);
		    }
		}
		return map;
	}
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("id", 1);
//		map.put("name", "joy");
//		map.put("time", new Date());
//		map.put("valid", true);
//		Object obj = SetGetReflect.mapToBean(Person.class, map);
//		System.out.println(JSON.toJSONString(obj));
//		
//		map = SetGetReflect.beanToMap(obj);
//		map.forEach((key,value)->{
//			System.out.println("key="+key+",value="+value);
//		});
		
	}
}
