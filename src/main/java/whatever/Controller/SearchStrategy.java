package whatever.Controller;

import whatever.exceptions.LocationNotFoundForProductException;
import whatever.model.Location;
import whatever.model.OrderDetail;
import whatever.model.StockRepository;

public interface SearchStrategy {

    public Long findLocation(Long product, Long quantity, StockRepository stockRep) throws LocationNotFoundForProductException;
}
