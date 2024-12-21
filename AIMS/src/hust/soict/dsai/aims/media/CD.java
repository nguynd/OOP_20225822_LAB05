package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.PlayerException;

import java.util.ArrayList;

public class CD extends Disc implements Playable {
	private String artist;
	private ArrayList<Track> tracks = new ArrayList<>();

	public String getArtist() {
		return artist;
	}

	public int getLength() {
		int totalLength = 0;
		for (Track track : tracks) {
			totalLength += track.getLength();
		}
		return totalLength;
	}

	public CD(CD dsc) throws Exception {
		super(dsc);
	}

	public CD(String title) {
		super(title);
	}

	public CD(String title, String category, float cost) throws Exception {
		super(title, category, cost);
	}

	public void addTrack(Track track) {
		if (!tracks.contains(track)) {
			tracks.add(track);
			System.out.println("Track " + track.getTitle() + " has been added.");
		} else {
			System.out.println("Track " + track.getTitle() + " already exists in the CD.");
		}
	}

	public void removeTrack(Track track) throws Exception {
		if (tracks.contains(track)) {
			tracks.remove(track);
			System.out.println("Track " + track.getTitle() + " has been removed.");
		} else {
			throw new Exception("ERROR: Track " + track.getTitle() + " does not exist in the CD.");
		}
	}

	public void play() throws PlayerException {
		if (this.getLength() > 0) {
			System.out.println("Now Playing CD...");
			System.out.println("ID: " + getId());
			System.out.println("Title: " + getTitle());
			System.out.println("Category: " + getCategory());
			System.out.println("Artist: " + getArtist());
			System.out.println("Length: " + getLength());
			System.out.println("Cost: " + getCost());

			for (Track track : tracks) {
				track.play();
			}
		} else {
			throw new PlayerException("ERROR: The CD has no tracks to play.");
		}
	}

	@Override
	public String toString() {
		return "ID: " + super.getId() +
				"\nTitle: " + super.getTitle() +
				"\nCategory: " + super.getCategory() +
				"\nArtist: " + getArtist() +
				"\nLength: " + getLength() +
				"\nCost: " + super.getCost();
	}
}
