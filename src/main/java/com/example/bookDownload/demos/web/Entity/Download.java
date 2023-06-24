package com.example.bookDownload.demos.web.Entity;

public class Download {

    private String userName;

    private String passWord;

    private String novelUrl;

    private String novelDownloadUrl;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNovelUrl() {
        return novelUrl;
    }

    public void setNovelUrl(String novelUrl) {
        this.novelUrl = novelUrl;
    }

    public String getNovelDownloadUrl() {
        return novelDownloadUrl;
    }

    public void setNovelDownloadUrl(String novelDownloadUrl) {
        this.novelDownloadUrl = novelDownloadUrl;
    }
}
