package hust.soict.dsai.aims.media;


import java.util.Comparator;
import java.util.Objects;

public abstract class Media {
	private int id;
	private String title;
	private String category;
	private float cost;
	private static int nbMedia = 0;
	public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
	public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

	public int getId() {
		nbMedia++;
		this.id = nbMedia;
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost)throws Exception {
		if(cost >=0 ) this.cost = cost;
		else throw new Exception("cost should be a positive number");
	}

	public Media(Media media) throws Exception {
		if(cost>=0){
			this.title = media.getTitle();
			nbMedia++;
			this.id =nbMedia;
			this.category = media.getCategory();
			this.cost = media.getCost();
		}else {
			throw new Exception("cost should be a positive number");
		}
	}

	public Media(String title){
		this.title = title;
		nbMedia++;
		this.id =nbMedia;
	}

	public Media(String title, String category, float cost)throws Exception {
		if(cost >=0 ) {
			this.title=title;
			this.category=category;
			this.cost = cost;
		}
		else throw new Exception("cost should be a positive number");
	}

	public boolean isMatch(String title){
		return true;
	}
	//@Override
	public boolean equals(Object o){
		Media media = (Media) o;
		if(this.getTitle() == media.getTitle()) {
			if(o instanceof Book&&this instanceof Book) return true;
			if(o instanceof CD&&this instanceof CD) return true;
			if(o instanceof DVD&&this instanceof DVD) return true;
			else return false;
		}

		else return false;
	}

}