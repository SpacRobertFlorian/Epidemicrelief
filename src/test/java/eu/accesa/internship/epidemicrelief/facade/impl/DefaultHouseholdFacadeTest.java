package eu.accesa.internship.epidemicrelief.facade.impl;

import eu.accesa.internship.epidemicrelief.converter.HouseholdConverter;
import eu.accesa.internship.epidemicrelief.data.HouseholdData;
import eu.accesa.internship.epidemicrelief.model.Household;
import eu.accesa.internship.epidemicrelief.service.HouseholdService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class DefaultHouseholdFacadeTest {

    private static final long HOUSEHOLD_ID = 1L;

    // unit under test
    private DefaultHouseholdFacade householdFacade;

    @Mock
    private HouseholdService householdService;
    @Mock
    private HouseholdConverter householdConverter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        householdFacade = new DefaultHouseholdFacade(householdService, householdConverter);
    }

    @Test
    public void givenNonExistingHousehold_whenGetById_expectEmptyResult() {
        when(householdService.getById(HOUSEHOLD_ID)).thenReturn(Optional.empty());

        Optional<HouseholdData> returnedHousehold = householdFacade.getById(HOUSEHOLD_ID);

        assertTrue(returnedHousehold.isEmpty());
    }

    @Test
    public void givenExistingHousehold_whenGetById_expectHouseholdConvertedToData() {
        Household household = new Household();
        HouseholdData householdData = new HouseholdData();

        when(householdService.getById(HOUSEHOLD_ID)).thenReturn(Optional.of(household));
        when(householdConverter.from(household)).thenReturn(householdData);

        Optional<HouseholdData> returnedHouseHold = householdFacade.getById(HOUSEHOLD_ID);

        assertTrue(returnedHouseHold.isPresent());
        assertSame(householdData, returnedHouseHold.get());
    }

    @Test
    public void givenValidHousehold_whenAddHousehold_saveHousehold() {
        Household household = new Household();
        HouseholdData householdData = new HouseholdData();
        when(householdConverter.to(householdData)).thenReturn(household);

        householdFacade.addHousehold(householdData);

        verify(householdService).addHousehold(household);
        verify(householdConverter).to(householdData);
        verify(householdConverter, never()).from(household);
    }
}
