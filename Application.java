
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
* Main class for implementing OS scheduling algorithms.
*
* @author ARYAK SEN

*
*/
public class Application
 {

    public static void main(String[] args)
     {




        String filename ;
        String allocationStrategy;
        int quantum=2;

        filename = args[0];
        allocationStrategy = args[1];
        System.out.println(allocationStrategy);






        if(args.length==3)
        {
            quantum = new Integer(args[2]);
        }

        BufferedReader br = null;


        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader("C:\\Users\\aryak\\Desktop\\Scheduling algorithms\\"+filename));




              if("FCFS".equalsIgnoreCase(allocationStrategy))
               {
                 List<Job> jobList = new ArrayList<Job>();

                 while ((sCurrentLine = br.readLine()) != null)
                 {

                     String a[] = sCurrentLine.split(",");
                     int processId = new Integer(a[0]);
                     int arrivalTime = new Integer(a[1]);
                     int cpuTime = new Integer(a[2]);

                     Job job = new Job(processId,arrivalTime,cpuTime);

                     jobList.add(job);


                 }
                FirstComeFirstServed firstComeFirstServed = new FirstComeFirstServed(jobList);
                firstComeFirstServed.run(jobList);


                }
               else if("SRT".equalsIgnoreCase(allocationStrategy))
                {
                  List<Job> jobList = new ArrayList<Job>();

                  while ((sCurrentLine = br.readLine()) != null)
                  {

                      String a[] = sCurrentLine.split(",");
                      int processId = new Integer(a[0]);
                      int arrivalTime = new Integer(a[1]);
                      int cpuTime = new Integer(a[2]);

                      Job job = new Job(processId,arrivalTime,cpuTime);

                      jobList.add(job);


                  }
                ShortestRemainingTime shortestRemainingTime = new ShortestRemainingTime(jobList);
                shortestRemainingTime.run(jobList);

                }

                else if("RR".equalsIgnoreCase(allocationStrategy))
                {
                  List<Job> jobList = new ArrayList<Job>();

                  while ((sCurrentLine = br.readLine()) != null)
                  {

                      String a[] = sCurrentLine.split(",");
                      
                      int processId = new Integer(a[0]);
                      int arrivalTime = new Integer(a[1]);
                      int cpuTime = new Integer(a[2]);

                      Job job = new Job(processId,arrivalTime,cpuTime);

                      jobList.add(job);


                  }
                RoundRobin roundRobin = new RoundRobin(jobList);
                roundRobin.run(jobList,quantum);

               }

               else if("SJF".equalsIgnoreCase(allocationStrategy))
               {
                 List<Job> jobList = new ArrayList<Job>();

                 while ((sCurrentLine = br.readLine()) != null)
                 {

                     String a[] = sCurrentLine.split(",");
                     int processId = new Integer(a[0]);
                     int arrivalTime = new Integer(a[1]);
                     int cpuTime = new Integer(a[2]);

                     Job job = new Job(processId,arrivalTime,cpuTime);

                     jobList.add(job);


                 }
               ShortestJobFirst shortestJobFirst=new ShortestJobFirst(jobList);
               shortestJobFirst.run(jobList);

             }

              else if("PP".equalsIgnoreCase(allocationStrategy))
              {List<Job> jobList = new ArrayList<Job>();

              while ((sCurrentLine = br.readLine()) != null)
              {

                  String a[] = sCurrentLine.split(",");
                  int processId = new Integer(a[0]);
                  int arrivalTime = new Integer(a[1]);
                  int cpuTime = new Integer(a[2]);
                  int priority = new Integer(a[3]);

                  Job job = new Job(processId,arrivalTime,cpuTime,priority);

                  jobList.add(job);


              }

              PriorityPreemptive pp = new PriorityPreemptive(jobList);
              pp.run(jobList);

             }

             else if("PNP".equalsIgnoreCase(allocationStrategy))
             {
             List<Job> jobList = new ArrayList<Job>();

             while ((sCurrentLine = br.readLine()) != null)
             {

                 String a[] = sCurrentLine.split(",");
                 int processId = new Integer(a[0]);
                 int arrivalTime = new Integer(a[1]);
                 int cpuTime = new Integer(a[2]);
                 int priority = new Integer(a[3]);

                 Job job = new Job(processId,arrivalTime,cpuTime, priority);

                 jobList.add(job);


             }
             PriorityNonPreemptive pnp = new PriorityNonPreemptive(jobList);
             pnp.run(jobList);

           }

            }
            catch (IOException e)
             {
               e.printStackTrace();
             }
             finally
             {
               try
                {
                   if (br != null)br.close();
                }
                catch (IOException ex)
                {
                 ex.printStackTrace();
                }
             }








    }


}
