package spring.tutorial.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.tutorial.model.Stock;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockPojo {
    private long id;
    private long productId;
    private long locationId;
    private int quantity;

    public static StockPojo fromStock(Stock stock) {
        return new StockPojo(stock.getId(), stock.getProduct().getId(), stock.getLocation().getId(), stock.getQuantity());
    }

    public static List<StockPojo> fromStockList(List<Stock> stocks) {
        List<StockPojo> out = new ArrayList<>();
        for (Stock stock : stocks) {
            out.add(fromStock(stock));
        }
        return out;
    }
}
