package htl.library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SchoolLibrary {
    private Map<String, Medium> media;

    public SchoolLibrary() {
        media = new HashMap<>();
    }

    public boolean addMedium(Medium medium) {
        boolean hasAdded = false;

        if(!media.containsKey(medium.getCatalogId())) {
            media.put(medium.getCatalogId(), medium);
            hasAdded = true;
        }

        return hasAdded;
    }

    public void addMediaFromFile(String path, MediumFactory factory) {
        SimpleLogger logger = SimpleLogger.getInstance();

        logger.logTrace("Loading media from file " + path);

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.readLine();   // Kopfzeile überspringen
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    try {
                        Medium medium = factory.createFromString(line);
                        if (!addMedium(medium)) {
                            logger.logError("Duplicate catalog id, not added: " + line);
                        }
                    } catch (SchoolLibraryException | IllegalArgumentException e) {
                        logger.logError("Invalid line skipped: " + line + " (" + e.getMessage() + ")");
                    }
                }
            }
        } catch (IOException e) {
            logger.logError("Could not load file " + path);
            throw new SchoolLibraryException("Exception occured while loading file.", e);
        }
    }

    public int getMediaCount() {
        return media.size();
    }

    public Medium getMediumByCatalogId(String catalog) {
        return media.get(catalog);
    }

    public List<Medium> getAvailableMedia() {
        List<Medium> availableMedia = new LinkedList<>();
        for (Medium medium: media.values()) {
            if(!medium.isBorrowed()) {
                availableMedia.add(medium);
            }
        }

        Collections.sort(availableMedia);
        return availableMedia;
    }

    public List<Medium> getRelevantCourseMedia(Course course) {
        List<Medium> relevantCourseMedia = new LinkedList<>();

        for(Medium medium: media.values()) {
            for (int i = 0; i < medium.getRelevantCourses().length; i++) {
                if(medium.getRelevantCourses()[i] == course) {
                    relevantCourseMedia.add(medium);
                }
            }
        }
        Collections.sort(relevantCourseMedia);
        return relevantCourseMedia;
    }

    public List<Magazine> getNewestAvailableMagazines() {
        List<Magazine> getNewestMagazine = new LinkedList<>();

        for(Medium medium: media.values()) {
            if(medium instanceof Magazine magazines) {
                if(!medium.isBorrowed()) {
                    getNewestMagazine.add(magazines);
                }
            }
        }
        Collections.sort(getNewestMagazine, new MagazineDateComparator());
        return getNewestMagazine;
    }
}
