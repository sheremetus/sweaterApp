package com.example.sweater.domain;

import javax.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private String text;
    private String tag;
private String filename;

    // Указываем базе данных,чтоодному пользователю соответствует множество сообщений
    // FetchType.EAGER означает что каждый раз когда мы получаем сообщение мы хотим получать сообщение об авторе
    // @JoinColumn(name="user_id") это нужно только для того чтобы в базе данных наше поле называлось user_id а не author_id
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Message(String text, String tag, User author) {
        this.text = text;
        this.tag = tag;
        this.author = author;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Метод возвращает <none> если у автора нет сообщения
     *
     */
    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public Message() {
    }
}
