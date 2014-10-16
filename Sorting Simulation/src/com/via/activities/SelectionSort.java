package com.via.activities;

import java.util.ArrayList;

public class SelectionSort {
	public static ArrayList<Couple> couples = new ArrayList<Couple>();
	public static void sort(int[] x) {
	    for (int i=0; i<x.length-1; i++) {
	        for (int j=i+1; j<x.length; j++) {
	            if (x[i] > x[j]) {
	            	couples.add(new Couple(i, j));
	                int temp = x[i];
	                x[i] = x[j];
	                x[j] = temp;
	            }
	        }
	    }
	}
}
