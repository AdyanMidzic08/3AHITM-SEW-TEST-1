package htl.Billa;

public class FoodArticleFactory implements ArticleFactory {
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
            String[] allergensSplited = parts[3].trim().split(",");

            AllergenType[] allergenTypes = new AllergenType[allergensSplited.length];
            for (int i = 0; i < allergensSplited.length; i++) {
                allergenTypes[i] = AllergenType.valueOf(allergensSplited[i].trim());
            }

            return new FoodArticle(name, barcode, quantity, allergenTypes);
        } catch (IllegalArgumentException e) {
            throw new ArticleManagementException("Exception occured while parsing line!", e);
        }
    }
}
