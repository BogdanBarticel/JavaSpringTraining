package spring.tutorial.odata;

import org.apache.olingo.odata2.api.ep.EntityProvider;
import org.apache.olingo.odata2.api.ep.EntityProviderException;
import org.apache.olingo.odata2.api.ep.EntityProviderReadProperties;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.apache.olingo.odata2.api.uri.info.PostUriInfo;
import org.apache.olingo.odata2.core.ODataResponseImpl;
import spring.tutorial.model.pojo.OrderRequest;
import spring.tutorial.service.CreateOrderService;

import java.io.InputStream;
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
        OrderRequest orderRequest = SimpleOrderMapper.buildOrderRequest(data);
        service.createOrder(orderRequest);
        return new ODataResponseImpl();
    }



}
