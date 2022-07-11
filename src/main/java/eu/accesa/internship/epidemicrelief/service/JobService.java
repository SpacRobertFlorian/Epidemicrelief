package eu.accesa.internship.epidemicrelief.service;

/**
 * Contains business logic related to Jobs
 */
public interface JobService {

    /**
     * Update products from database from a REST service
     */
    void restUpdateProduct();

    /**
     * Update products from database from a SOAP service
     */
    void soapUpdateProduct();

    /**
     * Update products from database from a chose API
     */
    void updateProduct() throws InterruptedException;
}
