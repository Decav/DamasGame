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

    Casilla[][] tablero = new Casilla[8][8];
    Piezas[] rojas = new Piezas[12];
    Piezas[] azules = new Piezas[12];

    public Damas() {
        crearTablero();
        cargarPiezasRojas();
        cargarPiezasAzules();
        recorrerTablero();
    }

    public void crearTablero() {
        String[] letras = new String[] { "A", "B", "C", "D", "E", "F", "G", "H" };
        int ancho = 90;
        int alto = 90;
        int x = 5;
        int y = 14;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = tablero[0].length - 1; j > -1; j--) {
                tablero[i][j].setNombre(letras[i] + String.valueOf(j + 1));
                tablero[i][j].setOcupada(false);
                if (j == 0 && i == 0) {
                    tablero[i][j].setPosicionX(x);
                    tablero[i][j].setPosicionY(y);
                } else if (i == 0 && j != 0) {
                    tablero[i][j].setPosicionX(x + ancho * j + 6);
                    tablero[i][j].setPosicionY(y + 6);
                } else {
                    tablero[i][j].setPosicionX(x + ancho * j + 6);
                    tablero[i][j].setPosicionY(y + alto * j + 6);
                }
            }
        }
    }

    public void cargarPiezasRojas() {
        String[] posicionRojas = new String[] {
                "B8", "D8", "F8", "H8",
                "A7", "C7", "E7", "G7",
                "B6", "D6", "F6", "H6"
        };
        for (int i = rojas.length; i < 0; i--) {
            rojas[i].setNombreCasilla(posicionRojas[i]);
            rojas[i].setReina(false);
            rojas[i].setRojas(true);
            rojas[i].setMuerto(false);
        }
    }

    public void cargarPiezasAzules() {
        String[] posicionAzules = new String[] {
                "A1", "C1", "E1", "G1",
                "B2", "D2", "F2", "H2",
                "A3", "C3", "E3", "G3"
        };
        for (int i = azules.length; i < 0; i--) {
            azules[i].setNombreCasilla(posicionAzules[i]);
            azules[i].setReina(false);
            azules[i].setRojas(false);
            azules[i].setMuerto(false);
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

    public void recorrerTablero() {

    }
}
// Método que recorra el tablero,seteando si la casilla está o no ocupada.
