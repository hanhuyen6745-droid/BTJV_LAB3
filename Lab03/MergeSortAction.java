import java.util.concurrent.RecursiveAction;

public class MergeSortAction extends RecursiveAction{
   private static final int SEQUENTIAL_THRESHOLD=10_000;
   private final int[] arr;
   private final int[] temp;
   private final int start, end;
   public MergeSortAction(int[] arr , int[] temp, int start, int end){
    this.arr=arr;
    this.temp=temp;
    this.start=start;
    this.end=end;
   }
    @Override
    protected void compute(){
        if(end-start <= SEQUENTIAL_THRESHOLD){
            Arrays.sort(Arr, start, end);
            return;
        }
        int mid=(start+end)/2;
        MergeSortAction left =new MergeSortAction(arr, temp,start, mid);
        MergeSortAction right =new MergeSortAction(arr, temp,mid, end);

        invokeAll(left, right);
        merge(start, mid, end);


    }
    private void merge (int start, int mid, int end){
        System.arraycopy(arr, start, temp, start, end-start);
        int i=start, j=mid, k=start;
        while(i<mid && j<end){
            if (temp[i]<= temp[j]){
                arr[k++]=temp[i++];
            }
            else{
                arr[k++]=temp[j++];
            }
        }
        while (i<mid) {
            arr[k++]=temp[i++];
            
        }
        while(i<mid){
            arr[k++]=temp[i++];
        }
        while(j<end){
            arr[k++]=temp[j++];
        }
    }
}