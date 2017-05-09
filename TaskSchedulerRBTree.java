package taskschedulerrbtree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author parthdesai namrathaanand shailchokshi sejalpatel
 */
public class TaskSchedulerRBTree {

   
    public static void main(String[] args) {
        

    	try{
        	
        	
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            

            boolean isFirstLine = true;
            String line;
            int taskNumber=0;
            int runTime=0;
            ArrayList<Task> taskList = new ArrayList<Task>() {};
            int t_startTime;
            int t_taskId;
            int t_totalTimeRequired;
            Task tempObj=null;
            while((line=reader.readLine())!=null){

                if(isFirstLine){
                    isFirstLine=false;
                    String[] tempLine = line.split(" ");
                    taskNumber=Integer.parseInt(tempLine[0]);
                    runTime=Integer.parseInt(tempLine[1]);
                }else{
                    String[] tempLine = line.split(",");
                    t_taskId = Integer.parseInt(tempLine[0]);
                    t_startTime = Integer.parseInt(tempLine[1]);
                    t_totalTimeRequired = Integer.parseInt(tempLine[2]);
                    tempObj= new Task(t_taskId, t_startTime, t_totalTimeRequired);
                    taskList.add(tempObj);
                }
            }
            
            Collections.sort(taskList);
            
            RedBlackClass rbtobj = new RedBlackClass();
            int timeslot =0;
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date timestart = new Date();
            
            while(timeslot <= runTime){
                ArrayList<Task> resultList = findTaskAddedToRedBlackTree(taskList,timeslot);
                for(int x =0; x < resultList.size();x++){
                    Task treenodeObj = resultList.get(x);
                    rbtobj.insertNode(treenodeObj, 0);
                }
                
                rbtobj.deleteMinServeAddAgain();
                System.out.println("\nTree in order traversal at time = :"+timeslot);
                timeslot++;
                Thread.sleep(1000);
                
                rbtobj.inOrderTraversal(rbtobj.root);

                rbtobj.drawJFrame();
            }
            Date dateend = new Date();
            double difference = (double)dateend.getTime()-timestart.getTime();
            System.out.println("Time :"+difference);
            System.out.println("Tasks Completed "+rbtobj.countcompleted);
            System.out.println(rbtobj.countcompleted/difference);
            

        }catch(Exception e){
            System.out.println("Task Scheduler exception: "+e);
        }
    }
    public static ArrayList<Task> findTaskAddedToRedBlackTree(ArrayList<Task> givenList, int time){
        
        ArrayList<Task> resultSet = new ArrayList<Task>();
        for(int i=0;i<givenList.size();i++){
            
            if(((Task)givenList.get(i)).time_start>time){
                break;
            }
            if(((Task)givenList.get(i)).time_start==time){
                resultSet.add((Task)givenList.get(i));
            }
        }
        
        return resultSet;
    }
    
}
