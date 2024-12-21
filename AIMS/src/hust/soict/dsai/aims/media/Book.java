package hust.soict.dsai.aims.media;

import java.util.ArrayList;

public class Book extends Media {
	private ArrayList<String> authors=new ArrayList<String>();

	public Book(Book book) throws Exception {
		super(book);
	}

	public Book(String title){
		super(title);
	}

	public Book(String title, String category, float cost) throws Exception {
		super(title, category, cost);
	}

	public void addAuthor(String author) {
		if(!authors.contains(author)) authors.add(author);
	}

	public void removeAuthor(String author) throws Exception{
		if(authors.contains(author)) authors.remove(author);
		else throw new Exception("Author does not exist");
	}

	@Override
	public String toString() {
		return "Id:"+super.getId()+"\nTitle:"+super.getTitle()+
				"\nCategory:"+super.getCategory()+
				"\nCost:"+super.getCost();
	}

}