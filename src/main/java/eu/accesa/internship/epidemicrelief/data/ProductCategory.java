package eu.accesa.internship.epidemicrelief.data;

public enum ProductCategory {
    FOOD("Food"), DRINKS("Drinks"), MEDICINE("Medicine");
    private final String category;

    ProductCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }
}
