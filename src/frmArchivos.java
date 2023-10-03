import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class frmArchivos extends JFrame {

    private JPanel panelMain;
    private JButton button1;
    private JComboBox comboBox1;
    private JLabel jlabUnidad;
    private JLabel jlabDirectorio;
    private JScrollPane jScrollDirectorios;
    private JLabel jLablArchivos;
    private JList list1;
    private JTextArea textArea1;
    private JComboBox comboBox2;
    private JTextArea textArea2;

    public frmArchivos(){
        setContentPane(panelMain);
        setTitle("Buscador de Archivos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setLocationRelativeTo(null);
        textArea1.setEditable(false);
        textArea2.setEditable(false);

        addUnidades();
        String ruta1 = comboBox1.getSelectedItem().toString();

        addRuta(ruta1);
        addDirectorios(ruta1);
        addArchivos(ruta1);
        setVisible(true);

        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String ruta = comboBox2.getSelectedItem().toString();
                textArea1.setText("");
                textArea2.setText("");
                comboBox2.removeAllItems();
                addRuta(ruta);
                addDirectorios(ruta);
                addArchivos(ruta);
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addRuta(comboBox1.getSelectedItem().toString());
            }
        });
    }

    public void addDirectorios(String rutaElegida) {

        File ruta = new File(rutaElegida);

        if (ruta.exists()) {
            File[] listaArchivos = ruta.listFiles();

            for (int i = 0; i < listaArchivos.length; i++) {
                if (listaArchivos[i].isDirectory()) {
                    if (i == 0) {
                        textArea1.setText(listaArchivos[i].toString());
                    } else {
                        textArea1.setText(textArea1.getText() + "\n" + listaArchivos[i].toString());
                    }
                }
            }
        }
    }



    public void addArchivos(String rutaElegida) {

        File ruta = new File(rutaElegida);

        if (ruta.exists()) {
            File[] listaArchivos = ruta.listFiles();

            for (int i = 0; i < listaArchivos.length; i++) {
                if (listaArchivos[i].isFile()) {
                    if (i == 0) {
                        textArea2.setText(listaArchivos[i].toString());
                    } else {
                        textArea2.setText(textArea2.getText() + "\n" + listaArchivos[i].toString());
                    }
                }
            }
        }
    }

    public void addRuta(String rutaElegida){
        File ruta = new File(rutaElegida);

        comboBox2.addItem(comboBox1.getSelectedItem());

        if(ruta.exists()){
            File[] listaArchivos = ruta.listFiles();

            for (int i = 0; i < listaArchivos.length; i++) {
                if (listaArchivos[i].isDirectory()) {
                    comboBox2.addItem(listaArchivos[i]);
                }
            }
        }
    }
    public void addUnidades(){
        File[] unidades = File.listRoots();
        File unidad;

        for (int i = 0; i < unidades.length; i++){
            unidad = unidades[i];
            comboBox1.addItem(unidad);
        }
    }

    public static void main(String[] args){
        new frmArchivos();
    }
}
