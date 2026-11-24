package htl.sports;

public class AthleteFactory {
    public static Athlete createFromString(String line) {
        Athlete athlete = null;
        try {
            String[] parts = line.split(",");

            if(parts.length == 4) {
                String raceNumber = parts[0].trim();
                String name = parts[1].trim();
                Time time = Time.parse(parts[3].trim());

                 athlete = new Athlete(raceNumber,name,time);
            }else if(parts.length == 5) {
                String raceNumber = parts[0].trim();
                String name = parts[1].trim();
                Time time = Time.parse(parts[3].trim());
                TriathlonDiscipline triathlonDiscipline = TriathlonDiscipline.valueOf(parts[4].trim());

                athlete = new Triathlete(raceNumber,name,time,triathlonDiscipline);
            }else {
                throw new SportsEventException("Exception occured while parsing line!");
            }
        }catch (Exception e) {
            throw new SportsEventException("Exception occured while parsing line!",e);
        }

        return athlete;
    }
}
