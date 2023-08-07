package com.uis.toDoAppProject;

import java.io.File;

public interface Constants {
	
	//files
	String filePath = "D:\\TaskManager\\";
		
	// Logger Constants
	String Path = "Log.txt";
	int CONF = 2;
	int CRITICAL = 4; //Exception
	int HIGH = 3; // CONTROL CAME TO VIEW+Controller: Tightly Coupled, Model -->FileSystem 
	int MEDIUM = 2; // for debugging purposes
	int LOW = 1; //for control flow
	
	//priorities for task
	String High ="HIGH";
	String Low ="LOW";
	String Medium="Medium";
	
	
}