package htl.Billa;

public class TechArticle extends Article {
    private int warrantyMonths;

    public TechArticle(String name, int barcode, int quantity, int warrantyMonths) {
        super(name, barcode, quantity);
        setWarrantyMonths(warrantyMonths);
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    private void setWarrantyMonths(int warrantyMonths) {
        if(warrantyMonths < 6) {
            warrantyMonths = 6;
        }
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public String toString() {
        return String.format("#%d %s (%d in stock)",getBarcode(),getName(),getQuantity());
    }
}
