package whatever.Controller;

import whatever.model.Location;
import whatever.model.OrderDetail;

public interface SearchStrategy {

    public Long findLocation(Long product, Long quantity);
}
