package spring.tutorial.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.tutorial.exceptions.NoStockFoundException;
import spring.tutorial.model.Stock;
import spring.tutorial.service.ExportStockService;

import java.util.List;

@Controller
public class StockExportController {

    @Autowired
    ExportStockService stockExporter;

    @RequestMapping("/export")
    @ResponseBody
    public String export(@RequestParam("location")String location) throws JsonProcessingException{
        try {
            List<Stock> stocks = stockExporter.exportAllStocksFromLocation(Integer.parseInt(location));
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValueAsString(stocks);
            return stocks.toString();
        } catch (NoStockFoundException ex) {
            return "nothing found";
        }

    }
}
