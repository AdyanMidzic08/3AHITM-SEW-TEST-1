package htl.sports;

public class Time implements Comparable<Time> {
    private static int MAXIMAL_HOURS = 23;
    private static int MAXIMAL_MINUTES = 59;
    private static int MAXIMAL_SECONDS = 59;

    private int hours;
    private int minutes;
    private int seconds;

    public Time() {
        this.hours = MAXIMAL_HOURS;
        this.minutes = MAXIMAL_MINUTES;
        this.seconds = MAXIMAL_SECONDS;
    }

    public static Time parse(String time) {
        Time result = new Time();
        String[] parts = time.split(":");

        try {
            if(parts.length == 3) {
                result.setHours(Integer.parseInt(parts[0].trim()));
                result.setMinutes(Integer.parseInt(parts[1].trim()));
                result.setSeconds(Integer.parseInt(parts[2].trim()));
            }else if(parts.length == 2) {
                result.setHours(0);
                result.setMinutes(Integer.parseInt(parts[0].trim()));
                result.setSeconds(Integer.parseInt(parts[1].trim()));
            }
        }catch (NumberFormatException e) {
            result.setHours(MAXIMAL_HOURS);
            result.setMinutes(MAXIMAL_MINUTES);
            result.setSeconds(MAXIMAL_SECONDS);
        }

        return result;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        if(hours < 0 || hours > MAXIMAL_HOURS) {
            hours = MAXIMAL_HOURS;
        }

        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        if(minutes < 0 || minutes > MAXIMAL_MINUTES) {
            minutes = MAXIMAL_MINUTES;
        }
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        if(seconds < 0 || seconds > MAXIMAL_SECONDS) {
            seconds = MAXIMAL_SECONDS;
        }
        this.seconds = seconds;
    }

    public int getTotalSeconds() {
        return ((hours * 60) * 60) + minutes * 60 + seconds;
    }

    @Override
    public int compareTo(Time o) {
        return Integer.compare(getTotalSeconds(),o.getTotalSeconds());
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d",getHours(),getMinutes(),getSeconds());
    }
}
