package hust.soict.dsai.aims.store;
import java.util.ArrayList;

import hust.soict.dsai.aims.media.Media;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();
    
    public ArrayList<Media> getItemsInStore() {
		return itemsInStore;
	}

    public void addMedia(Media media) {
    	if(itemsInStore.contains(media)) { 
    		System.out.println("The media " + media.getTitle() + " is already in the store.");
    	} else {
    		itemsInStore.add(media);
    		System.out.println(media.getTitle() + " has been added to the store.");
		}
    }
    public void removeMedia(Media media) {
		boolean removed = itemsInStore.remove(media);
		if(removed) {
			System.out.println(media.getTitle() + " has been removed.");
		}
		else
		{
			System.out.println(media.getTitle()+" is not found");
		}
	}
    public void printMedia() {
        if (itemsInStore.size() == 0) {
            System.out.println("The store is empty!");
        } else {
            System.out.println("********************STORE INVENTORY********************");
            for (Media media : itemsInStore) {
                System.out.println(media);
            }
            System.out.println("********************************************************");
        }
	}
    public Media search(String title) {
		for (Media media : itemsInStore) {
			if (media.getTitle().equals(title)) {
				return media;
			}
		}
		return null;
	}
}
