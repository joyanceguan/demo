package com.joyance.demo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils2 {
	
	/**
     * 如果目录不存在则创建目录
     * @param dir
	 * @throws Exception 
     */
    public static void createDirWhenNotExists(String dir) throws Exception{
        File file =new File(dir);
        if (!file.isDirectory()) {
            boolean mkdir = file.mkdirs();
            if (!mkdir){
                throw new Exception("目录创建失败");
            }
        }
    }


//	public static String readFile(String path) throws Exception{
//		String result = "";
//		FileInputStream fileInputStream = new FileInputStream(path);
//		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
//		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//		String line = null;
//		while((line = bufferedReader.readLine())!=null){
//			result+="\""+line+"\",\n";
//		}
//		bufferedReader.close();
//		inputStreamReader.close();
//		fileInputStream.close();
//		return result;
//	}
	
	public static List<String> readFile(String path) throws Exception{
		List<String> orderList = new ArrayList<String>();
		FileInputStream fileInputStream = new FileInputStream(path);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String line = null;
		while((line = bufferedReader.readLine())!=null){
			orderList.add(line);
		}
		bufferedReader.close();
		inputStreamReader.close();
		fileInputStream.close();
		return orderList;
	}
	
	public static void writeFile(String content,String path) throws IOException{
		 File file = new File(path);
	     FileWriter fw = new FileWriter(file);
	     fw.write(content);
	     fw.close();
	}
	
	
	public static void main(String[] args) throws Exception {
		List<String> allList = FileUtils2.readFile("/Users/joyance/Documents/y.txt");
		List<String> revokedList = FileUtils2.readFile("/Users/joyance/Documents/x.txt");
		System.out.println("allList size:"+allList.size()+",revokedList size="+revokedList.size());
		allList.removeAll(revokedList);
		System.out.println(allList.size());
//		for(String needRevokeOrder:allList){
//			System.out.println(needRevokeOrder);
//		}
	}
	
	
}
