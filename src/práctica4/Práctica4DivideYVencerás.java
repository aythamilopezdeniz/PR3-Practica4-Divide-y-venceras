package práctica4;

public class Práctica4DivideYVencerás {

    public static void main(String[] args) throws Exception {
/*        int[] v=new int[]{1000, 2000, 4000, 8000, 16000, 32000};
        int[][] vec=new int[5][1];
        for(int i=0;i<v.length;i++) {
            vec[i]=GeneraCaso.generaVector(v[i], true);
            CompruebaCaso.compruebaVector(vec[i], v[i]);
        }*/
        ConsoleMain();
        //UserInterface();
    }

    private static void ConsoleMain() throws Exception {
        new Console().Viewer();
    }

    private static void UserInterface() {
        new MainFrame();
    }
}