package htl.Billa;

import java.util.Objects;

public abstract class Article implements Comparable<Article> {
    private String name;
    private int barcode;
    private int quantity;

    public Article(String name, int barcode, int quantity) {
        this.name = name;
        if(!checkBarcode(barcode)) {
            throw new IllegalArgumentException();
        }
        this.barcode = barcode;
        setQuantity(quantity);
    }

    public String getName() {
        return name;
    }

    public int getBarcode() {
        return barcode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity < 0) {
            quantity = 0;
        }
        this.quantity = quantity;
    }

    public int buy(int buyedItems) {
        if(buyedItems < 1) {
            throw new IllegalArgumentException();
        }

        if(buyedItems > quantity) {
            buyedItems = quantity;
            quantity = 0;
        }else {
            quantity -= buyedItems;
        }
        return buyedItems;
    }

    @Override
    public int compareTo(Article o) {
        return name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(name, article.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public void restock(int restockItems) {
        if(restockItems < 0) {
            throw new IllegalArgumentException();
        }
        quantity += restockItems;
    }

    public static boolean checkBarcode(int barcode) {
        String digits = String.valueOf(barcode);
        boolean valid = false;

        // EAN-8 muss genau 8 Ziffern haben
        if (barcode > 0 && digits.length() == 8) {
            int sum = 0;

            // die ersten 7 Ziffern abwechselnd mit 1 und 3 gewichten
            for (int i = 0; i < 7; i++) {
                int digit = digits.charAt(i) - '0';
                if (i % 2 == 0) {
                    sum += digit;
                } else {
                    sum += digit * 3;
                }
            }

            int checkDigit = (10 - sum % 10) % 10;
            valid = checkDigit == digits.charAt(7) - '0';
        }

        return valid;
    }
}
