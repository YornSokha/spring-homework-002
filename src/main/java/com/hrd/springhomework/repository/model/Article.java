package com.hrd.springhomework.repository.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Article {
    private int id;
    @NotEmpty()
    private String title;
    @NotEmpty(message = "{article.author_not_empty}")
    private String author;
    private String description;
    private String image;
    @NotNull
    private Category category;

    public Article() {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", category=" + category +
                '}';
    }
}
