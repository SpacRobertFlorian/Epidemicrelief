package eu.accesa.internship.epidemicrelief.facade.impl;

import eu.accesa.internship.epidemicrelief.converter.PackageConverter;
import eu.accesa.internship.epidemicrelief.data.PackageData;
import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.service.PackageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultPackageFacadeTest {
    @Mock
    private PackageService packageService;
    @Mock
    private PackageConverter packageConverter;
    private DefaultPackageFacade packageFacade;

    @Before
    public void setUp() {
        initMocks(this);
        packageFacade = new DefaultPackageFacade(packageService, packageConverter);
    }

    @Test
    public void givenIdHousehold_whenGetPackageByIdHousehold_expectOptionalPackageData() {
        PackageData packageData = new PackageData();
        Package pack = new Package();

        when(packageService.getLastPackageByHouseholdId(1L)).thenReturn(Optional.of(pack));
        when(packageConverter.from(pack)).thenReturn(packageData);

        Optional<PackageData> optionalPackageData = packageFacade.getLastPackageByIdHousehold(1L);

        assertTrue(optionalPackageData.isPresent());
        assertSame(packageData, optionalPackageData.get());

        verify(packageService).getLastPackageByHouseholdId(1L);
        verify(packageConverter).from(pack);
        verifyNoMoreInteractions(packageConverter);
    }

    @Test
    public void givenPackageId_whenCancelPackage_expectDeletePackage() {
        packageFacade.cancelPackage(1L);

        verify(packageService).cancelPackage(1L);
        verifyNoMoreInteractions(packageConverter);
    }
}