package htl.news;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WebArticleFactory implements ArticleFactory {
    @Override
    public Article createFromString(String article) {
        WebArticle webArticle = null;
        String[] parts = article.split(";");
        try {
            if(parts.length != 5) {
                webArticle = null;
            }else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime publicationTime = LocalDateTime.parse(parts[0].trim(), formatter);
                NewsCategory newsCategory = NewsCategory.valueOf(parts[1].trim());
                String author = parts[2].trim();
                String title = parts[3].trim();
                String link = parts[4].trim();

                webArticle = new WebArticle(author,title,publicationTime,newsCategory,link);
            }
        }catch(IllegalArgumentException e) {

        }
        return webArticle;
    }
}
