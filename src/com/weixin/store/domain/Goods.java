package com.weixin.store.domain;

public class Goods {
    private long id;
    private String name;
    private double price;
    private String descprition;
    private String brand;
    private String cpu_brand;
    private String cpu_type;
    private String memory_capacity;
    private String hd_capacity;
    private String card_model;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescprition() {
        return descprition;
    }

    public void setDescprition(String descprition) {
        this.descprition = descprition;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCpu_brand() {
        return cpu_brand;
    }

    public void setCpu_brand(String cpu_brand) {
        this.cpu_brand = cpu_brand;
    }

    public String getCpu_type() {
        return cpu_type;
    }

    public void setCpu_type(String cpu_type) {
        this.cpu_type = cpu_type;
    }

    public String getMemory_capacity() {
        return memory_capacity;
    }

    public void setMemory_capacity(String memory_capacity) {
        this.memory_capacity = memory_capacity;
    }

    public String getHd_capacity() {
        return hd_capacity;
    }

    public void setHd_capacity(String hd_capacity) {
        this.hd_capacity = hd_capacity;
    }

    public String getCard_model() {
        return card_model;
    }

    public void setCard_model(String card_model) {
        this.card_model = card_model;
    }

    public String getDisplaysize() {
        return displaysize;
    }

    public void setDisplaysize(String displaysize) {
        this.displaysize = displaysize;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String displaysize;
    private String image;

}
