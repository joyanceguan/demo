package com.joyance.demo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

public class FileUtils4 {

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
	
	public static Set<String> readFile(String path) throws Exception{
		Set<String> set = new HashSet<String>();
		FileInputStream fileInputStream = new FileInputStream(path);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String line = null;
		while((line = bufferedReader.readLine())!=null){
			set.add(line.trim());
		}
		bufferedReader.close();
		inputStreamReader.close();
		fileInputStream.close();
		return set;
	}
	
	public static void writeFile(String content,String path) throws IOException{
		 File file = new File(path);
	     FileWriter fw = new FileWriter(file);
	     fw.write(content);
	     fw.close();
	}
	
	
	public static void main(String[] args) throws Exception {
//		Set<String> set2 = FileUtils4.readFile("/Users/joyance/Documents/z.txt");
		Set<String> set1 = FileUtils4.readFile("/Users/joyance/Documents/y.txt");
//		Set<String> set = Sets.difference(set2, set1);
//		System.out.println(set1);
		
		for(String str:set1){
			System.out.println(str);
		}
		
	}
}
