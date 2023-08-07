package com.uis.toDoAppProject;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
//import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class App {
	private static  Model a1 = new Model();	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Scanner num = new Scanner(System.in);
		String catName = null;
		boolean flag = true;
		Logger logger = Logger.getInstance();
		
		a1.readAllCategoryFromFile();
		while (flag) {
			try {
				menu1();
				int val = sc.nextInt();
				switch (val) {
				case 1:
					System.out.println("creating category");
					logger.log("VIEW-CONTROLLER", Constants.HIGH);
					if(Helper.categoryList.size()!=0) {
						System.out.println("Existing Category");
						for(String nameOfTheCategory : Helper.categoryList) {
							System.out.println(nameOfTheCategory);
						}
					}
					
					while (true) {
					    System.out.println(
							"Enter Category Name which contains 1 word , no special Character,no space, starts with letters and can contain alphanumeric :");
					
						catName = num.nextLine();
						boolean validate = Helper.categoryNameValidation(catName);
						if (validate) {
							break;
							} 
						}
					if(!Helper.categorySet.contains(catName)) {
					boolean result=a1.createCategory(catName);
					if(result) {
					System.out.println("New Category created successfully ");
					for(String nameOfTheCategory : Helper.categoryList) {
						System.out.println(nameOfTheCategory);
					}
					}else {
						System.out.println("New category not created ");
					}
					}else {
						System.out.println("Category already exists, give another name\n");
					}
					menu2(catName);
					System.out.println();
					break;

				case 2:
					System.out.println("load category");
					logger.log("VIEW-CONTROLLER", Constants.HIGH);
					if(Helper.categorySet.size()!=0) {
						for(String nameOfTheCategory : Helper.categoryList) {
							System.out.println(nameOfTheCategory);
						}
						while (true) {
						    System.out.println(
								"Enter Category Name which contains 1 word , no special Character,no space, starts with letters and can contain alphanumeric :");
						
							catName = num.nextLine();
							boolean validate = Helper.categoryNameValidation(catName);
							if (validate) {
								break;
								} 
							}
						if(Helper.categorySet.contains(catName)) {
							a1.readTaskFromFileLoadAllTask(catName);
						    menu2(catName);
						}else {
							System.out.println("Given category not present ");
						}
					}else {
						System.out.println("Directory is empty, Create new category");
					}					
					System.out.println();
					break;

				case 3:
					System.out.println("list all category");
					logger.log("VIEW-CONTROLLER", Constants.HIGH);
					if (Helper.categoryList.size() == 0) {
						System.out.println("List is empty, Create Category");
					} else {
						for(String nameOfTheCategory : Helper.categoryList) {
							System.out.println(nameOfTheCategory);
						}
					}
					System.out.println();
					break;

				case 4:
					System.out.println("delete Particular category");
					logger.log("VIEW-CONTROLLER", Constants.HIGH);
					if(Helper.categoryList.size()!=0) {
						for(String nameOfTheCategory : Helper.categoryList) {
							System.out.println(nameOfTheCategory);
						}
					while (true) {
					    System.out.println(
							"Enter Category Name which contains 1 word , no special Character,no space, starts with letters and can contain alphanumeric :");
						catName = num.nextLine();
						boolean validate = Helper.categoryNameValidation(catName);
						if (validate) {
							break;
							} 
						}
					
						if(Helper.categorySet.contains(catName)) {
							boolean result=a1.deleteParticularCategory(catName);
								if(result) {
								System.out.println("Deletion done successfully");
								if(Helper.categoryList.size()!=0) {
									System.out.println("Existing Category");
									for(String nameOfTheCategory : Helper.categoryList) {
										System.out.println(nameOfTheCategory);
									}
								}
								}else {
									System.out.println("Deletion is unsuccess");
								}
							}else {
								System.out.println("Given category is not present to delete");
							}
					}else {
						System.out.println("There is no category present in the file");
					}
						System.out.println();
						break;

				case 5:
					System.out.println("delete all category");
					logger.log("VIEW-CONTROLLER", Constants.HIGH);
					if(Helper.categoryList.size()!=0) {
						boolean result=a1.deleteAllCategory();
						if(result) {
							System.out.println("All category deleted successfully");
						}else {
							System.out.println("All category deletion is unsuccess");
						}
					}else {
						System.out.println("There is no category present in the file");
					}
						System.out.println();
						break;

				case 6:
					System.out.println("exit");
					flag = false;
					System.out.println();
					break;

				default:
					logger.log("VIEW-CONTROLLER", Constants.HIGH);
					System.out.println("Enter 1 to 6 numbers only !!!!!!!!!!!");
					System.out.println();
					break;

				}// switch

			} catch (InputMismatchException e) {
				logger.log("\\nThere was a InputMismatchException , give a number", Constants.CRITICAL);
				System.out.println("\nThere was a InputMismatchException , give a number ");
				// num.next();
				sc.nextLine(); // Consume the invalid input
				continue; // Continue to next iteration of the loop
			}

			catch (Exception ex1) {
				System.out.println("OOps !!!!!! something went wrong , try again!!!!!");
				ex1.printStackTrace();
				continue;
			}
		} // while

	} // main

	public static void menu1() {
		System.out.println("Enter 1 to Create Category");
		System.out.println("Enter 2 to load Category");
		System.out.println("Enter 3 to list All category");
		System.out.println("Enter 4 to delete Particular Category");
		System.out.println("Enter 5 to delete All Category");
		System.out.println("Enter 6 to exit ");
	}

	public static void menu2(String catName) {
		Logger logger = Logger.getInstance();
		Scanner sc = new Scanner(System.in);
		Scanner kb = new Scanner(System.in);
		boolean flag2 = true;
		String taskName="";
		while (flag2) {
			System.out.println();
			logger.log("VIEW-CONTROLLER", Constants.HIGH);
			System.out.println("Enter 1 to Add the Task");
			System.out.println("Enter 2 to Update the Task");
			System.out.println("Enter 3 to list all task");
			System.out.println("Enter 4 to delete particular Task");
			System.out.println("Enter 5 to delete all the Task");
			System.out.println("Enter 6 to sort task");
			System.out.println("Enter 7 Return to Main Menu");
			try {
				int val = kb.nextInt();
			switch (val) {

				case 1:
					System.out.println("Adding task");
					TaskBean t1 = a1.createTaskBean(catName);
					t1.setDuration();
					List<TaskBean> list = a1.writeTaskintoFile(t1, catName);
					
					if (Helper.mapTask.size() != 0) {
						System.out.println("Added Successfully");
						for(TaskBean t: list) {
							//System.out.println();
							System.out.println(t);
						}						
					}
					System.out.println();
					break;
				
				case 2:
					String newValue="";
					System.out.println("update the task");					
					a1.readTaskFromFileLoadAllTask(catName);
					if (Helper.mapTask.size() == 0) {
						System.out.println("No task");
					} else {
						for(TaskBean t: Helper.listTask) {
							System.out.println(t);
						}
						while (true) {
							System.out.println("Enter taskname(No special symbols and only one word):");
							taskName  = sc.nextLine();
							if (Helper.categoryNameValidation(taskName.trim())) {
								break;
							}
						}

						boolean updateFlag = true;
						while (updateFlag) {
							try {
								System.out.println("Enter 1 to update task name");
								System.out.println("Enter 2 to update description");
								System.out.println("Enter 3 to update created date ");
								System.out.println("Enter 4 to update end date");
								System.out.println("Enter 5 to update priority ");
								System.out.println("Enter 6 to update status");
								System.out.println("Enter 7 to exit");
								// kb.nextInt();
								int opt = sc.nextInt();
								Scanner up = new Scanner(System.in);
								switch (opt) {
								case 1:
									System.out.println("updating TaskName");
									while (true) {
										System.out.println("Enter task Name(cannot be empty or only spaces): ");
										newValue = up.nextLine();
										//System.out.println(newValue);
										if (newValue != null && !(newValue.trim().isEmpty())) {
											//System.out.println(catName + " ," + taskName + " ," + newValue + " , " + opt);											
											break;
										} else {
											System.out.println(
													"Invalid Task Description!! Please Enter valid Description to proceed...");
										}
									}
									List<TaskBean> updatedList = a1.updateParticularTask(catName.trim(),taskName.trim(), newValue.trim(), opt);
									System.out.println("After Updation");
									for(TaskBean t:updatedList) {
										System.out.println(t);
									}
                                    System.out.println();
									break;
									
								case 2:
									System.out.println("updating description");
									while (true) {
										System.out.println("Enter task description(cannot be empty or only spaces): ");
										newValue = up.nextLine();
										//System.out.println(newValue);
										if (newValue != null && !(newValue.trim().isEmpty())) {
											//System.out
												//	.println(catName + " ," + taskName + " ," + newValue + " , " + opt);		

											break;
										} else {
											System.out.println(
													"Invalid Task Description!! Please Enter valid Description to proceed...");
										}
									}
									List<TaskBean> updatedDesc = a1.updateParticularTask(catName.trim(),taskName.trim(), newValue.trim(), opt);
									System.out.println("After Updation");
									for(TaskBean t:updatedDesc) {
										System.out.println(t);
									}
									 System.out.println();
									break;

								case 3:
									System.out.println("updating created date");
									while (true) {
										System.out.println(
												"Enter Date Of Creation Of The Task In The Format dd/MM/yyyy: ");
										newValue = up.nextLine();
										SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
										try {
											Date d1 = sdf.parse(newValue.trim());
											break;
										} catch (ParseException ep) {
											System.out.println(
													"Re-Enter Date In The Format dd/MM/yyyy: ");
											newValue = up.nextLine();
											break;
										}
									}
									List<TaskBean> updateDate = a1.updateParticularTask(catName.trim(),
											taskName.trim(), newValue.trim(), opt);
									System.out.println("After Updation");
									for(TaskBean t: updateDate) {
										System.out.println(t);
									}
									 System.out.println();
									break;

								case 4:
									System.out.println("updating end date");
									while (true) {
										System.out.println("Enter Date Of end Of The Task In The Format dd/MM/yyyy: ");
										newValue = up.nextLine();
										SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
										try {
											Date d1 = sdf.parse(newValue.trim());
											break;
										} catch (ParseException ep) {
											System.out.println(
													"Re-Enter Date In The Format dd/MM/yyyy: ");
											newValue = up.nextLine();
											break;
										}
									}
									List<TaskBean> endDateUpdated= a1.updateParticularTask(catName.trim(),
											taskName.trim(), newValue.trim(), opt);
									System.out.println("After Updation");
									for(TaskBean t: endDateUpdated) {
										System.out.println(t);
									}
									 System.out.println();
									break;

								case 5:
									System.out.println("Priority Update");
									int updatePriority=0;
									while (true) {
										System.out.println(
												"Enter a number(10->high  5->medium  1->low) to set task priority");
										updatePriority= up.nextInt();
										if (updatePriority == 1 || updatePriority == 10 || updatePriority==5) {
											if (updatePriority == 1) {
												newValue = Constants.Low;
											}else if (updatePriority == 5) {
												newValue = Constants.Medium;
											}else {
												newValue = Constants.High;
											}
											break;
										}					
										 else {
											System.out.println(
													"Invalid Task Priority!! Please Enter valid value for priority to proceed...");
										}
									} // while
									List<TaskBean> priorityUpdate = a1.updateParticularTask(catName.trim(),
											taskName.trim(), newValue.trim(), opt);
									System.out.println("After Updation");
									for(TaskBean t:  priorityUpdate) {
										System.out.println(t);
									}
									 System.out.println();
									break;

								case 6:
									System.out.println("Status Update");
									while (true) {
										System.out.println(
												"Enter task status -> New / InProgress / Completed  [case insensitive,without leading and trailing spaces,type as one word ]: ");
										newValue = up.nextLine();
										if (newValue != null && !(newValue.trim().isEmpty())
												&& (newValue.split("\\s").length == 1)) {
											if (newValue.equalsIgnoreCase("New")
													|| newValue.equalsIgnoreCase("InProgress")
													|| newValue.equalsIgnoreCase("Completed")) {
												break;
											}

										} else
											System.out.println(
													"Invalid Task Status!! Please Enter valid value for Status to proceed...");
									}
									System.out.println("After Updation");
									List<TaskBean> statusUpdate = a1.updateParticularTask(catName.trim(),taskName.trim(), newValue.trim(), opt);
									for(TaskBean t:  statusUpdate) {
										System.out.println(t);
									}

									break;

								case 7:
									updateFlag = false;
									break;

								default:
									System.out.println("Enter 1 to 7 number ");
									break;
								}// in sw
							} catch (InputMismatchException e) {
								System.out.println("There was a InputMismatchException , give a number ");
								logger.log("\\nThere was a InputMismatchException , give a number", Constants.CRITICAL);
								sc.nextLine(); // Consume the invalid input
								//kb.nextLine();
								continue; // Continue to next iteration of the loop
							}
						} // while

					} // endif

					break;

				case 3:
					System.out.println("list all the task");
					a1.readTaskFromFileLoadAllTask(catName);
					boolean allTask=a1.listAllTask();
					if(!allTask) {
						System.out.println("No Task present");
					}
					System.out.println("");
					break;

				case 4:
					System.out.println("delete particular task ");
					a1.readTaskFromFileLoadAllTask(catName);
					//boolean x = false;
					if(Helper.listTask.size()!=0) {
						System.out.println("Existing Task");
						for(TaskBean t: Helper.listTask) {
							System.out.println(t);
						}
					while (true) {
						System.out.println("Enter taskname(No special symbols and only one word):");
						taskName = sc.next();
						if (Helper.categoryNameValidation(taskName)) { // boolean validate = categoryNameValidation(catName);
						break;
						}else {
							System.out.println("Invalid TaskName!! Please Enter valid taskname to proceed...");
						}
						} // while
							boolean deleteTask = a1.deleteParticularTask(catName, taskName);
							if(deleteTask) {
								System.out.println("Deletion done successfully");
								if(Helper.listTask.size()!=0) {
								System.out.println("list of task after deletion");
							    	for(TaskBean t:Helper.listTask) {
										System.out.println(t);
									}}else {
										System.out.println("there were no task to delete");
									}
								}else {
									System.out.println("Deletion is unsuccess !!");
								}
								
						} else {
						System.out.println("there were no task to delete");
					}
					break;				
					
				case 5:
					System.out.println("delete all task ");
					new FileWriter(Constants.filePath + catName.trim() + ".txt", false).close();
					break;
					

				case 6:
				//	System.out.println("Enter 1 Alpha sorting");
					//System.out.println("Enter  2 keyLength sorting");
					//Logger logger = Logger.getInstance();
					a1.readTaskFromFileLoadAllTask(catName);
					if (Helper.listTask.size() != 0) {
						
						System.out.println("before Sorting");
						for(TaskBean t:Helper.listTask) {
							System.out.println(t);
						}
					boolean sortFlag = true;
					while (sortFlag) {
					try {
						
						System.out.println("\nEnter 1 to sort task in Alphabetical Order");
						System.out.println("Enter 2 to sort task in Alphabetical Reverse Order");
						System.out.println("Enter 3 to sort task in increasing length of the taskName");
						System.out.println("Enter 4 to sort task in decreasing length of the taskName");
						System.out.println("Enter 5 to exit");
						int srt = sc.nextInt();
						switch (srt) {
						case 1:
							System.out.println("sort task in Alphabetical Orderr");
							new FileWriter(Constants.filePath + catName.trim() + ".txt", false).close();
							a1.sortingTaskInAlphabeticalOrder(catName);						
							break;

						case 2:
							System.out.println("sort task in Alphabetical Reverse Order");
							new FileWriter(Constants.filePath + catName.trim() + ".txt", false).close();
							a1.sortingTaskInAlphabeticalReverseOrder(catName);
							break;
						case 3:
							System.out.println("sort task in increasing length of the taskName");
							new FileWriter(Constants.filePath + catName.trim() + ".txt", false).close();
							a1.sortingTaskInLengthIncreasing(catName);						
							break;

						case 4:
							System.out.println("sort task in decreasing length of the taskName");
							new FileWriter(Constants.filePath + catName.trim() + ".txt", false).close();
							a1.sortingTaskInLengthDecreasing(catName);	
							break;
							
						case 5:
							sortFlag=false;						
							break;
							
						default:
							System.out.println("Enter 1 to 4 number ");
							break;
						}
					} catch (InputMismatchException e) {
						System.out.println("There was a InputMismatchException , give a number ");						
						logger.log("There was a InputMismatchException , give a number", Constants.CRITICAL);
						sc.nextLine(); // Consume the invalid input
						continue; // Continue to next iteration of the loop
					}
				} // while
						
						
					} else {
						System.out.println("no task inside the given category to sort " );
						System.out.println("");
					}
				    break;

				case 7:
					flag2 = false;
					break;
					
				default:
					System.out.println("Enter 1 to 7 numbers only !!!!!!!!!!!");
					System.out.println();
					break;	

				}// switch

			}catch (InputMismatchException e) {
				System.out.println("There was a InputMismatchException , give a number ");
				logger.log("There was a InputMismatchException , give a number",Constants.CRITICAL);
				//sc.nextLine(); // Consume the invalid input
				kb.nextLine(); // Consume the invalid input
				continue; // Continue to next iteration of the loop
			}

			catch (Exception ex1) {
				System.out.println("OOps !!!!!! something went wrong , try again!!!!!");
				logger.log("OOps !!!!!! something went wrong , try again!!!!!",Constants.CRITICAL);
				ex1.printStackTrace();
				continue;
			}
		} // while

	}// menu2

	
	

	

}
