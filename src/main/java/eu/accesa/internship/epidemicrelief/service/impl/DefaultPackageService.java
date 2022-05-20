package eu.accesa.internship.epidemicrelief.service.impl;

import eu.accesa.internship.epidemicrelief.data.PackageData;
import eu.accesa.internship.epidemicrelief.entity.*;
import eu.accesa.internship.epidemicrelief.entity.visitor.ProductVisitor;
import eu.accesa.internship.epidemicrelief.entity.visitor.model.ProductNecessity;
import eu.accesa.internship.epidemicrelief.model.Household;
import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.model.PackageProducts;
import eu.accesa.internship.epidemicrelief.model.Product;
import eu.accesa.internship.epidemicrelief.repository.HouseholdRepository;
import eu.accesa.internship.epidemicrelief.repository.PackageProductsRepository;
import eu.accesa.internship.epidemicrelief.repository.PackageRepository;
import eu.accesa.internship.epidemicrelief.repository.ProductRepository;
import eu.accesa.internship.epidemicrelief.service.PackageService;
import eu.accesa.internship.epidemicrelief.service.utils.enums.EnumPackageStatus;
import eu.accesa.internship.epidemicrelief.service.utils.packagestatus.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

public class DefaultPackageService implements PackageService {

    private final PackageRepository packageRepository;
    private final ProductRepository productRepository;
    private final HouseholdRepository householdRepository;
    private final PackageProductsRepository packageProductsRepository;

    @Autowired
    public DefaultPackageService(PackageRepository packageRepository, ProductRepository productRepository, HouseholdRepository householdRepository, PackageProductsRepository packageProductsRepository) {

        this.packageRepository = packageRepository;
        this.productRepository = productRepository;
        this.householdRepository = householdRepository;
        this.packageProductsRepository = packageProductsRepository;
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
                packageOptional.get().setProducts(packageProductsRepository.getAllByPack(packageOptional.get()));
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
    public PackageState handlePackage(Optional<PackageData> packageData) {
        if (packageData.isPresent()) {
            EnumPackageStatus status = packageData.get().getStatus();
            switch (status) {
                case CREATED:
                    return new CreatedState();
                case READY:
                    return new ReadyState();
                case DELIVERED:
                    return new DeliveredState();
            }
        }
        return new OrderState();
    }

    @Override
    public void fillPackage(Package aPackage) {
        List<ProductNecessity> productNecessityList = createNecessityList(aPackage);
        for (ProductNecessity product : productNecessityList) {

            Optional<Product> productByUuid = productRepository.findProductByUuid(product.getUuid());

            if (productByUuid.isPresent()) {
                if (productByUuid.get().getStock() >= product.getStock()) {
                    productByUuid.get().setStock(productByUuid.get().getStock() - product.getStock());
                } else {
                    productByUuid.get().setStock(0);
                }
                aPackage.getProducts().add(new PackageProducts(productByUuid.get(), aPackage, (long) product.getStock()));

                productRepository.save(productByUuid.get());
            }
        }
        packageRepository.save(aPackage);
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
