package htl.facilitymgt;

import java.util.LinkedHashSet;

public class Museum extends Facility {
    private LinkedHashSet<String> collectionFoci;

    public Museum(String name,City city, String street, int houseNumber) {
        super(name, city, street, houseNumber);
        collectionFoci = new LinkedHashSet<>();
    }

    public Museum(String name, City city, String street, int houseNumber, LinkedHashSet<String> collectionFoci) {
        super(name, city, street, houseNumber);
        this.collectionFoci = collectionFoci;
    }

    public LinkedHashSet<String> getCollectionFoci() {
        return collectionFoci;
    }

    public boolean addCollectionFocus(String collection) {
        return collectionFoci.add(collection);
    }

    public boolean hasCollectionFocus(String collection) {
        return collectionFoci.contains(collection);
    }

    @Override
    public String toString() {
        return super.toString() + " - Sammlungsschwerpunkte: " + String.join(", ", collectionFoci);
    }

}
