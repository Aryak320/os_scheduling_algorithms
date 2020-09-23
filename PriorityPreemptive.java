import java.util.List;
import java.util.Collections;

public class PriorityPreemptive extends AllocationStrategy
{
   double totalWaitingTime;
   double totalTurnAroundTime;

    public PriorityPreemptive(List<Job> jobs)
    {
        super(jobs);
    }

    public void run()
    {

    }

    public void run(List<Job> jobList)
    {


        Collections.sort(jobList);
        int totalBrstTym = 0;

        for (Job job:jobList)
         {
            totalBrstTym = totalBrstTym + job.getCPUTime();
         }

        Job p=null;
        System.out.println("GANTT CHART>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for(int burst=0;burst<totalBrstTym;burst++)
        {
          if(Max(burst,jobList,totalBrstTym)==-1)
          {
            System.out.print("__ ");
            totalBrstTym++;
            continue;
          }
          else
           {
             p=jobList.get(Max(burst,jobList,totalBrstTym));
           }
           System.out.print("P"+p.getId()+ " ");
           p.tick();
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
        System.out.println("Total Burst "+totalBrstTym);

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
    public int Max(int burst, List<Job> jobList,int totalBrstTym)
    {
        int j = -1;
        int max = -999;
        for (Job process:jobList)
        {
            if (process.getPriority() > max && process.getCPUTimeLeft() >0 && burst >= process.getSubmitTime())
            {
                max = process.getPriority();
                j = jobList.indexOf(process);
            }
        }
        return j;
    }

}
