package eu.accesa.internship.epidemicrelief.entity;

public class HouseholdMembers {
    protected int numberOfPersons;

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public HouseholdMembers(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }
}
