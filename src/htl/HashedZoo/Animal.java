package htl.HashedZoo;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

public class Animal implements Comparable<Animal>  {
    private String name;
    private String race;
    private double weight;
    private LocalDate birthday;

    public Animal(String name, String race, double weight, LocalDate birthday) {
        setName(name);
        setRace(race);
        setWeight(weight);
        setBirthday(birthday);
    }

    public String getName() {
        return name;
    }

    public String getRace() {
        return race;
    }

    public double getWeight() {
        return weight;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setWeight(double weight) {
        if(weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive!");
        }
        this.weight = weight;
    }

    private void setName(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be null or empty!");
        }
        this.name = name;
    }

    private void setBirthday(LocalDate birthday) {
        if(birthday.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birthday must not be in the future!");
        }
        this.birthday = birthday;
    }

    private void setRace(String race) {
        if(race == null || race.isBlank()) {
            throw new IllegalArgumentException("Race must not be null or empty!");
        }
        this.race = race;
    }

    @Override
    public int compareTo(Animal o) {
        return birthday.compareTo(o.birthday);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        String date = birthday.format(formatter);
        return String.format(Locale.US, "I am %s the %s. I was born on %s and I weigh %.2f kg.", name, race, date, weight);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return name.length();
    }
}




