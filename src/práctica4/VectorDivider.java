package práctica4;

public class VectorDivider {
    private final int[] vector;
    private int initialPointer=0;
    private int finalPointer=0;

    public VectorDivider(int[] vector) {
        this.vector=vector;
    }
    
    public void divide(){
        divide(0, vector.length-1);
    }

    public int[] getVector() {
        return vector;
    }

    public int getInitialPointer() {
        return initialPointer;
    }

    public int getFinalPointer() {
        return finalPointer;
    }
    
    private void divide(int inferiorLimit, int superiorLimit) {
        int pivot=(int)(Math.random()*superiorLimit+inferiorLimit);
        initialPointer=inferiorLimit;
        finalPointer=superiorLimit;
        while(initialPointer<finalPointer){
            while((initialPointer<=superiorLimit)&&(vector[initialPointer]<=vector[pivot]))
                initialPointer++;
            while((finalPointer>=inferiorLimit)&&(vector[finalPointer]>=vector[pivot]))
                finalPointer--;
            if(initialPointer<finalPointer){
                int aux=vector[initialPointer];
                vector[initialPointer]=vector[finalPointer];
                vector[finalPointer]=aux;
                initialPointer++;
                finalPointer--;
            }
        }
        if(initialPointer<pivot){
            int aux=vector[initialPointer];
            vector[initialPointer]=vector[pivot];
            vector[pivot]=aux;
            initialPointer++;
        }else{
            if(finalPointer>pivot){
                int aux=vector[pivot];
                vector[pivot]=vector[finalPointer];
                vector[finalPointer]=aux;
                finalPointer--;
            }
        }
    }
}