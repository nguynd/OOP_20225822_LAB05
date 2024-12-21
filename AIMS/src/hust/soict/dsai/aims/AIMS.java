package hust.soict.dsai.aims;

import hust.soict.dsai.aims.cart.carts;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.store.Store;

import javax.naming.LimitExceededException;
import javax.swing.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class AIMS {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Store store = new Store();
		carts cart=new carts();
		while(true){
			showMenu();
			int n = sc.nextInt();
			if(n==1){
				store.printMedia();
				while(true){
					storeMenu();
					int m=sc.nextInt();
					if(m==1){
						System.out.print("Enter title want to see details: ");
						String title = sc.next();
						System.out.println(store.search(title));
						while(true){
							mediaDetailsMenu();
							int q=sc.nextInt();
							if(q==1) {
								try {
									cart.addMedia(store.search(title));
								} catch (LimitExceededException e) {
									throw new RuntimeException(e);
								}
							}
							else if(q==2){
								if(store.search(title) instanceof CD||store.search(title) instanceof DVD) {
									try {
										((Playable) store.search(title)).play();
									} catch (PlayerException e) {
										//throw new RuntimeException(e);
										JOptionPane.showMessageDialog(null, e.getMessage());
									}
								}
							}
							else if(q==0) break;
						}

					}
					else if(m==2){
						System.out.print("Enter title want to add to cart: ");
						String title = sc.next();
						try {
							cart.addMedia(store.search(title));
						} catch (LimitExceededException e) {
							throw new RuntimeException(e);
						}

					}
					else if(m==3){
						System.out.print("Enter title want to add to cart: ");
						String title = sc.next();
						if(store.search(title) instanceof CD||store.search(title) instanceof DVD){
							try {
								((Playable) store.search(title)).play();
							} catch (PlayerException e) {
//                                throw new RuntimeException(e);
								JOptionPane.showMessageDialog(null, e.getMessage());
							}
						}

					}
					else if(m==4){
						cart.print();
					}
					else if(m==0) break;
				}

			}
			else if(n==2){
				System.out.println("Choose your media\n1:book\n2:dvd\n3:cd");
				int m1=sc.nextInt();
				System.out.print("Title:");
				String title=sc.next();
				System.out.print("Category:");
				String category=sc.next();
				System.out.print("Cost:");
				float cost=sc.nextFloat();
				if(m1==1){
					Book item= null;
					try {
						item = new Book(title,category,cost);
					} catch (Exception e) {
						e.printStackTrace();
					}

					System.out.println("Add or Remove\n1:Add\n2:Remove");
					int m2=sc.nextInt();
					if(m2==1) store.addMedia(item);
					else if(m2==2) {
						try {
							store.removeMedia(item);
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}
				}
				else if(m1==2){
					DVD item= null;
					try {
						item = new DVD(title,category,cost);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					System.out.println("Add or Remove\n1:Add\n2:Remove");
					int m2=sc.nextInt();
					if(m2==1) store.addMedia(item);
					else if(m2==2) {
						try {
							store.removeMedia(item);
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}
				}
				else if(m1==3){
					CD item= null;
					try {
						item = new CD(title,category,cost);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					System.out.println("Add or Remove\n1:Add\n2:Remove");
					int m2=sc.nextInt();
					if(m2==1) store.addMedia(item);
					else if(m2==2) {
						try {
							store.removeMedia(item);
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}
				}



			}
			else if(n==3){
				while(true){
					cartMenu();
					int m=sc.nextInt();
					if(m==1) {
						System.out.println("1:Filter by title\n2:Filter by id:");
						int q=sc.nextInt();
						if (q==1){
							System.out.print("Enter title want to see details: ");
							String title = sc.next();
							cart.searchTitle(title);

						}
						else if(q==2){
							System.out.print("Enter id want to see details: ");
							int id = sc.nextInt();
							cart.searchID(id);
						}
					}
					else if(m==2) {
						System.out.println("1:Sort by title\n2:Sort by cost:");
						int q=sc.nextInt();
						if(q==1) {
							cart.sortMediaByTitle();
							cart.print();
						}
						else if(q==2) {
							cart.sortMediaByCost();
							cart.print();
						}
					}
					else if(m==3) {
						System.out.print("Enter item's title wan to remove: ");
						String title = sc.next();
						try {
							store.removeMedia(store.search(title));
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}
					else if(m==4) {
						System.out.print("Enter item's title want to play: ");
						String title = sc.next();
						if(store.search(title) instanceof CD||store.search(title) instanceof DVD){
							try {
								((Playable) store.search(title)).play();
							} catch (PlayerException e) {
								JOptionPane.showMessageDialog(null, e.getMessage());
							}
						}
					}
					else if(m==5) {
						System.out.print("An order is created and empty the current cart");
					}
					else if(m==0) break;
				}
			}
			else if(n==0) break;
		}
	}
	public static void showMenu() {
		System.out.println("AIMS: ");
		System.out.println("--------------------------------");
		System.out.println("1. View store");
		System.out.println("2. Update store");
		System.out.println("3. See current cart");
		System.out.println("0. Exit");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2-3");
	}//done
	public static void storeMenu() {
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. See a media’s details");
		System.out.println("2. Add a media to cart");
		System.out.println("3. Play a media");
		System.out.println("4. See current cart");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2-3-4");
	}//done
	public static void mediaDetailsMenu() {
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. Add to cart");
		System.out.println("2. Play");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2");
	}//done
	public static void cartMenu() {
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. Filter medias in cart");
		System.out.println("2. Sort medias in cart");
		System.out.println("3. Remove media from cart");
		System.out.println("4. Play a media");
		System.out.println("5. Place order");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2-3-4-5");
	}//done




}