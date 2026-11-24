package htl.facilitymgt;

import java.util.LinkedHashSet;

public class MuseumFactory implements FacilityFactory {
    @Override
    public Facility createFromString(String facility) {
        String[] parts = facility.split(";");

        String name = parts[0].trim();
        String street = parts[1].trim();
        int houseNumber = Integer.parseInt(parts[2].trim());
        int zipCode = Integer.parseInt(parts[3].trim());
        String cityName = parts[4].trim();

        // "Archäologie, Geschichte, Kunst" -> jeden Teil einzeln trimmen
        LinkedHashSet<String> collectionFoci = new LinkedHashSet<>();
        for (String focus : parts[5].split(",")) {
            collectionFoci.add(focus.trim());
        }

        City city = new City(zipCode, cityName);

        return new Museum(name, city, street, houseNumber, collectionFoci);
    }
}
