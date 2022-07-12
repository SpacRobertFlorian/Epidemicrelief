package eu.accesa.internship.epidemicrelief.model;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validator;

import static javax.validation.Validation.buildDefaultValidatorFactory;
import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class HouseholdTest {
    private final String BLANK_STRING = "";
    private final String PROPERTY_NAME_CONTACT_ADDRESS = "contactAddress";
    private final String PROPERTY_NAME_REPRESENTATIVE = "representative";
    private final String PROPERTY_NAME_PHONE_NUMBER = "phone";
    private final String PROPERTY_NAME_EMAIL = "email";
    private Validator validator;

    @Before
    public void setUp() {
        initMocks(this);
        validator = buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void givenInvalidRepresentativeHousehold_whenSaveHousehold_expectException() {
        Household costel = new Household();
        costel.setRepresentative(BLANK_STRING);
        assertEquals(validator.validateProperty(costel, PROPERTY_NAME_REPRESENTATIVE).size(), 3);
    }

    @Test
    public void givenInvalidRepresentativeNameHousehold_whenSaveHousehold_expectException() {
        Household costel = new Household();
        costel.setRepresentative("Costel1");
        assertEquals(validator.validateProperty(costel, PROPERTY_NAME_REPRESENTATIVE).size(), 1);
    }

    @Test
    public void givenInvalidEmailBlankHousehold_whenSaveHousehold_expectException() {
        Household costel = new Household();
        costel.setEmail(BLANK_STRING);
        assertEquals(validator.validateProperty(costel, PROPERTY_NAME_EMAIL).size(), 1);
    }

    @Test
    public void givenInvalidEmailNullHousehold_whenSaveHousehold_expectException() {
        Household costel = new Household();
        costel.setEmail(null);
        assertEquals(validator.validateProperty(costel, PROPERTY_NAME_EMAIL).size(), 1);
    }

    @Test
    public void givenInvalidEmailHousehold_whenSaveHousehold_expectException() {
        Household costel = new Household();
        costel.setEmail("asd");
        assertEquals(validator.validateProperty(costel, PROPERTY_NAME_EMAIL).size(), 1);
    }

    @Test
    public void givenInvalidPhoneNumberHousehold_whenSaveHousehold_expectException() {
        Household costel = new Household();
        costel.setPhone("0733");
        assertEquals(validator.validateProperty(costel, PROPERTY_NAME_PHONE_NUMBER).size(), 1);
    }

    @Test
    public void givenBlankContactAddressHousehold_whenSaveHousehold_expectException() {
        Household costel = new Household();
        costel.setContactAddress(BLANK_STRING);
        assertEquals(validator.validateProperty(costel, PROPERTY_NAME_CONTACT_ADDRESS).size(), 2);
    }

    @Test
    public void givenBlankAddressHousehold_whenCreateHousehold_expectCreateHousehold() {
        Household costel = new Household();
        costel.setContactAddress(BLANK_STRING);
        assertEquals(validator.validateProperty(costel, PROPERTY_NAME_CONTACT_ADDRESS).size(), 2);
    }

    @Test
    public void givenInvalidAddressHousehold_whenCreateHousehold_expectCreateHousehold() {
        Household costel = new Household();
        costel.setContactAddress("q");
        assertEquals(validator.validateProperty(costel, PROPERTY_NAME_CONTACT_ADDRESS).size(), 1);
    }


    @Test
    public void givenValidRepresentativeHousehold_whenCreateHousehold_expectCreateHousehold() {
        Household costel = new Household();
        costel.setRepresentative("Costel");
        assertEquals(validator.validateProperty(costel, PROPERTY_NAME_REPRESENTATIVE).size(), 0);
    }

    @Test
    public void givenValidPhoneNumberHousehold_whenCreateHousehold_expectCreateHousehold() {
        Household costel = new Household();
        costel.setPhone("0712345115");
        assertEquals(validator.validateProperty(costel, PROPERTY_NAME_PHONE_NUMBER).size(), 0);
    }

    @Test
    public void givenValidEmailHousehold_whenCreateHousehold_expectCreateHousehold() {
        Household costel = new Household();
        costel.setEmail("rob@yahoo.com");
        assertEquals(validator.validateProperty(costel, PROPERTY_NAME_EMAIL).size(), 0);
    }

    @Test
    public void givenValidAddressHousehold_whenCreateHousehold_expectCreateHousehold() {
        Household costel = new Household();
        costel.setContactAddress("str:albania nr:56");
        assertEquals(validator.validateProperty(costel, PROPERTY_NAME_CONTACT_ADDRESS).size(), 0);
    }


}
