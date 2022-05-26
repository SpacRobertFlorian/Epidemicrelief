package eu.accesa.internship.epidemicrelief.service.impl;

import eu.accesa.internship.epidemicrelief.model.Household;
import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.model.PackageProducts;
import eu.accesa.internship.epidemicrelief.repository.*;
import eu.accesa.internship.epidemicrelief.service.PackageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultPackageServiceTest {

    @Mock
    private PackageRepository packageRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private HouseholdRepository householdRepository;
    @Mock
    private PackageProductsRepository packageProductsRepository;
    @Mock
    private NecessityRepository necessityRepository;

    @Mock
    private Package pack;
    @Mock
    private Household household;
    @Mock
    private PackageProducts packageProducts;
    private PackageService packageService;

    @Before
    public void setUp() {
        initMocks(this);
        packageService = new DefaultPackageService(packageRepository, productRepository, householdRepository, packageProductsRepository, necessityRepository);
    }

    @Test
    public void givenNone_whenGetAllPackages_expectListOfPackage() {
        List<Package> packageList = Mockito.spy(new ArrayList<>());
        packageList.add(pack);

        when(packageRepository.findAll()).thenReturn(packageList);

        List<Package> returnedPackageList = packageService.getAllPackages();

        verify(packageRepository).findAll();

        assertSame(returnedPackageList, packageList);
    }

    @Test
    public void givenNone_whenGetLastPackageByHouseholdId_expectEmptyOptional() {
        List<PackageProducts> products = Mockito.spy(new ArrayList<>());
        products.add(packageProducts);

        when(householdRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Package> packageOptional = packageService.getLastPackageByHouseholdId(1L);

        verify(householdRepository).findById(1L);
        verifyNoMoreInteractions(packageProductsRepository);

        assertTrue(packageOptional.isEmpty());
    }

    @Test
    public void givenIdHousehold_whenCreatePackage_expectNothing() {
        when(householdRepository.findById(1L)).thenReturn(Optional.of(household));

        packageService.createPackage(1L);

        verify(householdRepository).findById(1L);

    }

    @Test
    public void givenPackage_whenUpdatePackage_expectUpdatePackage() {
        Household household = new Household();
        household.setId(1L);

        Package pack = new Package();
        pack.setHousehold(household);

        when(householdRepository.findById(1L)).thenReturn(Optional.of(household));

        packageService.updatePackage(pack);

        verify(householdRepository).findById(1L);
        verify(packageRepository).save(pack);

    }

    @Test(expected = NullPointerException.class)
    public void givenInvalidPackage_whenUpdatePackage_expectEntityNullPointerException() {

        packageService.updatePackage(pack);

        verify(packageRepository).save(pack);
    }

    @Test
    public void givenValidPackage_whenSendPackage_expectSavePackage() {
        Household household = new Household();
        household.setId(1L);

        Package pack = new Package();
        pack.setHousehold(household);

        when(householdRepository.findById(1L)).thenReturn(Optional.of(household));

        packageService.sendPackage(pack);

        verify(householdRepository).findById(1L);
        verify(packageRepository).save(pack);
    }

    @Test
    public void givenValidPackage_whenFillPackage_expectFillPackage() {
        Household household = new Household();
        household.setId(1L);

        Package pack = new Package();
        pack.setHousehold(household);
        pack.setId(1L);

        household.setPackages(List.of(pack));

        packageService.fillPackage(pack);

        verify(packageRepository).save(pack);

    }
}