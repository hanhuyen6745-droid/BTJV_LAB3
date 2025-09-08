public class SumAction extends RecursiveAction {
    private static final int SEQUENTIAL_THRESHOLD=5_000_000;
    private List<Long> data;
    private long total =0L;
    public SumAction(List<Long> data){this.data =data; }
    protected long getTotal() {return total;}
    @Override
    protected void compute(){
        if(data.size()<= SEQUENTIAL_THRESHOLD){
            total=computeSummary();
        }else{
            int mid =data.size()/2;
            SumAction leftTask=new SumAction(data.subList(0,mid));
            SumAction rightTask=new SumAction(data.subList(mid, data.size()));

            //way 1
            leftTask.fork();
            rightTask.compute();
            leftTask.join();
            total=leftTask.getTotal()+rightTask.getTotal();
        }
        }
        //ham tinh tong
        private long computeSummary(){
            long sum=0l;
            for(Long l:data){
                sum+=l;
            }
            return sum;
        }

    }
