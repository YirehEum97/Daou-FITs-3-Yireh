package VO;

import java.sql.Timestamp;

public class BoardPostVO {
    private int id;
    private String title;
    private String content;
    private String author;
    private Timestamp regDate;

    public BoardPostVO() {
    }

    public BoardPostVO(int id, String title, String author, Timestamp regDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.regDate = regDate;
    }

    public BoardPostVO(int id, String title, String content, String author, Timestamp regDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.regDate = regDate;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }
}
