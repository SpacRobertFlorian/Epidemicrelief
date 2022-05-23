package eu.accesa.internship.epidemicrelief.entity;

import eu.accesa.internship.epidemicrelief.entity.visitor.Visitor;
import eu.accesa.internship.epidemicrelief.entity.visitor.model.ProductNecessity;

import java.util.List;

public class Family implements HouseholdMembers {

    private Long numberOfPersons;

    public Family(Long numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public Long getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(Long numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    @Override
    public List<ProductNecessity> productNecessityList(Visitor visitor) {
        return visitor.visit(this);
    }
}
