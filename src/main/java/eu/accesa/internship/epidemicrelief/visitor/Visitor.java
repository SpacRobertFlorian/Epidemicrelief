package eu.accesa.internship.epidemicrelief.visitor;

import eu.accesa.internship.epidemicrelief.visitor.model.Child;
import eu.accesa.internship.epidemicrelief.visitor.model.Family;
import eu.accesa.internship.epidemicrelief.visitor.model.NonVegan;
import eu.accesa.internship.epidemicrelief.visitor.model.Vegan;
import eu.accesa.internship.epidemicrelief.visitor.model.ProductNecessity;

import java.util.List;

public interface Visitor {

    List<ProductNecessity> visit(Vegan vegan);

    List<ProductNecessity> visit(Family family);

    List<ProductNecessity> visit(NonVegan nonVegan);

    List<ProductNecessity> visit(Child child);
}
