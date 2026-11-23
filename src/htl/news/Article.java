package htl.news;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Article implements Comparable<Article> {
    private String author;
    private String title;
    private LocalDateTime publicationTime;
    public NewsCategory category;

    public Article(String author, String title, LocalDateTime publicationTime, NewsCategory category) {
        setAuthor(author);
        setTitle(title);
        this.publicationTime = publicationTime;
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getPublicationTime() {
        return publicationTime;
    }

    public NewsCategory getCategory() {
        return category;
    }

    @Override
    public int compareTo(Article o) {
        return publicationTime.compareTo(o.publicationTime);
    }

    private void setAuthor(String author) {
        if(author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author must not be null or blank!");
        }
        this.author = author;
    }

    private void setTitle(String title) {
        if(title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be null or blank!");
        }
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("%s %s - %s (%s)",getPublicationTimePretty(),getAuthor(),getTitleTruncated(),getCategory());
    }

    public String getTitleTruncated() {
        String title = getTitle();

        if(title.length() > 30) {
            title = title.substring(0,27);
            title += "...";
        }
        return title;
    }

    public String getPublicationTimePretty() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return getPublicationTime().format(formatter);
    }
}
