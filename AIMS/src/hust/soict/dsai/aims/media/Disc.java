package hust.soict.dsai.aims.media;

public class Disc extends Media {
    private String director;
    private int length;

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    public Disc(Disc dsc) throws Exception {
        super(dsc);
        this.director = dsc.director;
        this.length = dsc.length;
        System.out.println("Disc duplicated successfully: " + dsc.getTitle());
    }

    public Disc(String title) {
        super(title);
        System.out.println("Disc created with title: " + title);
    }

    public Disc(String title, String category, float cost) throws Exception {
        super(title, category, cost);
        System.out.println("Disc created: Title = " + title + ", Category = " + category + ", Cost = " + cost);
    }
}
