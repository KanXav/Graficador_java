import javax.swing.*; 
import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 

public class GraficadorEcuaciones extends JFrame { 
    private JPanel panelEntrada = new JPanel();                 // Crea_un_panel_para_los_campos_de_entrada
    private PanelGrafica panelGrafica = new PanelGrafica();     // Crea_un_panel_personalizado_para_la_gráfica
    private JTextField txtA = new JTextField(5);        // Campo_de_texto_para_el_coeficiente_a_(x³)
    private JTextField txtB = new JTextField(5);        // Campo_de_texto_para_el_coeficiente_b_(x²)
    private JTextField txtC = new JTextField(5);        // Campo_de_texto_para_el_coeficiente_c_(x)
    private JTextField txtD = new JTextField(5);        // Campo_de_texto_para_el_coeficiente_d_(constante)
    private double a = 0, b = 0, c = 0, d = 0;                  // Variables_para_almacenar_los_coeficientes_de_la_ecuación

    public GraficadorEcuaciones() {
        initComponents(); 
    }

    private void initComponents() { 
        setTitle("Graficador de Ecuaciones"); 
        setSize(800, 600); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setLayout(new BorderLayout()); 

        // Configurar_barra_de_menú
        JMenuBar barraMenu = new JMenuBar(); 
        JMenu menuAyuda = new JMenu("Opciones");                   // Crea_un_menú_desplegable_llamado_"Opciones"
        JMenuItem itemCreditos = new JMenuItem("Créditos");     // Crea_un_ítem_de_menú_para_créditos
        itemCreditos.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) { 
                mostrarCreditos();                                   // Muestra_la_ventana_de_créditos
            }
        });
        menuAyuda.add(itemCreditos); 
        
        JMenuItem itemInstrucciones = new JMenuItem("Instrucciones"); 
        itemInstrucciones.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) { 
                mostrarInstrucciones();                                // Muestra_la_ventana_de_instrucciones
            }
        });
        menuAyuda.add(itemInstrucciones); 

       
        barraMenu.add(menuAyuda); 
        setJMenuBar(barraMenu); 

        // Panel_de_entrada
        panelEntrada.setBackground(new Color(240, 240, 240));
        panelEntrada.add(new JLabel("y = ")); 
        panelEntrada.add(txtA);                                 // Agrega_el_campo_de_texto_para_el_coeficiente_a
        panelEntrada.add(new JLabel("x³ + ")); 
        panelEntrada.add(txtB);                                 // Agrega_el_campo_de_texto_para_el_coeficiente_b
        panelEntrada.add(new JLabel("x² + ")); 
        panelEntrada.add(txtC);                                 // Agrega_el_campo_de_texto_para_el_coeficiente_c
        panelEntrada.add(new JLabel("x + ")); 
        panelEntrada.add(txtD);                                 // Agrega_el_campo_de_texto_para_el_coeficiente_d

        // Botón_para_graficar
        JButton btnGraficar = new JButton("Graficar");      
        btnGraficar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                generarGrafica();                               // Genera_la_gráfica_con_los_coeficientes_ingresados
            }
        });

        panelEntrada.add(btnGraficar); 

        // Panel_de_gráfica
        add(panelEntrada, BorderLayout.NORTH); 
        add(panelGrafica, BorderLayout.CENTER); 
        setVisible(true);
    }

    private void mostrarCreditos() {  // Método_para_mostrar_los_créditos_de_la_aplicación
        JOptionPane.showMessageDialog(this,            
            "© Todos los derechos reservados a Kan\n" + 
            "Graficador de Ecuaciones Cúbicas\n" , 
            "Créditos", 
            JOptionPane.INFORMATION_MESSAGE); 
            this.setBackground(new Color(57,0,1));
    }

    private void mostrarInstrucciones() {  // Método_para_mostrar_instrucciones_al_usuario
        String mensaje = 
            "¡Bienvenido al graficador de ecuaciones cúbicas!\n\n" +
            "Instrucciones:\n" +
            "1. Ingresa los coeficientes de la ecuación:\n" +
            "   y = Ax³ + Bx² + Cx + D\n" +
            "2. Presiona 'Graficar' para ver el resultado.\n\n" +
            "Ejemplos:\n" +
            "- Cúbica simple: A=1, B=0, C=0, D=0 → y = x³\n" +
            "- Cuadrática: A=0, B=1, C=-2, D=1 → y = x² - 2x + 1\n" +
            "- Lineal: A=0, B=0, C=2, D=3 → y = 2x + 3";
        
        JOptionPane.showMessageDialog(this, 
            mensaje, 
            "Instrucciones", 
            JOptionPane.INFORMATION_MESSAGE); 
    }

    private void generarGrafica() {                     // Método_para_procesar_los_coeficientes_y_actualizar_la_gráfica
        try { 
            a = Double.parseDouble(txtA.getText()); 
            b = Double.parseDouble(txtB.getText());     // Convierte_el_texto_de_los_campos_en_un_double
            c = Double.parseDouble(txtC.getText()); 
            d = Double.parseDouble(txtD.getText()); 
            panelGrafica.repaint();                     // Redibuja_el_panel_de_gráfica_con_los_nuevos_coeficientes
        } catch (NumberFormatException ex) { 
            JOptionPane.showMessageDialog(this, "¡Solo números permitidos!", "Error", JOptionPane.ERROR_MESSAGE); // Muestra_un_mensaje_de_error
        }
    }

    class PanelGrafica extends JPanel {                     // Clase_interna_para_el_panel_de_la_gráfica
        @Override
        protected void paintComponent(Graphics g) {         // Método_para_dibujar_la_gráfica
            super.paintComponent(g);                        // Llama_al_método_paintComponent_de_la_superclase
            Graphics2D g2 = (Graphics2D) g;                 // Convierte_el_objeto_Graphics_a_Graphics2D_para_funcionalidades_avanzadas
            this.setBackground(Color.WHITE);                // Establece_el_fondo_del_panel_en_blanco

            // Centrar_ejes
            int centroX = getWidth() / 2;                    // Calcula_la_coordenada_x_del_centro_del_panel
            int centroY = getHeight() / 2;                   // Calcula_la_coordenada_y_del_centro_del_panel

            // Dibujar_ejes
            g2.setColor(Color.BLACK);                        // Establece_el_color_de_dibujo_en_negro
            g2.drawLine(0, centroY, getWidth(), centroY); // Dibuja_el_eje_X_horizontal
            g2.drawLine(centroX, 0, centroX, getHeight()); // Dibuja_el_eje_Y_vertical

            // Escala
            final int ESCALA = 40;                             // Define_la_escala_(40_píxeles_por_unidad)

            // Dibujar_etiquetas_y_marcas_en_los_ejes
            g2.setFont(new Font("Arial", Font.PLAIN, 12)); 
            // Eje_X
            for (int i = -10; i <= 10; i++) {                 // Itera_sobre_los_valores_de_-10_a_10_para_el_eje_X
                if (i != 0) {                                 // Evita_dibujar_en_el_origen_para_no_superponer_etiquetas
                    int x = centroX + i * ESCALA;             // Calcula_la_coordenada_x_de_la_marca
                    g2.drawLine(x, centroY - 5, x, centroY + 5); // Dibuja_una_marca_vertical_de_10_píxeles
                    g2.drawString(String.valueOf(i), x - 5, centroY + 20); // Dibuja_la_etiqueta_numérica_debajo
                }
            }
            // Eje_Y
            for (int i = -10; i <= 10; i++) {                   // Itera_sobre_los_valores_de_-10_a_10_para_el_eje_Y
                if (i != 0) {                                   // Evita_dibujar_en_el_origen_para_no_superponer_etiquetas
                    int y = centroY - i * ESCALA;               // Calcula_la_coordenada_y_de_la_marca
                    g2.drawLine(centroX - 5, y, centroX + 5, y); // Dibuja_una_marca_horizontal_de_10_píxeles
                    g2.drawString(String.valueOf(i), centroX - 25, y + 5); // Dibuja_la_etiqueta_numérica_a_la_izquierda
                }
            }

            // Dibujar_gráfica
            g2.setColor(Color.RED);                             // Establece_el_color_de_la_gráfica_en_rojo
            int pxAnterior = 0, pyAnterior = 0;                 // Inicializa_las_coordenadas_del_punto_anterior
            boolean primero = true;                             // Bandera_para_evitar_dibujar_una_línea_en_el_primer_punto

            for (double x = -10; x <= 10; x += 0.05) {          // Itera_sobre_x_de_-10_a_10_con_pasos_de_0.05
                double y = a * Math.pow(x, 3) + b * Math.pow(x, 2) + c * x + d; // Calcula_y_según_la_ecuación_cúbica
                int px = (int) (centroX + (x * ESCALA));        // Convierte_la_coordenada_x_a_píxeles
                int py = (int) (centroY - (y * ESCALA));        // Convierte_la_coordenada_y_a_píxeles_(invierte_y)

                if (!primero) {                                 // Si_no_es_el_primer_punto
                    g2.drawLine(pxAnterior, pyAnterior, px, py); // Dibuja_una_línea_desde_el_punto_anterior_al_actual
                } else {                                        // Si_es_el_primer_punto
                    primero = false;                            // Desactiva_la_bandera_para_comenzar_a_dibujar_líneas
                }

                pxAnterior = px;                                 // Actualiza_la_coordenada_x_anterior
                pyAnterior = py;                                // Actualiza_la_coordenada_y_anterior
            }
        }
    }

    public static void main(String[] args) { 
        new GraficadorEcuaciones(); 
    }
}