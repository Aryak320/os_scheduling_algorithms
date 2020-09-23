import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;



public abstract class AllocationStrategy
{
    protected List<Job> Jobs;
    protected ArrayList<Job> Queue;

    public AllocationStrategy(List<Job> jobs)
     {
        super();
        Jobs = jobs;
     }

    public abstract void run();

}
