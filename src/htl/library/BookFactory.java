package htl.library;
public class BookFactory implements MediumFactory {
    @Override
    public Medium createFromString(String medium) {
        String[] parts = medium.split(";");
        if (parts.length != 5) {
            throw new SchoolLibraryException("Invalid line length!");
        }

        try {
            String catalogId = parts[0].trim();
            String author = parts[1].trim();
            String title = parts[2].trim();
            String[] courseParts = parts[3].split(",");
            Course[] courses = new Course[courseParts.length];

            for (int i = 0; i < courseParts.length; i++) {
                courses[i] = Course.valueOf(courseParts[i].trim());
            }

            int pageCount = Integer.parseInt(parts[4].trim());

            return new Book(catalogId, author, title, false, courses, pageCount);
        } catch (IllegalArgumentException e) {
            throw new SchoolLibraryException("Exception occured while parsing line!", e);
        }
    }
}
