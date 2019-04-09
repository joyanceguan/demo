package com.joyance.demo.base.sort;

public class TargetPath {

	private void x(String begin,String end,String desc){
		
		String[] beginArray = begin.split(",");
		int bx = Integer.parseInt(beginArray[0]);
		int by = Integer.parseInt(beginArray[1]);
		String[] endArray = end.split(",");
		int ex = Integer.parseInt(endArray[0]);
		int ey = Integer.parseInt(endArray[1]);
		
		if(bx < ex && by <= ey){
			int x = bx + 1;
			String desc1 = desc;
		    x(x+","+by,end,desc1+=(",走到"+x+","+by));
		}
		
	    if(by < ey && bx <= ex){
			int y = by + 1;
			String desc2 = desc;
			x(bx+","+y,end,desc2+=(",走到"+bx+","+y));
		}
		
		if(ex == bx && ey == by){
			System.out.println(desc);
		}
	}
	
	public static void main(String[] args) {
		TargetPath t = new TargetPath();
		t.x("0,0", "2,4","开始从0，0走了");
	}
}
