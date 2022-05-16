package eu.accesa.internship.epidemicrelief.service.impl;

import eu.accesa.internship.epidemicrelief.model.Household;
import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.repository.HouseholdRepository;
import eu.accesa.internship.epidemicrelief.repository.PackageRepository;
import eu.accesa.internship.epidemicrelief.service.PackageService;
import eu.accesa.internship.epidemicrelief.utils.enums.EnumPackageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DefaultPackageService implements PackageService {

    private final PackageRepository packageRepository;
    private final HouseholdRepository householdRepository;

    @Autowired
    public DefaultPackageService(PackageRepository packageRepository, HouseholdRepository householdRepository) {
        this.packageRepository = packageRepository;
        this.householdRepository = householdRepository;
    }

    @Override
    public List<Package> getAllPackages() {
        return packageRepository.findAll();
    }

    @Override
    public void fillPackage() {
    }

    @Override
    public Optional<Package> getPackage(Long idHousehold) {
        Optional<Household> household = householdRepository.findById(idHousehold);
        if (household.isPresent()) {
            Optional<Package> packageOptional = packageRepository.findTopByHouseholdOrderByHouseholdDesc(household.get());

            if (packageOptional.isPresent()) {
                return packageOptional;
            }
        }
        return Optional.empty();
    }

    @Override
    public void createPackage(Long idHousehold) {
        Package householdPackage = new Package();

        householdPackage.setStatus(EnumPackageStatus.CREATED);
        Optional<Household> household = householdRepository.findById(idHousehold);
        household.ifPresent(householdPackage::setHousehold);
        householdPackage.setCreatedDate(LocalDate.now());

        packageRepository.save(householdPackage);
    }

    @Override
    public void updatePackage(@NotNull Package packageStatus) {
        Objects.requireNonNull(packageStatus);

        Optional<Household> householdOptional = householdRepository.findById(packageStatus.getHousehold().getId());
        if (householdOptional.isEmpty()) {
            throw new EntityNotFoundException("Unable to find package to update; id: " + packageStatus.getId());
        }
        packageRepository.save(packageStatus);
    }

    @Override
    public void sendPackage(@NotNull Package packageStatus) {
        Objects.requireNonNull(packageStatus);
        Optional<Household> householdOptional = householdRepository.findById(packageStatus.getHousehold().getId());

        if (householdOptional.isEmpty()) {
            throw new EntityNotFoundException("Unable to find package to update; id: " + packageStatus.getId());
        }

        packageStatus.setDeliveredDate(LocalDate.now());
        packageRepository.save(packageStatus);
    }

}
