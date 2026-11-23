package htl.library;

import java.util.Comparator;

public class MagazineDateComparator implements Comparator<Magazine> {
    @Override
    public int compare(Magazine o1, Magazine o2) {
        int result = -Integer.compare(o1.getYear(), o2.getYear());
        if(result == 0) {
            result = -Integer.compare(o1.getMonth(),o2.getMonth());
        }
        return result;
    }
}
