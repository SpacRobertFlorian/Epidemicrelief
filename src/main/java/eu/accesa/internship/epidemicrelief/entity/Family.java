package eu.accesa.internship.epidemicrelief.entity;

import eu.accesa.internship.epidemicrelief.entity.visitor.Visitable;
import eu.accesa.internship.epidemicrelief.entity.visitor.Visitor;
import eu.accesa.internship.epidemicrelief.entity.visitor.model.ProductNecessity;

import java.util.List;

public class Family implements HouseholdMembers {

    private int numberOfPersons;

    public Family(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    @Override
    public List<ProductNecessity> productNecessityList(Visitor visitor) {
        return visitor.visit(this);
    }
}
