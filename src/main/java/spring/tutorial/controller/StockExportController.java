package spring.tutorial.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
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
            final CsvMapper mapper = new CsvMapper();
            final CsvSchema schema = mapper.schemaFor(Stock.class);
            final String csv = mapper.writer(schema.withUseHeader(true)).writeValueAsString(stocks);
            return csv;
        } catch (NoStockFoundException ex) {
            return "nothing found";
        }

    }
}
