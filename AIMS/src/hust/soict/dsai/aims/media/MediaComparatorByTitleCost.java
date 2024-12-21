package hust.soict.dsai.aims.media;
import java.util.Comparator;
public class MediaComparatorByTitleCost implements Comparator<Media> {

	public MediaComparatorByTitleCost() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public int compare(Media o1, Media o2) {
		int titleComp = o1.getTitle().compareTo(o2.getTitle());
		if (titleComp != 0) {
			return titleComp;
		}
		
		return Double.compare(o2.getCost(), o1.getCost());
	}
}
