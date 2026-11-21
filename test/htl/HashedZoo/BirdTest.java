package htl.HashedZoo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class BirdTest {
    @BeforeAll
    static void beforeAll() {
        Locale.setDefault(Locale.US);
    }

    @Test
    void testInheritance() {
        Bird bird = new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);

        assertTrue(bird instanceof Animal);
    }

    @Test
    void testConstructorWithValidValues() {
        Bird bird = new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);

        assertEquals("Tweety", bird.getName());
        assertEquals("Canary", bird.getRace());
        assertEquals(0.025, bird.getWeight(), 0.0001);
        assertEquals(LocalDate.of(2019, 9, 12), bird.getBirthday());
        assertEquals(20, bird.getWingSpan());
    }

    @Test
    void testToString() {
        Bird bird = new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);

        assertEquals("I am Tweety the Canary. I was born on 12.9.2019 and I weigh 0.03 kg. With a wing span of 20 cm I can fly! Chirp chirp!", bird.toString());
    }

    @Test
    void testConstructorWithNullName() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Bird(null, "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);
        });

        assertEquals("Name must not be null or empty!", e.getMessage());
    }

    @Test
    void testConstructorWithEmptyName() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Bird("", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);
        });

        assertEquals("Name must not be null or empty!", e.getMessage());
    }

    @Test
    void testConstructorWithBlankName() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Bird("                            ", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);
        });

        assertEquals("Name must not be null or empty!", e.getMessage());
    }

    @Test
    void testConstructorWithNullRace() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Bird("Tweety", null, 0.025, LocalDate.of(2019, 9, 12), 20);
        });

        assertEquals("Race must not be null or empty!", e.getMessage());
    }

    @Test
    void testConstructorWithEmptyRace() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Bird("Tweety", "", 0.025, LocalDate.of(2019, 9, 12), 20);
        });

        assertEquals("Race must not be null or empty!", e.getMessage());
    }

    @Test
    void testConstructorWithBlankRace() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Bird("Tweety", " ", 0.025, LocalDate.of(2019, 9, 12), 20);
        });

        assertEquals("Race must not be null or empty!", e.getMessage());
    }

    @Test
    void testConstructorWithNegativeWeight() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Bird("Tweety", "Canary", -0.025, LocalDate.of(2019, 9, 12), 20);
        });

        assertEquals("Weight must be positive!", e.getMessage());
    }

    @Test
    void testConstructorWithZeroWeight() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Bird("Tweety", "Canary", 0, LocalDate.of(2019, 9, 12), 20);
        });

        assertEquals("Weight must be positive!", e.getMessage());
    }

    @Test
    void testConstructorWithFutureBirthday() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Bird("Tweety", "Canary", 0.025, LocalDate.now().plusDays(100), 20);
        });

        assertEquals("Birthday must not be in the future!", e.getMessage());
    }

    @Test
    void testConstructorWithBirthdayToday() {
        Bird bird = new Bird("Tweety", "Canary", 0.025, LocalDate.now(), 20);

        assertEquals("Tweety", bird.getName());
        assertEquals("Canary", bird.getRace());
        assertEquals(0.025, bird.getWeight(), 0.0001);
        assertEquals(LocalDate.now(), bird.getBirthday());
        assertEquals(20, bird.getWingSpan());
    }

    @Test
    void testConstructorWithNegativeWingSpan() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), -20);
        });

        assertEquals("Wing span must be positive!", e.getMessage());
    }

    @Test
    void testConstructorWithZeroWingSpan() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), 0);
        });

        assertEquals("Wing span must be positive!", e.getMessage());
    }

    @Test
    void testSetWeight() {
        Bird bird = new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);

        bird.setWeight(0.030);

        assertEquals(0.030, bird.getWeight(), 0.0001);
    }

    @Test
    void testSetWeightWithNegativeValue() {
        Bird bird = new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            bird.setWeight(-0.030);
        });

        assertEquals("Weight must be positive!", e.getMessage());
    }

    @Test
    void testSetWeightWithZeroValue() {
        Bird bird = new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            bird.setWeight(0);
        });

        assertEquals("Weight must be positive!", e.getMessage());
    }

    @Test
    void testSetWeightChangesToString() {
        Bird bird = new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);

        bird.setWeight(0.04);

        assertEquals("I am Tweety the Canary. I was born on 12.9.2019 and I weigh 0.04 kg. With a wing span of 20 cm I can fly! Chirp chirp!", bird.toString());
    }

    @Test
    void testSetWingSpan() {
        Bird bird = new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);

        bird.setWingSpan(25);

        assertEquals(25, bird.getWingSpan());
    }

    @Test
    void testSetWingSpanWithNegativeValue() {
        Bird bird = new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            bird.setWingSpan(-1);
        });

        assertEquals("Wing span must be positive!", e.getMessage());
    }

    @Test
    void testSetWingSpanWithZeroValue() {
        Bird bird = new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            bird.setWingSpan(0);
        });

        assertEquals("Wing span must be positive!", e.getMessage());
    }

    @Test
    void testSetWingSpanChangesToString() {
        Bird bird = new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);

        bird.setWingSpan(25);

        assertEquals("I am Tweety the Canary. I was born on 12.9.2019 and I weigh 0.03 kg. With a wing span of 25 cm I can fly! Chirp chirp!", bird.toString());
    }

    @Test
    void testEquals() {
        Bird bird1 = new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);
        Bird bird2 = new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);

        assertTrue(bird1.equals(bird2));
    }

    @Test
    void testEqualsWithDifferentName() {
        Bird bird1 = new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);
        Bird bird2 = new Bird("Twitie", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);

        assertFalse(bird1.equals(bird2));
    }

    @Test
    void testEqualsWithDifferentRace() {
        Bird bird1 = new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);
        Bird bird2 = new Bird("Tweety", "Sparrow", 0.025, LocalDate.of(2019, 9, 12), 20);

        assertTrue(bird1.equals(bird2));
    }

    @Test
    void testEqualsWithDifferentBirthday() {
        Bird bird1 = new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);
        Bird bird2 = new Bird("Tweety", "Canary", 0.025, LocalDate.of(2020, 9, 12), 20);

        assertTrue(bird1.equals(bird2));
    }

    @Test
    void testEqualsWithDifferentWeight() {
        Bird bird1 = new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);
        Bird bird2 = new Bird("Tweety", "Canary", 0.05, LocalDate.of(2019, 9, 12), 20);

        assertTrue(bird1.equals(bird2));
    }

    @Test
    void testEqualsWithDifferentWingSpan() {
        Bird bird1 = new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);
        Bird bird2 = new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), 25);

        assertTrue(bird1.equals(bird2));
    }

    @Test
    void testEqualsWithDifferentEverythingButName() {
        Bird bird1 = new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);
        Bird bird2 = new Bird("Tweety", "Sparrow", 0.05, LocalDate.of(2020, 9, 12), 25);

        assertTrue(bird1.equals(bird2));
    }

    @Test
    void testHashCode() {
        Bird bird = new Bird("Tweety", "Canary", 0.025, LocalDate.of(2019, 9, 12), 20);

        assertEquals(6, bird.hashCode());
    }

    @Test
    void testCompareTo() {
        Animal rocky = new Animal("Rocky", "Rhinoceros", 2300, LocalDate.of(2012, 11, 11));
        Animal polly = new Animal("Polly", "Polar Bear", 400, LocalDate.of(2013, 7, 19));
        Animal leo = new Animal("Leo", "Lion", 190, LocalDate.of(2015, 8, 15));
        Animal coco = new Animal("Coco", "Chimpanzee", 50, LocalDate.of(2016, 1, 22));
        Animal feathers = new Bird("Feathers", "Falcon", 1, LocalDate.of(2018, 3, 30), 110);
        Animal waddles = new Bird("Waddles", "Penguin", 20, LocalDate.of(2018, 6, 30), 70);
        Animal sammy = new Animal("Sammy", "Seal", 150, LocalDate.of(2019, 2, 23));
        Animal quackers = new Bird("Quackers", "Duck", 1.2, LocalDate.of(2021, 7, 3), 85);

        List<Animal> animals = new LinkedList<>();
        animals.add(rocky);
        animals.add(polly);
        animals.add(leo);
        animals.add(coco);
        animals.add(feathers);
        animals.add(waddles);
        animals.add(sammy);
        animals.add(quackers);

        Collections.shuffle(animals);

        Collections.sort(animals);

        assertEquals(rocky, animals.get(0));
        assertEquals(polly, animals.get(1));
        assertEquals(leo, animals.get(2));
        assertEquals(coco, animals.get(3));
        assertEquals(feathers, animals.get(4));
        assertEquals(waddles, animals.get(5));
        assertEquals(sammy, animals.get(6));
        assertEquals(quackers, animals.get(7));
    }
}