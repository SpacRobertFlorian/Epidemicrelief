package eu.accesa.internship.epidemicrelief.visitor.model;

import eu.accesa.internship.epidemicrelief.visitor.Visitor;

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
