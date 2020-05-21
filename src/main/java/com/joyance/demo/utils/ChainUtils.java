package com.joyance.demo.utils;

public class ChainUtils {

	private String defaultTag() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement e : stackTrace) {
        	if(e.getClassName().startsWith("com.joyance")){
        		System.out.println(e.getClassName() + "\t"
                        + e.getMethodName() + "\t" + e.getLineNumber());
        	}
        }
        StackTraceElement log = stackTrace[1];
        String tag = null;
        for (int i = 1; i < stackTrace.length; i++) {
            StackTraceElement e = stackTrace[i];
            if (!e.getClassName().equals(log.getClassName())) {
                tag = e.getClassName() + "." + e.getMethodName();
                break;
            }
        }
        if (tag == null) {
            tag = log.getClassName() + "." + log.getMethodName();
        }
        System.out.println(tag);
        return tag;
    }
	
	public static void main(String[] args) {
		boolean a = true;
		boolean b = false;
		boolean c = false;
		boolean d = false;
		System.out.println(false || true && true && false);
	}
}
