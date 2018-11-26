package com.java.anagram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class Permutation 
{
	//public static final ArrayList<String> dictionaryList = new ArrayList<String>();
	
	public static final Set<String> dictionaryList = new HashSet<String>();
	
	
	public ArrayList<String> getAnagramList(HttpServletRequest request)
	
	{ 
		String str = (String) request.getParameter("anagram");
		int n = str.length(); 
		ArrayList<String> finalList=new ArrayList<String>();
		ArrayList<String> perm = new ArrayList<String>();
		Set<String> matchedDict = new HashSet<String>();
		Permutation permutation = new Permutation(); 
		ArrayList<String> matchedArray=permutation.permute(str, 0, n-1,perm); 
		InputStream is= this.getClass().getClassLoader().getResourceAsStream("com/java/anagram/dictionary.txt");
		
		String line =null;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is)); 
		try {
		//adding dictionary words in dictionarylist
			while ((line=br.readLine()) != null){
				dictionaryList.add(line);
			}
		
		//creating a subset of dictionary as per matching inputted word's length.
			for (String perms : matchedArray) {
					for(String dict:dictionaryList) {
						if(perms.length() == dict.length()) { 
							matchedDict.add(dict);
						}
					}
				}
				
		//creating the final matching Anagrams list
			for (String perms : matchedArray) {
				for(String mdict:matchedDict) {
					if(perms.equals(mdict)) {
						finalList.add(mdict);
					}
				}
			}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		matchedDict.clear();
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
		if (l == r) {
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

