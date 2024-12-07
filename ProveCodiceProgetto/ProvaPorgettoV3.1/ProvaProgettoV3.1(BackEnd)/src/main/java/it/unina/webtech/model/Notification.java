package it.unina.webtech.model;

public class Notification {
    private int id;
    private String title;
    private String content;

    // Costruttore
    public Notification() {
    }

    public Notification(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    // Metodi setter
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Notification{id=" + id + ", title='" + title + "', content='" + content + "'}";
    }
}