package com.sdu.zhiji.dao;


public class DescriptorCard {

    private int id;
    private int img;
    private String name;
    private String text;

    public DescriptorCard() {}

    public DescriptorCard(int id, int img, String name, String text) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
