package htl.sports;

import java.util.Objects;

public class Athlete implements Comparable<Athlete> {
    private String raceNumber;
    private String name;
    private Time time;

    public Athlete(String raceNumber, String name, Time time) {
        this.raceNumber = raceNumber;
        this.name = name;
        this.time = time;
    }

    public String getRaceNumber() {
        return raceNumber;
    }

    public String getName() {
        return name;
    }

    public Time getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Athlete athlete = (Athlete) o;
        return Objects.equals(raceNumber, athlete.raceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(raceNumber);
    }

    @Override
    public int compareTo(Athlete o) {
        int result = 0;

        if(raceNumber.equals(o.raceNumber)) {
            result = 0;
        }else if(getTime().getTotalSeconds() == o.getTime().getTotalSeconds()) {
            result = Integer.compare(Integer.parseInt(getRaceNumber()) ,Integer.parseInt(o.getRaceNumber()));
        }else {
            result = Integer.compare(getTime().getTotalSeconds(), o.getTime().getTotalSeconds());
        }

        return result;
    }

    @Override
    public String toString() {
        return String.format("#%s %s: %s",raceNumber,time,name);
    }
}
