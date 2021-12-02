package com.sdu.zhiji.dao;

public class Question {

    private int id;
    private String q;
    private String a1;
    private String a2;

    public Question() {
    }

    public Question(int id, String q, String a1, String a2) {
        this.id = id;
        this.q = q;
        this.a1 = a1;
        this.a2 = a2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

}
