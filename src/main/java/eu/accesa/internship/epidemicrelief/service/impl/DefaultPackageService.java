package eu.accesa.internship.epidemicrelief.service.impl;

import eu.accesa.internship.epidemicrelief.model.Household;
import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.model.PackageProducts;
import eu.accesa.internship.epidemicrelief.model.Product;
import eu.accesa.internship.epidemicrelief.repository.*;
import eu.accesa.internship.epidemicrelief.service.PackageService;
import eu.accesa.internship.epidemicrelief.utils.enums.EnumPackageStatus;
import eu.accesa.internship.epidemicrelief.visitor.ProductVisitor;
import eu.accesa.internship.epidemicrelief.visitor.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        householdPackage.setCreatedDate(LocalDateTime.now());

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
    public void sendPackage(@NotNull Package packageStatus) {
        Objects.requireNonNull(packageStatus);
        Optional<Household> householdOptional = householdRepository.findById(packageStatus.getHousehold().getId());

        if (householdOptional.isEmpty()) {
            throw new EntityNotFoundException("Unable to find package to update; id: " + packageStatus.getId());
        }

        packageStatus.setDeliveredDate(LocalDateTime.now());
        packageRepository.save(packageStatus);
    }

    @Transactional
    @Override
    public void cancelPackage(Long packageId) {

        Optional<Package> packageOptional = packageRepository.findById(packageId);
        List<PackageProducts> packageProducts = packageProductsRepository.findById_PackageId(packageId);
        rollBack(packageProducts);
        packageProductsRepository.deleteAll(packageProducts);

        if (packageOptional.isPresent()) {
            packageRepository.delete(packageOptional.get());
        } else {
            throw new EntityNotFoundException("Unable to find package to delete; id: " + packageId);

        }
    }

    private void rollBack(List<PackageProducts> packageProducts) {

        for (PackageProducts p : packageProducts) {
            Optional<Product> productRollBack = productRepository.findProductByUuid(p.getProduct().getUuid());
            productRollBack.ifPresent(product -> product.setStock(product.getStock() + p.getStock()));
        }
    }

    @Override
    public void fillPackage(@NotNull Package aPackage) {

        List<ProductNecessity> productNecessityList = createNecessityList(aPackage);
        for (ProductNecessity product : productNecessityList) {

            Optional<Product> productByUuid = productRepository.findProductByUuid(product.getUuid());

            if (productByUuid.isPresent()) {
                Product productFromUuid = productByUuid.get();
                Long productStock = product.getStock();
                if (productFromUuid.getStock() >= productStock) {
                    productFromUuid.setStock(productFromUuid.getStock() - productStock);
                } else {
                    productFromUuid.setStock(0L);
                }
                aPackage.getProducts().add(new PackageProducts(productFromUuid, aPackage, productStock));

                productRepository.save(productFromUuid);
            }
        }
        packageRepository.save(aPackage);
    }

    private List<ProductNecessity> createNecessityList(Package aPackage) {

        ProductVisitor productVisitor = new ProductVisitor(necessityRepository);
        Household household = aPackage.getHousehold();
        List<HouseholdMember> members = new LinkedList<>();
        List<ProductNecessity> productNecessityList = new ArrayList<>();

        members.add(new Family(household.getNumberOfPeople()));
        members.add(new Child(household.getNumberOfChildren()));
        members.add(new Vegan(household.getNumberOfVegans()));
        members.add(new NonVegan(household.getNumberOfNonVegans()));

        for (HouseholdMember householdMembers : members) {
            productNecessityList.addAll(householdMembers.productNecessityList(productVisitor));
        }
        return productNecessityList;
    }

}
