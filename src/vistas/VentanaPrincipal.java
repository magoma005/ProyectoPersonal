package vistas;

import modelo.Mascota;
import modelo.Persona;
import DAO.PersonaDAO;
import java.util.List;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {

    private JDesktopPane escritorio;
    private ArrayList<Mascota> listaPacientes = new ArrayList<>();
    private final PersonaDAO personaDAO = new PersonaDAO();
    private List<Persona> listaPersonas;

    public VentanaPrincipal() {
        setTitle("PetControl - Sistema de Gestión Clínica Veterinaria");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        listaPersonas = personaDAO.cargarPersonas();

        Color colorFondo = new Color(245, 251, 255);
        getContentPane().setBackground(colorFondo);
        // === Árbol lateral izquierdo ===
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Servicios");
        raiz.add(new DefaultMutableTreeNode("Medicina general"));
        raiz.add(new DefaultMutableTreeNode("Cirugía"));
        raiz.add(new DefaultMutableTreeNode("Vacunación"));
        raiz.add(new DefaultMutableTreeNode("Peluquería"));
        raiz.add(new DefaultMutableTreeNode("Urgencias"));
        JTree arbolServicios = new JTree(raiz);
        arbolServicios.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scrollArbol = new JScrollPane(arbolServicios);
        scrollArbol.setPreferredSize(new Dimension(180, 0));
        scrollArbol.setBorder(BorderFactory.createTitledBorder("Áreas disponibles"));
        add(scrollArbol, BorderLayout.WEST);

        // === Panel de contenido central ===
        JPanel panelContenido = new JPanel(new BorderLayout());
        panelContenido.setBackground(new Color(255, 248, 240));
        panelContenido.setOpaque(true);
        add(panelContenido, BorderLayout.CENTER);
        escritorio = new JDesktopPane();
        escritorio.setOpaque(false);
        panelContenido.add(escritorio, BorderLayout.CENTER);

        // === Panel de bienvenida personalizado ===
        JPanel panelBienvenida = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 249, 243));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        panelBienvenida.setOpaque(true);
        panelBienvenida.setBackground(new Color(255, 248, 240));
        panelBienvenida.setLayout(new BoxLayout(panelBienvenida, BoxLayout.Y_AXIS));
        panelBienvenida.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        escritorio.add(panelBienvenida);

        // === Contenido visual del panel ===
        ImageIcon iconoLogo = new ImageIcon(getClass().getResource("/imagenes/logo2.jpg"));
        Image imagenLogo = iconoLogo.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(imagenLogo));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelBienvenida.add(Box.createVerticalStrut(20));
        panelBienvenida.add(lblLogo);

        JLabel lblBienvenida = new JLabel("Bienvenido a PetControl");
        lblBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblBienvenida.setForeground(new Color(33, 97, 140));
        lblBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelBienvenida.add(Box.createVerticalStrut(20));
        panelBienvenida.add(lblBienvenida);

        JLabel lblMensaje = new JLabel("Selecciona una opción en el menú superior para comenzar.");
        lblMensaje.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelBienvenida.add(Box.createVerticalStrut(10));
        panelBienvenida.add(lblMensaje);
        panelBienvenida.add(Box.createVerticalGlue());

        // === Pie de página ===
        JLabel piePagina = new JLabel("© 2025 PetControl. Todos los derechos reservados.", SwingConstants.CENTER);
        piePagina.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        piePagina.setForeground(new Color(100, 100, 100));
        piePagina.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(piePagina, BorderLayout.SOUTH);

        // === Barra de menú ===
        JMenuBar barraMenu = new JMenuBar();
        barraMenu.setBackground(Color.WHITE);
        barraMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        barraMenu.add(Box.createHorizontalGlue());

        JMenu menuArchivo = new JMenu("Archivo");
        menuArchivo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        itemSalir.addActionListener(e -> {
            personaDAO.guardarPersonas(listaPersonas);
            JOptionPane.showMessageDialog(this, "👋 Gracias por usar PetControl. ¡Hasta pronto!");
            System.exit(0);
        });
        menuArchivo.add(itemSalir);

        JMenu menuPacientes = new JMenu("Pacientes");
        menuPacientes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JMenuItem itemPacientes = new JMenuItem("Ver Pacientes");
        itemPacientes.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        itemPacientes.addActionListener(e -> new FormMascota().setVisible(true));
        menuPacientes.add(itemPacientes);

        JMenu menuConsultas = new JMenu("Consultas");
        menuConsultas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JMenuItem itemConsultas = new JMenuItem("Ver Consultas");
        itemConsultas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        itemConsultas.addActionListener(e -> new FormConsulta().setVisible(true));
        menuConsultas.add(itemConsultas);

        JMenu menuRegistro = new JMenu("Registro");
        menuRegistro.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JMenuItem itemPersonas = new JMenuItem("Gestionar personas");
        itemPersonas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        itemPersonas.addActionListener(e -> new FormPersona().setVisible(true));
        menuRegistro.add(itemPersonas);

        barraMenu.add(menuArchivo);
        barraMenu.add(menuPacientes);
        barraMenu.add(menuConsultas);
        barraMenu.add(menuRegistro);
        barraMenu.add(Box.createHorizontalGlue());
        setJMenuBar(barraMenu);

        setVisible(true); // Mostrar ventana primero

        // Forzar ubicación del panel bienvenida
        int ancho = 460;
        int alto = 400;
        int x = (escritorio.getWidth() - ancho) / 2;
        int y = (escritorio.getHeight() - alto) / 2;
        panelBienvenida.setBounds(x, y, ancho, alto);
        panelBienvenida.revalidate();
        panelBienvenida.repaint();

        // Reubicar si se cambia el tamaño de la ventana
        escritorio.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int ancho = 460;
                int alto = 400;
                int x = (escritorio.getWidth() - ancho) / 2;
                int y = (escritorio.getHeight() - alto) / 2;
                panelBienvenida.setBounds(x, y, ancho, alto);
            }
        });
    }

    public static void mostrarSplashScreen() {
        JWindow splash = new JWindow();
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        JLabel lblTexto = new JLabel("Cargando PetControl...", SwingConstants.CENTER);
        lblTexto.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(lblTexto, BorderLayout.NORTH);
        ImageIcon icono = new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/logo.jpg"));
        JLabel lblImagen = new JLabel(icono, SwingConstants.CENTER);
        panel.add(lblImagen, BorderLayout.CENTER);
        splash.getContentPane().add(panel);
        splash.setSize(400, 400);
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        splash.setVisible(false);
        splash.dispose();
    }
    
    public static void main(String[] args) {
        // Mostrar splash antes de iniciar app
        mostrarSplashScreen();
        // Lanzar ventana principal
        SwingUtilities.invokeLater(() -> new VentanaPrincipal());
    }
}