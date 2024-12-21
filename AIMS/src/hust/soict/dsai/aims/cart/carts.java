package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.naming.LimitExceededException;
import java.util.Collections;

public class carts {
	public static final int MAX_NUMBERS_ORDERED = 20;

	private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

	public int qtyOrdered = 0;

	public String addMedia(Media media) throws LimitExceededException {
		if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {
			throw new LimitExceededException("ERROR: Maximum number of items in the cart reached.");
		} else if (itemsOrdered.contains(media)) {
			return media.getTitle() + " is already in the cart.";
		} else {
			qtyOrdered++;
			itemsOrdered.add(media);
			return media.getTitle() + " has been added to the cart.";
		}
	}

	public ObservableList<Media> getItemsOrdered() {
		return itemsOrdered;
	}

	public void removeMedia(Media media) throws LimitExceededException {
		if (itemsOrdered.isEmpty()) {
			throw new LimitExceededException("ERROR: The cart is empty. No items to remove.");
		}
		if (itemsOrdered.contains(media)) {
			itemsOrdered.remove(media);
			System.out.println("Item removed successfully from the cart.");
		} else {
			System.out.println("ERROR: Item not found in the cart.");
		}
	}

	public float totalCost() {
		float total = 0;
		for (Media media : itemsOrdered) {
			total += media.getCost();
		}
		return total;
	}

	public void print() {
		System.out.println("======================= CART =======================");
		System.out.println("Items in your cart:");
		for (Media media : itemsOrdered) {
			System.out.println(media);
		}
		System.out.println("---------------------------------------------------");
		System.out.println("Total cost: " + totalCost());
		System.out.println("===================================================");
	}

	public void empty() {
		if (itemsOrdered.isEmpty()) {
			System.out.println("The cart is already empty.");
		} else {
			qtyOrdered = 0;
			itemsOrdered.clear();
			System.out.println("All items have been removed from the cart.");
		}
	}

	public void placeOrder() {
		if (itemsOrdered.isEmpty()) {
			System.out.println("ERROR: Your cart is empty. Cannot place an order.");
		} else {
			System.out.println("Order placed successfully.");
			itemsOrdered.clear();
			qtyOrdered = 0;
		}
	}

	public String searchID(int ID) {
		for (Media item : itemsOrdered) {
			if (item.getId() == ID) {
				return item.toString();
			}
		}
		return "No item found with the given ID.";
	}

	public String searchTitle(String title) {
		for (Media item : itemsOrdered) {
			if (item.isMatch(title)) {
				return item.toString();
			}
		}
		return "No item found with the given title.";
	}

	public Media searchToRemove(String title) {
		for (Media media : itemsOrdered) {
			if (media.getTitle().equals(title)) {
				return media;
			}
		}
		return null;
	}

	public void sortMediaByTitle() {
		Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
		System.out.println("Items have been sorted by title (and cost).\n");
	}

	public void sortMediaByCost() {
		Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
		System.out.println("Items have been sorted by cost (and title).\n");
	}
}
