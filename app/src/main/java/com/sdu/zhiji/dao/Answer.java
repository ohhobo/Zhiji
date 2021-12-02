package com.sdu.zhiji.dao;

public class Answer {
    private int id;
    private int a1;
    private int a2;

    public Answer() {}

    public Answer(int id, int a1, int a2) {
        this.id = id;
        this.a1 = a1;
        this.a2 = a2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getA1() {
        return a1;
    }

    public void setA1(int a1) {
        this.a1 = a1;
    }

    public int getA2() {
        return a2;
    }

    public void setA2(int a2) {
        this.a2 = a2;
    }

}
