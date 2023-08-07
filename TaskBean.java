package com.uis.toDoAppProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

/*
 * TasKBean class represents the data holder:
- name
- desc
- cr_dt
- end_dt
- priority
- status
- tags
 */

public class TaskBean {
	private String name;
	private String desc;
	private String cr_date;
	private String end_date;
	private String priority;
	private String status;
	private long duration;

	public TaskBean() {
		super();
	}

	public TaskBean(String name, String desc, String cr_date, String end_date, String priority, String status) {
		this.name = name;
		this.desc = desc;
		this.cr_date = cr_date;
		this.end_date = end_date;
		this.priority = priority;
		this.status = status;

		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
		Date d1 = sdf.parse(this.cr_date.trim());
		Date d2 = sdf.parse(this.end_date.trim());
		
		
		long day1 = (long) (d2.getTime() / (1000 * 60 * 60 * 24));// convert milliseconds to num of days
		long day2 = (long) (d1.getTime() / (1000 * 60 * 60 * 24));
		this.duration = day1 - day2;
		} catch (ParseException e) {
			System.out.println("Invalid Date!! Entered date should be in the format dd/MM/yyyy");
         
		}
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCr_date() {
		return cr_date;
	}

	public void setCr_date(String cr_date) {
		this.cr_date = cr_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String endDate) {
		this.end_date = endDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
		Date d1 = sdf.parse(this.cr_date.trim());
		Date d2 = sdf.parse(this.end_date.trim());
		
		
		long day1 = (long) (d2.getTime() / (1000 * 60 * 60 * 24));// convert milliseconds to num of days
		long day2 = (long) (d1.getTime() / (1000 * 60 * 60 * 24));
		this.duration = day1 - day2;
		} catch (ParseException e) {
			System.out.println("Invalid Date!! Entered date should be in the format dd/MM/yyyy");

		}
		// this.duration = duration;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ( this.name +  " : " + this.desc + " : " + this.cr_date + " : "+ this.end_date + " : " + this.priority + " : " + this.status + " : " + this.duration);
	}

}

