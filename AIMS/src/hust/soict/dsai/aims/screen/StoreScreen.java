package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.carts;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CD;
import hust.soict.dsai.aims.media.DVD;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class StoreScreen extends JFrame {
	private Store store;
	private carts cart;

	public static JPanel center = new JPanel();

	JPanel createNorth(carts cart) {
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		north.add(createMenuBar());
		north.add(createHeader(cart));
		return north;
	}

	JMenuBar createMenuBar() {
		JMenu menu = new JMenu("Options");

		JMenu smUpdateStore = new JMenu("Update Store");
		JMenuItem add_book = new JMenuItem("Add Book");
		add_book.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Book book = null;
				try {
					book = new Book(null, null, 0);
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
				AddBookToStoreScreen addBookToStoreScreen = new AddBookToStoreScreen(store, cart, book);
				dispose();
			}
		});
		smUpdateStore.add(add_book);
		JMenuItem add_cd = new JMenuItem("Add CD");
		add_cd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CD cd = null;
				try {
					cd = new CD(null, null, 0);
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
				AddCDToStoreScreen addCdToStoreScreen = new AddCDToStoreScreen(store, cart, cd);
				dispose();
			}
		});
		smUpdateStore.add(add_cd);
		JMenuItem add_dvd = new JMenuItem("Add DVD");
		add_dvd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DVD dvd = null;
				try {
					dvd = new DVD(null, null, 0);
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
				AddDVDToStoreScreen addDVDToStoreScreen = new AddDVDToStoreScreen(store, cart, dvd);
				dispose();
			}
		});
		smUpdateStore.add(add_dvd);

		menu.add(smUpdateStore);
		JMenuItem view_store = new JMenuItem("View Store");
		menu.add(view_store);
		JMenuItem view_cart = new JMenuItem("View Cart");
		menu.add(view_cart);
		view_cart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CartScreen cartScreen = new CartScreen(store, cart);
				cartScreen.setDefaultCloseOperation(1);
			}
		});

		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuBar.add(menu);

		return menuBar;
	}

	JPanel createHeader(carts cart) {

		JPanel header = new JPanel();
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

		JLabel title = new JLabel("AIMS");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
		title.setForeground(Color.CYAN);

		JButton view_cart = new JButton("View Cart");
		view_cart.setPreferredSize(new Dimension(100, 50));
		view_cart.setMaximumSize(new Dimension(100, 50));
		view_cart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CartScreen cartScreen = new CartScreen(store, cart);
				cartScreen.setDefaultCloseOperation(1);
			}
		});

		header.add(Box.createRigidArea(new Dimension(10, 10)));
		header.add(title);
		header.add(Box.createHorizontalGlue());
		header.add(view_cart);

		return header;
	}

	public void createCenter() {
		center.setLayout(new GridLayout(4, 4));
	}

	public static JPanel createFilterPanel(List<Media> mediaList, carts cart) {
		JPanel filterPanel = new JPanel();
		filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		// Filter by title
		JLabel filterLabel = new JLabel("Filter by title: ");
		JTextField filterField = new JTextField(15);
		JButton filterButton = new JButton("Filter");

		// Filter by type
		JLabel typeLabel = new JLabel("Filter by type: ");
		String[] mediaTypes = {"All", "Book", "CD", "DVD"};
		JComboBox<String> typeComboBox = new JComboBox<>(mediaTypes);

		filterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String filterText = filterField.getText().toLowerCase();
				String selectedType = (String) typeComboBox.getSelectedItem();

				List<Media> filteredList = mediaList.stream()
						.filter(media -> media.getTitle().toLowerCase().contains(filterText))
						.filter(media -> {
							if ("All".equals(selectedType)) return true;
							if ("Book".equals(selectedType)) return media instanceof Book;
							if ("CD".equals(selectedType)) return media instanceof CD;
							return media instanceof DVD;
						})
						.collect(Collectors.toList());
				updateMediaDisplay(filteredList, cart);
			}
		});

		filterPanel.add(filterLabel);
		filterPanel.add(filterField);
		filterPanel.add(typeLabel);
		filterPanel.add(typeComboBox);
		filterPanel.add(filterButton);

		return filterPanel;
	}

	private static void updateMediaDisplay(List<Media> updatedMediaList, carts cart) {
		JFrame mediaFrame = new JFrame("Filtered and Sorted Media");
		mediaFrame.setSize(800, 600);
		mediaFrame.setLayout(new GridLayout(0, 1));

		for (Media media : updatedMediaList) {
			mediaFrame.add(new MediaStore(media, cart));
		}

		mediaFrame.setVisible(true);
	}

	public StoreScreen(Store store, carts cart) {

		this.store = store;
		this.cart = cart;
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		cp.add(createNorth(cart), BorderLayout.NORTH);

		// Add filter panel
		JPanel filterPanel = createFilterPanel(store.getItemsInStore(), cart);
		cp.add(filterPanel, BorderLayout.SOUTH);

		createCenter();
		cp.add(center, BorderLayout.CENTER);

		setVisible(true);
		setTitle("Store");
		setSize(1074, 728);
	}

	public static void main(String[] args) {
		Store store = new Store();
		carts cart = new carts();

		StoreScreen screen = new StoreScreen(store, cart);
	}
}
