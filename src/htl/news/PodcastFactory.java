package htl.news;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PodcastFactory implements ArticleFactory {
    @Override
    public Article createFromString(String article) {
        Podcast podcast = null;
        String[] parts = article.split(";");
        try {
            if(parts.length != 5) {
                podcast = null;
            }else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime publicationTime = LocalDateTime.parse(parts[0].trim(), formatter);
                NewsCategory newsCategory = NewsCategory.valueOf(parts[1].trim());
                String author = parts[2].trim();
                String title = parts[3].trim();
                int duration = Integer.parseInt(parts[4].trim());

                podcast = new Podcast(author,title,publicationTime,newsCategory,duration);
            }
        }catch(IllegalArgumentException e) {

        }
        return podcast;
    }
}
