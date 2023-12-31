package formulario;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class formulario4 extends JFrame {

    private Connection conexion;
    private String nombreBase;
    private List<JTextField> nombreColumnasFields;
    private List<JComboBox<String>> tipoDatoComboBoxes;
    private List<JTextField> longitudColumnasFields;
    private int numColumnas;

    public formulario4(String usuario, String contrasena, String host, String puerto, String nombreBase) {
    	
   	 // Cargar la imagen del ícono
       ImageIcon iconoVentana = new ImageIcon("C:\\Users\\cecil\\OneDrive\\Documentos\\7 SEMESTRE\\OSCAR\\Formulario\\fondo.png");

       // Obtener la imagen del ImageIcon
       Image imagenIcono = iconoVentana.getImage();

       // Establecer la imagen como ícono de la ventana
       setIconImage(imagenIcono);
       
        this.nombreBase = nombreBase;
        this.nombreColumnasFields = new ArrayList<>();
        this.tipoDatoComboBoxes = new ArrayList<>();
        this.longitudColumnasFields = new ArrayList<>();
        this.numColumnas = 1;

        setTitle("Crear Tablas en " + nombreBase);
        setSize(800, 600); 
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        Color colorPanel = new Color(140, 207, 234);
        panel.setBackground(colorPanel);

        JPanel panelNombre = new JPanel();
        panelNombre.setBackground(colorPanel);

        Font nuevaFuente = new Font("Arial", Font.BOLD, 16);
        Font nuevaFuente2 = new Font("Arial", Font.BOLD, 12);
        
        // Cargar los iconos desde archivos de imagen (asegúrate de que los archivos de imagen existan)
        ImageIcon iconoCreatT = new ImageIcon("crearT.png");
        ImageIcon iconoAgregarF = new ImageIcon("agregarF.png");
        ImageIcon iconoEliminarF = new ImageIcon("eliminarF.png");
        ImageIcon iconoGenerarC = new ImageIcon("generarC.png");

        
        JLabel lblNombreTabla = new JLabel("Nombre de la tabla:");
        JTextField txtNombreTabla = new JTextField(20);
        panelNombre.add(lblNombreTabla);
        panelNombre.add(txtNombreTabla);

        JPanel panelColumnas = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelColumnas.setBackground(colorPanel);
        JLabel lblNumColumnas = new JLabel("Número de columnas:");
        JSpinner numColumnasSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        JButton btnGenerarCampos = new JButton("Generar Campos",iconoGenerarC );
        panelColumnas.add(lblNumColumnas);
        panelColumnas.add(numColumnasSpinner);
        panelColumnas.add(btnGenerarCampos);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(colorPanel);
        JButton btnCrearTabla = new JButton("Crear Tabla",iconoCreatT );
        JButton btnAgregarCampo = new JButton("Agregar Campo", iconoAgregarF);
        JButton btnEliminarCampo = new JButton("Eliminar Campo", iconoEliminarF);
        
        setButtonStyles(btnCrearTabla);
        setButtonStyles(btnAgregarCampo);
        setButtonStyles(btnEliminarCampo);
        
        lblNombreTabla.setFont(nuevaFuente);
        lblNumColumnas.setFont(nuevaFuente);
        numColumnasSpinner.setFont(nuevaFuente2);
        
        txtNombreTabla.setFont(nuevaFuente2);
        
        
        panelBotones.add(btnCrearTabla);
        panelBotones.add(btnAgregarCampo);
        panelBotones.add(btnEliminarCampo);

        panel.add(panelNombre, BorderLayout.NORTH);
        panel.add(panelColumnas, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        btnGenerarCampos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numColumnas = (int) numColumnasSpinner.getValue();
                crearCamposDeTextoYTiposDeDato(numColumnas, panelColumnas);

                // Maximizar la ventana y ajustar la interfaz
               // setExtendedState(JFrame.MAXIMIZED_BOTH);
                revalidate();
                repaint();
                
            }
        });

        btnCrearTabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreTabla = txtNombreTabla.getText();

                if (!nombreTabla.isEmpty()) {
                    crearTabla(nombreTabla, numColumnas);
                } else {
                    JOptionPane.showMessageDialog(formulario4.this, "Por favor, ingrese un nombre para la tabla.");
                }
            }
        });

        btnAgregarCampo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numColumnas < 10) { // Establece un límite razonable
                    JTextField campoTexto = new JTextField(20);
                    JLabel label = new JLabel("Nombre de columna " + (numColumnas + 1) + ":");
                    JComboBox<String> tipoDatoComboBox = new JComboBox<>(new String[]{"INT", "VARCHAR", "DATE", "DOUBLE", "BOOLEAN"});
                    JLabel tipoDatoLabel = new JLabel("Tipo de dato:");
                    JTextField longitudColumna = new JTextField(5);
                    JLabel longitudLabel = new JLabel("Longitud:");

                
                    
                    JPanel campoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    Color colorPanel = new Color(140, 207, 234);
                    campoPanel.setBackground(colorPanel);
                    
                    label.setFont(nuevaFuente);
                    tipoDatoComboBox.setFont(nuevaFuente2);
                    tipoDatoLabel.setFont(nuevaFuente2);
                    longitudLabel.setFont(nuevaFuente2);
                    
                    campoTexto.setFont(nuevaFuente2);
                    longitudColumna.setFont(nuevaFuente2);
                    
                    

                    campoPanel.add(label);
                    campoPanel.add(campoTexto);
                    campoPanel.add(tipoDatoLabel);
                    campoPanel.add(tipoDatoComboBox);
                    campoPanel.add(longitudLabel);
                    campoPanel.add(longitudColumna);

                    panelColumnas.add(campoPanel);

                    nombreColumnasFields.add(campoTexto);
                    tipoDatoComboBoxes.add(tipoDatoComboBox);
                    longitudColumnasFields.add(longitudColumna);

                    numColumnas++;

                    panel.revalidate();
                    panel.repaint();
                }
            }
        });

        btnEliminarCampo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numColumnas > 1) { // Asegúrate de que al menos haya un campo
                    int lastIndex = nombreColumnasFields.size() - 1;
                    panelColumnas.remove(lastIndex);
                    nombreColumnasFields.remove(lastIndex);
                    tipoDatoComboBoxes.remove(lastIndex);
                    longitudColumnasFields.remove(lastIndex);

                    numColumnas--;

                    panel.revalidate();
                    panel.repaint();
                }
            }
        });

        add(panel);

        String url = "jdbc:mysql://" + host + ":" + puerto + "/" + nombreBase;
        try {
            conexion = DriverManager.getConnection(url, usuario, contrasena);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos: " + e.getMessage());
        }

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private void setButtonStyles(JButton button) {
        // Establecer el color de fondo
        button.setBackground(new Color(140, 207, 234));

        // Establecer el color de texto
        button.setForeground(Color.BLACK);

        // Establecer el borde
        button.setBorder(new LineBorder(new Color(99, 196, 236), 2));

        // Establecer el margen interno
        button.setMargin(new Insets(10, 15, 10, 15));

        // Establecer la fuente
        button.setFont(new Font("Arial", Font.BOLD, 16));

    }

    private void setComboBoxStyles(JComboBox<String> comboBox) {
        // Establecer el color de fondo
        comboBox.setBackground(new Color(112, 236, 197 )); // Cambia esto al color que desees

        // Establecer el color de texto
        comboBox.setForeground(Color.BLACK);

        // Establecer el borde
        comboBox.setBorder(new LineBorder(new Color(99, 196, 236), 2));

        // Establecer la fuente
        comboBox.setFont(new Font("Arial", Font.BOLD, 12)); // Cambia esto según tus preferencias
    }

    private void setTextFieldStyles(JTextField textField) {
        // Establecer el color de fondo
        textField.setBackground(new Color(255, 255, 255)); // Cambia esto al color que desees

        // Establecer el color de texto
        textField.setForeground(Color.BLACK);

        // Establecer el borde
        textField.setBorder(new LineBorder(new Color(99, 196, 236), 2));

        // Establecer la fuente
        textField.setFont(new Font("Arial", Font.BOLD, 12)); // Cambia esto según tus preferencias
    }

    private void crearCamposDeTextoYTiposDeDato(int numColumnas, JPanel panel) {
        nombreColumnasFields.clear();
        tipoDatoComboBoxes.clear();
        longitudColumnasFields.clear();
        panel.removeAll();

        for (int i = 0; i < numColumnas; i++) {
            JTextField campoTexto = new JTextField(20);
            setTextFieldStyles(campoTexto);
            JLabel label = new JLabel("Nombre de columna " + (i + 1) + ":");
            JComboBox<String> tipoDatoComboBox = new JComboBox<>(new String[]{"INT", "VARCHAR", "DATE", "DOUBLE", "BOOLEAN"});
            setComboBoxStyles(tipoDatoComboBox);
            JLabel tipoDatoLabel = new JLabel("Tipo de dato:");
            JTextField longitudColumna = new JTextField(5);
            setTextFieldStyles(longitudColumna);
            JLabel longitudLabel = new JLabel("Longitud:");

            JPanel campoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            Color colorPanel = new Color(140, 207, 234);
            campoPanel.setBackground(colorPanel);

            campoPanel.add(label);
            campoPanel.add(campoTexto);
            campoPanel.add(tipoDatoLabel);
            campoPanel.add(tipoDatoComboBox);
            campoPanel.add(longitudLabel);
            campoPanel.add(longitudColumna);

            panel.add(campoPanel);

            nombreColumnasFields.add(campoTexto);
            tipoDatoComboBoxes.add(tipoDatoComboBox);
            longitudColumnasFields.add(longitudColumna);
        }

        panel.revalidate();
        panel.repaint();
    }

    private void crearTabla(String nombreTabla, int numColumnas) {
        try {
            Statement statement = conexion.createStatement();
            StringBuilder query = new StringBuilder("CREATE TABLE " + nombreTabla + " (id INT AUTO_INCREMENT PRIMARY KEY");

            for (int i = 0; i < numColumnas; i++) {
                JTextField columna = nombreColumnasFields.get(i);
                JComboBox<String> tipoDatoCombo = tipoDatoComboBoxes.get(i);
                JTextField longitudColumna = longitudColumnasFields.get(i);
                String nombreColumna = columna.getText().trim();
                String tipoDato = tipoDatoCombo.getSelectedItem().toString();
                String longitud = longitudColumna.getText().trim();
                
                if (nombreColumna.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre para todas las columnas.");
                    return;
                }
                if (tipoDato.equals("VARCHAR")) {
                    if (longitud.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Por favor, ingrese la longitud para las columnas VARCHAR.");
                        return;
                    }
                    tipoDato += "(" + longitud + ")";
                }else if (tipoDato.equals("INT")) {
                    if (!longitud.isEmpty()) {
                        try {
                            // Verificar si la longitud es un valor numérico válido
                            Integer.parseInt(longitud);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "La longitud para las columnas INT debe ser un valor numérico.");
                            return;
                        }
                    }
                }
                query.append(", ").append(nombreColumna).append(" ").append(tipoDato);
            }

            query.append(")");

            statement.execute(query.toString());
            JOptionPane.showMessageDialog(this, "Tabla " + nombreTabla + " creada con éxito.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al crear la tabla: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            formulario4 form = new formulario4("usuario", "contrasena", "localhost", "puerto", "nombre_base");
            form.setVisible(true);
        });
    }
}
