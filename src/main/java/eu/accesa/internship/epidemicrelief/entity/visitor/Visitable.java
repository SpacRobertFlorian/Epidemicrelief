package eu.accesa.internship.epidemicrelief.entity.visitor;

import eu.accesa.internship.epidemicrelief.entity.visitor.model.ProductNecessity;
import eu.accesa.internship.epidemicrelief.model.Necessity;

import java.util.List;

public interface Visitable {
    List<ProductNecessity> productNecessityList(Visitor visitor);
}
