package spring.tutorial.odata;

import org.apache.olingo.odata2.api.ep.EntityProvider;
import org.apache.olingo.odata2.api.ep.EntityProviderException;
import org.apache.olingo.odata2.api.ep.EntityProviderReadProperties;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.apache.olingo.odata2.api.uri.info.PostUriInfo;
import org.apache.olingo.odata2.core.ODataResponseImpl;
import spring.tutorial.model.Address;
import spring.tutorial.model.Order;
import spring.tutorial.model.OrderRequest;
import spring.tutorial.service.CreateOrderService;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ShopODataSingleProcessor extends ODataSingleProcessor {

    private final CreateOrderService service;

    public ShopODataSingleProcessor(CreateOrderService service) {
        this.service = service;
    }

    @Override
    public ODataResponse createEntity(PostUriInfo uriInfo, InputStream content, String requestContentType, String contentType) throws EntityProviderException {


        EntityProviderReadProperties properties = EntityProviderReadProperties.init().mergeSemantic(false).build();
        ODataEntry entry = EntityProvider.readEntry(requestContentType, uriInfo.getStartEntitySet(), content, properties);
        Map<String, Object> data = entry.getProperties();
        OrderRequest orderRequest = buildOrderRequest(data);
        service.createOrder(orderRequest);
        return new ODataResponseImpl();
    }

    private OrderRequest buildOrderRequest(Map<String, Object> data){
        OrderRequest or = new OrderRequest();
        or.setCustomer(1);
        Map<Integer, Integer> products = new HashMap<>();
        products.put((int)data.get("Product"), (int)data.get("Quantity"));
        or.setProducts(products);
        Address add = new Address();
        add.setCountry((String)data.get("Country"));
        add.setCounty((String)data.get("County"));
        add.setCity((String)data.get("City"));
        add.setStreet((String)data.get("Street"));
        or.setDestination(add);
        return or;
    }


}
