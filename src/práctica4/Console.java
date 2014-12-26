package práctica4;

import java.util.Arrays;

public class Console implements OrdenarVector {
    public void Viewer() throws Exception {
        //int[] vector={2,7,9,8,3,4,15,32,4,5,4,3};
        int[] vector={1,2,3,4,5,6,7,8,9};
        menoresQue(vector,6,0,vector.length);
        int p=Arrays.binarySearch(vector, 0,vector.length,6);
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
    }

    @Override
    public void menoresQue(int[] vector, int umbral, int linf, int lsup) throws Exception {
        if(linf>lsup)throw new Exception();
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