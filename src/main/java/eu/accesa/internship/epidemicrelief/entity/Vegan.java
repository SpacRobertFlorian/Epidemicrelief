package eu.accesa.internship.epidemicrelief.entity;

import eu.accesa.internship.epidemicrelief.entity.visitor.Visitable;
import eu.accesa.internship.epidemicrelief.entity.visitor.Visitor;
import eu.accesa.internship.epidemicrelief.entity.visitor.model.ProductNecessity;

import java.util.List;

public class Vegan extends HouseholdMembers implements Visitable {
    public Vegan(int numberOfPersons) {
        super(numberOfPersons);
    }

    @Override
    public List<ProductNecessity> productNecessityList(Visitor visitor) {
        return visitor.visit(this);
    }
}
