import java.util.List;
import java.util.Collections;

public class ShortestJobFirst extends AllocationStrategy
{
   double totalWaitingTime;
   double totalTurnAroundTime;

    public ShortestJobFirst(List<Job> jobs)
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
              if(Min(burst,jobList,totalBrstTym)==-1)
              {
                System.out.print("__ ");
                totalBrstTym++;
                continue;
              }
              else if(p == null)
              {
                p=jobList.get(Min(burst,jobList,totalBrstTym));
              }
              else if(p.getCPUTimeLeft()<=0)
              {
                p=jobList.get(Min(burst,jobList,totalBrstTym));
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
    public int Min(int burst, List<Job> jobList,int totalBrstTym)
    {
        int j = -1;
        int min = totalBrstTym;
        for (Job process:jobList)
        {
            if (process.getCPUTimeLeft() < min && process.getCPUTimeLeft() >0 && burst >= process.getSubmitTime())
            {
                min = process.getCPUTimeLeft();
                j = jobList.indexOf(process);
            }
        }
        return j;
    }

}
