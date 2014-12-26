package práctica4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class MainFrame extends JFrame implements OrdenarVector {
    private JTextField vectorString;
    private JTextField umbral;
    private JTextField resultado;
    private int[] vector;

    public MainFrame() {
        this.setTitle("Práctica 4: Divide y vencerás");
        this.setMinimumSize(new Dimension(300, 100));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        createSplitPane();
        this.pack();
        this.setVisible(true);
    }

    private void createSplitPane() {
        JSplitPane split=new JSplitPane(JSplitPane.VERTICAL_SPLIT, 
                createPrimaryPanel(), createSecondaryPanel());
        split.setResizeWeight(1);
        this.getContentPane().add(split);
    }

    private JPanel createPrimaryPanel() {
        JPanel vectorPanel=new JPanel();
        vectorPanel.setLayout(new BorderLayout());
        vectorPanel.add(createVectorPanel(), BorderLayout.NORTH);
        vectorPanel.add(createUmbralPanel(), BorderLayout.CENTER);
        return vectorPanel;
    }

    private JPanel createVectorPanel() {
        JPanel vectorPanel=new JPanel();
        vectorString=new JTextField(15);
        vectorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        vectorPanel.add(new Label("Vector: "));
        vectorPanel.add(vectorString);
        vectorString.setEditable(true);
        return vectorPanel;
    }

    private JPanel createUmbralPanel() {
        JPanel umbralPanel=new JPanel();
        umbral=new JTextField(4);
        umbralPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        umbralPanel.add(new Label("Umbral: "));
        umbralPanel.add(umbral);
        umbral.setEditable(true);
        return umbralPanel;
    }

    private JPanel createSecondaryPanel() {
        JPanel secondaryPanel=new JPanel();
        resultado=new JTextField(15);
        secondaryPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        secondaryPanel.add(new Label("Resultado: "));
        secondaryPanel.add(resultado);
        resultado.setEditable(false);
        secondaryPanel.add(createCalcularButton());
        return secondaryPanel;
    }

    private JButton createCalcularButton() {
        JButton calcular=new JButton("Validar");
        calcular.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int tamaño=obtenerTamaño(vectorString.getText());
                vector=new int[tamaño];
                vector=obtenerVector(vectorString.getText());
                int numeroUmbral=comprobarUmbral(umbral.getText());
                try {
                    menoresQue(vector, numeroUmbral, 0, vector.length);
                } catch (Exception ex) {
                }
                int p=Arrays.binarySearch(vector, 0,vector.length,numeroUmbral);
                int[]array2=Arrays.copyOfRange(vector, 0, p+1);
                VectorSorter vs=new VectorSorter(array2);
                try{
                    vs.sort();
                }catch(Exception ex){
                    resultado.setText("Vector pequeño");
                }
                vector=vs.getVector();
                String result="";
                for(int i=vector.length-1;i>=0;i--){
                    if(i==0)
                        result+=vector[i];
                    else
                        result+=vector[i]+",";
                }
                resultado.setText(result);
            }
        });
        return calcular;
    }
    
    private int comprobarUmbral(String umbral) {
        String numero="0";
        for(int i=0;i<umbral.length();i++) {
            if(i==0){
                if(Character.isDigit(umbral.charAt(i)))
                    numero+=umbral.charAt(i);
            }else if(Character.isDigit(umbral.charAt(i))){
                if(i==umbral.length()-1){
                    numero+=umbral.charAt(i);
                }else
                    numero+=umbral.charAt(i);
            }else if(!Character.isDigit(umbral.charAt(i))&&
                    Character.isDigit(umbral.charAt(i-1))){
                if(i!=umbral.length()-1){
                    numero+=umbral.charAt(i);
                }
            }
        }
        return Integer.parseInt(numero);
    }
    
    private int obtenerTamaño(String vectorString) {
        int tamaño=0;
        for(int i=0;i<vectorString.length();i++){
            if(i>0){
                if(!Character.isDigit(vectorString.charAt(i))&&
                        Character.isDigit(vectorString.charAt(i-1)))
                    tamaño++;
            }
            if(Character.isDigit(vectorString.charAt(i))&&
                    i==vectorString.length()-1)
                tamaño++;
        }
        return tamaño;
    }
    
    private int[] obtenerVector(String vectorString) {
        int a=0;
        String numero="";
        for(int i=0;i<vectorString.length();i++){
            if(i==0){
                if(Character.isDigit(vectorString.charAt(i)))
                    numero+=vectorString.charAt(i);
            }else if(Character.isDigit(vectorString.charAt(i))){
                if(i==vectorString.length()-1){
                    numero+=vectorString.charAt(i);
                    vector[a]=Integer.parseInt(numero);
                    a++;
                    numero="";
                }else
                    numero+=vectorString.charAt(i);
            }else if(!Character.isDigit(vectorString.charAt(i))&&
                    Character.isDigit(vectorString.charAt(i-1))){
                if(i!=vectorString.length()-1){
                    vector[a]=Integer.parseInt(numero);
                    a++;
                    numero="";
                }
            }
        }
        return vector;
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