package com.weigthwatchers;
import java.util.Arrays; 
import java.util.Collections;
import java.util.Random;
//Author:Paul Tsiouper, November 7,2018
/*Question 3:
Generate 500 random numbers and print the nth smallest number in a programming language
of your choice.*/
public class QuestionThree {
	

	
	public static void main (String[] args)  
    {
		Random rand = new Random(); 
		Integer[] arr= new Integer[500]; 
		for (int i = 0; i < arr.length; i++) {
    	  arr[i]=rand.nextInt(1000000);
		}
		printNthSmallestNumber(arr, 25);
	}

	public static void printNthSmallestNumber(Integer[] arr, Integer nthSmallest) {
		  Arrays.sort(arr);
		  if(nthSmallest >=1 && nthSmallest<=500) {
			  System.out.println("Number"+(nthSmallest)+"th="+arr[nthSmallest-1]);
			  }
		  }
	}
