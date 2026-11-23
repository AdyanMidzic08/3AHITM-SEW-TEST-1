package htl.news;

import htl.library.SimpleLogger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class NewsAgency {
    private PriorityQueue<Article> articles;
    private Article latestReleasedArticle;

    public NewsAgency() {
        articles = new PriorityQueue<>();
    }

    public void addArticle(Article article) {
        if(article != null) {
            articles.add(article);
        }
    }

    public boolean hasMoreArticles() {
        boolean hasMoreArticles = false;

        if(articles.size() != 0) {
            hasMoreArticles = true;
        }

        return hasMoreArticles;
    }

    public void releaseArticle() {
        if(articles.peek() == null) {
            throw new NewsException("No articles to release!");
        }
        latestReleasedArticle = articles.peek();
        articles.remove(latestReleasedArticle);
    }

    public Article getLatestReleasedArticle() {
        return latestReleasedArticle;
    }

    public void addArticlesFromFile(String path, ArticleFactory factory) {
        SimpleLogger logger = SimpleLogger.getInstance();

        logger.logTrace("Loading articles from file " + path);

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.readLine();   // Kopfzeile überspringen
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    Article article = factory.createFromString(line);
                    if (article != null) {
                        addArticle(article);
                    } else {
                        logger.logError("Invalid line : " + line);
                    }
                }
            }
        } catch (IOException e) {
            logger.logError("Could not load file " + path);
            throw new NewsException("Oh no, something happened while loading from file!", e);
        }
    }
}
