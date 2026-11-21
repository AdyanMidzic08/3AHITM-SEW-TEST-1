package htl.HashedZoo;

import java.time.LocalDate;

public class Bird extends Animal {

    private int wingSpan;

    public Bird(String name, String race, double weight, LocalDate birthday, int wingSpan) {
        super(name, race, weight, birthday);
        setWingSpan(wingSpan);
    }

    public int getWingSpan() {
        return wingSpan;
    }

    public void setWingSpan(int wingSpan) {
        if(wingSpan <= 0) {
            throw new IllegalArgumentException("Wing span must be positive!");
        }
        this.wingSpan = wingSpan;
    }

    @Override
    public String toString() {
        return String.format("%s With a wing span of %d cm I can fly! Chirp chirp!",super.toString(),wingSpan);
    }
}
