package com.joyance.demo.main;

import java.lang.invoke.MethodHandle;
import static java.lang.invoke.MethodHandles.lookup;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/*
 * 类别：jvm
 * 描述：通过MethodHandles调用祖父类的方法
 */
public class JvmGrandFather {
	class Forefathers{
		void thinking(){
			System.out.println("I'm forefathers");
		}
	}

	class GrandFather extends Forefathers{
		void thinking(){
			System.out.println("I'm grandfather");
		}
	}
	
	class Father extends GrandFather{
		void thinking(){
			System.out.println("I'm father");
		}
	}
	
	class Son extends Father{
		void thinking(){

			try {
//				错误，书上原版例子，实际返回了父类
//				MethodType mt = MethodType.methodType(void.class);
//				MethodHandle mh = lookup().findSpecial(GrandFather.class,"thinking",mt,getClass());
//				mh.invoke(this);
				
//				正确
//				MethodType mt = MethodType.methodType(void.class);
//				Field IMPL_LOOKUP = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
//				IMPL_LOOKUP.setAccessible(true);
//				MethodHandles.Lookup lkp = (MethodHandles.Lookup)IMPL_LOOKUP.get(null);
//				MethodHandle h1 = lkp.findSpecial(Forefathers.class, "thinking", mt, GrandFather.class);
//				h1.invoke(this);
				
//				正确
//				MethodType mt = MethodType.methodType(void.class);
//				MethodHandle mh = lookup().findVirtual(Forefathers.class,"thinking",mt).bindTo(new Forefathers());
//				mh.invokeExact();

			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
	
	public int add(int a,int b){
	   int result = a+b;
	   System.out.println(result);
	   return result;
	}
	
	 public MethodHandle getHandler() {
	        MethodHandle mh = null;
	        MethodType mt = MethodType.methodType(String.class, int.class, int.class);
	        MethodHandles.Lookup lk = MethodHandles.lookup();
	        try {
	            mh = lk.findVirtual(String.class, "substring", mt);
	            Object result1 = mh.invoke("abcdefg", 0, 3);
	            System.out.println(result1);
	        } catch (Throwable e) {
	            e.printStackTrace();
	        }
	        return mh;
	    }
	 
	 public void getHandler2() throws Throwable {
		    MethodType mt = MethodType.methodType(String.class,int.class,int.class);
		    TestMath t = new TestMath();
		    MethodHandle mh = lookup().findVirtual(TestMath.class, "add", mt);
		    mh.invoke(t,2,6);
	    }
	
	public static void main(String[] args) throws Throwable {
		JvmGrandFather jvmGrandFather = new JvmGrandFather();
		//父子类
//		(jvmGrandFather.new Son()).thinking();
		//methodHandle
		jvmGrandFather.getHandler2();
	}
}
