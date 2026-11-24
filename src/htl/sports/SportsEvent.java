package htl.sports;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SportsEvent {
    private LinkedList<Athlete> athletes;
    private String name;

    public SportsEvent(String name) {
        this.name = name;
        athletes = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void addAthlete(Athlete athlete) {
        if(athletes.contains(athlete)) {
            // gleiche Startnummer ist schon drin (equals in Athlete vergleicht die Startnummer)
            throw new SportsEventException("Athlete #" + athlete.getRaceNumber() + " already crossed the finish line!");
        }
        athletes.add(athlete);
    }

    public int getParticipantCount() {
        return athletes.size();
    }

    public List<Athlete> getTopAthletes(int number) {
        // ohne Comparator -> verwendet compareTo aus Athlete bzw. Triathlete
        Collections.sort(athletes);
        List<Athlete> topAthletes = new LinkedList<>();

        if(number > athletes.size()) {
            number = athletes.size();
        }

        for (int i = 0; i < number; i++) {
            topAthletes.add(athletes.get(i));
        }
        
        return topAthletes;
    }

    public void readFromFile(String path) {
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.readLine();
            String line;

            while((line = reader.readLine()) != null) {
                if(!line.isBlank()) {
                    Athlete athlete = AthleteFactory.createFromString(line);
                    addAthlete(athlete);
                }
            }
        }catch (IOException e) {
            throw new SportsEventException("Exception occurred while reading the file!", e);
        }
    }

    public Athlete getAthleteByRaceNumber(String raceNumber) {
        boolean hasFound = false;
        Athlete foundAthlete = null;

        for (Athlete athlete : athletes) {
            if(!hasFound) {
                if(athlete.getRaceNumber().equals(raceNumber)) {
                    foundAthlete = athlete;
                    hasFound = true;
                }
            }
        }
        return foundAthlete;
    }
}
