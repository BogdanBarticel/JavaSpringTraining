package spring.tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.tutorial.exception.NoStockFoundException;
import spring.tutorial.model.Location;
import spring.tutorial.model.Stock;
import spring.tutorial.repository.LocationRepository;
import spring.tutorial.repository.StockRepository;

import java.util.List;

@Service
public class ExportStockService {

    private StockRepository stockRep;
    private LocationRepository locationRep;

    @Autowired
    public ExportStockService(StockRepository stockRep, LocationRepository locationRep) {
        this.stockRep = stockRep;
        this.locationRep = locationRep;
    }

    public List<Stock> exportAllStocksFromLocation(int locationId) {
        Location location = locationRep.findOne((long)locationId);
        List<Stock> stocks = stockRep.findByLocation(location);
        if (stocks.isEmpty()) {
            throw new NoStockFoundException();
        }
        return stocks;
    }
}
