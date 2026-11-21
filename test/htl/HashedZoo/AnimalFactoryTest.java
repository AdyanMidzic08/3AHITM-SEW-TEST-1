package htl.HashedZoo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AnimalFactoryTest {
    @Test
    void testCreateFromTooShortString() {
        ZooException e = assertThrows(ZooException.class, () -> {
            AnimalFactory.createFromString("Spike,Hedgehog,2020-06-16");
        });

        assertEquals("Exception occured while parsing line!", e.getMessage());
        IllegalArgumentException cause = (IllegalArgumentException) e.getCause();
        assertEquals("String must contain 4 or 5 values!", cause.getMessage());
    }

    @Test
    void testCreateFromTooLongString() {
        ZooException e = assertThrows(ZooException.class, () -> {
            AnimalFactory.createFromString("Feathers,Falcon,1,2018-03-30,110,390");
        });

        assertEquals("Exception occured while parsing line!", e.getMessage());
        IllegalArgumentException cause = (IllegalArgumentException) e.getCause();
        assertEquals("String must contain 4 or 5 values!", cause.getMessage());
    }

    @Test
    void testCreateAnimalFromValidString() {
        Animal animal = AnimalFactory.createFromString("Spike,Hedgehog,0.8,2020-06-16");

        assertEquals("Spike", animal.getName());
        assertEquals("Hedgehog", animal.getRace());
        assertEquals(0.8, animal.getWeight(), 0.0001);
        assertEquals(LocalDate.of(2020, 6, 16), animal.getBirthday());
    }

    @Test
    void testCreateBirdFromValidString() {
        Animal animal = AnimalFactory.createFromString("Feathers,Falcon,1,2018-03-30,110");
        Bird bird = (Bird) animal;

        assertEquals("Feathers", bird.getName());
        assertEquals("Falcon", bird.getRace());
        assertEquals(1, bird.getWeight(), 0.0001);
        assertEquals(LocalDate.of(2018, 3, 30), bird.getBirthday());
        assertEquals(110, bird.getWingSpan());
    }

    @Test
    void testCreateFromInvalidName() {
        ZooException e = assertThrows(ZooException.class, () -> {
            AnimalFactory.createFromString(",Hedgehog,0.8,2020-06-16");
        });

        assertEquals("Exception occured while parsing line!", e.getMessage());
        IllegalArgumentException cause = (IllegalArgumentException) e.getCause();
        assertEquals("Name must not be null or empty!", cause.getMessage());
    }

    @Test
    void testCreateFromInvalidRace() {
        ZooException e = assertThrows(ZooException.class, () -> {
            AnimalFactory.createFromString("Spike,     ,0.8,2020-06-16");
        });

        assertEquals("Exception occured while parsing line!", e.getMessage());
        IllegalArgumentException cause = (IllegalArgumentException) e.getCause();
        assertEquals("Race must not be null or empty!", cause.getMessage());
    }

    @Test
    void testCreateFromInvalidWeight() {
        ZooException e = assertThrows(ZooException.class, () -> {
            AnimalFactory.createFromString("Spike,Hedgehog,-123,2020-06-16");
        });

        assertEquals("Exception occured while parsing line!", e.getMessage());
        IllegalArgumentException cause = (IllegalArgumentException) e.getCause();
        assertEquals("Weight must be positive!", cause.getMessage());
    }

    @Test
    void testCreateFromInvalidBirthday() {
        ZooException e = assertThrows(ZooException.class, () -> {
            AnimalFactory.createFromString(String.format("Spike,Hedgehog,0.8,%d-06-16", LocalDate.now().plusYears(1).getYear()));
        });

        assertEquals("Exception occured while parsing line!", e.getMessage());
        IllegalArgumentException cause = (IllegalArgumentException) e.getCause();
        assertEquals("Birthday must not be in the future!", cause.getMessage());
    }

    @Test
    void testCreateFromInvalidWingSpan() {
        ZooException e = assertThrows(ZooException.class, () -> {
            AnimalFactory.createFromString("Feathers,Falcon,1,2018-03-30,0");
        });

        assertEquals("Exception occured while parsing line!", e.getMessage());
        IllegalArgumentException cause = (IllegalArgumentException) e.getCause();
        assertEquals("Wing span must be positive!", cause.getMessage());
    }
}