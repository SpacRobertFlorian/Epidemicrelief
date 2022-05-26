package eu.accesa.internship.epidemicrelief.entity.visitor;

import eu.accesa.internship.epidemicrelief.entity.Child;
import eu.accesa.internship.epidemicrelief.entity.Family;
import eu.accesa.internship.epidemicrelief.entity.NonVegan;
import eu.accesa.internship.epidemicrelief.entity.Vegan;
import eu.accesa.internship.epidemicrelief.entity.visitor.model.ProductNecessity;
import eu.accesa.internship.epidemicrelief.model.Necessity;
import eu.accesa.internship.epidemicrelief.repository.NecessityRepository;
import eu.accesa.internship.epidemicrelief.utils.enums.PersonCategory;

import java.util.ArrayList;
import java.util.List;

public class ProductVisitor implements Visitor {

    private final NecessityRepository necessityRepository;

    public ProductVisitor(NecessityRepository necessityRepository) {
        this.necessityRepository = necessityRepository;
    }

    @Override
    public List<ProductNecessity> visit(Vegan vegan) {
        List<ProductNecessity> productNecessityList = new ArrayList<>();

        List<Necessity> necessities = necessityRepository.findAllByPersonCategory(PersonCategory.VEGAN);
        for (Necessity necessity : necessities) {
            ProductNecessity productNecessity = new ProductNecessity(necessity.getProduct().getUuid(), necessity.getQuantity());
            productNecessity.setStock(necessity.getQuantity() * vegan.getNumberOfPersons());
            productNecessityList.add(productNecessity);
        }
        return productNecessityList;
    }

    @Override
    public List<ProductNecessity> visit(Family family) {
        List<ProductNecessity> productNecessityList = new ArrayList<>();
        //TODO sa fac o filtrare sa nu fie null
        List<Necessity> necessities = necessityRepository.findAllByPersonCategory(PersonCategory.FAMILY);

        for (Necessity necessity : necessities) {
            if (necessity != null) {
                ProductNecessity productNecessity = new ProductNecessity(necessity.getProduct().getUuid(), necessity.getQuantity());
                productNecessity.setStock(necessity.getQuantity() * family.getNumberOfPersons() * 60);
                productNecessityList.add(productNecessity);
            }
        }
        return productNecessityList;
    }

    @Override
    public List<ProductNecessity> visit(NonVegan nonVegan) {
        List<ProductNecessity> productNecessityList = new ArrayList<>();
        List<Necessity> necessities = necessityRepository.findAllByPersonCategory(PersonCategory.NON_VEGAN);
        for (Necessity necessity : necessities) {
            ProductNecessity productNecessity = new ProductNecessity(necessity.getProduct().getUuid(), necessity.getQuantity());
            productNecessity.setStock(necessity.getQuantity() * nonVegan.getNumberOfPersons());
            productNecessityList.add(productNecessity);
        }
        return productNecessityList;

    }

    @Override
    public List<ProductNecessity> visit(Child child) {
        List<ProductNecessity> productNecessityList = new ArrayList<>();
        List<Necessity> necessities = necessityRepository.findAllByPersonCategory(PersonCategory.CHILD);
        for (Necessity necessity : necessities) {
            if (necessity.getProduct() != null) {
                ProductNecessity productNecessity = new ProductNecessity(necessity.getProduct().getUuid(), necessity.getQuantity());
                productNecessity.setStock(necessity.getQuantity() * child.getNumberOfPersons());
                productNecessityList.add(productNecessity);
            }
        }
        return productNecessityList;

    }
}
