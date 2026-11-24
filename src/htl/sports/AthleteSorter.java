package htl.sports;

import java.util.Comparator;

public class AthleteSorter implements Comparator<Athlete> {
    @Override
    public int compare(Athlete o1, Athlete o2) {
        return Integer.compare(o1.getTime().getTotalSeconds(),o2.getTime().getTotalSeconds());
    }
}
