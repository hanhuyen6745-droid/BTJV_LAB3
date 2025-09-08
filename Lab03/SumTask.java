import java.util.List;

public class SumTask extends RecusiveTask<Long>{
    private static final int SEQUENTIAL_THRESHOLD=5_000_000;
    private List<Long> data;
    public SumTask (List<Long> data ){this.data=data;}


   @Override
    protected Long compute(){
        if(data.size() <= SEQUENTIAL_THRESHOLD){
            return computeSummary();
        }
        else{
            int mid= data.size()/2;
            SumTask leftTask=new SumTask(data.subList(0, mid));
            SumTask righTask=new SumTask(data.subList(mid, data.size()));

            leftTask.fork();
            long rightResult=righTask.compute();
            long leftResult=leftTask.join();
            return leftResult+rightResult;
        }

    }
    private long computeSummary(){
        long sum =0l;
        for(Long l: data){
            sum+=l;
        }
        return sum;
    }
    
}
    
