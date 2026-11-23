package htl.news;

import java.time.LocalDateTime;

public class Podcast extends Article{
    private int duration;

    public Podcast(String author, String title, LocalDateTime publicationTime, NewsCategory category, int duration) {
        super(author, title, publicationTime, category);
        setDuration(duration);
    }

    public int getDuration() {
        return duration;
    }

    private void setDuration(int duration) {
        if(duration <= 0) {
            throw new IllegalArgumentException("Duration must be at least 1!");
        }
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("%s - %d:%02d", super.toString(), duration / 60, duration % 60);
    }
}
