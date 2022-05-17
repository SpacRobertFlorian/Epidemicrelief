package eu.accesa.internship.epidemicrelief.entity.visitor;

import eu.accesa.internship.epidemicrelief.entity.Child;
import eu.accesa.internship.epidemicrelief.entity.Family;
import eu.accesa.internship.epidemicrelief.entity.NonVegan;
import eu.accesa.internship.epidemicrelief.entity.Vegan;
import eu.accesa.internship.epidemicrelief.entity.visitor.model.ProductNecessity;

import java.util.List;

public interface Visitor {

    List<ProductNecessity> visit(Vegan vegan);

    List<ProductNecessity> visit(Family family);

    List<ProductNecessity> visit(NonVegan nonVegan);

    List<ProductNecessity> visit(Child child);
}
