package com.uis.toDoAppProject;

import java.util.Comparator;

class MyComparatorNameReverse implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		// TODO Auto-generated method stub
		return  (o2.compareTo(o1));
	}
	
}

class MyComparatorlengthIncrease implements Comparator<String> {
	@Override
	public int compare(String o1, String o2) {
		// TODO Auto-generated method stub
		return  (o1.length()-(o2.length()));
	}
	
}

class MyComparatorlengthDecrease implements Comparator<String> {
	@Override
	public int compare(String o1, String o2) {
		// TODO Auto-generated method stub
		return  (-o1.length()+(o2.length()));
	}
	
}