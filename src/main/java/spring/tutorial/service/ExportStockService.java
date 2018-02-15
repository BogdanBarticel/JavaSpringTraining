package spring.tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.tutorial.model.Location;
import spring.tutorial.repository.StockRepository;
import spring.tutorial.exception.NoStockFoundException;
import spring.tutorial.model.Stock;

import java.util.List;

@Service
public class ExportStockService {

    public List<Stock> exportAllStocksFromLocation(Location location, StockRepository stockRep) throws NoStockFoundException {
        List<Stock> stocks = stockRep.findByLocation(location);
        if (stocks.isEmpty()) {
            throw new NoStockFoundException("No Stock Was found In Location '" + location + "'");
        }
        return stocks;
    }
}
