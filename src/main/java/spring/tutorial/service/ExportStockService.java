package spring.tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.tutorial.exception.NoStockFoundException;
import spring.tutorial.model.Location;
import spring.tutorial.model.Stock;
import spring.tutorial.repository.StockRepository;

import java.util.List;

@Service
public class ExportStockService {

    private StockRepository stockRep;

    @Autowired
    public ExportStockService(StockRepository stockRep) {
        this.stockRep = stockRep;
    }

    public List<Stock> exportAllStocksFromLocation(Location location) {
        List<Stock> stocks = stockRep.findByLocation(location);
        if (stocks.isEmpty()) {
            throw new NoStockFoundException();
        }
        return stocks;
    }
}
