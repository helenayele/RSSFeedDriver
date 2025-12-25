package org.example.rssfeed.model;

import java.time.LocalDateTime;
import java.util.List;

public class RssFeed {
        private String title;
        private String description;
        private String link;
        private LocalDateTime publishedDate;
        private List<RssItem> items;

        // Constructors, Getters, Setters
        public RssFeed() {}

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public String getLink() { return link; }
        public void setLink(String link) { this.link = link; }

        public LocalDateTime getPublishedDate() { return publishedDate; }
        public void setPublishedDate(LocalDateTime publishedDate) { this.publishedDate = publishedDate; }

        public List<RssItem> getItems() { return items; }
        public void setItems(List<RssItem> items) { this.items = items; }

}
