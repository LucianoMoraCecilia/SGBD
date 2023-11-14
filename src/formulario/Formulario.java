package formulario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Formulario {
 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();

        });
    }

    private static void createAndShowGUI() {
        // Crear el frame principal
        JFrame frame = new JFrame("SISTEMA GESTOR DE BASE DE DATOS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Tamaño del frame
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        Color colorPersonalizado = new Color(217, 160, 236);
     
        
        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Crear un menú "Archivo"
        JMenu menuArchivo = new JMenu("MENÚ");
        JMenuItem itemAbrir = new JMenuItem("Conectar a base de datos");
        JMenuItem itemGuardar = new JMenuItem("Crear base de datos");
        JMenuItem itemTabla = new JMenuItem("Crear Tablas");
        menuArchivo.add(itemAbrir);
       menuArchivo.add(itemGuardar);
    //    menuArchivo.addSeparator();
        menuArchivo.add(itemTabla);

        // Agregar el menú "Archivo" a la barra de menú
        menuBar.add(menuArchivo);

        // Configurar el frame para usar la barra de menú
        frame.setJMenuBar(menuBar);


      
        // Manejar el evento del botón "Conectar"
        itemAbrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear y configurar el segundo formulario (formulario2)
                formulario2 segundoFormulario = new formulario2();
                segundoFormulario.setVisible(true); // Mostrar el segundo formulario
                frame.dispose();
            }
        });
        

        // Crear un JLabel para mostrar la imagen
        ImageIcon icon = new ImageIcon("C:\\Users\\cecil\\OneDrive\\Documentos\\7 SEMESTRE\\OSCAR\\Formulario\\fondo.png");
        JLabel label = new JLabel(icon);

        // Configurar el JLabel para centrar la imagen
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        // Crear el segundo panel (2/3 del ancho)
        JPanel panel2 = new JPanel();
        panel2.setBackground(colorPersonalizado);
        // Agregar el JLabel al panel1
        panel2.add(label);

        // Configurar el gestor de diseño del frame como GridBagLayout
        frame.setLayout(new GridBagLayout());
        
    

        // Crear restricciones para el panel1
        GridBagConstraints constraints1 = new GridBagConstraints();
        constraints1.gridx = 0; // Posición en la cuadrícula X
        constraints1.gridy = 0; // Posición en la cuadrícula Y
        constraints1.gridwidth = 1; // Ancho en celdas (1/3)
        constraints1.fill = GridBagConstraints.BOTH; // Rellenar el espacio horizontal y verticalmente
        constraints1.weightx = 0.25; // Proporción de espacio horizontal (1/3)
        constraints1.weighty = 1.0; // Proporción de espacio vertical
      //  frame.add(panel1, constraints1);

        // Crear restricciones para el panel2
        GridBagConstraints constraints2 = new GridBagConstraints();
        constraints2.gridx = 1; // Posición en la cuadrícula X
        constraints2.gridy = 0; // Posición en la cuadrícula Y
        constraints2.gridwidth = 3; // Ancho en celdas (2/3)
        constraints2.fill = GridBagConstraints.BOTH; // Rellenar el espacio horizontal y verticalmente
        constraints2.weightx = 0.75; // Proporción de espacio horizontal (2/3)
        constraints2.weighty = 1.0; // Proporción de espacio vertical
        frame.add(panel2, constraints2);

        ImageIcon frameIcon = new ImageIcon("C:\\Users\\cecil\\OneDrive\\Documentos\\7 SEMESTRE\\OSCAR\\Formulario\\fondo.jpeg"); // Reemplaza con la ruta de tu icono
        frame.setIconImage(frameIcon.getImage());

        
        // Hacer visible el frame
        frame.setVisible(true);
    }
}
