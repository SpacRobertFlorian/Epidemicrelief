package eu.accesa.internship.epidemicrelief.soap.consuming;

import eu.accesa.internship.wsdl.GetProductRequest;
import eu.accesa.internship.wsdl.GetProductResponse;
import eu.accesa.internship.wsdl.ListOfUuid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;


public class SOAPClient extends WebServiceGatewaySupport {
    @Value("${choose.api.uri}")
    private String CHOSE_URI;
    private static final Logger log = LoggerFactory.getLogger(SOAPClient.class);

    public GetProductResponse getProducts(ListOfUuid product) {

        GetProductRequest request = new GetProductRequest();
        request.setList(product);

        log.info("Requesting location for " + product);

        return (GetProductResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8082/ws/soap-products", request,
                        new SoapActionCallback(CHOSE_URI));
    }

}
