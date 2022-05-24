package eu.accesa.internship.epidemicrelief.service.impl;

import eu.accesa.internship.epidemicrelief.entity.*;
import eu.accesa.internship.epidemicrelief.entity.visitor.ProductVisitor;
import eu.accesa.internship.epidemicrelief.entity.visitor.model.ProductNecessity;
import eu.accesa.internship.epidemicrelief.model.*;
import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.repository.*;
import eu.accesa.internship.epidemicrelief.service.PackageService;
import eu.accesa.internship.epidemicrelief.utils.enums.EnumPackageStatus;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

public class DefaultPackageService implements PackageService {

    private final PackageRepository packageRepository;
    private final ProductRepository productRepository;
    private final HouseholdRepository householdRepository;
    private final PackageProductsRepository packageProductsRepository;
    private final NecessityRepository necessityRepository;


    @Autowired
    public DefaultPackageService(PackageRepository packageRepository, ProductRepository productRepository,
                                 HouseholdRepository householdRepository,
                                 PackageProductsRepository packageProductsRepository,
                                 NecessityRepository necessityRepository) {

        this.packageRepository = packageRepository;
        this.productRepository = productRepository;
        this.householdRepository = householdRepository;
        this.packageProductsRepository = packageProductsRepository;
        this.necessityRepository = necessityRepository;
    }

    @Override
    public @org.jetbrains.annotations.NotNull List<Package> getAllPackages() {
        return packageRepository.findAll();
    }

    @Override
    public @org.jetbrains.annotations.NotNull Optional<Package> getLastPackageByHouseholdId(Long idHousehold) {
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
    public void updatePackage(@NotNull @org.jetbrains.annotations.NotNull Package packageStatus) {
        Objects.requireNonNull(packageStatus);

        Optional<Household> householdOptional = householdRepository.findById(packageStatus.getHousehold().getId());
        if (householdOptional.isEmpty()) {
            throw new EntityNotFoundException("Unable to find package to update; id: " + packageStatus.getId());
        }
        packageRepository.save(packageStatus);
    }

    @Override
    public void sendPackage(@NotNull @org.jetbrains.annotations.NotNull Package packageStatus) {
        Objects.requireNonNull(packageStatus);
        Optional<Household> householdOptional = householdRepository.findById(packageStatus.getHousehold().getId());

        if (householdOptional.isEmpty()) {
            throw new EntityNotFoundException("Unable to find package to update; id: " + packageStatus.getId());
        }

        packageStatus.setDeliveredDate(LocalDate.now());
        packageRepository.save(packageStatus);
    }

    //TODO nu imi face stergerea
    @Transactional
    @Override
    public void cancelPackage(Long packageId) {
//        packageProductsRepository.delete(packageId);
        Optional<Package> packageOptional = packageRepository.findById(packageId);
        List<PackageProducts> packageProducts = packageProductsRepository.findById_PackageId(packageId);
        packageProductsRepository.deleteAll(packageProducts);

        if (packageOptional.isPresent()) {
            packageRepository.delete(packageOptional.get());
        } else {
            throw new EntityNotFoundException("Unable to find package to delete; id: " + packageId);

        }
    }

//    @Override
//    public PackageState handlePackage(Optional<PackageData> packageData) {
//        if (packageData.isPresent()) {
//            EnumPackageStatus status = packageData.get().getStatus();
//            switch (status) {
//                case CREATED:
//                    return new CreatedState();
//                case READY:
//                    return new ReadyState();
//                case DELIVERED:
//                    return new DeliveredState();
//            }
//        }
//        return new OrderState();
//    }


    @Override
    public void fillPackage(@org.jetbrains.annotations.NotNull Package aPackage) {
        List<ProductNecessity> productNecessityList = createNecessityList(aPackage);
        for (ProductNecessity product : productNecessityList) {

            Optional<Product> productByUuid = productRepository.findProductByUuid(product.getUuid());

            if (productByUuid.isPresent()) {
                if (productByUuid.get().getStock() >= product.getStock()) {
                    productByUuid.get().setStock(productByUuid.get().getStock() - product.getStock());
                } else {
                    productByUuid.get().setStock(0L);
                }
                aPackage.getProducts().add(new PackageProducts(productByUuid.get(), aPackage, product.getStock()));

                productRepository.save(productByUuid.get());
            }
        }
        packageRepository.save(aPackage);
    }

    private List<ProductNecessity> createNecessityList(Package aPackage) {

        ProductVisitor productVisitor = new ProductVisitor(necessityRepository);
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
