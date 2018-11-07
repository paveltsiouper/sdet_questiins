package com.weigthwatchers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
//Author:Paul Tsiouper, November 7,2018
/*Question 1:

There is a file containing a word and its possible meanings (like a Dictionary). The contents of the file look like this:

Apple – a fruit, a tech firm
Table – an object, contains rows and columns when used in context of computers
Orange – a fruit

Given a path to the file, do the following:

a)	Create a method called doesFileExist(String path) 
which takes the path of the file and tells the user if the file exists at that path or not. 
Assume all paths are relative to your project structure
If the file does not exist, catch the requisite exception.
b)	Read each word and its possible meanings and print them out. Your output should look like this:

Word1
Meaning 1
Meaning 2
Word2
Meaning1
Meaning2

Use appropriate data structures wherever necessary.*/


public class QuestionOne {
	
	public final static String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\weigthwatchers\\";
	
	public static void main(String[] arg) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter a filename:"); //Type source.txt
		String input = "";
		try {
			input = br.readLine();
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(doesFileExist(input)) {

			String pattern = "(.+)(\\s–\\s+)(a.{0,1}\\s+\\S+)(,.)(.+)|(.+)(\\s–\\s+)([a.{0,1}]\\s+.+)";
			Pattern r = Pattern.compile(pattern);
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath+input), "Cp1252"));         

			    String line;
			    while ((line = br.readLine()) != null) {
			    	System.out.println("");
			    	Matcher m = r.matcher(line);
                   if (m.find())
			        {
			    	  for (int i=1;i < m.groupCount()+1;i++) {
			    		  if(m.group(i) !=null){
			    			  if(!m.group(i).contains("–")&&!m.group(i).contains(",")) {
				    			  
				    			  System.out.format("'%s'\n", m.group(i));
				    		  }
			    			  
			    		  }
			    	}

			     }
			   }
			    br.close();
			    
			} catch (IOException e) {
			    e.printStackTrace();
			}
			
		};
	}
	
	
	public static boolean doesFileExist(String sourceName) {

			File f = new File(filePath+sourceName);
			if (f.exists()){
				System.out.println("File:"+ sourceName + " located at the directory="+filePath);
				return true;
			}
			else {
				System.out.println("Unable locate File:"+ sourceName + " located at the directory="+filePath);
				return true;
			}
			}

}
