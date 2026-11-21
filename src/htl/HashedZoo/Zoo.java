package htl.HashedZoo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Zoo {
    private final AnimalNode[] cages;
    private int animalCount;

    public Zoo(int cagesCount) {
        if (cagesCount <= 0) {
            throw new IllegalArgumentException("Cages count must be positive!");
        }
        cages = new AnimalNode[cagesCount];
    }

    private int getCageIndex(Animal animal) {
        return animal.hashCode() % cages.length;
    }

    public int addAnimal(Animal animal) {
        if (findAnimal(animal.getName()) != null) {
            throw new ZooException("Zoo already contains animal!");
        }

        int index = getCageIndex(animal);
        AnimalNode newNode = new AnimalNode(animal);

        if (cages[index] == null) {
            cages[index] = newNode;
        } else {
            AnimalNode current = cages[index];
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }

        animalCount++;
        return index;
    }

    public Animal findAnimal(String name) {
        // hashCode() von Animal = Länge des Namens
        int index = name.length() % cages.length;

        AnimalNode current = cages[index];
        while (current != null) {
            if (current.getValue().getName().equals(name)) {
                return current.getValue();
            }
            current = current.getNext();
        }
        return null;
    }

    public void addAnimalsFromFile(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.readLine(); // Kopfzeile überspringen
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    addAnimal(AnimalFactory.createFromString(line));
                }
            }
        } catch (IOException e) {
            throw new ZooException("Exception occurred while loading animals from file!", e);
        }
    }

    public int getAnimalCount() {
        return animalCount;
    }

    public int getCagesCount() {
        return cages.length;
    }

    public int getCageSize(int cage) {
        int size = 0;
        AnimalNode current = cages[cage];
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }

    public double getAverageCageSize() {
        return (double) animalCount / cages.length;
    }

    public int getLargestCageSize() {
        int largest = 0;
        for (int i = 0; i < cages.length; i++) {
            largest = Math.max(largest, getCageSize(i));
        }
        return largest;
    }

    public double getTotalWeightByCage(int cage) {
        double total = 0;
        AnimalNode current = cages[cage];
        while (current != null) {
            total += current.getValue().getWeight();
            current = current.getNext();
        }
        return total;
    }

    public double getAverageWingSpanByCage(int cage) {
        // Durchschnitt über alle Tiere im Käfig, Nicht-Vögel zählen mit 0
        int size = getCageSize(cage);
        if (size == 0) {
            return 0;
        }

        int totalWingSpan = 0;
        AnimalNode current = cages[cage];
        while (current != null) {
            if (current.getValue() instanceof Bird bird) {
                totalWingSpan += bird.getWingSpan();
            }
            current = current.getNext();
        }
        return (double) totalWingSpan / size;
    }
}
