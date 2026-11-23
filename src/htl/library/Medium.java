package htl.library;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class Medium implements Comparable<Medium> {
    private String catalogId;
    private String author;
    private String title;
    private boolean isBorrowed;
    private Course[] relevantCourses;

    @Override
    public int compareTo(Medium o) {
        int result =  author.compareTo(o.author);
        if(result == 0) {
            result = title.compareTo(o.title);
        }
        return result;
    }

    public Medium(String catalogId, String author, String title, boolean isBorrowed, Course[] relevantCourses) {
        setCatalogId(catalogId);
        this.author = author;
        this.title = title;
        this.isBorrowed = isBorrowed;
        this.relevantCourses = relevantCourses;
    }

    public Medium(String catalogId, String author, String title, Course[] relevantCourses) {
        this(catalogId,author,title,false,relevantCourses);
    }

    public void setCatalogId(String catalogId) {
        if(!checkIfCatalogIdIsValid(catalogId)) {
            throw new IllegalArgumentException();
        }
        this.catalogId = catalogId;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public Course[] getRelevantCourses() {
        return relevantCourses;
    }

    public boolean borrow() {
        boolean isValid = false;

        if(!isBorrowed) {
            isBorrowed = true;
            isValid = true;
        }

        return isValid;
    }

    public void giveBack() {
        if(isBorrowed) {
            isBorrowed = false;
        }else {
            throw new SchoolLibraryException("You must borrow a medium before you can return it!");
        }
    }

    public boolean isRelevantForCourse(Course course) {
        boolean isValid = false;

        for (int i = 0; i < relevantCourses.length && !isValid; i++) {
            if(course == relevantCourses[i]) {
                isValid = true;
            }
        }

        return isValid;
    }

    public static boolean checkIfCatalogIdIsValid(String catalogId) {
        boolean valid = false;
        String digits = "";
        for (int i = 3; i < catalogId.length(); i++) {
            digits += catalogId.charAt(i);
        }

        // muss mit LEO beginnen, danach genau 6 Ziffern
        if (catalogId.startsWith("LEO") && digits.length() == 6 && digits.matches("[0-9]+")) {
            int sum = 0;

            // die ersten 5 Ziffern mit 3, 4, 5, 6, 7 gewichten
            for (int i = 0; i < 5; i++) {
                int digit = digits.charAt(i) - '0';
                sum += digit * (i + 3);
            }

            int checkDigit = sum % 10;
            valid = checkDigit == digits.charAt(5) - '0';
        }
        return valid;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Medium medium = (Medium) o;
        return Objects.equals(catalogId, medium.catalogId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(catalogId);
    }

    @Override
    public String toString() {
        return "Medium{" +
                "catalogId='" + catalogId + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", isBorrowed=" + isBorrowed +
                ", relevantCourses=" + Arrays.toString(relevantCourses) +
                '}';
    }
}
