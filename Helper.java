package com.uis.toDoAppProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Helper {
	//public static TaskBean task = new TaskBean();
	
	//category
	public static HashSet<String> categorySet = new HashSet<>();
	public static List<String> categoryList = new ArrayList<>();
	
	//task
	public static List<TaskBean> listTask = new ArrayList<>();
	public static HashMap<String, TaskBean> mapTask = new HashMap<>();
	
	
	
	public static boolean categoryNameValidation(String categoryName) {
		if (categoryName.equals("null")) {
			System.out.println("Category Name cannot be null, give other Name");
			return false;
		}
		for (int i = 0; i < categoryName.length(); i++) {
			if (categoryName.charAt(0) >= 48 && categoryName.charAt(0) <= 57) {
				System.out.println("Category Name should not start with Number");
				return false;
			}
			if (categoryName.charAt(i) == 32 || (categoryName.charAt(i) >= 33 && categoryName.charAt(i) <= 47)
					|| (categoryName.charAt(i) >= 58 && categoryName.charAt(i) <= 64)
					|| (categoryName.charAt(i) >= 91 && categoryName.charAt(i) <= 96)
					|| (categoryName.charAt(i) >= 123 && categoryName.charAt(i) <= 126)) {

				System.out.println(
						"Category Name should not contain space or Special Character \n give Category Name start with letter");
				return false;
			}
		} // end for
		return true;
	}
	
	
	
}
