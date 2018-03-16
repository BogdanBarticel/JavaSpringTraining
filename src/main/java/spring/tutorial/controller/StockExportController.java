package spring.tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.tutorial.model.Location;
import spring.tutorial.model.Stock;
import spring.tutorial.model.pojo.StockPojo;
import spring.tutorial.repository.LocationRepository;
import spring.tutorial.service.ExportStockService;

import java.util.ArrayList;
import java.util.List;


@RestController
public class StockExportController {

    private ExportStockService stockExporter;
    private LocationRepository locationRep;

    @Autowired
    public StockExportController(ExportStockService stockExporter, LocationRepository locationRep) {
        this.stockExporter = stockExporter;
        this.locationRep = locationRep;
    }


    @GetMapping(path = "/export", produces = "text/csv")
    public List<StockPojo> export(@RequestParam("location") long locationId) {
        int id = (int) locationId;
        return StockPojo.fromStockList(stockExporter.exportAllStocksFromLocation(id));
    }

    @GetMapping(path = "/export/all", produces = "text/csv")
    public List<StockPojo> exportAll() {
        List<Location> locations = locationRep.findAll();
        List<Stock> stockList = new ArrayList<>();
        for (Location loc : locations) {
            stockList.addAll(stockExporter.exportAllStocksFromLocation((int) loc.getId()));
        }
        return StockPojo.fromStockList(stockList);
    }
}
