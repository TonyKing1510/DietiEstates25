package com.example.prova2.model;

public class Notification {
    private int id;
    private String title;
    private String content;

    // Costruttore pubblico
    public Notification(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // Getter e Setter per ogni campo
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
    // Metodo toString per visualizzare un riassunto della notifica
    @Override
    public String toString() {
        return "Notification{id=" + id + ", title='" + title + "', content='" + content + "'}";
    }
}
