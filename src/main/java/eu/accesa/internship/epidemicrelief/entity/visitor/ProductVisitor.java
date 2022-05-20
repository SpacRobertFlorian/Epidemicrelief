package eu.accesa.internship.epidemicrelief.entity.visitor;

import eu.accesa.internship.epidemicrelief.entity.Child;
import eu.accesa.internship.epidemicrelief.entity.Family;
import eu.accesa.internship.epidemicrelief.entity.NonVegan;
import eu.accesa.internship.epidemicrelief.entity.Vegan;
import eu.accesa.internship.epidemicrelief.entity.visitor.model.ProductNecessity;

import java.util.ArrayList;
import java.util.List;

public class ProductVisitor implements Visitor {

    //    private static final String UUID_VITAMIN_JUICE = "988159b6-3246-4eeb-b83d-35286d5df054";
//    private static final String UUID_CANNED_VEGETABLES = "540a916d-87a6-4951-b011-eef844ad516a";
//    private static final String UUID_WATER = "1a90d1a0-b3aa-4b98-946b-2bd5ac89c79b";
//    private static final String UUID_MEAT = "b84877ec-c768-43d4-9b21-0c36023ab078";
//    private static final String UUID_CHOCOLATE = "668cdeb8-0119-4637-bcd4-5cce8f67b16b";
    private static final String UUID_VITAMIN_JUICE = "23f9cad8-c02a-4b90-a330-c727dfa7b94c";
    private static final String UUID_CANNED_VEGETABLES = "2fa9e768-be37-450c-b1d1-6c7bd0b1bced";
    private static final String UUID_WATER = "4cafb0c1-1a55-46d4-baea-530ca06cda30";
    private static final String UUID_MEAT = "fd897188-c248-440d-b625-9adfcd2d668f";
    private static final String UUID_CHOCOLATE = "a88fdac5-ab67-4bbb-b6f6-f874032244c8";

    @Override
    public List<ProductNecessity> visit(Vegan vegan) {
        List<ProductNecessity> products = new ArrayList<>();
        if (vegan != null) {
            products.add(new ProductNecessity(UUID_CANNED_VEGETABLES, vegan.getNumberOfPersons() * 10));
        }
        return products;
    }

    @Override
    public List<ProductNecessity> visit(Family family) {
        List<ProductNecessity> products = new ArrayList<>();
        if (family != null) {
            products.add(new ProductNecessity(UUID_WATER, family.getNumberOfPersons() * 60));
        }
        return products;
    }

    @Override
    public List<ProductNecessity> visit(NonVegan nonVegan) {
        List<ProductNecessity> products = new ArrayList<>();
        if (nonVegan != null) {
            products.add(new ProductNecessity(UUID_MEAT, nonVegan.getNumberOfPersons() * 10));
        }
        return products;
    }

    @Override
    public List<ProductNecessity> visit(Child child) {
        List<ProductNecessity> products = new ArrayList<>();
        if (child != null) {
            products.add(new ProductNecessity(UUID_VITAMIN_JUICE, child.getNumberOfPersons()));
            products.add(new ProductNecessity(UUID_CHOCOLATE, child.getNumberOfPersons()));
        }
        return products;
    }
}
