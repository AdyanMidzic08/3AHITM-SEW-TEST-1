package htl.facilitymgt;

public class SchoolFactory implements FacilityFactory {
    @Override
    public Facility createFromString(String facility) {
        String[] parts = facility.split(";");

        String schoolType = parts[1].trim();
        String name = parts[2].trim();
        String street = parts[3].trim();
        int houseNumber = Integer.parseInt(parts[4].trim());
        int zipCode = Integer.parseInt(parts[5].trim());
        String cityName = parts[6].trim();

        City city = new City(zipCode, cityName);

        return new School(name, city,street,houseNumber,schoolType);
    }
}
