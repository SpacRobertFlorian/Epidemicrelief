package eu.accesa.internship.epidemicrelief.service.impl;

import eu.accesa.internship.epidemicrelief.model.Household;
import eu.accesa.internship.epidemicrelief.repository.HouseholdRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class HouseholdServiceTest {
    @Mock
    private HouseholdRepository repository;
    private DefaultHouseholdService householdService;
    @Mock
    private Household household;

    @Before
    public void setUp() {
        initMocks(this);
        householdService = new DefaultHouseholdService(repository);
    }

    @Test
    public void givenNone_whenGetAllHouseholds_expectListOfHousehold() {
        List<Household> households = new ArrayList<>();
        households.add(household);

        when(householdService.getAllHouseholds()).thenReturn(households);

        List<Household> returnedList = householdService.getAllHouseholds();

        assertSame(returnedList, households);

        verify(repository).findAll();
    }

    @Test
    public void givenId_whenGetById_expectHousehold() {
        when(repository.findById(1L)).thenReturn(Optional.of(household));

        Optional<Household> returnedHousehold = householdService.getById(1L);

        assertTrue(returnedHousehold.isPresent());
        assertSame(returnedHousehold.get(), household);

        verify(repository).findById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void givenInvalidId_whenGetById_expectOptionalEmpty() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Optional<Household> returnedHousehold = householdService.getById(1L);

        assertTrue(returnedHousehold.isEmpty());

        verify(repository).findById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void givenHousehold_whenAddHousehold_expectSaveHousehold() {
        Household household = new Household();

        householdService.addHousehold(household);

        verify(repository).save(household);
        verifyNoMoreInteractions(repository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidHousehold_whenAddHousehold_expectIllegalArgumentException() {
        householdService.addHousehold(household);
    }

    @Test(expected = NullPointerException.class)
    public void givenInvalidHousehold_whenAddHousehold_expectIllegalNullPointerException() {

        householdService.addHousehold(null);
    }

    @Test
    public void givenValidHousehold_whenUpdateHousehold_expectUpdateHousehold() {
        Household household = new Household();
        household.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(household));

        householdService.updateHousehold(household);

        verify(repository).findById(1L);
        verify(repository).save(household);
        verifyNoMoreInteractions(repository);
    }

    @Test(expected = EntityNotFoundException.class)
    public void givenValidHousehold_whenUpdateHousehold_expectOptionalEmpty() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        householdService.updateHousehold(household);

        verify(repository).findById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void givenHouseholdId_whenDeleteHousehold_expectDeleteHousehold() {
        Household household = new Household();
        household.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(household));

        householdService.deleteHousehold(1L);

        verify(repository).findById(1L);
        verify(repository).delete(household);
        verifyNoMoreInteractions(repository);
    }

    @Test(expected = EntityNotFoundException.class)
    public void givenInvalidHouseholdId_whenDeleteHousehold_expectOptionalEmpty() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        householdService.deleteHousehold(1L);

        verify(repository).findById(1L);
    }

}
