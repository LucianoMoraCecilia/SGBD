package formulario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class formulario3 extends JFrame {

	 private DefaultListModel<String> listModel;
	    private JList<String> listaBasesDeDatos;
	    private String usuario;
	    private String contrasena;
	    private String host;
	    private String puerto;
	    
    public formulario3(String usuario, String contrasena, String host, String puerto, String nombreBase) {

    	this.usuario = usuario;
        this.contrasena = contrasena;
        this.host = host;
        this.puerto = puerto;
        		
        // Configurar el título y el tamaño de la ventana
        setTitle("CREAR BASE DE DATOS");
        setSize(350, 400);
        setLocationRelativeTo(null);

        // Crear un panel para organizar los componentes
        JPanel panel = new JPanel(new GridLayout(0,1)); 
        Color colorPanel = new Color(100, 136, 194);
        panel.setBackground(colorPanel);
        
     // Cargar los iconos desde archivos de imagen (asegúrate de que los archivos de imagen existan)
        ImageIcon iconoNombreBD = new ImageIcon("nombre_bd.png");
        ImageIcon iconoCharset = new ImageIcon("charset.png");
        ImageIcon iconoCrear = new ImageIcon("crearbd.png");
        
     // Etiquetas
        JLabel lblnombreBD = new JLabel("Nombre de la base de datos: ", iconoNombreBD, JLabel.LEFT);
        JLabel lblcharset = new JLabel("Char_set: ", iconoCharset, JLabel.LEFT);


        // Cajas de texto
        JTextField txtnomBD = new JTextField(10);
        String[] opciones = {"utf8_general_ci", "utf8mb4_general_ci", "latin1_swedish_ci", "utf16_general_ci", "binary", "utf8_bin", "utfmb4_bin"};
        JComboBox<String> comboBox = new JComboBox<>(opciones);
        
        // Agregar etiquetas y cajas de texto al panel
        panel.add(lblnombreBD);
        panel.add(txtnomBD);
        panel.add(lblcharset);
        panel.add(comboBox);

        // Crear un segundo panel para los botones
        JPanel panelBotones = new JPanel();

        // Botones
        JButton btnCrear = new JButton("Crear", iconoCrear);
        

        // Agregar los botones al segundo panel
        panelBotones.add(btnCrear);

        // Configurar el layout del formulario principal como BorderLayout
        setLayout(new BorderLayout());

        // Agregar el panel de componentes al centro
        add(panel, BorderLayout.CENTER);

        // Agregar el panel de botones en la parte inferior
        add(panelBotones, BorderLayout.SOUTH);

        // Cierra la ventana cuando se hace clic en el botón de cierre
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    // Agregar un ActionListener al botón "Crear" para crear la base de datos
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el nombre de la base de datos desde el campo de texto
                String nombreNuevaBD = txtnomBD.getText();
                // Obtener el valor seleccionado en el JComboBox (CHARSET)
                String charsetSeleccionado = comboBox.getSelectedItem().toString();

                // Validar que el nombre no esté vacío
                if (nombreNuevaBD.isEmpty()) {
                    JOptionPane.showMessageDialog(formulario3.this, "El nombre de la base de datos no puede estar vacío.");
                    return;
                }

                // Crea una nueva conexión a MySQL utilizando los datos recibidos
                String url = "jdbc:mysql://" + host + ":" + puerto;
                try {
                    Connection conexion = DriverManager.getConnection(url, usuario, contrasena);

                    // Crea la nueva base de datos
                    Statement statement = conexion.createStatement();
                    String query = "CREATE DATABASE " + nombreNuevaBD;
                    statement.executeUpdate(query);
                    JOptionPane.showMessageDialog(formulario3.this, "Base de datos creada con éxito.");

                    // Limpia las cajas de texto
                    txtnomBD.setText("");
                    comboBox.setSelectedIndex(0);
                    
                    // Cierra la conexión
                    conexion.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(formulario3.this, "Error al crear la base de datos: " + ex.getMessage());
                }
            }
        });

    }
    }
