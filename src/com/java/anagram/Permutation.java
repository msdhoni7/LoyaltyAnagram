package com.java.anagram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
//import java.util.Dictionary;

//Java program to print all permutations of a 
//given string. 
public class Permutation 
{ 
	//public static void main(String[] args) 
	public ArrayList<String> getAnagramList(HttpServletRequest request)
	{ 
		String str = (String) request.getParameter("anagram");
		int n = str.length(); 
		ArrayList<String> finalList=new ArrayList<String>();
		ArrayList<String> perm = new ArrayList<String>();
		Permutation permutation = new Permutation(); 
		ArrayList<String> matchedArray=permutation.permute(str, 0, n-1,perm); 
		//System.out.println("Printing matchedArray" + matchedArray);
		
		// pass the path to the file as a parameter 
		try {
			
			//ServletContext sc = request.getSession().getServletContext();
		      //URL url = sc.getResource("../WebContent/WEB-INF/docs/dictionary.txt");
		      //System.out.println("path:"+request.getContextPath()+"/WEB-INF/docs/dictionary.txt");
		     // System.out.println("URL:"+url);
		   // Path resPath = new Path(url);
		   
		   //   File resFile = new File(url);
		    //  FileReader resRdr = new FileReader(resFile);
			BufferedReader br = new BufferedReader(new FileReader(request.getContextPath()+"/WebContent/WEB-INF/docs/dictionary.txt"));
			
			String line =null;
			
			try {
				for (String perms : matchedArray) {
					br=new BufferedReader(new FileReader(request.getContextPath()+"/WebContent/WEB-INF/docs/dictionary.txt"));
					
					while ((line=br.readLine()) != null){
						if(line.length()==perms.length()&&perms.equals(line)){
							//System.out.println("perms value from line:"+perms);
					
						finalList.add(line);
						
						}
						}
					}
					
				
				System.out.println("Final list is "+finalList);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					if(br!=null) {
					br.close();}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return finalList;
	}

	/** 
	* permutation function 
	* @param str string to calculate permutation for 
	* @param l starting index 
	* @param r end index 
	*/
	private ArrayList<String> permute(String str, int l, int r,ArrayList<String> inputarr ) 
	{ 
		//ArrayList<String> arr=new ArrayList<String>();
		if (l == r) {
			//System.out.println(str); 
			inputarr.add(str);
		}
		else
		{ 
			for (int i = l; i <= r; i++) 
			{ 
				str = swap(str,l,i); 
				permute(str, l+1, r,inputarr); 
				str = swap(str,l,i); 
			} 
		} 
		return inputarr;
	} 

	/** 
	* Swap Characters at position 
	* @param a string value 
	* @param i position 1 
	* @param j position 2 
	* @return swapped string 
	*/
	public String swap(String a, int i, int j) 
	{ 
		char temp; 
		char[] charArray = a.toCharArray(); 
		temp = charArray[i] ; 
		charArray[i] = charArray[j]; 
		charArray[j] = temp; 
		return String.valueOf(charArray); 
	} 

} 

