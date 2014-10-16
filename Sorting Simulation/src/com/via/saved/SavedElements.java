package com.via.saved;

public class SavedElements {
	public static int sortType;
	public static int mode;
	public static int quantity;
	public static int step;
	public static int max;
	public static int delay;
	public static boolean canReset = true;
	public static int[] array = new int[50];
	public SavedElements() {
		mode = 1;
		step = 20;
		max = 700;
		sortType = 1;
		quantity = 34;
		for(int i=0; i<quantity; i++)
			array[i] = (int)(Math.random() * quantity + 1);
	}
	
}
