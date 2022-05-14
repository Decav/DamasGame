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
public class Piezas {
    boolean reina;
    String nombreCasilla;
    boolean muerto;
    boolean rojas;

    public Piezas() {

    }

    public String getNombreCasilla() {
        return this.nombreCasilla;
    }

    public void setNombreCasilla(String nombreCasilla) {
        this.nombreCasilla = nombreCasilla;
    }

    public boolean isReina() {
        return this.reina;
    }

    public void setReina(boolean reina) {
        this.reina = reina;
    }

    public boolean isMuerto() {
        return this.muerto;
    }

    public void setMuerto(boolean muerto) {
        this.muerto = muerto;
    }

    public boolean isRojas() {
        return this.rojas;
    }

    public void setRojas(boolean rojas) {
        this.rojas = rojas;
    }
}
