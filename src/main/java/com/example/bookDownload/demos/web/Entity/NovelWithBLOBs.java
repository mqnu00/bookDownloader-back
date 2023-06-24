package com.example.bookDownload.demos.web.Entity;

import org.python.antlr.ast.Str;

import java.util.Arrays;
import java.util.StringJoiner;

public class NovelWithBLOBs extends Novel {
    private String description;

    private byte[] pic;

    public NovelWithBLOBs(String title, String author, String description, byte[] pic) {
        super.setTitle(title);
        super.setAuthor(author);
        this.description = description;
        this.pic = pic;
    }

    public NovelWithBLOBs(int id, String title, String author, String description, byte[] pic) {

        super();
        this.description = description;
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NovelWithBLOBs.class.getSimpleName() + "[", "]")
                .add("description='" + description + "'")
                .add("id='" + super.getId() + "'")
                .add("author='" + super.getAuthor() + "'")
                .toString();
    }
}