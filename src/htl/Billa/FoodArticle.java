package htl.Billa;

import java.util.HashSet;
import java.util.List;

public class FoodArticle extends Article {
    private HashSet<AllergenType> allergens;

    public FoodArticle(String name, int barcode, int quantity, AllergenType[] allergens) {
        super(name, barcode, quantity);
        this.allergens = new HashSet<>();
        this.allergens.addAll(List.of(allergens));
    }

    public boolean addAllergen(AllergenType allergenType) {
        return false;
    }

    public boolean containsAnyAllergen(AllergenType[] allergenTypes) {
        boolean contains = false;

        for (int i = 0; i < allergenTypes.length; i++) {
            if (allergens.contains(allergenTypes[i])) {
                contains = true;
            }
        }

        return contains;
    }

    public boolean removeAllergen(AllergenType allergenType) {
        return false;
    }
}
