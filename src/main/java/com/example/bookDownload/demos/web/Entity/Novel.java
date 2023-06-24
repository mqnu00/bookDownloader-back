package com.example.bookDownload.demos.web.Entity;

public class Novel {

    private int id;

    private String title;

    private String description;

    private String author;

    private byte[] pic;

    public Novel(String title, String description, String author, byte[] pic) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.pic = pic;
    }

    public Novel(int id, String title, String description, String author, byte[] pic) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.pic = pic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }
}
