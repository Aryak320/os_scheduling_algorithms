import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class RoundRobin extends AllocationStrategy
{
   double totalWaitingTime;
   double totalTurnAroundTime;

    public RoundRobin(List<Job> jobs)
    {
        super(jobs);
    }

    public void run()
    {

    }

    public void run(List<Job> jobList, int quantum)
    {


        Collections.sort(jobList);
        int totalBrstTym = 0;

        for (Job job:jobList)
         {
            totalBrstTym = totalBrstTym + job.getCPUTime();
         }
        List<Integer> ready_queue = new ArrayList<Integer>();
        Job p=null;
        int job_index=0;
        System.out.println("GANTT CHART>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for(int burst=0;burst<totalBrstTym;burst++)
        {
           if(job_index<jobList.size() && jobList.get(job_index).getSubmitTime()==burst)
           {
             ready_queue.add(job_index++);
           }

           if(ready_queue.isEmpty())
           {
             System.out.print("__ ");
             totalBrstTym++;
             continue;
           }
           else if(p == null)
           {
             p=jobList.get(ready_queue.get(0));
           }
             else if(p.con_burst==quantum && p.getCPUTimeLeft()>0 )
             {
               int temp = ready_queue.get(0);
               ready_queue.remove(0);
               ready_queue.add(temp);
               p.reset_con_burst();
               p=jobList.get(ready_queue.get(0));
             }
             else if(p.getCPUTimeLeft()<=0)
             {
               ready_queue.remove(0);
               p.reset_con_burst();
               if(ready_queue.isEmpty())
               {
                 p= null;
                 System.out.print("__ ");
                 totalBrstTym++;
                 continue;
               }
               else
               {
                 p=jobList.get(ready_queue.get(0));
               }
             }




           System.out.print("P"+p.getId()+ " ");
           p.tick();
           p.increment_con_burst();
           for (Job job:jobList)
            {
               if(job.getId()!=p.getId() && job.getCPUTimeLeft()>0 && job.getSubmitTime()<=burst)
                  {
                    job.waitingTime++;
                  }

            }
           if(p.fresh)
           {
             p.setStartTime(burst+1);
             p.fresh=false;
           }
           if(p.getCPUTimeLeft()<=0)
           {
             p.setEndTime(burst+1);
           }

        }


        System.out.println();
        System.out.println("============================================ ");
        System.out.println("Process ID | Turnaround time | Waiting time ");
        System.out.println("============================================ ");
        for (Job job:jobList)
         {
             job.turnAroundTime=job.getEndTime()-job.getSubmitTime();
             totalWaitingTime=totalWaitingTime+job.waitingTime;
             totalTurnAroundTime=totalTurnAroundTime+job.turnAroundTime;
             System.out.println("    "+job.getId()+"     | "+"   "+job.turnAroundTime+"         | "+"    "+job.waitingTime+"   ");
         }

         System.out.println("Average waiting time="+totalWaitingTime/jobList.size());
         System.out.println("Average turn around time="+totalTurnAroundTime/jobList.size());
    }


}
