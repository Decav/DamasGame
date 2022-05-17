/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parland.damas;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;

/**
 *
 * 13-05-2022
 */
public class DamasGUI extends javax.swing.JFrame {

    /**
     * Creates new form DamasGUI
     */
    Damas damas;
    // botones de las piezas y las opciones de movimiento
    JButton[] btnAzules;
    JButton[] btnRojas;
    JButton[] btnOpciones;
    // tablero y piezas
    Piezas[] rojas;
    Piezas[] azules;
    Casilla[][] tablero;

    public DamasGUI() {
        initComponents();
        cargarBotones();
    }

    public void cargarBotones() {
        this.damas = new Damas();
        // Casillas del tablero
        tablero = this.damas.getTablero();

        // piezas del tablero
        rojas = this.damas.getRojas();
        azules = this.damas.getAzules();

        // Inicialización de botones
        btnAzules = new JButton[azules.length];
        btnRojas = new JButton[rojas.length];
        btnOpciones = new JButton[4];

        // Cargar Botones
        for (int i = 0; i < btnAzules.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                for (int h = 0; h < tablero[0].length; h++) {
                    if (azules[i].getNombreCasilla().equals(tablero[j][h].getNombre())) {
                        btnAzules[i] = new JButton();
                        btnAzules[i].setName(i + ",Azul");
                        btnAzules[i].setBounds(tablero[j][h].getPosicionX(),
                                tablero[j][h].getPosicionY(), 90, 90);
                        btnAzules[i].setContentAreaFilled(false);
                        btnAzules[i].setIcon(new ImageIcon(getClass().getResource("/images/PiezaJugador.png")));

                        btnAzules[i].addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                clickBtnActivarOpciones(e);
                            }
                        });

                        jPanel2.add(btnAzules[i]);
                    }

                    if (rojas[i].getNombreCasilla().equals(tablero[j][h].getNombre())) {
                        btnRojas[i] = new JButton();
                        btnRojas[i].setName(i + ",Roja");
                        btnRojas[i].setBounds(tablero[j][h].getPosicionX(),
                                tablero[j][h].getPosicionY(), 90, 90);
                        btnRojas[i].setContentAreaFilled(false);
                        btnRojas[i].setIcon(new ImageIcon(getClass().getResource("/images/PiezaRival.png")));

                        btnRojas[i].addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {

                                clickBtnActivarOpciones(e);
                            }
                        });

                        jPanel2.add(btnRojas[i]);
                    }
                }
            }
        }
        for (int i = 0; i < btnOpciones.length; i++) {
            btnOpciones[i] = new JButton();
            btnOpciones[i].setName("opcionMov" + i);
            btnOpciones[i].setBounds(0, 0, 90, 90);
            // btnOpciones[i].setBackground(Color.decode("#F2F056"));
            btnOpciones[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    clickOpcion(e);
                }
            });
            btnOpciones[i].setVisible(false);
            jPanel2.add(btnOpciones[i]);
        }
    }
    // LO QUE FALTA POR HACER

    // sersiorarse :v setear si está o no ocupada la casilla
    // Hacer funcionar los turnos
    // Comer piezas + salto de espacio

    // Activa los botones de movimiento segun si es roja o azul
    public void clickBtnActivarOpciones(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        // arreglo de string de dos posiciones que en [0] tiene la posicion de la pieza
        // dentro del arreglo
        // [1] tiene el color de la ficha o pieza
        String[] arregloDesc = button.getName().split(",");
        int posicionArray = Integer.parseInt(arregloDesc[0]);
        boolean isRojo = arregloDesc[1].equals("Roja");
        if (isRojo) {
            String nuevaPosicion = getPositionXYCasilla(rojas[posicionArray].getNombreCasilla());
            opcionesMovBtn(nuevaPosicion, rojas[posicionArray], posicionArray);
        } else {
            String nuevaPosicion = getPositionXYCasilla(azules[posicionArray].getNombreCasilla());
            opcionesMovBtn(nuevaPosicion, azules[posicionArray], posicionArray);
        }
    }

    public void clickOpcion(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        String[] arregloDesc = button.getName().split(",");
        String nombreCasillaActual = arregloDesc[0];
        System.out.println("|" + nombreCasillaActual + "|");
        int posicionArray = Integer.parseInt(arregloDesc[1]);
        boolean isRoja = arregloDesc[2].equals("true");
        int[] pActualCasillaTab = new int[] {
                Integer.parseInt(arregloDesc[3].split("-")[0]),
                Integer.parseInt(arregloDesc[3].split("-")[1]) };
        int[] pMoverseCasillaTab = new int[] {
                Integer.parseInt(arregloDesc[4].split("-")[0]),
                Integer.parseInt(arregloDesc[4].split("-")[1]) };

        Casilla casillaActual = tablero[pActualCasillaTab[0]][pActualCasillaTab[1]];
        Casilla casillaMov = tablero[pMoverseCasillaTab[0]][pMoverseCasillaTab[1]];
        System.out.println(casillaActual.getNombre());
        System.out.println(btnAzules[posicionArray].getName());
        for (int i = 0; i < 4; i++) {
            btnOpciones[i].setVisible(false);
        }
        if (casillaActual.getNombre().equals(nombreCasillaActual)) {
            System.out.println("nombreCasillaActual: " + nombreCasillaActual);
            casillaActual.setOcupada(false);
            casillaMov.setOcupada(true);
            if (isRoja) {
                rojas[posicionArray].setNombreCasilla(casillaMov.getNombre());
                System.out.println(casillaMov.getPosicionX() + "," + casillaMov.getPosicionY());
                btnRojas[posicionArray].setLocation(casillaMov.getPosicionX(), casillaMov.getPosicionY());
            } else {
                btnAzules[posicionArray].setLocation(casillaMov.getPosicionX(), casillaMov.getPosicionY());
                azules[posicionArray].setNombreCasilla(casillaMov.getNombre());
            }
        } else {
            System.out.println("cayó al else");
        }
        System.out.println(tablero[pActualCasillaTab[0]][pActualCasillaTab[1]].isOcupada());
    }

    public String getPositionXYCasilla(String nombreCasilla) {
        String posicionGeneral = "";
        for (int x = 0; x < this.tablero.length; x++) {
            for (int y = 0; y < this.tablero.length; y++) {
                if (this.tablero[x][y].getNombre().equals(nombreCasilla)) {
                    posicionGeneral = x + "," + y;
                }
            }
        }
        return posicionGeneral;
    }

    public void opcionesMovBtn(String posicion, Piezas pieza, int posicionPiezaArray) {
        boolean isReina = pieza.isReina();
        boolean roja = pieza.isRojas();

        String[] arr = posicion.split(",");
        int x = Integer.parseInt(arr[0]);
        int y = Integer.parseInt(arr[1]);
        int[][] opcionP;
        if (!roja) {
            int[][] opcionA = { { x - 1, y - 1 }, { x - 1, y + 1 }, { x + 1, y - 1 }, { x + 1, y + 1 } };
            opcionP = opcionA;
        } else {
            int[][] opcionR = { { x + 1, y - 1 }, { x + 1, y + 1 }, { x - 1, y - 1 }, { x - 1, y + 1 } };
            opcionP = opcionR;
        }

        int maxOp;
        String dir;
        if (isReina) {
            maxOp = 4;
            dir = "/images/MovOpcionalReina.png";
        } else {
            maxOp = 2;
            dir = "/images/MovOpcional.png";
        }
        System.out.println(btnAzules[posicionPiezaArray].getName());
        for (int i = 0; i < maxOp; i++) {
            // Valida que no se salga del rango del arreglo
            if (opcionP[i][0] >= 0 && opcionP[i][0] < tablero.length && opcionP[i][1] >= 0
                    && opcionP[i][1] < tablero.length) {

                if (this.tablero[opcionP[i][0]][opcionP[i][1]].isOcupada() == false) {
                    Casilla c = this.tablero[opcionP[i][0]][opcionP[i][1]];
                    btnOpciones[i].setName(pieza.getNombreCasilla() + "," +
                            posicionPiezaArray + "," +
                            pieza.isRojas() + "," +
                            x + "-" + y + "," +
                            opcionP[i][0] + "-" + opcionP[i][1] + ",");
                    btnOpciones[i].setIcon(new ImageIcon(getClass().getResource(dir)));
                    btnOpciones[i].setContentAreaFilled(false);
                    btnOpciones[i].setLocation(c.getPosicionX(), c.getPosicionY());
                    System.out.println(c.getPosicionX() + " " + c.getPosicionY());
                    btnOpciones[i].setVisible(true);
                } else {
                    btnOpciones[i].setVisible(false);
                }
            } else {
                btnOpciones[i].setVisible(false);
            }

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 770, Short.MAX_VALUE));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 770, Short.MAX_VALUE));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 770, 770));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tablero.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 770, 770));

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton12ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DamasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DamasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DamasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DamasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DamasGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
