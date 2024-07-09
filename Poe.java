package Jehoshaphat;

import javax.swing.JOptionPane;

public class Poe {
    
    static String done;
    static String longest; 
    static String taskFound;
    static String dev_tasks; 
    static String deleted; 
    static String devNames;
    static String search;
    static String t_name;
    static String report;
    
    
    static int option = 0;
    static String choice; 
    
    static int size = TaskInfo.getNumberOfTasks();
    
    static String[] developerNames = new String[size];
    static String[] taskNames = new String[size];
    static String[] taskIDs = new String[size];
    static String[] taskStatuses =new String[size];
    static int[] taskDurations = new int[size];
    
    /*
        In the following method, the user is given options to perform a number of different operations
        on the task details
    */
    public static void manipulateArrays(String developerName[], String taskName[], String taskID[], String[] status, int duration[]){
        //the following lines copy the local arrays into the global arrays so the information can be accessed anywhere
        System.arraycopy(developerName, 0, developerNames, 0, size);
        System.arraycopy(taskName, 0, taskNames, 0, size);
        System.arraycopy(taskID, 0, taskIDs, 0, size);
        System.arraycopy(status, 0, taskStatuses, 0, size);
        System.arraycopy(duration, 0, taskDurations, 0, size);
        
        
        /*
        The options to perform manipute the arrays is only presented to the user if they select 1
        */ 
        option = Integer.parseInt(JOptionPane.showInputDialog("""
                                                              `````TASKS CAMPTURED`````.
                                                              Press 1 to operate on the captured data or 0 to quit"""));
        do{
            choice = JOptionPane.showInputDialog("```CHOOSE AN OPTION```\n"
                    + "a) View the list of all tasks with the status of done\n"
                    + "b) View the name of the developer whose task takes the longest to complete\n"
                    + "c) Search for a Task\n"
                    + "d) View the list of tasks assigned to a developer\n"
                    + "e) Delete a task\n"
                    + "f) Get a report of all the captured Tasks");
            
            switch(choice){
                case "a": // the user gets the list of done tasks if they select a
                    doneTasks();
                    break;
                case "b":
                    longestTask();
                    break;
                case "c":
                    search = JOptionPane.showInputDialog("Name of the task to be searched");
                    searchTask(search);
                    break;
                case "d":
                    devNames = JOptionPane.showInputDialog("Developer full names");
                    developerTasks(devNames);
                    break;
                case "e":
                    t_name = JOptionPane.showInputDialog("Name of the task to be deleted");
                   deleteTask(t_name);
                    break;
                case "f":
                    displayReport();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Choose from the provided options!");
                    
            }
        
            // the user is asked whether they want to perfor more operations on the arrays
            option = Integer.parseInt(JOptionPane.showInputDialog("Press 1 to continue operating on the data and 0 to stop"));
            
        }while(option == 1);

        
    }
    
    public static void doneTasks(){
        
        for(int i = 0; i < size; i++){
            if(taskStatuses[i].equals("Done")){
                done = (developerNames[i]+", "
                        +taskNames[i]+ ", "
                        +taskDurations[i]);
            }
        }
        JOptionPane.showMessageDialog(null, done);

    }
        
    public static void longestTask(){
        int maximum;
        maximum = taskDurations[0];
        int position = 0; // this variable will hold the location of the largest number in the taskDurations array
        // this loop finds the largest value in the taskDurations array and stores it in the maximum variable
        for(int i = 0; i < size; i++){
            
            if(taskDurations[i] > maximum){
                maximum = taskDurations[i];
                position = i;
            }                        
        }
        
         longest = (developerNames[position]+ ", " + maximum);
         JOptionPane.showMessageDialog(null,longest);
        
    }
    
    /*
     This method allows a user to search for a Task using the task name
    */
    public static String searchTask(String searching){
        searching = search;
        for(int i = 0; i < size; i++){
            if(taskNames[i].equals(searching)){
                taskFound = (taskNames[i] + ", " + developerNames[i] +", "+ taskStatuses[i]);
                i = size; // this line will terminate the loop when a developer name is found
                
            }
            else{
                taskFound = "Task not found";
            }
        }
        JOptionPane.showMessageDialog(null, taskFound);
        return searching;

    }
    
    /*
        This method allows the user to search for all the tasks assigned to  particular developer
    */
    public static String developerTasks(String developer){
        developer = devNames;
        for(int i = 0; i < size; i++){
            if(developerNames[i].equals(devNames)){
               dev_tasks = (taskNames[i] + ", " + taskStatuses[i]);
               i = size; // this line will terminate the loop when a developer name is found
            }
            else{
                dev_tasks = "Developer name not found";
            }
        }
        JOptionPane.showMessageDialog(null, dev_tasks);
        return dev_tasks;

    }
    /*
     If the user enters a task that is in the array, the task will be deleted along with all the details related to it.
    */
    public static String deleteTask(String task){
        task = t_name;
        int deleteIndex; // this variable will hold the name of the task to be deleted
        /*
            The loop finds the task to be deleted, and if the task is found, its index is noted and everything at that
            index is removed iin the parallel arrays.
        */
        for(int i = 0; i < size; i++){
            
            if(taskNames[i].equals(task)){
               deleteIndex = i;
               
               deleted = ("Task \"" + taskNames[i] + "\" successfully deleted.");
               
                taskNames[deleteIndex] = "";
                
                developerNames[deleteIndex] = "";
                
                taskIDs[deleteIndex] = "";
                
                taskStatuses[deleteIndex] = "";
                
                taskDurations[deleteIndex] = 0;
                
                i = size;
            }
                         
        }
        JOptionPane.showMessageDialog(null,deleted);
        return deleted;
    }
    public static void displayReport(){
        for(int i = 0; i < size; i++){
            if(!"".equals(taskNames[i]) && taskDurations[i] != 0){
                report = (taskNames[i] + "\n"
                        +developerNames[i]+ "\n"
                        + taskIDs[i] + "\n"
                        + taskStatuses[i] +"\n"
                        + taskDurations[i] + "\n");
            } 
            JOptionPane.showMessageDialog(null, report);
        }
    }
}