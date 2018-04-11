package spring.tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.tutorial.exception.NoStockFoundException;
import spring.tutorial.model.Location;
import spring.tutorial.model.Stock;
import spring.tutorial.model.pojo.StockPojo;
import spring.tutorial.repository.LocationRepository;
import spring.tutorial.repository.StockRepository;

import java.util.ArrayList;
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
        List<Stock> stocks = stockRep.findByLocation(locationId);
        if (stocks.isEmpty()) {
            throw new NoStockFoundException();
        }
        return stocks;
    }

    public List<StockPojo> exportAll() {
        List<Location> locations = locationRep.findAll();
        List<Stock> stockList = new ArrayList<>();
        for (Location loc : locations) {
            stockList.addAll(exportAllStocksFromLocation((int) loc.getId()));
        }
        return StockPojo.fromStockList(stockList);
    }
}
