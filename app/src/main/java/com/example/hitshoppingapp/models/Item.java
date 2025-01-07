package com.example.hitshoppingapp.models;

public class Item {

    private String name;

    private long count;

    private long image;

    public Item(String name, long count, long image)
    {
        this.name = name;
        this.count = count;
        this.image = image;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void setImage(long image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCount() {
        return count;
    }

    public long getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
