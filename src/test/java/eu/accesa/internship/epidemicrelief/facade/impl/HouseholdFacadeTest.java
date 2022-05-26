package eu.accesa.internship.epidemicrelief.facade.impl;

import eu.accesa.internship.epidemicrelief.converter.HouseholdConverter;
import eu.accesa.internship.epidemicrelief.data.HouseholdData;
import eu.accesa.internship.epidemicrelief.model.Household;
import eu.accesa.internship.epidemicrelief.service.HouseholdService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class HouseholdFacadeTest {

    @Mock
    private HouseholdService householdService;
    @Mock
    private HouseholdConverter householdConverter;
    private DefaultHouseholdFacade householdFacade;

    @Before
    public void setUp() {
        initMocks(this);

        householdFacade = new DefaultHouseholdFacade(householdService, householdConverter);
    }

    @Test
    public void givenNone_whenGetHouseholds_expectListOfHousehold() {
        Household household = new Household();
        List<Household> householdList = new ArrayList<>();
        householdList.add(household);

        HouseholdData householdData = new HouseholdData();
        List<HouseholdData> householdDataList = new ArrayList<>();
        householdDataList.add(householdData);

        when(householdService.getAllHouseholds()).thenReturn(householdList);
        when(householdConverter.from(household)).thenReturn(householdData);

        List<HouseholdData> returnedList = householdFacade.getHouseholds();

        assertEquals(returnedList, householdDataList);

        verify(householdService).getAllHouseholds();
        verify(householdConverter).from(household);
    }

    @Test
    public void givenValidHouseholdId_whenGetById_expectHouseholdData() {
        Household household = new Household();
        HouseholdData householdData = new HouseholdData();

        when(householdService.getById(1L)).thenReturn(Optional.of(household));
        when(householdConverter.from(household)).thenReturn(householdData);

        Optional<HouseholdData> returnedHouseholdData = householdFacade.getById(1L);

        assertTrue(returnedHouseholdData.isPresent());
        assertSame(returnedHouseholdData.get(), householdData);

        verify(householdService).getById(1L);
        verify(householdConverter).from(household);
        verifyNoMoreInteractions(householdConverter);
    }

    @Test
    public void givenValidHouseholdData_whenAddHousehold_expectSaveHousehold() {
        Household household = new Household();
        HouseholdData householdData = new HouseholdData();

        when(householdConverter.to(householdData)).thenReturn(household);

        householdFacade.addHousehold(householdData);

        verify(householdService).addHousehold(household);
        verify(householdConverter).to(householdData);
        verifyNoMoreInteractions(householdConverter);
        verifyNoMoreInteractions(householdService);
    }

    @Test
    public void givenValidHouseholdData_whenUpdateHousehold_expectUpdateHousehold() {
        Household household = new Household();
        HouseholdData householdData = new HouseholdData();

        when(householdConverter.to(householdData)).thenReturn(household);

        householdFacade.updateHousehold(householdData);

        verify(householdConverter).to(householdData);
        verify(householdService).updateHousehold(household);
        verifyNoMoreInteractions(householdService);
        verifyNoMoreInteractions(householdConverter);
    }

    @Test
    public void givenValidHouseholdId_whenDeleteHousehold_expectDeleteHousehold() {
        householdFacade.deleteHousehold(1L);

        verify(householdService).deleteHousehold(1L);
        verifyNoMoreInteractions(householdService);
        verifyNoMoreInteractions(householdConverter);
    }

}
