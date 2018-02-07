package spring.tutorial.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.tutorial.exceptions.NoStockFoundException;
import spring.tutorial.model.Stock;

import java.util.List;

@Controller
public class StockExportController {

    @Autowired
    ExportStockService stockExporter;

    @RequestMapping("/export")
    @ResponseBody
    public String export(@RequestParam("location")String location){
        try {
            List<Stock> stocks = stockExporter.exportAllStocksFromLocation(Integer.parseInt(location));
            return stocks.toString();
        } catch (NoStockFoundException ex) {
            return "nothing found";
        }

    }
}
