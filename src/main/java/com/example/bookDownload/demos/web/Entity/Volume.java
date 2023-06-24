package com.example.bookDownload.demos.web.Entity;

public class Volume {
    private Integer id;

    private String title;

    private Integer novelId;

    private String content;

    public Volume(Integer id, String title, Integer novelId, String content) {
        this.id = id;
        this.title = title;
        this.novelId = novelId;
        this.content = content;
    }

    public Volume(Integer id, String title, Integer novelId) {
        this.id = id;
        this.title = title;
        this.novelId = novelId;
    }

    public Volume(String title, Integer novelId, String content) {
        this.title = title;
        this.novelId = novelId;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getNovelId() {
        return novelId;
    }

    public void setNovelId(Integer novelId) {
        this.novelId = novelId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}