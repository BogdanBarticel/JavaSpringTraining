package java.spring.tutorial.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.spring.tutorial.exceptions.NoStockFoundException;
import java.spring.tutorial.model.Stock;
import java.spring.tutorial.model.StockRepository;

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
