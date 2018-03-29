package spring.tutorial.odata;

import org.apache.olingo.odata2.api.ODataDebugCallback;

public class ShopDebugCallback implements ODataDebugCallback {
    @Override
    public boolean isDebugEnabled() {
        return true;
    }
}
