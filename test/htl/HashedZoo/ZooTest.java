package htl.HashedZoo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ZooTest {
    @Test
    void testCreateZooWithNegativeCageCount() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Zoo(-1);
        });

        assertEquals("Cages count must be positive!", e.getMessage());
    }

    @Test
    void testCreateZooWithZeroCageCount() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Zoo(0);
        });

        assertEquals("Cages count must be positive!", e.getMessage());
    }

    @Test
    void testEmptyZoo() {
        Zoo zoo = new Zoo(4);

        assertEquals(0, zoo.getAnimalCount());
        assertEquals(4, zoo.getCagesCount());

        assertEquals(0, zoo.getCageSize(0));
        assertEquals(0, zoo.getCageSize(1));
        assertEquals(0, zoo.getCageSize(2));
        assertEquals(0, zoo.getCageSize(3));

        assertEquals(0.0, zoo.getAverageCageSize(), 0.001);
        assertEquals(0, zoo.getLargestCageSize());

        assertEquals(0.0, zoo.getTotalWeightByCage(0), 0.001);
        assertEquals(0.0, zoo.getTotalWeightByCage(1), 0.001);
        assertEquals(0.0, zoo.getTotalWeightByCage(2), 0.001);
        assertEquals(0.0, zoo.getTotalWeightByCage(3), 0.001);

        assertEquals(0.0, zoo.getAverageWingSpanByCage(0), 0.001);
        assertEquals(0.0, zoo.getAverageWingSpanByCage(1), 0.001);
        assertEquals(0.0, zoo.getAverageWingSpanByCage(2), 0.001);
        assertEquals(0.0, zoo.getAverageWingSpanByCage(3), 0.001);
    }

    @Test
    void testAddSingleAnimalChangesStatistics() {
        Animal animal = new Animal("Leo", "Lion", 190, LocalDate.of(2015, 8, 15));

        Zoo zoo = new Zoo(4);

        assertEquals(3, zoo.addAnimal(animal));

        assertEquals(1, zoo.getAnimalCount());
        assertEquals(4, zoo.getCagesCount());

        assertEquals(0, zoo.getCageSize(0));
        assertEquals(0, zoo.getCageSize(1));
        assertEquals(0, zoo.getCageSize(2));
        assertEquals(1, zoo.getCageSize(3));

        assertEquals(0.25, zoo.getAverageCageSize(), 0.001);
        assertEquals(1, zoo.getLargestCageSize());

        assertEquals(0.0, zoo.getTotalWeightByCage(0), 0.001);
        assertEquals(0.0, zoo.getTotalWeightByCage(1), 0.001);
        assertEquals(0.0, zoo.getTotalWeightByCage(2), 0.001);
        assertEquals(190.0, zoo.getTotalWeightByCage(3), 0.001);

        assertEquals(0.0, zoo.getAverageWingSpanByCage(0), 0.001);
        assertEquals(0.0, zoo.getAverageWingSpanByCage(1), 0.001);
        assertEquals(0.0, zoo.getAverageWingSpanByCage(2), 0.001);
        assertEquals(0.0, zoo.getAverageWingSpanByCage(3), 0.001);
    }

    @Test
    void testFindSingleAnimal() {
        Animal animal = new Animal("Leo", "Lion", 190, LocalDate.of(2015, 8, 15));

        Zoo zoo = new Zoo(4);

        assertEquals(null, zoo.findAnimal("Leo"));

        assertEquals(3, zoo.addAnimal(animal));

        assertEquals(animal, zoo.findAnimal("Leo"));
    }

    @Test
    void testAddDuplicateAnimal() {
        Animal animal = new Animal("Leo", "Lion", 190, LocalDate.of(2015, 8, 15));

        Zoo zoo = new Zoo(4);

        assertEquals(3, zoo.addAnimal(animal));

        ZooException e = assertThrows(ZooException.class, () -> {
            zoo.addAnimal(animal);
        });

        assertEquals("Zoo already contains animal!", e.getMessage());

        assertEquals(1, zoo.getAnimalCount());
        assertEquals(4, zoo.getCagesCount());

        assertEquals(0, zoo.getCageSize(0));
        assertEquals(0, zoo.getCageSize(1));
        assertEquals(0, zoo.getCageSize(2));
        assertEquals(1, zoo.getCageSize(3));

        assertEquals(0.25, zoo.getAverageCageSize(), 0.001);
        assertEquals(1, zoo.getLargestCageSize());

        assertEquals(0.0, zoo.getTotalWeightByCage(0), 0.001);
        assertEquals(0.0, zoo.getTotalWeightByCage(1), 0.001);
        assertEquals(0.0, zoo.getTotalWeightByCage(2), 0.001);
        assertEquals(190.0, zoo.getTotalWeightByCage(3), 0.001);

        assertEquals(0.0, zoo.getAverageWingSpanByCage(0), 0.001);
        assertEquals(0.0, zoo.getAverageWingSpanByCage(1), 0.001);
        assertEquals(0.0, zoo.getAverageWingSpanByCage(2), 0.001);
        assertEquals(0.0, zoo.getAverageWingSpanByCage(3), 0.001);
    }

    @Test
    void testAddMultipleAnimalsChangesStatistics() {
        Animal waddles = new Bird("Waddles", "Penguin", 20, LocalDate.of(2018, 6, 30), 70);
        Animal rocky = new Animal("Rocky", "Rhinoceros", 2300, LocalDate.of(2012, 11, 11));
        Animal sammy = new Animal("Sammy", "Seal", 150, LocalDate.of(2019, 2, 23));
        Animal polly = new Animal("Polly", "Polar Bear", 400, LocalDate.of(2013, 7, 19));
        Animal quackers = new Bird("Quackers", "Duck", 1.2, LocalDate.of(2021, 7, 3), 85);
        Animal feathers = new Bird("Feathers", "Falcon", 1, LocalDate.of(2018, 3, 30), 110);
        Animal coco = new Animal("Coco", "Chimpanzee", 50, LocalDate.of(2016, 1, 22));
        Animal leo = new Animal("Leo", "Lion", 190, LocalDate.of(2015, 8, 15));

        Zoo zoo = new Zoo(5);

        assertEquals(2, zoo.addAnimal(waddles));
        assertEquals(0, zoo.addAnimal(rocky));
        assertEquals(0, zoo.addAnimal(sammy));
        assertEquals(0, zoo.addAnimal(polly));
        assertEquals(3, zoo.addAnimal(quackers));
        assertEquals(3, zoo.addAnimal(feathers));
        assertEquals(4, zoo.addAnimal(coco));
        assertEquals(3, zoo.addAnimal(leo));

        assertEquals(8, zoo.getAnimalCount());
        assertEquals(5, zoo.getCagesCount());

        assertEquals(3, zoo.getCageSize(0));
        assertEquals(0, zoo.getCageSize(1));
        assertEquals(1, zoo.getCageSize(2));
        assertEquals(3, zoo.getCageSize(3));
        assertEquals(1, zoo.getCageSize(4));

        assertEquals(1.6, zoo.getAverageCageSize(), 0.001);
        assertEquals(3, zoo.getLargestCageSize());

        assertEquals(2850.0, zoo.getTotalWeightByCage(0), 0.001);
        assertEquals(0.0, zoo.getTotalWeightByCage(1), 0.001);
        assertEquals(20.0, zoo.getTotalWeightByCage(2), 0.001);
        assertEquals(192.2, zoo.getTotalWeightByCage(3), 0.001);
        assertEquals(50.0, zoo.getTotalWeightByCage(4), 0.001);

        assertEquals(0.0, zoo.getAverageWingSpanByCage(0), 0.001);
        assertEquals(0.0, zoo.getAverageWingSpanByCage(1), 0.001);
        assertEquals(70.0, zoo.getAverageWingSpanByCage(2), 0.001);
        assertEquals(65.0, zoo.getAverageWingSpanByCage(3), 0.001);
        assertEquals(0.0, zoo.getAverageWingSpanByCage(4), 0.001);
    }

    @Test
    void testChangeAnimalWeightChangesStatistics() {
        Bird waddles = new Bird("Waddles", "Penguin", 20, LocalDate.of(2018, 6, 30), 70);
        Animal polly = new Animal("Polly", "Polar Bear", 400, LocalDate.of(2013, 7, 19));
        Bird quackers = new Bird("Quackers", "Duck", 1.2, LocalDate.of(2021, 7, 3), 85);
        Bird feathers = new Bird("Feathers", "Falcon", 1, LocalDate.of(2018, 3, 30), 110);

        Zoo zoo = new Zoo(2);

        assertEquals(1, zoo.addAnimal(waddles));
        assertEquals(1, zoo.addAnimal(polly));
        assertEquals(0, zoo.addAnimal(quackers));
        assertEquals(0, zoo.addAnimal(feathers));

        assertEquals(97.5, zoo.getAverageWingSpanByCage(0), 0.001);
        assertEquals(35.0, zoo.getAverageWingSpanByCage(1), 0.001);

        waddles.setWingSpan(65);
        quackers.setWeight(90);
        feathers.setWingSpan(120);

        assertEquals(102.5, zoo.getAverageWingSpanByCage(0), 0.001);
        assertEquals(32.5, zoo.getAverageWingSpanByCage(1), 0.001);
    }

    @Test
    void testChangeBirdWingSpanChangesStatistics() {
        Animal sammy = new Animal("Sammy", "Seal", 150, LocalDate.of(2019, 2, 23));
        Animal polly = new Animal("Polly", "Polar Bear", 400, LocalDate.of(2013, 7, 19));
        Animal quackers = new Bird("Quackers", "Duck", 1.2, LocalDate.of(2021, 7, 3), 85);

        Zoo zoo = new Zoo(5);

        assertEquals(0, zoo.addAnimal(sammy));
        assertEquals(0, zoo.addAnimal(polly));
        assertEquals(3, zoo.addAnimal(quackers));

        assertEquals(550.0, zoo.getTotalWeightByCage(0), 0.001);
        assertEquals(0.0, zoo.getTotalWeightByCage(1), 0.001);
        assertEquals(0.0, zoo.getTotalWeightByCage(2), 0.001);
        assertEquals(1.2, zoo.getTotalWeightByCage(3), 0.001);
        assertEquals(0.0, zoo.getTotalWeightByCage(4), 0.001);

        sammy.setWeight(155.0);
        quackers.setWeight(1.0);

        assertEquals(555.0, zoo.getTotalWeightByCage(0), 0.001);
        assertEquals(0.0, zoo.getTotalWeightByCage(1), 0.001);
        assertEquals(0.0, zoo.getTotalWeightByCage(2), 0.001);
        assertEquals(1.0, zoo.getTotalWeightByCage(3), 0.001);
        assertEquals(0.0, zoo.getTotalWeightByCage(4), 0.001);
    }

    @Test
    void testFindMultipleAnimals() {
        Animal waddles = new Bird("Waddles", "Penguin", 20, LocalDate.of(2018, 6, 30), 70);
        Animal rocky = new Animal("Rocky", "Rhinoceros", 2300, LocalDate.of(2012, 11, 11));
        Animal sammy = new Animal("Sammy", "Seal", 150, LocalDate.of(2019, 2, 23));
        Animal polly = new Animal("Polly", "Polar Bear", 400, LocalDate.of(2013, 7, 19));
        Animal quackers = new Bird("Quackers", "Duck", 1.2, LocalDate.of(2021, 7, 3), 85);
        Animal feathers = new Bird("Feathers", "Falcon", 1, LocalDate.of(2018, 3, 30), 110);
        Animal coco = new Animal("Coco", "Chimpanzee", 50, LocalDate.of(2016, 1, 22));
        Animal leo = new Animal("Leo", "Lion", 190, LocalDate.of(2015, 8, 15));

        Zoo zoo = new Zoo(5);

        assertEquals(null, zoo.findAnimal("Waddles"));
        assertEquals(null, zoo.findAnimal("Rocky"));
        assertEquals(null, zoo.findAnimal("Sammy"));
        assertEquals(null, zoo.findAnimal("Polly"));
        assertEquals(null, zoo.findAnimal("Quackers"));
        assertEquals(null, zoo.findAnimal("Feathers"));
        assertEquals(null, zoo.findAnimal("Coco"));
        assertEquals(null, zoo.findAnimal("Leo"));

        assertEquals(2, zoo.addAnimal(waddles));

        assertEquals(waddles, zoo.findAnimal("Waddles"));
        assertEquals(null, zoo.findAnimal("Rocky"));
        assertEquals(null, zoo.findAnimal("Sammy"));
        assertEquals(null, zoo.findAnimal("Polly"));
        assertEquals(null, zoo.findAnimal("Quackers"));
        assertEquals(null, zoo.findAnimal("Feathers"));
        assertEquals(null, zoo.findAnimal("Coco"));
        assertEquals(null, zoo.findAnimal("Leo"));

        assertEquals(0, zoo.addAnimal(rocky));

        assertEquals(waddles, zoo.findAnimal("Waddles"));
        assertEquals(rocky, zoo.findAnimal("Rocky"));
        assertEquals(null, zoo.findAnimal("Sammy"));
        assertEquals(null, zoo.findAnimal("Polly"));
        assertEquals(null, zoo.findAnimal("Quackers"));
        assertEquals(null, zoo.findAnimal("Feathers"));
        assertEquals(null, zoo.findAnimal("Coco"));
        assertEquals(null, zoo.findAnimal("Leo"));

        assertEquals(0, zoo.addAnimal(sammy));

        assertEquals(waddles, zoo.findAnimal("Waddles"));
        assertEquals(rocky, zoo.findAnimal("Rocky"));
        assertEquals(sammy, zoo.findAnimal("Sammy"));
        assertEquals(null, zoo.findAnimal("Polly"));
        assertEquals(null, zoo.findAnimal("Quackers"));
        assertEquals(null, zoo.findAnimal("Feathers"));
        assertEquals(null, zoo.findAnimal("Coco"));
        assertEquals(null, zoo.findAnimal("Leo"));

        assertEquals(0, zoo.addAnimal(polly));

        assertEquals(waddles, zoo.findAnimal("Waddles"));
        assertEquals(rocky, zoo.findAnimal("Rocky"));
        assertEquals(sammy, zoo.findAnimal("Sammy"));
        assertEquals(polly, zoo.findAnimal("Polly"));
        assertEquals(null, zoo.findAnimal("Quackers"));
        assertEquals(null, zoo.findAnimal("Feathers"));
        assertEquals(null, zoo.findAnimal("Coco"));
        assertEquals(null, zoo.findAnimal("Leo"));

        assertEquals(3, zoo.addAnimal(quackers));

        assertEquals(waddles, zoo.findAnimal("Waddles"));
        assertEquals(rocky, zoo.findAnimal("Rocky"));
        assertEquals(sammy, zoo.findAnimal("Sammy"));
        assertEquals(polly, zoo.findAnimal("Polly"));
        assertEquals(quackers, zoo.findAnimal("Quackers"));
        assertEquals(null, zoo.findAnimal("Feathers"));
        assertEquals(null, zoo.findAnimal("Coco"));
        assertEquals(null, zoo.findAnimal("Leo"));

        assertEquals(3, zoo.addAnimal(feathers));

        assertEquals(waddles, zoo.findAnimal("Waddles"));
        assertEquals(rocky, zoo.findAnimal("Rocky"));
        assertEquals(sammy, zoo.findAnimal("Sammy"));
        assertEquals(polly, zoo.findAnimal("Polly"));
        assertEquals(quackers, zoo.findAnimal("Quackers"));
        assertEquals(feathers, zoo.findAnimal("Feathers"));
        assertEquals(null, zoo.findAnimal("Coco"));
        assertEquals(null, zoo.findAnimal("Leo"));

        assertEquals(4, zoo.addAnimal(coco));

        assertEquals(waddles, zoo.findAnimal("Waddles"));
        assertEquals(rocky, zoo.findAnimal("Rocky"));
        assertEquals(sammy, zoo.findAnimal("Sammy"));
        assertEquals(polly, zoo.findAnimal("Polly"));
        assertEquals(quackers, zoo.findAnimal("Quackers"));
        assertEquals(feathers, zoo.findAnimal("Feathers"));
        assertEquals(coco, zoo.findAnimal("Coco"));
        assertEquals(null, zoo.findAnimal("Leo"));

        assertEquals(3, zoo.addAnimal(leo));

        assertEquals(waddles, zoo.findAnimal("Waddles"));
        assertEquals(rocky, zoo.findAnimal("Rocky"));
        assertEquals(sammy, zoo.findAnimal("Sammy"));
        assertEquals(polly, zoo.findAnimal("Polly"));
        assertEquals(quackers, zoo.findAnimal("Quackers"));
        assertEquals(feathers, zoo.findAnimal("Feathers"));
        assertEquals(coco, zoo.findAnimal("Coco"));
        assertEquals(leo, zoo.findAnimal("Leo"));
    }
}