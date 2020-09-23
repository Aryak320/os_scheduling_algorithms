public class Job implements Comparable<Job>
{
    private int id, submitTime, CPUTime, CPUTimeLeft, priority;
    public boolean fresh;
    private int startTime = 0, endTime = 0, waitTime=0;


    public int ProcessCompletionTime;
    public int processArrivalTime;
    public int waitingTime;
    public int turnAroundTime;
    public int con_burst;

    public Job(int id, int submitTime, int CPUTime)
     {
        super();
        this.id = id;
        this.submitTime = submitTime;
        this.CPUTime = CPUTime;
        this.CPUTimeLeft = CPUTime;
        this.fresh=true;
        this.waitingTime=0;
        this.con_burst=0;
     }
     public Job(int id, int submitTime, int CPUTime, int priority)
      {
         super();
         this.id = id;
         this.priority=priority;
         this.submitTime = submitTime;
         this.CPUTime = CPUTime;
         this.CPUTimeLeft = CPUTime;
         this.fresh=true;
         this.waitingTime=0;
         this.con_burst=0;
      }

    public void tick()
    {
      CPUTimeLeft--;

    }
    public void increment_con_burst()
    {
      con_burst++;

    }
    public void reset_con_burst()
    {
      con_burst=0;

    }
    public void start(int sysTime)
     {
        startTime = sysTime;
     }
    public void end(int p)
    {
      endTime = p;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubmitTime() {
        return submitTime;
    }
    public int getPriority() {
        return priority;
    }

    public void setSubmitTime(int submitTime) {
        this.submitTime = submitTime;
    }

    public int getCPUTime() {
        return CPUTime;
    }

    public void setCPUTime(int cPUTime) {
        CPUTime = cPUTime;
    }

    public int getCPUTimeLeft() {
        return CPUTimeLeft;
    }

    public void setCPUTimeLeft(int cPUTimeLeft) {
        CPUTimeLeft = cPUTimeLeft;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }



   @Override
   public int compareTo(Job j)
   {
     if (getSubmitTime()> j.getSubmitTime())
       {return 1;}
    else if (getSubmitTime()< j.getSubmitTime())
        {return -1;}
    else
        { return 0;}
   }

}
