package eu.accesa.internship.epidemicrelief.converter;

import eu.accesa.internship.epidemicrelief.data.PackageData;
import eu.accesa.internship.epidemicrelief.model.Package;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class PackageConverter {
    @NotNull
    public PackageData from(Package source) {
        PackageData target = new PackageData();

        target.setId(source.getId());
        target.setHousehold(source.getHousehold());
        target.setStatus(source.getStatus());
        target.setCreatedDate(source.getCreatedDate());
        target.setProducts(source.getProducts());
        //target.setProducts(source.getProducts());
        target.setDeliveredDate(source.getDeliveredDate());
        if (source.getDeliveredDate() != null) {
            target.setDateDiff(DAYS.between(source.getDeliveredDate(), LocalDate.now()));
        } else {
            target.setDateDiff(null);

        }
        return target;
    }

    public Package to(PackageData source) {
        Package target = new Package();

        target.setProducts(source.getProducts());
        target.setStatus(source.getStatus());
        target.setCreatedDate(source.getCreatedDate());
        target.setHousehold(source.getHousehold());
        target.setId(source.getId());
        target.setDeliveredDate(source.getDeliveredDate());

        return target;
    }
}
