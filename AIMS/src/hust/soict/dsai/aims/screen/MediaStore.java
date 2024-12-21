package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.cart.carts;
import hust.soict.dsai.aims.media.Playable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class MediaStore extends JPanel {
	private Media media;
	private static List<Media> mediaList;

	public MediaStore(Media media, carts cart) {
		this.media = media;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel title = new JLabel(media.getTitle());
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
		title.setAlignmentX(CENTER_ALIGNMENT);

		JLabel cost = new JLabel("" + media.getCost() + " $");
		cost.setAlignmentX(CENTER_ALIGNMENT);

		JPanel container = new JPanel();
		container.setLayout(new FlowLayout(FlowLayout.CENTER));

		JButton addToCartButton = new JButton("Add to cart");
		addToCartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					cart.addMedia(media);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Failed to add to cart: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		container.add(addToCartButton);

		if (media instanceof Playable) {
			JButton playButton = new JButton("Play");
			playButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						((Playable) media).play();
						JOptionPane.showMessageDialog(null, "Playing: " + media.getTitle(), "Now Playing", JOptionPane.INFORMATION_MESSAGE);
					} catch (PlayerException ex) {
						JOptionPane.showMessageDialog(null, "Cannot play media: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			container.add(playButton);
		}

		this.add(Box.createVerticalGlue());
		this.add(title);
		this.add(cost);
		this.add(Box.createVerticalGlue());
		this.add(container);

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	public static JPanel createFilterPanel(List<Media> mediaList, carts cart) {
		MediaStore.mediaList = mediaList;
		JPanel filterPanel = new JPanel();
		filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel filterLabel = new JLabel("Filter by title: ");
		JTextField filterField = new JTextField(15);
		JButton filterButton = new JButton("Filter");

		filterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String filterText = filterField.getText().toLowerCase();
				List<Media> filteredList = mediaList.stream()
						.filter(media -> media.getTitle().toLowerCase().contains(filterText))
						.collect(Collectors.toList());
				updateMediaDisplay(filteredList, cart);
			}
		});

		filterPanel.add(filterLabel);
		filterPanel.add(filterField);
		filterPanel.add(filterButton);

		JLabel sortLabel = new JLabel("Sort by: ");
		String[] sortOptions = {"Title", "Cost"};
		JComboBox<String> sortComboBox = new JComboBox<>(sortOptions);
		JButton sortButton = new JButton("Sort");

		sortButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedOption = (String) sortComboBox.getSelectedItem();
				List<Media> sortedList;
				if ("Title".equals(selectedOption)) {
					sortedList = mediaList.stream()
							.sorted((m1, m2) -> m1.getTitle().compareToIgnoreCase(m2.getTitle()))
							.collect(Collectors.toList());
				} else {
					sortedList = mediaList.stream()
							.sorted((m1, m2) -> Float.compare(m1.getCost(), m2.getCost()))
							.collect(Collectors.toList());
				}
				updateMediaDisplay(sortedList, cart);
			}
		});

		filterPanel.add(sortLabel);
		filterPanel.add(sortComboBox);
		filterPanel.add(sortButton);

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
}
