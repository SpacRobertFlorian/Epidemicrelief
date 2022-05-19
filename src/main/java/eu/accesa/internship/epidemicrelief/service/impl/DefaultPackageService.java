package eu.accesa.internship.epidemicrelief.service.impl;

import eu.accesa.internship.epidemicrelief.entity.*;
import eu.accesa.internship.epidemicrelief.entity.visitor.ProductVisitor;
import eu.accesa.internship.epidemicrelief.entity.visitor.model.ProductNecessity;
import eu.accesa.internship.epidemicrelief.model.Household;
import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.model.Product;
import eu.accesa.internship.epidemicrelief.repository.HouseholdRepository;
import eu.accesa.internship.epidemicrelief.repository.PackageRepository;
import eu.accesa.internship.epidemicrelief.repository.ProductRepository;
import eu.accesa.internship.epidemicrelief.service.PackageService;
import eu.accesa.internship.epidemicrelief.utils.enums.EnumPackageStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

public class DefaultPackageService implements PackageService {

    private final PackageRepository packageRepository;
    private final ProductRepository productRepository;
    private final HouseholdRepository householdRepository;

    public DefaultPackageService(PackageRepository packageRepository, ProductRepository productRepository, HouseholdRepository householdRepository) {

        this.packageRepository = packageRepository;
        this.productRepository = productRepository;
        this.householdRepository = householdRepository;
    }

    @Override
    public List<Package> getAllPackages() {
        return packageRepository.findAll();
    }

    @Override
    public Optional<Package> getPackage(Long idHousehold) {
        Optional<Household> household = householdRepository.findById(idHousehold);
        if (household.isPresent()) {
            Optional<Package> packageOptional = household.get().getLatestPackage();

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

    @Override
    public void cancelPackage(@NotNull Package aPackage) {
        //TODO cancel button
        packageRepository.delete(aPackage);
    }

    @Override
    public void fillPackage(Package aPackage) {
        List<ProductNecessity> productNecessityList = createNecessityList(aPackage);
        for (ProductNecessity product : productNecessityList) {

            Optional<Product> productByUuid = productRepository.findProductByUuid(product.getUuid());

            if (productByUuid.isPresent()) {
                Product saveProduct = new Product(productByUuid.get());
                if (productByUuid.get().getStock() >= product.getStock()) {

                    saveProduct.setStock(product.getStock());
                    productByUuid.get().setStock(productByUuid.get().getStock() - product.getStock());
                } else {

                    productByUuid.get().setStock(0);
                }
                aPackage.getProducts().add(saveProduct);
                productRepository.save(productByUuid.get());
            }
        }
    }

    private List<ProductNecessity> createNecessityList(Package aPackage) {

        ProductVisitor productVisitor = new ProductVisitor();
        Household household = aPackage.getHousehold();
        List<HouseholdMembers> members = new LinkedList<>();
        List<ProductNecessity> productNecessityList = new ArrayList<>();

        members.add(new Family(household.getNumberOfPeople()));
        members.add(new Child(household.getNumberOfChildren()));
        members.add(new Vegan(household.getNumberOfVegans()));
        members.add(new NonVegan(household.getNumberOfNonVegans()));

        for (HouseholdMembers householdMembers : members) {
            productNecessityList.addAll(householdMembers.productNecessityList(productVisitor));
        }
        return productNecessityList;
    }


}
