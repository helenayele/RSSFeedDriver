package org.example.rssfeed.model;

import java.time.LocalDateTime;

public class RssItem {
    private String title;
    private String description;
    private String link;
    private String author;
    private LocalDateTime publishedDate;
    private String content;

    // Constructors, Getters, Setters
    public RssItem() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public LocalDateTime getPublishedDate() { return publishedDate; }
    public void setPublishedDate(LocalDateTime publishedDate) { this.publishedDate = publishedDate; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

}
