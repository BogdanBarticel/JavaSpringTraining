package whatever.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whatever.exceptions.NoStockFoundException;
import whatever.model.Stock;
import whatever.model.StockRepository;

import java.util.List;

@Service
public class ExportStockService {

    @Autowired
    StockRepository stockRep;

    public List<Stock> exportAllStocksFromLocation(long locationId) throws NoStockFoundException {
        List<Stock> stocks  = stockRep.findByLocation(locationId);
        if (stocks.isEmpty()) throw new NoStockFoundException("No Stock Was found In Location '" + locationId + "'");
        return stocks;
    }
}
