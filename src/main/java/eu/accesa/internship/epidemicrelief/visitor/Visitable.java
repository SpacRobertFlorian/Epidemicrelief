package eu.accesa.internship.epidemicrelief.visitor;

import eu.accesa.internship.epidemicrelief.visitor.model.ProductNecessity;

import java.util.List;

public interface Visitable {
    List<ProductNecessity> productNecessityList(Visitor visitor);
}
