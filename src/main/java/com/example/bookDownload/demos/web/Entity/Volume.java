package com.example.bookDownload.demos.web.Entity;

public class Volume {

    int id;
    String title;
    int novel_id;

    String content;

    public Volume(int id, String title, int novel_id, String content) {
        this.id = id;
        this.title = title;
        this.novel_id = novel_id;
        this.content = content;
    }

    public Volume(String title, int novel_id, String content) {
        this.title = title;
        this.novel_id = novel_id;
        this.content = content;
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

    public int getNovel_id() {
        return novel_id;
    }

    public void setNovel_id(int novel_id) {
        this.novel_id = novel_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ReadPage{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", novel_id=" + novel_id +
                ", file_path='" + content + '\'' +
                '}';
    }
}
