package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.PlayerException;

public class Track implements Playable {
	private String title;
	private int length;

	public String getTitle() {
		return title;
	}

	public int getLength() {
		return length;
	}

	public Track(String title, int length) {
		this.title = title;
		this.length = length;
		System.out.println("Track created: Title = " + title + ", Length = " + length);
	}

	public void play() throws PlayerException {
		if (this.getLength() > 0) {
			System.out.println("Now playing track: " + this.getTitle());
			System.out.println("Track length: " + this.getLength() + " minutes");
		} else {
			throw new PlayerException("Error: Track length must be greater than zero.");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Track track = (Track) o;
		return this.getLength() == track.getLength() && this.getTitle().equals(track.getTitle());
	}

	@Override
	public String toString() {
		return "Track [Title=" + title + ", Length=" + length + " minutes]";
	}
}
