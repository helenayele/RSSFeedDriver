package org.example.rssfeed.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.example.rssfeed.model.RssFeed;
import org.example.rssfeed.model.RssItem;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class RssFeedDriverService {
    public RssFeed fetchFeed(String feedUrl) throws Exception {
     //   URL url = new URL(feedUrl);
        try (InputStream inputStream = new URL(feedUrl).openStream();
             InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {

            SyndFeedInput input = new SyndFeedInput();
            SyndFeed syndFeed = input.build(reader);

            // Process your feed here
            RssFeed rssFeed = new RssFeed();
            rssFeed.setTitle(syndFeed.getTitle());
            rssFeed.setDescription(syndFeed.getDescription());
            rssFeed.setLink(syndFeed.getLink());
            rssFeed.setPublishedDate(convertToLocalDateTime(syndFeed.getPublishedDate()));

            List<RssItem> items = new ArrayList<>();
            for (SyndEntry entry : syndFeed.getEntries()) {
                RssItem item = new RssItem();
                item.setTitle(entry.getTitle());
                item.setDescription(entry.getDescription() != null ? entry.getDescription().getValue() : null);
                item.setLink(entry.getLink());
                item.setAuthor(entry.getAuthor());
                item.setPublishedDate(convertToLocalDateTime(entry.getPublishedDate()));

                if (entry.getContents() != null && !entry.getContents().isEmpty()) {
                    item.setContent(entry.getContents().get(0).getValue());
                }

                items.add(item);
            }

            rssFeed.setItems(items);
            return rssFeed;
        } catch (IOException | FeedException e) {
            // Handle exceptions
            throw new RuntimeException("Failed to fetch RSS feed", e);
        }


    }

    private LocalDateTime convertToLocalDateTime(Date date) {
        if (date == null) return null;
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public List<RssFeed> fetchMultipleFeeds(List<String> feedUrls) {
        List<RssFeed> feeds = new ArrayList<>();
        for (String url : feedUrls) {
            try {
                feeds.add(fetchFeed(url));
            } catch (Exception e) {
                System.err.println("Failed to fetch feed: " + url + " - " + e.getMessage());
            }
        }
        return feeds;
    }

}
