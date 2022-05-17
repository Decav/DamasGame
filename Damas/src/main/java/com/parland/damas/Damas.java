/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parland.damas;

/**
 *
 * @author Usuario
 */
public class Damas {

    private Casilla[][] tablero = new Casilla[8][8];
    private Piezas[] rojas = new Piezas[12];
    private Piezas[] azules = new Piezas[12];

    public Damas() {
        cargarJuego();
    }

    public void cargarJuego() {
        crearTablero();
        cargarPiezasRojas();
        cargarPiezasAzules();
        actualizacionInicialTablero();
    }

    public void crearTablero() {
        String[] letras = new String[] { "A", "B", "C", "D", "E", "F", "G", "H" };
        // el grosor de la casilla
        int ancho = 90;
        int alto = 90;
        // para simular la separación entre el tablero visual
        int separacion = 6;
        // posicion de los pixeles dentro de JPanel2
        int x = 5;
        int y = 4;

        // recorrer tablero
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                tablero[i][j] = new Casilla();
                tablero[i][j].setNombre(letras[j] + String.valueOf(tablero.length - i));
                tablero[i][j].setOcupada(false);

                // posiciones del pixel
                if (j == 0 && i == 0) {
                    tablero[i][j].setPosicionX(x);
                    tablero[i][j].setPosicionY(y);
                } else if (i == 0 && j != 0) {
                    tablero[i][j].setPosicionX(tablero[i][j - 1].getPosicionX() + ancho + separacion);
                    tablero[i][j].setPosicionY(y);
                } else {
                    tablero[i][j].setPosicionX(tablero[i - 1][j].getPosicionX());
                    tablero[i][j].setPosicionY(tablero[i - 1][j].getPosicionY() + alto + separacion);
                }
            }
        }
    }

    public void cargarPiezasRojas() {
        // los nombres de las casillas en las que tienen que estar posicionadas
        String[] posicionRojas = new String[] {
                "B8", "D8", "F8", "H8",
                "A7", "C7", "E7", "G7",
                "B6", "D6", "F6", "H6"
        };
        for (int i = 0; i < rojas.length; i++) {
            rojas[i] = new Piezas(posicionRojas[i], false, false, true);
        }
    }

    public void cargarPiezasAzules() {
        String[] posicionAzules = new String[] {
                "A1", "C1", "E1", "G1",
                "B2", "D2", "F2", "H2",
                "A3", "C3", "E3", "G3"
        };
        for (int i = 0; i < azules.length; i++) {
            azules[i] = new Piezas();
            azules[i].setNombreCasilla(posicionAzules[i]);
            azules[i].setReina(false);
            azules[i].setRojas(false);
            azules[i].setMuerto(false);
        }
    }

    public void recorrerTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                System.out.print(tablero[i][j].getNombre());
                if (!(j == tablero.length - 1)) {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }

    public void recorrerTableroIsOcupada() {
        int contador = 0;
        System.out.println("\n");
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j].isOcupada()) {
                    contador++;
                }
            }
        }
        System.out.println(contador);
    }

    public void recorrerRojas() {
        System.out.print("\n\n");
        for (int i = 0; i < rojas.length; i++) {
            System.out.print(rojas[i].getNombreCasilla() + ", ");
        }
    }

    public void recorrerAzules() {
        System.out.print("\n\n");
        for (int i = 0; i < azules.length; i++) {
            System.out.print(azules[i].getNombreCasilla() + ", ");
        }
    }

    // Método que recorra el tablero,seteando si la casilla está o no ocupada.
    public void actualizacionInicialTablero() {
        int i = 0;
        while (i < rojas.length) {
            for (int j = 0; j < tablero.length; j++) {
                for (int h = 0; h < tablero[0].length; h++) {
                    if (tablero[j][h].getNombre().equals(rojas[i].getNombreCasilla())) {
                        tablero[j][h].setOcupada(true);
                    }
                    if (tablero[j][h].getNombre().equals(azules[i].getNombreCasilla())) {
                        tablero[j][h].setOcupada(true);
                    }
                }
            }
            i++;
        }

    }

    public Casilla[][] getTablero() {
        return this.tablero;
    }

    public Piezas[] getRojas() {
        return this.rojas;
    }

    public Piezas[] getAzules() {
        return this.azules;
    }

    // public static void main(String[] args) {
    // Damas dama = new Damas();

    // dama.recorrerTablero();
    // dama.recorrerRojas();
    // dama.recorrerAzules();
    // dama.recorrerTableroIsOcupada();
    // }
}
