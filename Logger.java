package com.uis.toDoAppProject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;


public class Logger implements Cloneable, Serializable{
	//public static final boolean LOGTOMONITOR = true;
	private static Logger instance = null;
	
	public void log(String data, int priority) {
	/*class X implements Runnable{
	 public void run()
     {
	}
	
	class Test {
	main()
	{
	X a1= new X();
	Thread t1= new Thread(a1);
	t1.start();
	
	}
	*/
		//I dont want class which implements Runnable. I need Runnable(i) only temporarily.so u can use Anonymous inner class 
		new Thread(new Runnable() {
			public void run() {
			Date dt = new Date();	
			BufferedWriter bw = null;
			try {
				String msg = dt + " : " + data;
				bw = new BufferedWriter(new FileWriter(Constants.Path,true));
				try {
					//only Messages greater than or equal to  MEdium are logged, 
					//messages of lower priority are not Logged
					if(priority >= Constants.CONF) {
					bw.write(msg);
					bw.newLine();
				/*	if(Logger.LOGTOMONITOR) {
						System.out.println("Logger : " + msg);
					}*/
					}
				}catch(IOException ex2) {
					ex2.printStackTrace();
				}finally {
					if(bw!=null) {
						bw.close();
					}
				}
			}catch(IOException ex1) {
				ex1.printStackTrace();
				
			}
			catch(Exception ex3) {
				ex3.printStackTrace();
			}
			}//run
		}).start();
	}//log
	
	
	private Logger()
	{
		
		//This code is must,so that modification of class Class object of SingletonLogger
		//is prevented using reflection
		if(instance != null) {
			throw new RuntimeException("Reflection not supported !!!");
		}
	}
	
	public static Logger getInstance()
	{
		if(instance == null) {
			synchronized(Logger.class) {
				if(instance == null) {
				instance =  new Logger();
				}
			}
		}
		return instance;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		//return super.clone(); new Object is created, violating Singleton rules
		//return instance; // u cannot clone
		throw new RuntimeException("Object cloning not supported !!!");
	}
	@Override
	public String toString() {
		return (String.valueOf(hashCode()));
	}

	
	/**
	- Whenever u are applying serialization ,
	 write method ->readResolve() internal method of JVM
	- Whenever we Deserialization, JVM will call readResolve() internal method of JVM internally
	  with the help of this it will create new deserialized Object 
	- Here we are preventing creation of new deserialized Object by returning instance
	 */
	private Object readResolve()
	{
		System.out.println("Inside readResolve method----");
		return instance; //deserialization 
	}
	
}





