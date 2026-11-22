package htl.Billa;

import htl.HashedZoo.Animal;
import htl.HashedZoo.Bird;
import htl.HashedZoo.ZooException;

import java.time.LocalDate;

public class TechArticleFactory implements ArticleFactory {
    @Override
    public Article createFromString(String article) {
        try {
            String[] parts = article.split(";");
            if (parts.length != 4) {
                throw new IllegalArgumentException("String must contain 4 values!");
            }

            int barcode = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            int quantity = Integer.parseInt(parts[2].trim());
            int warranty = Integer.parseInt(parts[3].trim());

            return new TechArticle(name, barcode, quantity, warranty);
        } catch (IllegalArgumentException e) {
            throw new ArticleManagementException("Exception occured while parsing line!", e);
        }
    }
}
