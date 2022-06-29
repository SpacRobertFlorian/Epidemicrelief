package eu.accesa.internship.epidemicrelief.soap.consuming;

import eu.accesa.internship.wsdl.GetProductRequest;
import eu.accesa.internship.wsdl.GetProductResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;


public class Client extends WebServiceGatewaySupport {
    private static final Logger log =  LoggerFactory.getLogger(Client.class);

    public GetProductResponse getProduct(String product) {

        GetProductRequest request = new GetProductRequest();
        request.setName(product);

        log.info("Requesting location for " + product);

        return (GetProductResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8082/ws/soap-products", request,
                        new SoapActionCallback(
                                "http://localhost:8082/soap-products/GetProdudctRequest"));
    }
}
