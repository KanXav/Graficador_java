import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JuegoAdivinanzaPaneles extends JFrame {
    // Paneles
    private JPanel panelMenu = new JPanel();          // Panel_de_inicio
    private JPanel panelJuego = new JPanel();         // Panel_del_juego
    private JPanel panelCreditos = new JPanel();      // Panel_de_créditos

    // Componentes_del_juego
    private JTextField txtNumero = new JTextField();
    private JLabel lblMensaje = new JLabel("¡ADIVINA EL NÚMERO SECRETO!"); // Mensaje_de_bienvenida
    private JLabel lblIntentos = new JLabel("Intentos: 0");                // Etiqueta_para_mostrar_los_intentos
    private JButton btnAdivinar = new JButton("ADIVINAR");                 // Botón_para_adivinar_el_número
    private int numeroSecreto;
    private int intentos = 0;

    // Barra_de_menú
    private JMenuBar barraMenu = new JMenuBar();
    private JMenuItem itemInicio = new JMenuItem("Inicio");          // Pantalla_de_inicio
    private JMenuItem itemCreditos = new JMenuItem("Créditos");      // Pantalla_de_créditos
    private JMenuItem itemReiniciar = new JMenuItem("Reiniciar");    // Reiniciar_el_juego

    private void initComponents() {     // Inicializar_los_componentes_del_juego

        // Configuración_de_la_ventana
        setTitle("Juego de Adivinanza");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null); // Layout_nulo_para_posicionar_manualmente
        setVisible(true);

        // Configurar_barra_de_menú
        barraMenu.add(itemInicio);
        barraMenu.add(itemReiniciar);
        barraMenu.add(itemCreditos);

        setJMenuBar(barraMenu);

        // Panel_de_Menú_de_Inicio
        configurarPanelMenu();
        // Panel_de_Juego
        configurarPanelJuego();
        // Panel_de_Créditos
        configurarPanelCreditos();

        // Listeners_de_los_botones_de_la_barra_de_menú
        itemInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanel("menu");
            }
        });
    
        itemCreditos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanel("creditos");
            }
        });
    
        itemReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJuego();
            }
        });

        // Mostrar_solo_el_panel_inicial
        panelMenu.setVisible(true);
        panelJuego.setVisible(false);
        panelCreditos.setVisible(false);
       
    }

    private void configurarPanelMenu() {
        panelMenu.setLayout(null);
        panelMenu.setBounds(0, 0, 500, 400);
        panelMenu.setBackground(new Color(144, 83, 173));

        // Título
        JLabel lblTitulo = new JLabel("¡BIENVENIDO!", SwingConstants.CENTER);
        lblTitulo.setBounds(50, 50, 400, 30);
        lblTitulo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        panelMenu.add(lblTitulo);

        // Imagen
        JLabel lblImagen = new JLabel(new ImageIcon("Inicio.png"));
        lblImagen.setBounds(100, 100, 250, 162);
        panelMenu.add(lblImagen);

        // Botón_para_iniciar_el_juego
        JButton btnInicio = new JButton("Comenzar Juego");
        btnInicio.setBounds(150, 280, 200, 30);
        btnInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelMenu.setVisible(false);
                panelJuego.setVisible(true);
                generarNumeroSecreto();
            }
        });
        panelMenu.add(btnInicio);

        add(panelMenu);
    }

    private void configurarPanelJuego() {
        panelJuego.setLayout(null);
        panelJuego.setBounds(0, 0, 500, 400);

        // Instruccion_del_juego
        lblMensaje.setBounds(50, 50, 400, 30);
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        panelJuego.add(lblMensaje);

        // Campo_para_ingresar_el_número_probable
        txtNumero.setBounds(200, 100, 100, 25);
        txtNumero.setHorizontalAlignment(JTextField.CENTER);
        panelJuego.add(txtNumero);

        // Botón_para_adivinar_el_número
        btnAdivinar.setBounds(200, 150, 100, 30);
        btnAdivinar.setForeground(Color.WHITE);
        btnAdivinar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarIntento();
                btnAdivinar.setBackground(new Color( (int) (Math.random()*256),(int) (Math.random()*256),(int) (Math.random()*256))); 
            }
        });
        panelJuego.add(btnAdivinar);
        
        // Etiqueta_para_mostrar_los_intentos
        lblIntentos.setBounds(50, 200, 400, 30);
        lblIntentos.setHorizontalAlignment(SwingConstants.CENTER);
        panelJuego.add(lblIntentos);

        add(panelJuego);
    }

    private void configurarPanelCreditos() {
        panelCreditos.setLayout(null);
        panelCreditos.setBounds(0, 0, 500, 400);
        panelCreditos.setBackground(new Color(57,0,1));

        // Etiqueta_de_créditos
        JLabel lblTexto = new JLabel("© Todos los derechos reservados a Kan", SwingConstants.CENTER);
        lblTexto.setBounds(50, 150, 400, 30);
        lblTexto.setForeground(Color.WHITE);
        panelCreditos.add(lblTexto);
        add(panelCreditos);
    }

    // Método_para_mostrar_el_panel_seleccionado_en_la_barra_de_menú
    private void mostrarPanel(String nombrePanel) {
        panelMenu.setVisible(nombrePanel.equals("menu"));
        panelJuego.setVisible(nombrePanel.equals("juego"));
        panelCreditos.setVisible(nombrePanel.equals("creditos"));
    }

    // Método_para_generar_un_número_secreto_aleatorio_e_inicializar_intentos
    private void generarNumeroSecreto() {
        numeroSecreto = (int) (Math.random() * 100) + 1;
        intentos = 0;
        lblIntentos.setText("Intentos: 0");
        txtNumero.setText("");
        btnAdivinar.setEnabled(true);
        txtNumero.setEnabled(true);
        lblMensaje.setText("¡ADIVINA EL NÚMERO SECRETO!");
    }

    // Método_para_reiniciar_el_panel_del_juego
    private void reiniciarJuego() {
        generarNumeroSecreto();
        txtNumero.setText("");
        lblIntentos.setText("Intentos: 0");
        panelJuego.setBackground(Color.WHITE); // Cambiar_color_de_fondo_a_inicial
       // mostrarPanel("juego");
    }

    // Método_para_verificar_el_intento_del_usuario_(Es_lo_que_pasa_al_hacer_click_en_el_boton_adivinar)
    private void verificarIntento() {
        try {
            int numero = Integer.parseInt(txtNumero.getText());
            intentos++;
            lblIntentos.setText("Intentos: " + intentos);

            if (numero == numeroSecreto) {
                lblMensaje.setText("¡CORRECTO! Número secreto: " + numeroSecreto);  // Mensaje_de_acierto
                btnAdivinar.setEnabled(false);           // Deshabilitar_el_boton_de_adivinar
                txtNumero.setEnabled(false);        // Deshabilitar_el_campo_de_texto
                panelJuego.setBackground(Color.GREEN); // Cambiar_color_de_fondo_a_verde
            } else if (numero < numeroSecreto) {
                lblMensaje.setText("Demasiado BAJO. Sigue intentando...");      // Mensaje_de_numero_bajo
            } else {
                lblMensaje.setText("Demasiado ALTO. Sigue intentando...");  // Mensaje_de_numero_alto
            }
        } catch (NumberFormatException ex) {
            lblMensaje.setText("¡ERROR! Solo números enteros.");        // Mensaje_de_error
        }
        txtNumero.setText("");
    }

    // Método_principal
    public JuegoAdivinanzaPaneles() {
        initComponents();
    }

    // Método_para_ejecutar_la_aplicación
    public static void main(String[] args) {
        new JuegoAdivinanzaPaneles();
    }
}