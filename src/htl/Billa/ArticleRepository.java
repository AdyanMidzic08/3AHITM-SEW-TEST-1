package htl.Billa;

import java.util.*;

public class ArticleRepository {
    private Map<Integer, Article> articles;

    public ArticleRepository() {
        articles = new HashMap<>();
    }

    public boolean addArticle(Article article) {
        boolean added = false;

        if(!articles.containsKey(article.getBarcode()) && article.getQuantity() > 0) {
            articles.put(article.getBarcode(), article);
            added = true;
        }

        return added;
    }

    public Article getArticleByBarcode(int barcode) {
        List<Article> articleList = new ArrayList<>(articles.values());
        Article foundedArticle = null;
        boolean articleFound = false;

        for(Article article: articleList) {
            if(!articleFound) {
                if(article.getBarcode() == barcode) {
                    foundedArticle = article;
                    articleFound = true;
                }
            }
        }

        return foundedArticle;
    }

    public List<Article> getArticlesWithQuantityBelow(int quantity) {
        List<Article> articleWithQuantityBelowList = new ArrayList<>();

        for(Article article: articles.values()) {
            if(article.getQuantity() < quantity) {
                articleWithQuantityBelowList.add(article);
            }
        }

        ArticleQuantityComparator articleQuantityComparator = new ArticleQuantityComparator();
        Collections.sort(articleWithQuantityBelowList, articleQuantityComparator);

        return articleWithQuantityBelowList;
    }

    public List<Article> getFoodWithoutAllergens(AllergenType[] allergenTypes) {
        List<Article> foodWithoutAllergensList = new LinkedList<>();


        for(Article article: articles.values()) {
            if(article instanceof FoodArticle foodArticle && !foodArticle.containsAnyAllergen(allergenTypes)) {
                foodWithoutAllergensList.add(foodArticle);
            }
        }
        Collections.sort(foodWithoutAllergensList);
        return foodWithoutAllergensList;
    }

    public List<Article> getSortedArticles() {
        List<Article> sorted = new ArrayList<>(articles.values());
        Collections.sort(sorted);
        return sorted;
    }

    public TechArticle getTechArticleWithLongestWarranty() {
        TechArticle techArticle = null;

        for(Article article: articles.values()) {
            if(article instanceof TechArticle tech) {
                if(techArticle == null || tech.getWarrantyMonths() > techArticle.getWarrantyMonths()) {
                    techArticle = tech;
                }
            }
        }

        return techArticle;
    }
}
