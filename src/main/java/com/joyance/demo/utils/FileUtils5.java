package com.joyance.demo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class FileUtils5 {

	static String f = "delete from risk_user where user_type = 0 and risk_type ='CHEAT' and user_id = '#{userId}';\n";
	  
	
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


	public static String readFile(String path) throws Exception{
		String result = "";
		FileInputStream fileInputStream = new FileInputStream(path);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String line = null;
		while((line = bufferedReader.readLine())!=null){
			String v = f.replaceAll("\\#\\{userId\\}", line);
			result+=v;
		}
		bufferedReader.close();
		inputStreamReader.close();
		fileInputStream.close();
		return result;
	}
	
	public static void writeFile(String content,String path) throws IOException{
		 File file = new File(path);
	     FileWriter fw = new FileWriter(file);
	     fw.write(content);
	     fw.close();
	}
	
	
	public static void main(String[] args) throws Exception {
		String str = FileUtils5.readFile("/Users/joyance/Documents/y.txt");
		System.out.println(str);
//		FileUtils.writeFile("abc","/Users/user/Documents/workspaces/my_workspace/demo/src/main/resources/test1.json");
		
		List<String> containList = new ArrayList<String>();
		containList = null;
//		containList.add("a");
//		containList.add("abc");
		String scope = containList.stream().max(Comparator.comparingInt(x -> x.length())).orElseThrow(NoSuchElementException::new);
		System.out.println(scope);
	}
}
