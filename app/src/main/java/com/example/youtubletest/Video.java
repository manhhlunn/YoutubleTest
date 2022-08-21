package com.example.youtubletest;

public class Video {
    private String img;
    private String name;
    private String author;
    private String desc;
    private String id;

    public Video(String img, String name, String author, String desc, String id) {
        this.img = img;
        this.name = name;
        this.author = author;
        this.desc = desc;
        this.id = id;
    }

    public Video() {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
