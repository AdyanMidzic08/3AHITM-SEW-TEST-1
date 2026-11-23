package htl.news;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WebArticle extends Article {
    private String link;

    public WebArticle(String author, String title, LocalDateTime publicationTime, NewsCategory category, String link) {
        super(author, title, publicationTime, category);
        setLink(link);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        boolean isWorking = false;

        if(link == null || link.isBlank()) {
            isWorking = false;
        }else {
            if(link.contains("http://") || link.contains("https://")) {
                isWorking = true;
            }
        }

        if(!isWorking) {
            throw new IllegalArgumentException("Link must not be null and start with http:// or https://!");
        }

        this.link = link;
    }


}
