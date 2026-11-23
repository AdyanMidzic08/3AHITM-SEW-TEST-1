package htl.library;

public class MagazineFactory implements MediumFactory {
    @Override
    public Medium createFromString(String medium) {
        String[] parts = medium.split(";");
        if(parts.length != 6) {
            throw new SchoolLibraryException("Invalid line length!");
        }
        try {
            String catalogId = parts[0].trim();
            int year = Integer.parseInt(parts[1].trim());
            int month = Integer.parseInt(parts[2].trim());
            String author = parts[3].trim();
            String title = parts[4].trim();
            String[] courseParts = parts[5].split(",");
            Course[] courses = new Course[courseParts.length];

            for (int i = 0; i < courseParts.length; i++) {
                courses[i] = Course.valueOf(courseParts[i].trim());
            }

            return new Magazine(catalogId,author,title,false,courses,month,year);

        }catch (IllegalArgumentException e) {
            throw new SchoolLibraryException("Execotion occured while parsing line", e);
        }
    }
}
