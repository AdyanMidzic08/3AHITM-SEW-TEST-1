package htl.sports;

import jdk.jshell.spi.ExecutionControlProvider;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SportsEventTest {
    @Test
    void testConstructor() {
        SportsEvent cityNightRun = new SportsEvent("10. Linzer Sparkasse City Night Run");
        assertEquals("10. Linzer Sparkasse City Night Run", cityNightRun.getName());
        assertEquals(0, cityNightRun.getParticipantCount());
    }

    @Test
    void testAddAthlete() {
        SportsEvent cityNightRun = new SportsEvent("10. Linzer Sparkasse City Night Run");

        assertEquals(0, cityNightRun.getParticipantCount());

        Athlete athlete = AthleteFactory.createFromString("646,Kienberger Michael Mag.,FHOÖ Sportsteam,0:19:06");
        cityNightRun.addAthlete(athlete);

        assertEquals(1, cityNightRun.getParticipantCount());

        athlete = AthleteFactory.createFromString("1056,Herndler Sigrid,laufend-besser.at,0:19:02");
        cityNightRun.addAthlete(athlete);

        assertEquals(2, cityNightRun.getParticipantCount());

        try {
            athlete = AthleteFactory.createFromString("646,Kienberger Michael Mag.,FHOÖ Sportsteam,0:19:06");
            cityNightRun.addAthlete(athlete);
        }
        catch(Exception e) {
            //do nothing here - exceptions are covered in different test case
        }

        assertEquals(2, cityNightRun.getParticipantCount());

        athlete = AthleteFactory.createFromString("637,Jung Manuel,2radchaoten.com,0:18:35");
        cityNightRun.addAthlete(athlete);

        assertEquals(3, cityNightRun.getParticipantCount());

        try {
            athlete = AthleteFactory.createFromString("1056,Herndler Sigrid,laufend-besser.at,0:19:02");
            cityNightRun.addAthlete(athlete);
        }
        catch(Exception e) {
            //do nothing here - exceptions are covered in different test case
        }

        assertEquals(3, cityNightRun.getParticipantCount());

        athlete = AthleteFactory.createFromString("389,Exel Dominik,Ried In Traunkreis,0:17:51");
        cityNightRun.addAthlete(athlete);

        assertEquals(4, cityNightRun.getParticipantCount());
    }

    @Test
    void testAddDuplicateAthlete() {
        SportsEvent sportsEvent = new SportsEvent("Ironman Leonding");

        assertEquals(0, sportsEvent.getParticipantCount());

        Athlete athlete = AthleteFactory.createFromString("646,Kienberger Michael Mag.,FHOÖ Sportsteam,0:19:06");
        sportsEvent.addAthlete(athlete);

        assertEquals(1, sportsEvent.getParticipantCount());

        SportsEventException e = assertThrows(SportsEventException.class, () -> {
            Athlete duplicate = AthleteFactory.createFromString("646,Kienberger Michael Mag.,FHOÖ Sportsteam,0:19:06");
            sportsEvent.addAthlete(duplicate);
        });

        assertEquals("Athlete #646 already crossed the finish line!", e.getMessage());

        assertEquals(1, sportsEvent.getParticipantCount());

        Triathlete triaKiefer = new Triathlete("24S", "Chantal Kiefer", Time.parse("21:26"), TriathlonDiscipline.SWIM);
        sportsEvent.addAthlete(triaKiefer);

        assertEquals(2, sportsEvent.getParticipantCount());

        e = assertThrows(SportsEventException.class, () -> {
            Triathlete triaKieferMeasureError = new Triathlete("24S", "Kiefer Chantal", Time.parse("21:29"), TriathlonDiscipline.SWIM);
            sportsEvent.addAthlete(triaKieferMeasureError);
        });

        assertEquals("Athlete #24S already crossed the finish line!", e.getMessage());

        assertEquals(2, sportsEvent.getParticipantCount());
    }

    @Test
    void testReadFromFile() {
        SportsEvent cityNightRun = new SportsEvent("10. Linzer Sparkasse City Night Run");
        assertEquals(0, cityNightRun.getParticipantCount());
        cityNightRun.readFromFile("data/linz_night_run.csv");
        assertEquals(51, cityNightRun.getParticipantCount());

        SportsEvent linzTriathlon = new SportsEvent("12. Linz Triathlon");
        assertEquals(0, linzTriathlon.getParticipantCount());
        linzTriathlon.readFromFile("data/linz_tria_complete.csv");
        assertEquals(59, linzTriathlon.getParticipantCount());
    }

    @Test
    void testReadFromMissingFile() {
        SportsEventException e = assertThrows(SportsEventException.class, () -> {
            SportsEvent leondingIronman = new SportsEvent("Leondinger Ironman");
            leondingIronman.readFromFile("data/ironman_leonding.csv");
        });

        assertEquals("Exception occurred while reading the file!", e.getMessage());
        assertEquals(true, e.getCause() instanceof IOException);
    }

    @Test
    void testReadFromFileWithDuplicates() {
        SportsEventException e = assertThrows(SportsEventException.class, () -> {
            SportsEvent leondingIronman = new SportsEvent("12. Linz Triathlon");
            leondingIronman.readFromFile("data/linz_tria_complete_with_dupes.csv");
        });
    }

    @Test
    void testGetTopAthletesDummyData() {
        SportsEvent leondingIronman = new SportsEvent("Leondinger Ironman");

        List<Athlete> topAthletes = leondingIronman.getTopAthletes(10);
        assertEquals(0, topAthletes.size());

        Athlete athleteKienberger = AthleteFactory.createFromString("646,Kienberger Michael Mag.,FHOÖ Sportsteam,0:19:06");
        leondingIronman.addAthlete(athleteKienberger);

        topAthletes = leondingIronman.getTopAthletes(10);
        assertEquals(1, topAthletes.size());
        assertEquals(athleteKienberger, topAthletes.get(0));

        Athlete athleteJung = AthleteFactory.createFromString("637,Jung Manuel,2radchaoten.com,0:18:35");
        leondingIronman.addAthlete(athleteJung);

        topAthletes = leondingIronman.getTopAthletes(10);
        assertEquals(2, topAthletes.size());
        assertEquals(athleteJung, topAthletes.get(0));
        assertEquals(athleteKienberger, topAthletes.get(1));

        Athlete athleteExel = AthleteFactory.createFromString("389,Exel Dominik,Ried In Traunkreis,0:17:51");
        leondingIronman.addAthlete(athleteExel);

        topAthletes = leondingIronman.getTopAthletes(3);
        assertEquals(3, topAthletes.size());
        assertEquals(athleteExel, topAthletes.get(0));
        assertEquals(athleteJung, topAthletes.get(1));
        assertEquals(athleteKienberger, topAthletes.get(2));

        Athlete athleteFegerl = AthleteFactory.createFromString("583,Fegerl Michael,Tri Team 1. USC Traun,0:19:14");
        leondingIronman.addAthlete(athleteFegerl);

        topAthletes = leondingIronman.getTopAthletes(3);
        assertEquals(3, topAthletes.size());
        assertEquals(athleteExel, topAthletes.get(0));
        assertEquals(athleteJung, topAthletes.get(1));
        assertEquals(athleteKienberger, topAthletes.get(2));

        topAthletes = leondingIronman.getTopAthletes(50);
        assertEquals(4, topAthletes.size());
        assertEquals(athleteExel, topAthletes.get(0));
        assertEquals(athleteJung, topAthletes.get(1));
        assertEquals(athleteKienberger, topAthletes.get(2));
        assertEquals(athleteFegerl, topAthletes.get(3));
    }

    @Test
    void testGetTopAthletesTriaData() {
        SportsEvent linzTriathlon = new SportsEvent("12. Linz Triathlon");

        assertEquals(0, linzTriathlon.getParticipantCount());
        List<Athlete> topAthletes = linzTriathlon.getTopAthletes(10);
        assertEquals(0, topAthletes.size());

        linzTriathlon.readFromFile("data/linz_tria_complete.csv");
        assertEquals(59, linzTriathlon.getParticipantCount());

        topAthletes = linzTriathlon.getTopAthletes(20);
        assertEquals(20, topAthletes.size());

        assertEquals("SWIM #34S 00:11:59: Christoph Prem", topAthletes.get(0).toString());
        assertEquals("SWIM #33S 00:14:52: Thomas Skarzynski", topAthletes.get(2).toString());
        assertEquals("SWIM #37S 00:15:43: Fabian Hornaus", topAthletes.get(5).toString());
        assertEquals("SWIM #23S 00:17:11: Joanna Planke", topAthletes.get(8).toString());
        assertEquals("SWIM #39S 00:18:33: Stefan Enzenebner", topAthletes.get(11).toString());
        assertEquals("SWIM #21S 00:20:22: Michael Hausmann", topAthletes.get(14).toString());
        assertEquals("SWIM #30S 00:21:15: Anne Weber", topAthletes.get(17).toString());
        assertEquals("SWIM #13S 23:59:59: Carlo Pedersoli", topAthletes.get(19).toString());

        topAthletes = linzTriathlon.getTopAthletes(100);
        assertEquals(59, topAthletes.size());

        assertEquals("SWIM #28S 00:13:42: Peter Pötsch", topAthletes.get(1).toString());
        assertEquals("SWIM #2S 00:14:57: Julia Schaffelhofer", topAthletes.get(4).toString());
        assertEquals("SWIM #35S 00:17:08: Erwin Schwarzbauer", topAthletes.get(7).toString());
        assertEquals("SWIM #31S 00:17:41: Jaqueline Forster", topAthletes.get(10).toString());
        assertEquals("SWIM #26S 00:19:02: Daniela Weber", topAthletes.get(13).toString());
        assertEquals("SWIM #32S 00:21:06: Stefan Jäger", topAthletes.get(16).toString());
        assertEquals("SWIM #13S 23:59:59: Carlo Pedersoli", topAthletes.get(19).toString());
        assertEquals("BIKE #2B 01:12:39: Andreas Madlmair", topAthletes.get(22).toString());
        assertEquals("BIKE #37B 01:18:30: Hubert Lehenbauer", topAthletes.get(25).toString());
        assertEquals("BIKE #39B 01:24:23: Alexander Tohati", topAthletes.get(28).toString());
        assertEquals("BIKE #35B 01:26:28: Herta Mair", topAthletes.get(31).toString());
        assertEquals("BIKE #32B 01:29:41: Klaus Schönberger", topAthletes.get(34).toString());
        assertEquals("BIKE #1B 01:39:09: Markus Durstberger", topAthletes.get(37).toString());
        assertEquals("RUN #34R 00:40:03: Stefan Füreder", topAthletes.get(40).toString());
        assertEquals("RUN #28R 00:46:58: Joseph Honeder", topAthletes.get(43).toString());
        assertEquals("RUN #25R 00:47:50: Werner Wetzlinger", topAthletes.get(46).toString());
        assertEquals("RUN #32R 00:52:32: Lukas Koblmüller", topAthletes.get(49).toString());
        assertEquals("RUN #1R 00:56:22: Georg Durstberger", topAthletes.get(52).toString());
        assertEquals("RUN #31R 00:58:12: Angelika Gottinger", topAthletes.get(55).toString());
        assertEquals("RUN #30R 01:13:44: Michaela Endmayr-Weber", topAthletes.get(58).toString());
    }

    @Test
    void testGetByRaceNumber() {
        SportsEvent cityNightRun = new SportsEvent("10. Linzer Sparkasse City Night Run");
        assertEquals(null, cityNightRun.getAthleteByRaceNumber("123"));
        cityNightRun.readFromFile("data/linz_night_run.csv");

        assertEquals("#659 00:17:52: Böhm Florian", cityNightRun.getAthleteByRaceNumber("659").toString());
        assertEquals(null, cityNightRun.getAthleteByRaceNumber("423"));
        assertEquals("#628 00:17:07: Graschitz Daniel LL.M.(WU)", cityNightRun.getAthleteByRaceNumber("628").toString());
        assertEquals(null, cityNightRun.getAthleteByRaceNumber("799"));
        assertEquals("#383 00:19:16: Dutzler Markus", cityNightRun.getAthleteByRaceNumber("383").toString());
        assertEquals(null, cityNightRun.getAthleteByRaceNumber("25R"));
        assertEquals("#759 00:18:24: Ahammer Daniel", cityNightRun.getAthleteByRaceNumber("759").toString());
        assertEquals(null, cityNightRun.getAthleteByRaceNumber("PROO"));
        assertEquals("#1039 00:17:55: Kaufmann Lukas", cityNightRun.getAthleteByRaceNumber("1039").toString());
        assertEquals(null, cityNightRun.getAthleteByRaceNumber("2344"));
        assertEquals("#1053 00:18:15: Schuster Bernadette", cityNightRun.getAthleteByRaceNumber("1053").toString());
    }
}