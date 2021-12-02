package com.sdu.zhiji.dao;

public class Descriptor {

    private int id;
    private String[] symbols;
    private int[] idQ;

    public Descriptor() {
    }

    public Descriptor(int id, String[] symbols, int[] idQ) {
        this.id = id;
        this.symbols = symbols;
        this.idQ = idQ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getSymbols() {
        return symbols;
    }

    public void setSymbol(String[] symbols) {
        this.symbols = symbols;
    }

    public int[] getIdQ() {
        return idQ;
    }

    public void setIdQ(int[] idQ) {
        this.idQ = idQ;
    }

}
