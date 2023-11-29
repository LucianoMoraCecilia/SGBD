package formulario;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class formulario2 extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JTextField txtHost;
    private JTextField txtPuerto;
    private JList<String> listaBasesDeDatos; // Cambio el JComboBox a JList
    private DefaultListModel<String> listModel;
    private Connection conexion;



    public formulario2() {
    	
    	 // Cargar la imagen del ícono
        ImageIcon iconoVentana = new ImageIcon("C:\\Users\\cecil\\OneDrive\\Documentos\\7 SEMESTRE\\OSCAR\\Formulario\\fondo.png");

        // Obtener la imagen del ImageIcon
        Image imagenIcono = iconoVentana.getImage();

        // Establecer la imagen como ícono de la ventana
        setIconImage(imagenIcono);
        
        // Configurar el título y el tamaño de la ventana
        setTitle("CONECTAR A BASE DE DATOS");
        setSize(800, 600);
        setLocationRelativeTo(null);
    
        
     // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Crear un menú "Archivo"
        JMenu menuArchivo = new JMenu("MENÚ");
        
        
        ImageIcon iconoCrearBD = new ImageIcon("crear.png"); 
        
     // Redimensionar la imagen al tamaño deseado ( 25*25 píxeles)
        Image imagenRedimensionada = iconoCrearBD.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

        // Crear una nueva instancia de ImageIcon con la imagen redimensionada
        ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
        
        ImageIcon iconoCrearTablas = new ImageIcon("addtabla.png");
        
        // Redimensionar la imagen al tamaño deseado ( 25*25 píxeles)
           Image imagentabla = iconoCrearTablas.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

           // Crear una nueva instancia de ImageIcon con la imagen redimensionada
           ImageIcon iconoAddtabla = new ImageIcon(imagentabla);
           
           ImageIcon iconoAddatos = new ImageIcon("addatos.png");
           
           // Redimensionar la imagen al tamaño deseado ( 25*25 píxeles)
              Image imagenDatos = iconoAddatos.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

              // Crear una nueva instancia de ImageIcon con la imagen redimensionada
              ImageIcon iconoDatos = new ImageIcon(imagenDatos);

        JMenuItem itemCrearBD = new JMenuItem("Crear base de datos", iconoRedimensionado);
        JMenuItem itemCrearTabla = new JMenuItem("Crear Tablas", iconoAddtabla);
        JMenuItem itemAddDatos = new JMenuItem("Insertar Datos", iconoDatos);
             
        
        menuArchivo.add(itemCrearBD);        
        menuArchivo.add(itemCrearTabla);
        menuArchivo.add(itemAddDatos);
        
        // Agregar el menú "Archivo" a la barra de menú
        menuBar.add(menuArchivo);

        // Configurar el frame para usar la barra de menú
        setJMenuBar(menuBar);

    
        itemCrearBD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String usuario = txtUsuario.getText();
                String contrasena = new String(txtContrasena.getPassword());
                String host = txtHost.getText();
                String puerto = txtPuerto.getText();
                String nombreBase = listaBasesDeDatos.getSelectedValue();
             //   llenarListaBasesDeDatos(conexion);
                
                if (usuario.isEmpty() || contrasena.isEmpty() || host.isEmpty() || puerto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos antes de crear la base de datos.");
                    return;
                }

                if (conexion == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, establezca una conexión antes de intentar crear la base de datos.");
                    return;
                }
                
                // Crear una instancia del formulario3 y pasar la conexión
                formulario3 nuevoFormulario = new formulario3(usuario, contrasena, host, puerto, nombreBase);
                
                // Hacer visible el nuevo formulario
                nuevoFormulario.setVisible(true);            }
        });

        itemCrearTabla.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 String usuario = txtUsuario.getText();
                 String contrasena = new String(txtContrasena.getPassword());
                 String host = txtHost.getText();
                 String puerto = txtPuerto.getText();
                 String nombreBase = listaBasesDeDatos.getSelectedValue();

                 if (nombreBase == null) {
                     JOptionPane.showMessageDialog(null, "Por favor, seleccione una base de datos primero.");
                     return;
                 }

                 // Crear una instancia del formulario4 y pasar la conexión y el nombre de la base
                 formulario4 nuevoFormulario4 = new formulario4(usuario, contrasena, host, puerto, nombreBase);

                 // Hacer visible el nuevo formulario4
                 nuevoFormulario4.setVisible(true);
            }
        });
        
        itemAddDatos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 String usuario = txtUsuario.getText();
                 String contrasena = new String(txtContrasena.getPassword());
                 String host = txtHost.getText();
                 String puerto = txtPuerto.getText();
                 String nombreBase = listaBasesDeDatos.getSelectedValue();

                 if (nombreBase == null) {
                     JOptionPane.showMessageDialog(null, "Por favor, seleccione una base de datos primero.");
                     return;
                 }

                 // Crear una instancia del formulario4 y pasar la conexión y el nombre de la base
                 formulario5 nuevoFormulario5 = new formulario5(usuario, contrasena, host, puerto, nombreBase);

                 // Hacer visible el nuevo formulario4
                 nuevoFormulario5.setVisible(true); 
                 nuevoFormulario5.setLocationRelativeTo(null);
            }
        });
        
     // Crear un panel para organizar los componentes
        JPanel panel = new JPanel(new GridLayout(0,1)); 
        panel.setPreferredSize(new Dimension(400, 600));
        Color colorPanel = new Color(140, 207, 234);
        panel.setBackground(colorPanel);
        
        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.setPreferredSize(new Dimension(300, 600));
       Color colorPanel2 = new Color(255, 255, 255);
      panel2.setBackground(colorPanel2);
        
     // Etiquetas
      
   // Crear una nueva fuente con el nombre, estilo y tamaño deseados
      Font nuevaFuente = new Font("Arial", Font.BOLD, 16);

      Font nuevaFuente2 = new Font("Arial", Font.BOLD, 14);
      
      
      ImageIcon usuarioIcon = new ImageIcon("user.png");
      ImageIcon contrasenaIcon = new ImageIcon("contra.png"); 
      ImageIcon hostIcon = new ImageIcon("host.png"); 
      ImageIcon puertoIcon = new ImageIcon("pprt.png"); 
      
      JLabel lblUsuario = new JLabel("Usuario:");
      lblUsuario.setIcon(usuarioIcon);

      JLabel lblContrasena = new JLabel("Contraseña:");
      lblContrasena.setIcon(contrasenaIcon);

      JLabel lblHost = new JLabel("Host:");
      lblHost.setIcon(hostIcon);

      JLabel lblPuerto = new JLabel("Puerto:");
      lblPuerto.setIcon(puertoIcon);
       
   // Aplicar la nueva fuente a las etiquetas
      lblUsuario.setFont(nuevaFuente);
      lblContrasena.setFont(nuevaFuente);
      lblHost.setFont(nuevaFuente);
      lblPuerto.setFont(nuevaFuente);

        
        // Cajas de texto
        txtUsuario = new JTextField(10);
        txtContrasena = new JPasswordField(10);
        txtHost = new JTextField(10);
        txtPuerto = new JTextField(10);       
        

     // Aplicar la nueva fuente y centrar el texto horizontalmente
        txtUsuario.setFont(nuevaFuente2);
        txtUsuario.setHorizontalAlignment(JTextField.CENTER);

        txtContrasena.setFont(nuevaFuente2);
        txtContrasena.setHorizontalAlignment(JTextField.CENTER);

        txtHost.setFont(nuevaFuente2);
        txtHost.setHorizontalAlignment(JTextField.CENTER);

        txtPuerto.setFont(nuevaFuente2);
        txtPuerto.setHorizontalAlignment(JTextField.CENTER);
        
     // Botones
        JButton btnTest = new JButton("Test");
        JButton btnConectar = new JButton("Conectar");
        JButton btnUsarBD = new JButton("Usar BD");
        JButton btnActu = new JButton("");

        // Aplicar estilos a los botones
        setButtonStyles(btnTest);
        setButtonStyles(btnConectar);
        setButtonStyles(btnUsarBD);
        setButtonStyles(btnActu);

        // Iconos para los botones
        ImageIcon testIcon = new ImageIcon("test.png");
        ImageIcon conectarIcon = new ImageIcon("conectar.png");
        ImageIcon usarBDIcon = new ImageIcon("usarbd.png");
        ImageIcon actuIcon = new ImageIcon("actu.png");

        // Asignar iconos a los botones
        btnTest.setIcon(testIcon);
        btnConectar.setIcon(conectarIcon);
        btnUsarBD.setIcon(usarBDIcon);
        btnActu.setIcon(actuIcon);

        Color colorFondoLista = new Color(255, 255, 255);
     // Crear el modelo de lista para la JList
        listModel = new DefaultListModel<>();
        listaBasesDeDatos = new JList<>(listModel);
        listaBasesDeDatos.setBackground(colorFondoLista);
        
        // Agregar etiquetas y componentes al panel
        panel.add(lblUsuario);
        panel.add(txtUsuario);
        panel.add(lblContrasena);
        panel.add(txtContrasena);
        panel.add(lblHost);
        panel.add(txtHost);
        panel.add(lblPuerto);
        panel.add(txtPuerto);
       
       // panel.add(cboBasesDeDatos);
        panel2.add(new JScrollPane(listaBasesDeDatos)); // Usar JScrollPane para la JList


        // Crear un segundo panel para los botones
        JPanel panelBotones = new JPanel();
        Color colorPanel3 = new Color(140, 207, 234);
        panelBotones.setBackground(colorPanel3);
        
     // Agregar un ActionListener al botón "Test" para probar la conexión
        btnTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testConnection();
            }
        });

        // Agregar un ActionListener al botón "Conectar" para la conexión
        btnConectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conectar();
             
            }
        });

        // Agregar un ActionListener al botón "UsarBD" para establecer la conexión a la BD
        btnUsarBD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usarBD();
            }
        });
        
        btnActu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actuBDs();
            }
        });
   

        // Agregar los botones al segundo panel
        panelBotones.add(btnTest);
        panelBotones.add(btnConectar);
        panelBotones.add(btnUsarBD);
        panelBotones.add(btnActu);

        // Configurar el layout del formulario principal como BorderLayout
        setLayout(new BorderLayout());

        // Agregar el panel de componentes al centro
       add(panel, BorderLayout.CENTER);

        // Agregar el panel de botones en la parte inferior
        add(panelBotones, BorderLayout.SOUTH);
        
       // add(panel, BorderLayout.EAST);
        add(panel2, BorderLayout.WEST);

        // Cierra la ventana cuando se hace clic en el botón de cierre
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
 
    }
    private void setButtonStyles(JButton button) {
        // Establecer el color de fondo
        button.setBackground(new Color(140, 207, 234 ));

        // Establecer el color de texto
        button.setForeground(Color.BLACK);

        // Establecer el borde
        button.setBorder(new LineBorder(new Color(99, 196, 236), 1));

        // Establecer el margen interno
        button.setMargin(new Insets(10, 15, 10, 15));

        // Establecer la fuente
        button.setFont(new Font("Arial", Font.BOLD, 16));

    }
    
    private void testConnection(){
    	String usuario = txtUsuario.getText();
        String contrasena = new String(txtContrasena.getPassword());
        String host = txtHost.getText();
        String puerto = txtPuerto.getText();
        String url = "jdbc:mysql://" + host + ":" + puerto;
        
        try {
            // Intentar establecer una conexión a la base de datos MySQL
            Connection connection = DriverManager.getConnection(url, usuario, contrasena);
            
            // Si la conexión se realizó con éxito, muestra un mensaje de prueba exitosa
            JOptionPane.showMessageDialog(null, "Prueba exitosa: Conexión establecida correctamente.");
            
            // No olvides cerrar la conexión cuando hayas terminado de usarla.
            connection.close();
        } catch (SQLException ex) {
            // Manejar cualquier error de conexión
            JOptionPane.showMessageDialog(null, "Error al realizar la prueba de conexión: " + ex.getMessage(), "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    // Método para probar la conexión
    private void conectar() {
        String usuario = txtUsuario.getText();
        String contrasena = new String(txtContrasena.getPassword());
        String host = txtHost.getText();
        String puerto = txtPuerto.getText();
        String url = "jdbc:mysql://" + host + ":" + puerto;

// En el método testConnection
        try {
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            JOptionPane.showMessageDialog(this, "Conexión exitosa");
            // Llenar la lista de bases de datos después de una conexión exitosa
            llenarListaBasesDeDatos(conexion);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    // Método para establecer la conexión
    private void usarBD() {
        String usuario = txtUsuario.getText();
        String contrasena = new String(txtContrasena.getPassword());
        String host = txtHost.getText();
        String puerto = txtPuerto.getText();
       // String nombreBase = cboBasesDeDatos.getSelectedItem().toString();
        String nombreBase = listaBasesDeDatos.getSelectedValue();

        if (nombreBase == null) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una base de datos primero.");
            return;
        }
        
        String url = "jdbc:mysql://" + host + ":" + puerto + "/" + nombreBase;

        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);
            JOptionPane.showMessageDialog(this, "Conexión establecida");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos: " + e.getMessage());
        }
    }
    
    private void actuBDs() {
    	String usuario = txtUsuario.getText();
        String contrasena = new String(txtContrasena.getPassword());
        String host = txtHost.getText();
        String puerto = txtPuerto.getText();
        String url = "jdbc:mysql://" + host + ":" + puerto;

        try {
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            JOptionPane.showMessageDialog(this, "Actualizacion exitosa");
            // Llenar la lista de bases de datos después de una conexión exitosa
            llenarListaBasesDeDatos(conexion);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar las base de datos: " + e.getMessage());
        } 
    }

    // Método para llenar la lista de bases de datos disponibles
    private void llenarListaBasesDeDatos(Connection conexion) {
       // cboBasesDeDatos.removeAllItems(); // Limpia la lista previa
    	listModel.clear();
       // List<String> basesDeDatos = new ArrayList<>();

        try {
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SHOW DATABASES");

            while (resultSet.next()) {
                String nombreBase = resultSet.getString(1);
              //  basesDeDatos.add(nombreBase);
                listModel.addElement(nombreBase);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener la lista de bases de datos: " + e.getMessage());
        }
       
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            formulario2 ventana = new formulario2();
            ventana.setVisible(true);           
            
        });
    }
}
