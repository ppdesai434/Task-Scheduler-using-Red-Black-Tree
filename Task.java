
package taskschedulerrbtree;

import java.util.Collections;

/**
 *
 * @author parthdesai namrathaanand shailchokshi sejalpatel
 */
public class Task implements Comparable{
            int taskId;
            int unfairness;
            int time_start;
            int timeRequired;
    public Task(int t_taskId, int t_startTime, int t_totalTimeRequired){
        this.timeRequired=t_totalTimeRequired;
        this.unfairness=0;
    	this.taskId=t_taskId;
        this.time_start=t_startTime;

    }

    @Override
    public int compareTo(Object o) {
        int compare_Start_time = ((Task)o).time_start;
        return this.time_start-compare_Start_time;
    }
}
