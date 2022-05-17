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
    private boolean reina;
    private String nombreCasilla;
    private boolean muerto;
    private boolean rojas;

    // Constructores
    public Piezas(String nombreCasilla, boolean reina, boolean muerto, boolean rojas) {
        this.nombreCasilla = nombreCasilla;
        this.reina = reina;
        this.muerto = muerto;
        this.rojas = rojas;
    }

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
