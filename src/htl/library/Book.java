package htl.library;

public class Book extends Medium {
    private int pageCount;

    public Book(String catalogId, String author, String title, boolean isBorrowed, Course[] relevantCourses, int pageCount) {
        super(catalogId, author, title, isBorrowed, relevantCourses);
        this.pageCount = pageCount;
    }

    public Book(String catalogId, String author, String title, Course[] relevantCourses, int pageCount) {
        super(catalogId, author, title, relevantCourses);
        setPageCount(pageCount);
    }

    public int getPageCount() {
        return pageCount;
    }

    private void setPageCount(int pageCount) {
        if(pageCount <= 0) {
            throw new IllegalArgumentException();
        }
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return String.format("%s %s - %s (%d pages)",getCatalogId(),getAuthor(),getTitle(),pageCount);
    }
}
