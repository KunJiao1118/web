package com.jk.demo.dao.Dao_entities;

public class Book {
    private String pid;
    private String name;
    private String image;
    public Book() {
        this.pid = null;
        this.name = null;
        this.image = null;
        this.writer = null;
        this.press = null;
        this.time = null;
        this.ISBN = null;
        this.page = null;
        this.category = null;
        this.intro = null;
    }
    public Book(String pid, String name, String image, String writer, String press, String time, String ISBN, String page, String category, String intro) {
        this.pid = pid;
        this.name = name;
        this.image = image;
        this.writer = writer;
        this.press = press;
        this.time = time;
        this.ISBN = ISBN;
        this.page = page;
        this.category = category;
        this.intro = intro;
    }

    private String writer;
    private String press;
    private String time;
    private String ISBN;
    private String page;
    private String category;
    private String intro;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String iamge) {
        this.image = iamge;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "Book{" +
                "pid='" + pid + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", writer='" + writer + '\'' +
                ", press='" + press + '\'' +
                ", time='" + time + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", page='" + page + '\'' +
                ", category='" + category + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }
}
