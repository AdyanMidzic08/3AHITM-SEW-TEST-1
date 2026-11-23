package htl.library;

public class Magazine extends Medium {
    private int month;
    private int year;

    public Magazine(String catalogId, String author, String title, boolean isBorrowed, Course[] relevantCourses, int month, int year) {
        super(catalogId, author, title, isBorrowed, relevantCourses);
        this.month = month;
        this.year = year;
    }

    public Magazine(String catalogId, String author, String title, Course[] relevantCourses, int year, int month) {
        super(catalogId, author, title, relevantCourses);
        this.year = year;
        setMonth(month);
    }


    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    private void setMonth(int month) {
        if(month <= 0 || month >= 13) {
            throw new IllegalArgumentException();
        }
        this.month = month;
    }

    private void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("%s %s - %s (%d/%d)",getCatalogId(),getAuthor(),getTitle(),year,month);
    }
}
