package com.example.bcsd5.board;

public class BoardItemData {
    private String title, author;
    private long createdTime;

    public BoardItemData(String title, String author, long createdTime) {
        this.title = title;
        this.author = author;
        this.createdTime = createdTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
}
