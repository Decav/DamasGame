package com.parland.damas;

public class Ficha {

    private int x;
    private int y;
    private boolean reina;

    public Ficha(int x, int y, boolean reina) {
        this.x = x;
        this.y = y;
        this.reina = reina;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setReina(boolean reina) {
        this.reina = reina;
    }

}
