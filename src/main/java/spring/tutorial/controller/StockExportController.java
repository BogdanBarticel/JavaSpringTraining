package spring.tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.tutorial.model.pojo.StockPojo;
import spring.tutorial.service.ExportStockService;

import java.util.List;


@RestController
public class StockExportController {

    private ExportStockService stockExporter;


    @Autowired
    public StockExportController(ExportStockService stockExporter) {
        this.stockExporter = stockExporter;
    }


    @GetMapping(path = "/export", produces = "text/csv")
    public List<StockPojo> export(@RequestParam("location") long locationId) {
        int id = (int) locationId;
        return StockPojo.fromStockList(stockExporter.exportAllStocksFromLocation(id));
    }

    @GetMapping(path = "/export/all", produces = "text/csv")
    public List<StockPojo> exportAll() {
        return stockExporter.exportAll();
    }
}
