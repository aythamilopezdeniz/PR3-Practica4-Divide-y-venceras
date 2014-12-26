package práctica4;

public class VectorSorter {
    private final int[] vector;

    public VectorSorter(int[] vector) {
        this.vector=vector;
    }

    public int[] getVector() {
        return vector;
    }
    
    public void sort(){
        quickSort(vector, 0, vector.length-1);
    }

    private void quickSort(int[] vector, int inferiorLimit, int superiorLimit) {
         int i=inferiorLimit; 
         int j=superiorLimit;
         int pivote=vector[(inferiorLimit+superiorLimit) / 2];
         int auxiliar;
         do{
             while(vector[i]<pivote)i++;
             while(vector[j]>pivote)j--;
             if(i<=j){
                 auxiliar=vector[j];
                 vector[j]=vector[i];
                 vector[i]=auxiliar;
                 i++;
                 j--;
             }
         }
         while (i<=j);
         if(inferiorLimit<j)
             quickSort(vector,inferiorLimit, j);
         if(superiorLimit>i)
             quickSort(vector,i,superiorLimit);
    }
}