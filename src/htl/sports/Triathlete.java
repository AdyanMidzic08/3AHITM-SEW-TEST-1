package htl.sports;

public class Triathlete extends Athlete {
    private TriathlonDiscipline discipline;

    public Triathlete(String raceNumber, String name, Time time,TriathlonDiscipline discipline) {
        super(raceNumber, name, time);
        this.discipline = discipline;
    }

    public TriathlonDiscipline getDiscipline() {
        return discipline;
    }

    @Override
    public int compareTo(Athlete o) {
        int result = 0;

        // nur wenn der andere auch ein Triathlet ist: zuerst nach Disziplin (SWIM < BIKE < RUN)
        if (o instanceof Triathlete) {
            Triathlete other = (Triathlete) o;
            result = discipline.compareTo(other.getDiscipline());
        }

        // gleiche Disziplin oder normaler Athlet -> normaler Vergleich aus Athlete
        if (result == 0) {
            result = super.compareTo(o);
        }

        return result;
    }

    @Override
    public String toString() {
        return discipline + " " + super.toString();
    }
}
