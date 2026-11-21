package htl.HashedZoo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    @BeforeAll
    static void beforeAll() {
        Locale.setDefault(Locale.US);
    }

    @Test
    void testConstructorWithValidValues() {
        Animal animal = new Animal("Leo", "Lion", 190, LocalDate.of(2015, 8, 15));

        assertEquals("Leo", animal.getName());
        assertEquals("Lion", animal.getRace());
        assertEquals(190, animal.getWeight(), 0.0001);
        assertEquals(LocalDate.of(2015, 8, 15), animal.getBirthday());
    }

    @Test
    void testToString() {
        Animal animal = new Animal("Leo", "Lion", 190.25, LocalDate.of(2015, 8, 15));

        assertEquals("I am Leo the Lion. I was born on 15.8.2015 and I weigh 190.25 kg.", animal.toString());
    }

    @Test
    void testConstructorWithNullName() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Animal(null, "Lion", 190, LocalDate.of(2015, 8, 15));
        });

        assertEquals("Name must not be null or empty!", e.getMessage());
    }

    @Test
    void testConstructorWithEmptyName() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Animal("", "Lion", 190, LocalDate.of(2015, 8, 15));
        });

        assertEquals("Name must not be null or empty!", e.getMessage());
    }

    @Test
    void testConstructorWithBlankName() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Animal(" ", "Lion", 190, LocalDate.of(2015, 8, 15));
        });

        assertEquals("Name must not be null or empty!", e.getMessage());
    }

    @Test
    void testConstructorWithNullRace() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Animal("Leo", null, 190, LocalDate.of(2015, 8, 15));
        });

        assertEquals("Race must not be null or empty!", e.getMessage());
    }

    @Test
    void testConstructorWithEmptyRace() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Animal("Leo", "", 190, LocalDate.of(2015, 8, 15));
        });

        assertEquals("Race must not be null or empty!", e.getMessage());
    }

    @Test
    void testConstructorWithBlankRace() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Animal("Leo", "      ", 190, LocalDate.of(2015, 8, 15));
        });

        assertEquals("Race must not be null or empty!", e.getMessage());
    }

    @Test
    void testConstructorWithNegativeWeight() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Animal("Leo", "Lion", -190, LocalDate.of(2015, 8, 15));
        });

        assertEquals("Weight must be positive!", e.getMessage());
    }

    @Test
    void testConstructorWithZeroWeight() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Animal("Leo", "Lion", 0, LocalDate.of(2015, 8, 15));
        });

        assertEquals("Weight must be positive!", e.getMessage());
    }

    @Test
    void testConstructorWithFutureBirthday() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Animal("Leo", "Lion", 190, LocalDate.now().plusDays(1));
        });

        assertEquals("Birthday must not be in the future!", e.getMessage());
    }

    @Test
    void testConstructorWithBirthdayToday() {
        Animal animal = new Animal("Leo", "Lion", 190, LocalDate.now());

        assertEquals("Leo", animal.getName());
        assertEquals("Lion", animal.getRace());
        assertEquals(190, animal.getWeight(), 0.0001);
        assertEquals(LocalDate.now(), animal.getBirthday());
    }

    @Test
    void testSetWeight() {
        Animal animal = new Animal("Leo", "Lion", 190, LocalDate.of(2015, 8, 15));
        animal.setWeight(200);

        assertEquals(200, animal.getWeight(), 0.0001);
    }

    @Test
    void testSetWeightWithNegativeValue() {
        Animal animal = new Animal("Leo", "Lion", 190, LocalDate.of(2015, 8, 15));
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            animal.setWeight(-1);
        });

        assertEquals("Weight must be positive!", e.getMessage());
    }

    @Test
    void testSetWeightWithZeroValue() {
        Animal animal = new Animal("Leo", "Lion", 190, LocalDate.of(2015, 8, 15));
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            animal.setWeight(0);
        });

        assertEquals("Weight must be positive!", e.getMessage());
    }

    @Test
    void testSetWeightChangesToString() {
        Animal animal = new Animal("Leo", "Lion", 190, LocalDate.of(2016, 12, 2));
        animal.setWeight(200);

        assertEquals("I am Leo the Lion. I was born on 2.12.2016 and I weigh 200.00 kg.", animal.toString());
    }

    @Test
    void testEquals() {
        Animal animal1 = new Animal("Leo", "Lion", 190, LocalDate.of(2015, 8, 15));
        Animal animal2 = new Animal("Leo", "Lion", 190, LocalDate.of(2015, 8, 15));

        assertTrue(animal1.equals(animal2));
    }

    @Test
    void testEqualsWithDifferentName() {
        Animal animal1 = new Animal("Leo", "Lion", 190, LocalDate.of(2015, 8, 15));
        Animal animal2 = new Animal("Lea", "Lion", 190, LocalDate.of(2015, 8, 15));

        assertFalse(animal1.equals(animal2));
    }

    @Test
    void testEqualsWithDifferentRace() {
        Animal animal1 = new Animal("Leo", "Lion", 190, LocalDate.of(2015, 8, 15));
        Animal animal2 = new Animal("Leo", "Tiger", 190, LocalDate.of(2015, 8, 15));

        assertTrue(animal1.equals(animal2));
    }

    @Test
    void testEqualsWithDifferentBirthday() {
        Animal animal1 = new Animal("Leo", "Lion", 190, LocalDate.of(2015, 8, 15));
        Animal animal2 = new Animal("Leo", "Lion", 190, LocalDate.of(2015, 8, 16));

        assertTrue(animal1.equals(animal2));
    }

    @Test
    void testEqualsWithDifferentWeight() {
        Animal animal1 = new Animal("Leo", "Lion", 190, LocalDate.of(2015, 8, 15));
        Animal animal2 = new Animal("Leo", "Lion", 200, LocalDate.of(2015, 8, 15));

        assertTrue(animal1.equals(animal2));
    }

    @Test
    void testEqualsWithDifferentEverythingButName() {
        Animal animal1 = new Animal("Leo", "Lion", 190, LocalDate.of(2015, 8, 15));
        Animal animal2 = new Animal("Leo", "Cat", 5, LocalDate.of(2008, 4, 1));

        assertTrue(animal1.equals(animal2));
    }

    @Test
    void testHashCode() {
        Animal animal = new Animal("Leo", "Lion", 190, LocalDate.of(2015, 8, 15));

        assertEquals(3, animal.hashCode());
    }

    @Test
    void testCompareTo() {
        Animal rocky = new Animal("Rocky", "Rhinoceros", 2300, LocalDate.of(2012, 11, 11));
        Animal polly = new Animal("Polly", "Polar Bear", 400, LocalDate.of(2013, 7, 19));
        Animal leo = new Animal("Leo", "Lion", 190, LocalDate.of(2015, 8, 15));
        Animal coco = new Animal("Coco", "Chimpanzee", 50, LocalDate.of(2016, 1, 22));
        Animal sammy = new Animal("Sammy", "Seal", 150, LocalDate.of(2019, 2, 23));

        List<Animal> animals = new LinkedList<>();
        animals.add(rocky);
        animals.add(polly);
        animals.add(leo);
        animals.add(coco);
        animals.add(sammy);

        Collections.shuffle(animals);

        Collections.sort(animals);

        assertEquals(rocky, animals.get(0));
        assertEquals(polly, animals.get(1));
        assertEquals(leo, animals.get(2));
        assertEquals(coco, animals.get(3));
        assertEquals(sammy, animals.get(4));
    }
}