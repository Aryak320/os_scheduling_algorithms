import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

    public class FirstComeFirstServed extends AllocationStrategy
    {
       double totalWaitingTime;
       double totalTurnAroundTime;

        public FirstComeFirstServed(List<Job> jobs)
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

            int temp=0;
            Job p= jobList.get(temp);
          
            System.out.println("GANTT CHART>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            for(int burst=0;burst<totalBrstTym;burst++)
            {

                if(!(p.getSubmitTime()<=burst && p.getCPUTimeLeft()>0))
                 {
                 if(p.getCPUTimeLeft()<=0)
                {
                  p= jobList.get(++temp);


                }
                 if(p.getSubmitTime()>burst)
                {
                  System.out.print("__ ");
                  totalBrstTym++;
                  continue;
                }
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


    }
