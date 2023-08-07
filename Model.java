package com.uis.toDoAppProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;





public class Model {

	public Model() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/********************* Category Methods ****************/
	
	public void readAllCategoryFromFile() {
		try {
			File fi= new File(Constants.filePath);
			File[] fil=fi.listFiles();
			if(fil.length !=0) {
			for(File f:fil) {
				String str=f.getName().substring(0,(f.getName().length()-4));
				Helper.categorySet.add(str);
				Helper.categoryList.add(str);
			}	
			}	
			}catch(Exception ex) {
				ex.printStackTrace();
			}
	}
	
	public boolean createCategory(String categoryName) 
	{		try {
				File newCategory = new File(Constants.filePath + categoryName.trim() + ".txt");
				newCategory.createNewFile();
				//System.out.println("New file created -->");				
				if(Helper.categorySet.add(categoryName)) {
					Helper.categoryList.add(categoryName);
				}
	         }catch (FileNotFoundException ex) {
	             ex.printStackTrace();
	             return false;
	         } catch (IOException ex) {
	             ex.printStackTrace();
	             return false;
	         }
	         return true;			

	}
	
	

	
	public boolean deleteParticularCategory(String categoryName) {
		
		File fr1 = new File(Constants.filePath.trim() + categoryName.trim() + ".txt");				
		if(fr1.delete()) {
			Helper.categoryList.remove(categoryName);		
			Helper.categorySet.remove(categoryName);	
			return true; 
		}
			
			return false ;		
		}

	

	public boolean deleteAllCategory() throws IOException, FileNotFoundException {
		File directory = new File(Constants.filePath.trim());	
		 if (!directory.isDirectory()) {
		        return false; // Not a directory, cannot delete contents
		    }

		    File[] files = directory.listFiles();
		    for (File file : files) {
		    	file.delete();
		    }
		    Helper.categoryList.clear();		
			Helper.categorySet.clear();	
		    return true;

       }



	/******************** END ***************************/ 
	
    /********************* Task Methods ****************/
	public List<TaskBean> writeTaskintoFile(TaskBean t1, String catName) throws IOException, FileNotFoundException {
		FileWriter fw = null;
		String cont1 = t1.getName() + ":" + t1.getDesc() + ":" + t1.getCr_date() + ":" + t1.getEnd_date() + ":"
				+ t1.getPriority() + ":" + t1.getStatus() + " :"+t1.getDuration();
		try {
			fw = new FileWriter(Constants.filePath + catName + ".txt", true); //appending: opening  file n write/append to the already contents in the file 
			try {
				fw.write(cont1);
				fw.write("\n");
				Helper.listTask.add(t1);
			    Helper.mapTask.put(catName, t1);
			} finally {
				fw.close();
			}
         
		} catch (Exception ex) {
			ex.printStackTrace();			
		}
		return Helper.listTask;
		
	}

	

	public  void readTaskFromFileLoadAllTask(String catName) throws IOException, FileNotFoundException {
		FileReader fr1 = null;
		BufferedReader br = null;
		String line = "";
		Helper.listTask.clear();
		Helper.mapTask.clear();
		try {
			int cnt=0;
			fr1 = new FileReader("D:\\TaskManager\\".trim() + catName.trim() + ".txt");
			br = new BufferedReader(fr1);
			try{
				while ((line = br.readLine()) != null)
				{
					String[] name = line.split(":");
					String key = name[0].trim();
					TaskBean t1 = new TaskBean(name[0].trim(),name[1].trim(),name[2].trim(),name[3].trim(),name[4].trim(),name[5].trim());
			        Helper.listTask.add(t1);
			        Helper.mapTask.put(key, t1);
				}	
			} finally {
				fr1.close();
				br.close();
			}

		} catch (Exception ex) {
			System.out.println("Something went wrong!!!!!!! because of these reasons  ");
			System.out.println("give valid Constants.filePath with .txt extension ");
			System.out.println("Content inside file should be in below format");
			System.out.println("TaskName : description : 9/4/2023 : 15/4/2023 : 1  : New : 10 ");
		}
			
	}
	
	public List<TaskBean> updateParticularTask(String catName,String taskName,String newValue,int option)  {
		//List<TaskBean> list = new ArrayList();
	  try {
			FileReader fr = new FileReader(Constants.filePath.trim() + catName.trim() + ".txt" );
			BufferedReader br = new BufferedReader(fr);
			String line = "", cont = "" , updatedValue = "";
			TaskBean t1 = new TaskBean();			
		
			while ((line = br.readLine()) != null) {
				String[] name = line.split(":");

				if (taskName.trim().equals(name[0].trim())) {
					
					switch (option) {
					case 1:
						//System.out.println("Task Name Update");
						t1 = new TaskBean(newValue.trim(), name[1].trim(), name[2].trim(), name[3].trim(),
								name[4].trim(), name[5].trim());

						updatedValue =newValue.trim() + " : " + name[1].trim() + " : " + name[2].trim() + " : "
								+ name[3].trim() + " : " + name[4].trim() + " : " + name[5].trim();
						cont = cont +updatedValue + "\n";
						break;
						
					case 2:
						//System.out.println("Description Update");
						t1 = new TaskBean(name[0].trim(), newValue.trim(), name[2].trim(), name[3].trim(),
								name[4].trim(), name[5].trim());

						updatedValue = name[0].trim() + " : " + newValue.trim() + " : " + name[2].trim() + " : "
								+ name[3].trim() + " : " + name[4].trim() + " : " + name[5].trim();
						cont = cont +updatedValue + "\n";
						System.out.println("content - > "+cont);
						//list.add(t1);
						break;

					case 3:
						//System.out.println("Created Date Update");
						t1 = new TaskBean(name[0].trim(), name[1].trim(), newValue.trim(), name[3].trim(),
								name[4].trim(), name[5].trim());

						updatedValue = name[0].trim() + " : " + name[1].trim() + " : " + newValue.trim() + " : "
								+ name[3].trim() + " : " + name[4].trim() + " : " + name[5].trim();
						cont = cont + updatedValue + "\n";
					break;
						
					case 4:
						//System.out.println("End date Update");
						t1 = new TaskBean(name[0].trim(), name[1].trim(),  name[2].trim(),newValue.trim(),
								name[4].trim(), name[5].trim());

						updatedValue = name[0].trim() + " : " + newValue.trim() + " : " + name[2].trim() + " : "
								+ newValue.trim() + " : " + name[4].trim() + " : " + name[5].trim();
						cont = cont + updatedValue + "\n";
						//list.add(t1);
						break;
					
					case 5:
					//	System.out.println("Priority Update");
						t1 = new TaskBean(name[0].trim(), name[1].trim(), name[2].trim(), name[3].trim(),
								newValue.trim(), name[5].trim());

						updatedValue = name[0].trim() + " : " + name[1].trim() + " : " + name[2].trim() + " : "
								+ name[3].trim() + " : " + newValue.trim() + " : " + name[5].trim();
						cont = cont + updatedValue + "\n";
						//list.add(t1);
						break;	
						
					case 6:
						//System.out.println("Status Update");
						t1 = new TaskBean(name[0].trim(), name[1].trim(), name[2].trim(), name[3].trim(),
								name[4].trim(),  newValue.trim());

						updatedValue = name[0].trim() + " : " + "15/4/2023" + " : " + name[2].trim() + " : "
								+ name[3].trim() + " : " + name[4].trim() + " : " + newValue.trim();
						cont = cont + updatedValue + "\n";
						//list.add(t1);
						break;		
						

					} // switch*/

				} else {
					cont = cont + line + "\n";
					t1 = new TaskBean(name[0].trim(), name[1].trim(), name[2].trim(), name[3].trim(),
							name[4].trim(), name[5].trim());
					//list.add(t1);
				}

			} // while
	
	 FileWriter fw = new FileWriter(Constants.filePath.trim() + catName.trim() + ".txt");
	 fw.write(cont);
     fw.close();
	 new Model().readTaskFromFileLoadAllTask(catName);
	 } catch (Exception ex) {
			System.out.println("Ops!!!!!");
		}
		return Helper.listTask;		
	}
	
	public boolean listAllTask() {
		if (Helper.listTask.size() != 0) {
			for (TaskBean t : Helper.listTask) {
				System.out.println(t);
			}
			return true;
			
		}
		return false;
	}
	
public boolean deleteParticularTask(String categoryName,String taskName) throws IOException, FileNotFoundException {
		
		FileReader fr1 = null;
		BufferedReader br = null;
		String line = "" ,cont = "";
		try {
			fr1 = new FileReader(Constants.filePath.trim() + categoryName.trim() + ".txt");
			br = new BufferedReader(fr1);
			try {
				
				while ((line = br.readLine()) != null)
				{
					String[] name = line.split(":");
					if(!(taskName.equals(name[0].trim()) ))
					{
					   cont = cont + line + "\n";
					   TaskBean t1 = new TaskBean(name[0].trim(),name[1].trim(),name[2].trim(),name[3].trim(),name[4].trim(),name[5].trim());
					   
					}
				}				
				
         FileWriter fw = new FileWriter(Constants.filePath.trim() + categoryName.trim() + ".txt");
         fw.write(cont);
         fw.close();	
         new Model().readTaskFromFileLoadAllTask(categoryName);
        
			} finally {
				fr1.close();
				br.close();
				
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true ;		
	}
 	
public  TaskBean createTaskBean(String catName) {
	try {
		TaskBean taskObj = new TaskBean();
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		String taskName="";
		while (true) {
			System.out.println("Enter taskname(No special symbols and only one word):");
			taskName = sc1.nextLine();				
			if (Helper.categoryNameValidation(taskName)) { // boolean validate = categoryNameValidation(catName);
				 if (!Helper.mapTask.containsKey(taskName)) {
					 taskObj.setName(taskName);
					 break;							
					} else {
						System.out.println("Task Name already present,Give another name");
					}
				} else {
					System.out.println("Invalid TaskName!! Please Enter valid taskname to proceed...");
				}
			}//while
		
		
		while (true) {
			System.out.println("Enter task description(cannot be empty or only spaces): ");
			String taskDesc = sc1.nextLine();
			if (taskDesc != null && !(taskDesc.trim().isEmpty())) {
				taskObj.setDesc(taskDesc);
				break;
			} else {
				System.out.println("Invalid Task Description!! Please Enter valid Description to proceed...");
			}
		} // while

		while (true) {
			System.out.println("Enter Date Of Creation Of The Task In The Format dd/MM/yyyy: ");

			String creationDate = sc1.nextLine();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				Date d1 = sdf.parse(creationDate.trim());
				taskObj.setCr_date(creationDate.trim());
				System.out.println(taskObj.getCr_date());
				break;
			} catch (ParseException ep) {
				System.out.println("Re-Enter Date In The Format dd/MM/yyyy: ");
				creationDate = sc1.nextLine();
				taskObj.setCr_date(creationDate.trim());
				System.out.println(taskObj.getEnd_date());
				break;
			}

		}

		while (true) {
			System.out.println("Enter End Date Of The Task In The Format dd/MM/yyyy: ");
			String endDate = sc1.nextLine();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				Date d1 = sdf.parse(endDate.trim());
				taskObj.setEnd_date(endDate);
				System.out.println(taskObj.getEnd_date());
				break;
			} catch (ParseException ep) {
				System.out.println("Re-Enter Date In The Format dd/MM/yyyy: ");
				endDate = sc1.nextLine();
				taskObj.setEnd_date(endDate);
				System.out.println(taskObj.getCr_date());
				break;
			}

		} // while

		while (true) {
			System.out.println("Enter an integer to set task priority (10->high  5->medium  1->low): ");
			Scanner sc = new Scanner(System.in);
			int taskPriority = sc.nextInt();
			if (taskPriority == 1 || taskPriority == 5 || taskPriority == 10) {
				if (taskPriority == 1) {
				       taskObj.setPriority(Constants.Low);
				}else if (taskPriority == 5) {
					    taskObj.setPriority(Constants.Medium);
				}else {
					 taskObj.setPriority(Constants.High);
				}
				break;
			} else {
				System.out.println("Invalid Task Priority!! Please Enter valid value for priority to proceed...");
			}
		}

		while (true) {
			System.out.println(
					"Enter task status -> New / InProgress / Completed  [case insensitive,without leading and trailing spaces,type as one word ]: ");
			Scanner sc = new Scanner(System.in);
			String taskStatus = sc.nextLine();
			if (taskStatus != null && !(taskStatus.trim().isEmpty()) && (taskStatus.split("\\s").length == 1)) {
				if (taskStatus.equalsIgnoreCase("New") || taskStatus.equalsIgnoreCase("InProgress")
						|| taskStatus.equalsIgnoreCase("Completed")) {
					taskObj.setStatus(taskStatus);
					break;
				}

			} else
				System.out.println("Invalid Task Status!! Please Enter valid value for Status to proceed...");
		}

		return taskObj;

	} catch (Exception e) {
		System.out.println("Oops!! Something Went Wrong Restart The Application...");
		// System.exit(1);
		return null;
	}
  }

 public void sortingTaskInAlphabeticalOrder(String categoryName) {
	 TreeMap<String,TaskBean> tree=new TreeMap<>();
		tree.putAll(Helper.mapTask);		
		for(String key : tree.keySet()) {
			System.out.println(tree.get(key));
			try {
				new Model().writeTaskintoFile(tree.get(key), categoryName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
  }
 
 public void sortingTaskInAlphabeticalReverseOrder(String categoryName) {
	 TreeMap<String,TaskBean> tree=new TreeMap<>(new MyComparatorNameReverse());
		tree.putAll(Helper.mapTask);		
		for(String key : tree.keySet()) {
			System.out.println(tree.get(key));
			try {
				new Model().writeTaskintoFile(tree.get(key), categoryName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
  }
 
 public void sortingTaskInLengthIncreasing(String categoryName) {
	 TreeMap<String,TaskBean> tree=new TreeMap<>(new MyComparatorlengthIncrease());
		tree.putAll(Helper.mapTask);		
		for(String key : tree.keySet()) {
			System.out.println(tree.get(key));
			try {
				new Model().writeTaskintoFile(tree.get(key), categoryName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
  }
 
 public void sortingTaskInLengthDecreasing(String categoryName) {
	 TreeMap<String,TaskBean> tree=new TreeMap<>(new MyComparatorlengthDecrease());
		tree.putAll(Helper.mapTask);		
		for(String key : tree.keySet()) {
			System.out.println(tree.get(key));
			try {
				new Model().writeTaskintoFile(tree.get(key), categoryName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
  }

}	


	
	/******************** END ***************************/ 

	
	
	
	
	
