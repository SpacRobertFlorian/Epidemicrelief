package eu.accesa.internship.epidemicrelief.utils;

import eu.accesa.internship.epidemicrelief.utils.enums.Currency;
import org.springframework.beans.factory.annotation.Value;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.ResourceBundle;

public class Internationalization {
    //TODO nu merge nu stiu dc nu ia valorile din yaml
    //TODO continuat task 8
    //TODO nu merge @Value deoarece fac cu new Internationalization sa ii fac autowire
    @Value("${internationalization.country}")
    private String country = "EN";
    @Value("${internationalization.lang}")
    private String lang = "en";

    private final Locale locale = new Locale(country, lang);

    private String removeWhitespaces(String str) {
        str = str.replaceAll("\\s+", "");
        return str;
    }

    public String translateWord(String word) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundle", locale);
        return resourceBundle.getString(removeWhitespaces(word));
    }

    public String getCurrency(String country) {
        Locale localeCurrency = new Locale("country", "currency");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundle", localeCurrency);
        return resourceBundle.getString(country);
    }

    public String getCountry() {
        return country;
    }

    //convert current currency to a specific one
    public Double calculateCurrency(Double price, Currency localCurrency) {
        Locale localeCurrency = new Locale("currency", localCurrency.getCategory());
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundle", localeCurrency);
        String strCurrency = getCurrency(country);
        Double currency = Double.valueOf(resourceBundle.getString(strCurrency));
        return price * currency;
    }


    public String getDateFormat() {
        Locale localeDate = new Locale("country", "dateformat");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundle", localeDate);
        return resourceBundle.getString(country);
    }

    public String getGMT() {
        Locale localeDate = new Locale("country", "gmt");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundle", localeDate);
        return resourceBundle.getString(country);
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
