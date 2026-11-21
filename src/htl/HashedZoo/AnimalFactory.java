package htl.HashedZoo;

import java.time.LocalDate;

public class AnimalFactory {
    public static Animal createFromString(String animal) {
        try {
            String[] parts = animal.split(",");
            if (parts.length != 4 && parts.length != 5) {
                throw new IllegalArgumentException("String must contain 4 or 5 values!");
            }

            String name = parts[0];
            String race = parts[1];
            double weight = Double.parseDouble(parts[2]);
            LocalDate birthday = LocalDate.parse(parts[3]);

            if (parts.length == 5) {
                int wingSpan = Integer.parseInt(parts[4]);
                return new Bird(name, race, weight, birthday, wingSpan);
            }
            return new Animal(name, race, weight, birthday);
        } catch (IllegalArgumentException e) {
            throw new ZooException("Exception occured while parsing line!", e);
        }
    }
}
