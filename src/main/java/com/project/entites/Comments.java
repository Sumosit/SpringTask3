package com.project.entites;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;

@Component
@Scope("prototype")
public class Comments {
    int id, postId;
    String author;
    String comment;
    Timestamp date;

    public Comments() {

    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Comments(int id, int postId, String author, String comment, Timestamp date) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.comment = comment;
        this.date = date;
    }
}
