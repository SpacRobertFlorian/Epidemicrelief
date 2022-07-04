package eu.accesa.internship.epidemicrelief.soap.consuming.config;

import eu.accesa.internship.epidemicrelief.soap.consuming.SOAPClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ProductConfiguration {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("eu.accesa.internship.wsdl");
        return marshaller;
    }

    @Bean
    public SOAPClient productClient(Jaxb2Marshaller marshaller) {
        SOAPClient client = new SOAPClient();
        client.setDefaultUri("http://localhost:8082/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
