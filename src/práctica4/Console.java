package práctica4;

import java.util.Arrays;

public class Console implements OrdenarVector {
    private int[] tamaño=new int[]{100, 200, 500, 1000, 2000, 5000};
    public Console() throws Exception {
        for(int i=0;i<tamaño.length;i++) {
            int[] vector=GeneraCaso.generaVector(tamaño[i], true);
            System.out.print("Vector "+i+" ");
            Viewer(vector);
        }
    }

    public final void Viewer(int[] vector) throws Exception {
        menoresQue(vector,vector.length/2,0,vector.length);
        int p=Arrays.binarySearch(vector, 0, vector.length, (vector.length/2-((int)Math.random()*10)));
        int[]array2=Arrays.copyOfRange(vector, 0, p+1);
        VectorSorter vs=new VectorSorter(array2);
        vs.sort();
        vector=vs.getVector();
        for(int i=vector.length-1;i>=0;i--){
            if(i==0)
                System.out.print(vector[i]);
            else
                System.out.print(vector[i]+",");
        }
        System.out.println("");
    }

    @Override
    public void menoresQue(int[] vector, int umbral, int linf, int lsup) throws Exception {
        if(linf>lsup)throw new Exception("Limite inferior > Limite superior");
        if(linf==lsup)return;
        VectorDivider vd=new VectorDivider(vector);
        vd.divide();
        if(vector[vd.getInitialPointer()-1]==umbral)return;
        if(umbral>vector[vd.getFinalPointer()+1])
            menoresQue(vector, umbral,vd.getInitialPointer(),lsup);
        else
            menoresQue(vector, umbral,linf,vd.getFinalPointer());
    }
}