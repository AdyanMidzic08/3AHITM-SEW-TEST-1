package htl.HashedZoo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ZooFileTest {

    @Test
    void testAddAnimalsFromNonExistingFile() {
        Zoo zoo = new Zoo(4);

        ZooException e = assertThrows(ZooException.class, () -> {
            zoo.addAnimalsFromFile("data/doesnotexist.csv");
        });

        assertEquals("Exception occurred while loading animals from file!", e.getMessage());
        assertEquals(true, e.getCause() instanceof IOException);
    }

    @Test
    void testAddAnimalsFromFileChangesStatistics() {
        Zoo zoo = new Zoo(4);

        zoo.addAnimalsFromFile("data/animals.csv");

        assertEquals(67, zoo.getAnimalCount());
        assertEquals(4, zoo.getCagesCount());

        assertEquals(15, zoo.getCageSize(0));
        assertEquals(26, zoo.getCageSize(1));
        assertEquals(16, zoo.getCageSize(2));
        assertEquals(10, zoo.getCageSize(3));

        assertEquals(16.75, zoo.getAverageCageSize(), 0.001);
        assertEquals(26, zoo.getLargestCageSize());

        assertEquals(1435.1, zoo.getTotalWeightByCage(0), 0.001);
        assertEquals(11935.92, zoo.getTotalWeightByCage(1), 0.001);
        assertEquals(632.725, zoo.getTotalWeightByCage(2), 0.001);
        assertEquals(882.7, zoo.getTotalWeightByCage(3), 0.001);

        assertEquals(30.0, zoo.getAverageWingSpanByCage(0), 0.001);
        assertEquals(10.846, zoo.getAverageWingSpanByCage(1), 0.001);
        assertEquals(49.687, zoo.getAverageWingSpanByCage(2), 0.001);
        assertEquals(31.0, zoo.getAverageWingSpanByCage(3), 0.001);
    }

    @Test
    void testFindAnimalsFromFile() {
        Zoo zoo = new Zoo(4);

        assertEquals(null, zoo.findAnimal("Rolo"));
        assertEquals(null, zoo.findAnimal("Hopper"));
        assertEquals(null, zoo.findAnimal("Aurora"));

        zoo.addAnimalsFromFile("data/animals.csv");

        Animal rolo = new Animal("Rolo", "Tortoise", 100, LocalDate.of(2011, 5, 14));
        Animal hopper = new Animal("Hopper", "Kangaroo", 85, LocalDate.of(2017, 5, 9));
        Bird aurora = new Bird("Aurora", "Bald Eagle", 6, LocalDate.of(2015, 12, 10), 220);

        assertEquals(rolo, zoo.findAnimal("Rolo"));
        assertEquals(hopper, zoo.findAnimal("Hopper"));
        assertEquals(aurora, zoo.findAnimal("Aurora"));
    }

    @Test
    void testAddFromFileAndManuallyWithDuplicate() {
        Zoo zoo = new Zoo(4);

        zoo.addAnimalsFromFile("data/animals.csv");

        ZooException e = assertThrows(ZooException.class, () -> {
            zoo.addAnimal(new Animal("Leo", "Cat", 4, LocalDate.of(2008, 4, 1)));
        });

        assertEquals("Zoo already contains animal!", e.getMessage());
    }
}