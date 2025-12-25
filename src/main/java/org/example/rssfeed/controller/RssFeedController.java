package org.example.rssfeed.controller;
import org.example.rssfeed.service.RssFeedDriverService;
import org.example.rssfeed.model.RssFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rss")
public class RssFeedController {

    @Autowired
    private RssFeedDriverService rssFeedDriverService;

    @GetMapping("/feed")
    public ResponseEntity<RssFeed> getFeed(@RequestParam(name = "url") String url) {
        try {
            RssFeed feed = rssFeedDriverService.fetchFeed(url);
            return ResponseEntity.ok(feed);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/feeds")
    public ResponseEntity<List<RssFeed>> getMultipleFeeds(@RequestBody List<String> urls) {
        List<RssFeed> feeds = rssFeedDriverService.fetchMultipleFeeds(urls);
        return ResponseEntity.ok(feeds);
    }
}