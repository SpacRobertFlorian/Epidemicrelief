package eu.accesa.internship.epidemicrelief.visitor;

import eu.accesa.internship.epidemicrelief.visitor.model.*;
import eu.accesa.internship.epidemicrelief.model.Necessity;
import eu.accesa.internship.epidemicrelief.repository.NecessityRepository;
import eu.accesa.internship.epidemicrelief.utils.enums.PersonCategory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductVisitor implements Visitor {

    private final NecessityRepository necessityRepository;

    public ProductVisitor(NecessityRepository necessityRepository) {
        this.necessityRepository = necessityRepository;
    }

    @NotNull
    private List<ProductNecessity> getProductNecessities(HouseholdMember member, PersonCategory category) {

        List<ProductNecessity> productNecessityList = new ArrayList<>();
        List<Necessity> necessities = necessityRepository.findAllByPersonCategory(category);

        for (Necessity necessity : necessities) {
            if (necessity != null) {
                ProductNecessity productNecessity = new ProductNecessity(necessity.getProduct().getUuid(), necessity.getQuantity());
                productNecessity.setStock(necessity.getQuantity() * member.getNumberOfPersons());
                productNecessityList.add(productNecessity);
            }
        }
        return productNecessityList;
    }

    @Override
    public List<ProductNecessity> visit(Family family) {
        List<ProductNecessity> productNecessityList = new ArrayList<>();
        List<Necessity> necessities = necessityRepository.findAllByPersonCategory(PersonCategory.FAMILY)
                .stream().filter(Objects::nonNull).collect(Collectors.toList());

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
    public List<ProductNecessity> visit(Vegan vegan) {
        return getProductNecessities(vegan, PersonCategory.VEGAN);
    }

    @Override
    public List<ProductNecessity> visit(NonVegan nonVegan) {
        return getProductNecessities(nonVegan, PersonCategory.NON_VEGAN);
    }

    @Override
    public List<ProductNecessity> visit(Child child) {
        return getProductNecessities(child, PersonCategory.CHILD);
    }
}
