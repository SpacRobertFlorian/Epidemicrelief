package eu.accesa.internship.epidemicrelief.utils;

import eu.accesa.internship.epidemicrelief.exception.CustomException;
import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.model.Product;
import eu.accesa.internship.epidemicrelief.repository.ProductRepository;
import eu.accesa.internship.epidemicrelief.utils.enums.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class Internationalization {
    //TODO nu merge nu stiu dc nu ia valorile din yaml
    //TODO continuat task 8
    //TODO nu merge @Value deoarece fac cu new Internationalization sa ii fac autowire
    //TODO sa trimit si Locale prin constructor

    private final ProductRepository productRepository;

    private Locale locale;

    public Internationalization(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private String removeWhitespaces(String str) {
        str = str.replaceAll("\\s+", "");
        return str;
    }

    public String translateStaticWords(String word) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundle", locale);
        return resourceBundle.getString(removeWhitespaces(word));
    }

    public String translateDynamicWords(String word) {
        Optional<Product> product = productRepository.findProductByName(word);
        if (product.isPresent()) {
            switch (locale.getCountry()) {
                case "RO":
                    return product.get().getNameRo();
                case "FR":
                    return product.get().getNameFr();
                default:
                    return product.get().getName();
            }
        } else {
            throw new CustomException("Word is not present in the database");
        }
    }

    public String getCurrency(String country) {
        Locale localeCurrency = new Locale("country", "currency");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundle", localeCurrency);
        return resourceBundle.getString(country);
    }

    public String getCountry() {
        return locale.getCountry();
    }

    //Convert current currency to a specific one
    public Double calculateCurrency(Double price, Currency localCurrency) {
        Locale localeCurrency = new Locale("currency", localCurrency.getCategory());
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundle", localeCurrency);
        String strCurrency = getCurrency(locale.getCountry());
        Double currency = Double.valueOf(resourceBundle.getString(strCurrency));
        return price * currency;
    }


    public String getDateFormat() {
        Locale localeDate = new Locale("country", "dateformat");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundle", localeDate);
        return resourceBundle.getString(locale.getCountry());
    }

    public String getGMT() {
        Locale localeDate = new Locale("country", "gmt");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundle", localeDate);
        return resourceBundle.getString(locale.getCountry());
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

//    public LocalDateTime getDateFormat(LocalDateTime dateTime) {
//        String dateFormat = getDateFormat();
//
//        String gmt = getGMT();
//        ZoneId id = ZoneId.of(gmt);
//        LocalDateTime date = LocalDateTime.now(id);
//        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat + " HH:mm");
//        long minutes = ChronoUnit.MINUTES.between(dateTime, date);
//        long hours = ChronoUnit.HOURS.between(dateTime, date);
//
//        date.minusHours(hours);
//        date.minusMinutes(minutes);
//
//        return date;
//    }


}
