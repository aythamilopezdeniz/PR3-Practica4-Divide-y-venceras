package práctica4;

public class CompruebaCaso {
    public static void compruebaVector(int[] vector, int tamaño){
        int n=vector.length;
        if(n!=tamaño) System.out.println("compruebaVector: El vector no tiene el tamaño esperado");
        int i,inversiones;
        for(inversiones=0,i=1;i<n;i++) /* calcula el número de elementos desordenados */
            if(vector[i]<vector[i-1]) inversiones++;
        if(inversiones==0){
            int faltan;
            for(faltan=0,i=0;i<n;i++) /* calcula el número de elementos que faltan */
                if(vector[i] != i+1) faltan++;
            if(faltan==0)
                return; /* Ordenación correcta */
        }
        else {
            System.out.print("compruebaVector: En un vector de "+n+" elementos ");
            System.out.println("se ha(n) encontrado "+inversiones+" posición(es) desordenada(s)");
        }
        byte [] aux = new byte[n];
        if(aux != null){
            int faltan=0, sobran=0;
            for(i=0;i<n;i++)	aux[i]=0;
            for(i=0;i<n;i++)
                if((vector[i] > 0) && (vector[i] <= n))
                    aux[vector[i]-1]=1; /* vec[i] está */
                else
                    sobran++;  /* vec[i] tiene un valor fuera de rango */
            for(i=0;i<n;i++) /* cuenta los no encontrados */
                if(aux[i] == 0 ) faltan++;
            if(faltan!=0){
                System.out.print("compruebaVector: Error en los datos ");
                System.out.println("falta(n) "+faltan+" elemento(s)");
            }
            if(sobran!=0)
                System.out.println("compruebaVector: "+sobran+" elemento(s) esta(n) fuera de rango");
        }
    }
}