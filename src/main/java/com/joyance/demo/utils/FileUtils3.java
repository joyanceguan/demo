package com.joyance.demo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Joiner;

public class FileUtils3 {
	
	static String f = "update illegal_asset_item set status = 2 where illegal_id = #{illegalId};\n";
  
			
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
			String v = f.replaceAll("\\#\\{illegalId\\}", line);
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
		String str = FileUtils3.readFile("/Users/joyance/Documents/y.txt");
//		System.out.println(str);
		FileUtils.writeFile(str, "/Users/joyance/Documents/x.txt");
	}
}
