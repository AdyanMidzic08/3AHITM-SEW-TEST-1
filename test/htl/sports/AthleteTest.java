package htl.sports;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AthleteTest {
    @Test
    void testInheritance() {
        Time marathonWr = Time.parse("2:01:39");
        Athlete bestMarathonRunner = new Athlete("4", "Eliud Kipchoge", marathonWr);

        assertEquals(true, bestMarathonRunner instanceof Comparable<Athlete>);
    }

    @Test
    void testConstructorAndGetters() {
        Time marathonWr = Time.parse("2:01:39");
        Athlete bestMarathonRunner = new Athlete("4", "Eliud Kipchoge", marathonWr);

        assertEquals("4", bestMarathonRunner.getRaceNumber());
        assertEquals("Eliud Kipchoge", bestMarathonRunner.getName());
        assertEquals("02:01:39", bestMarathonRunner.getTime().toString());

        Time medley400Wr = Time.parse("4:03");
        Athlete fastestSwimmer = new Athlete("420", "Michael Phelps", medley400Wr);

        assertEquals("420", fastestSwimmer.getRaceNumber());
        assertEquals("Michael Phelps", fastestSwimmer.getName());
        assertEquals("00:04:03", fastestSwimmer.getTime().toString());

        Athlete disqualifiedRunner = new Athlete("13", "Oscar Pistorius", Time.parse("Disqualified..."));

        assertEquals("13", disqualifiedRunner.getRaceNumber());
        assertEquals("Oscar Pistorius", disqualifiedRunner.getName());
        assertEquals("23:59:59", disqualifiedRunner.getTime().toString());
    }

    @Test
    void testToString() {
        Time marathonWr = Time.parse("2:01:39");
        Athlete bestMarathonRunner = new Athlete("4", "Eliud Kipchoge", marathonWr);
        assertEquals("#4 02:01:39: Eliud Kipchoge", bestMarathonRunner.toString());

        Time medley400Wr = Time.parse("4:03");
        Athlete fastestSwimmer = new Athlete("420", "Michael Phelps", medley400Wr);
        assertEquals("#420 00:04:03: Michael Phelps", fastestSwimmer.toString());
    }

    @Test
    void testEquals() {
        Athlete athlete = new Athlete("13", "Jane Doe", Time.parse("12:34"));
        Athlete sameAthlete = new Athlete("13", "John Doe", Time.parse("12:13:14"));
        Athlete differentAthlete = new Athlete("7", "John Doe", Time.parse("12:34"));

        assertEquals(athlete, sameAthlete);
        assertEquals(sameAthlete, athlete);
        assertNotEquals(athlete, differentAthlete);
        assertNotEquals(differentAthlete, athlete);
        assertNotEquals(sameAthlete, differentAthlete);
        assertNotEquals(differentAthlete, sameAthlete);
    }

    @Test
    void testComparable() {
        Athlete fastAthlete = new Athlete("123", "Jane Doe", Time.parse("1:23:45"));
        Athlete midAthlete = new Athlete("77", "John Doe", Time.parse("1:45:07"));
        Athlete alsoMidAthlete = new Athlete("97", "Max Mustermann", Time.parse("1:45:07"));
        Athlete slowAthlete = new Athlete("101", "Piotr Kowalski", Time.parse("1:59:23"));

        assertEquals(true, 0 == fastAthlete.compareTo(new Athlete("123", "Marge Simpson", Time.parse("11:22:33")))); //same race number
        assertEquals(true, 0 > fastAthlete.compareTo(midAthlete));
        assertEquals(true, 0 > fastAthlete.compareTo(alsoMidAthlete));
        assertEquals(true, 0 > fastAthlete.compareTo(slowAthlete));

        assertEquals(true, 0 == midAthlete.compareTo(new Athlete("77", "Ned Flanders", Time.parse("22:33:11")))); //same race number
        assertEquals(true, 0 < midAthlete.compareTo(fastAthlete));
        assertEquals(true, 0 > midAthlete.compareTo(alsoMidAthlete)); //lower race number!
        assertEquals(true, 0 > midAthlete.compareTo(slowAthlete));

        assertEquals(true, 0 == alsoMidAthlete.compareTo(new Athlete("97", "Seymour Skinner", Time.parse("11:33:22")))); //same race number
        assertEquals(true, 0 < alsoMidAthlete.compareTo(fastAthlete));
        assertEquals(true, 0 < alsoMidAthlete.compareTo(midAthlete)); //higher race number!
        assertEquals(true, 0 > alsoMidAthlete.compareTo(slowAthlete));

        assertEquals(true, 0 == slowAthlete.compareTo(new Athlete("101", "Homer Simpson", Time.parse("13:31:13")))); //same race number
        assertEquals(true, 0 < slowAthlete.compareTo(fastAthlete));
        assertEquals(true, 0 < slowAthlete.compareTo(midAthlete));
        assertEquals(true, 0 < slowAthlete.compareTo(alsoMidAthlete));
    }
}