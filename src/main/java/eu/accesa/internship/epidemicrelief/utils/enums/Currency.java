package eu.accesa.internship.epidemicrelief.utils.enums;

public enum Currency {
    EURO("Euro"), POUND("Pound"), DOLLAR("Dollar"), RON("Ron");
    private final String currency;

    Currency(String category) {
        this.currency = category;
    }

    public String getCategory() {
        return this.currency;
    }

}
