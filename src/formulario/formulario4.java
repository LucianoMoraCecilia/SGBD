package formulario;

import javax.swing.*;
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
        this.nombreBase = nombreBase;
        this.nombreColumnasFields = new ArrayList<>();
        this.tipoDatoComboBoxes = new ArrayList<>();
        this.longitudColumnasFields = new ArrayList<>();
        this.numColumnas = 1;

        setTitle("Crear Tablas en " + nombreBase);
        setSize(450, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        Color colorPanel = new Color(100, 136, 194);
        panel.setBackground(colorPanel);

        JPanel panelNombre = new JPanel();
        panelNombre.setBackground(colorPanel);

        JLabel lblNombreTabla = new JLabel("Nombre de la tabla:");
        JTextField txtNombreTabla = new JTextField(20);
        panelNombre.add(lblNombreTabla);
        panelNombre.add(txtNombreTabla);

        JPanel panelColumnas = new JPanel(new GridLayout(2, 4));
        panelColumnas.setBackground(colorPanel);
        JLabel lblNumColumnas = new JLabel("Número de columnas:");
        JSpinner numColumnasSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        JButton btnGenerarCampos = new JButton("Generar Campos");
        panelColumnas.add(lblNumColumnas);
        panelColumnas.add(numColumnasSpinner);
        panelColumnas.add(btnGenerarCampos);

        JPanel panelBotones = new JPanel();
        JButton btnCrearTabla = new JButton("Crear Tabla");
        JButton btnAgregarCampo = new JButton("Agregar Campo");
        JButton btnEliminarCampo = new JButton("Eliminar Campo");
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
                setExtendedState(JFrame.MAXIMIZED_BOTH);
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
                    Color colorPanel = new Color(100, 136, 194);
                    campoPanel.setBackground(colorPanel);

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

    private void crearCamposDeTextoYTiposDeDato(int numColumnas, JPanel panel) {
        nombreColumnasFields.clear();
        tipoDatoComboBoxes.clear();
        longitudColumnasFields.clear();
        panel.removeAll();

        for (int i = 0; i < numColumnas; i++) {
            JTextField campoTexto = new JTextField(20);
            JLabel label = new JLabel("Nombre de columna " + (i + 1) + ":");
            JComboBox<String> tipoDatoComboBox = new JComboBox<>(new String[]{"INT", "VARCHAR", "DATE", "DOUBLE", "BOOLEAN"});
            JLabel tipoDatoLabel = new JLabel("Tipo de dato:");
            JTextField longitudColumna = new JTextField(5);
            JLabel longitudLabel = new JLabel("Longitud:");

            JPanel campoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            Color colorPanel = new Color(100, 136, 194);
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
            formulario4 form = new formulario4("usuario", "contrasena", "localhost", "3306", "nombre_base");
            form.setVisible(true);
        });
    }
}
