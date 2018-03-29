package spring.tutorial.odata;

import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.core.servlet.ODataServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
class ShopServlet extends ODataServlet {

    private final ODataServiceFactory factory;

    @Autowired
    public ShopServlet(ODataServiceFactory factory) {
        this.factory = factory;
    }

    @Override
    protected ODataServiceFactory getServiceFactory(HttpServletRequest request) {
        return factory;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        req.setAttribute(ODataServiceFactory.FACTORY_INSTANCE_LABEL, factory);
        super.service(req, res);
    }
}

