package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.PlayerException;

public class DVD extends Disc implements Playable {
	private String director;
	private int length;

	public String getDirector() {
		return director;
	}

	public int getLength() {
		return length;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public DVD(DVD dvd) throws Exception {
		super(dvd);
		this.director = dvd.getDirector();
		this.length = dvd.getLength();
		System.out.println("DVD copied successfully: " + dvd.getTitle());
	}

	public DVD(String title) {
		super(title);
		System.out.println("DVD created with title: " + title);
	}

	public DVD(String title, String category, float cost) throws Exception {
		super(title, category, cost);
		System.out.println("DVD created: Title = " + title + ", Category = " + category + ", Cost = " + cost);
	}

	public DVD(String title, String category, String director, int length, float cost) throws Exception {
		super(title, category, cost);
		this.director = director;
		this.length = length;
		System.out.println("DVD created: Title = " + title + ", Category = " + category + ", Director = " + director + ", Length = " + length + " minutes, Cost = " + cost);
	}

	public DVD(String title, String category, String director, float cost) throws Exception {
		super(title, category, cost);
		this.director = director;
		System.out.println("DVD created: Title = " + title + ", Category = " + category + ", Director = " + director + ", Cost = " + cost);
	}

	@Override
	public String toString() {
		return "Id: " + super.getId() + "\nTitle: " +
				super.getTitle() +
				"\nCategory: " + super.getCategory() + "\nDirector: " +
				this.director + "\nLength: " +
				this.length + " minutes\nCost: " +
				super.getCost() + "$";
	}

	@Override
	public boolean isMatch(String title) {
		return super.getTitle().equalsIgnoreCase(title);
	}

	public void play() throws PlayerException {
		if (this.getLength() > 0) {
			System.out.println("Now playing DVD: " + this.getTitle());
			System.out.println("Duration: " + this.getLength() + " minutes");
		} else {
			throw new PlayerException("ERROR: DVD length is non-positive.");
		}
	}
}
