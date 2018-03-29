package spring.tutorial.odata;

import org.apache.olingo.odata2.api.ODataService;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.tutorial.service.CreateOrderService;

@Component
public class ShopOrderServiceFactory extends ODataServiceFactory {

    private final CreateOrderService service;

    @Autowired
    public ShopOrderServiceFactory(CreateOrderService service){
        this.service = service;
    }

    @Override
    public ODataService createService(ODataContext oDataContext){
        return createODataSingleProcessorService(new ShopEdmProvider(), new ShopODataSingleProcessor(service));
    }
}
