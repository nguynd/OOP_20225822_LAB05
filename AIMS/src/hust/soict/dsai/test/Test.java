package hust.soict.dsai.test;

import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CD;
import hust.soict.dsai.aims.media.DVD;
import hust.soict.dsai.aims.media.Media;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Media>mediae=new ArrayList<Media>();

        Book book= null;
        try {
            book = new Book("titanic","romantic",100);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        DVD dvd= null;
        try {
            dvd = new DVD("tom and jerry","cartoon","habera",50,200);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        CD cd= null;
        try {
            cd = new CD("attack on titan","action",500);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        mediae.add(book);
        mediae.add(dvd);
        mediae.add(cd);

        for (Media media : mediae) {
            System.out.println(media.toString()+"\n");
        }


    }
}