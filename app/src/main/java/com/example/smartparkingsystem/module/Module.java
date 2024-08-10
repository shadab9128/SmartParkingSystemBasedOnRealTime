package com.example.smartparkingsystem.module;

public class Module {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    private int image;

    public Module(String name, int image) {
        this.name = name;
        this.image = image;
    }
}
