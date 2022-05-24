package eu.accesa.internship.epidemicrelief.converter;

import eu.accesa.internship.epidemicrelief.data.PackageData;
import eu.accesa.internship.epidemicrelief.data.ProductData;
import eu.accesa.internship.epidemicrelief.model.Package;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class PackageConverter {
    /**
     * Converts Package to PackageData
     *
     * @param source the {@link Package} to be converted
     * @return PackageData containing data from Package
     */
    @NonNull
    public PackageData from(@NonNull Package source) {
        PackageData target = new PackageData();

        target.setId(source.getId());
        target.setHousehold(source.getHousehold());
        target.setStatus(source.getStatus());
        target.setCreatedDate(source.getCreatedDate());
        target.setProducts(source.getProducts());
        target.setDeliveredDate(source.getDeliveredDate());

        if (source.getDeliveredDate() != null) {
            target.setDateDiff(DAYS.between(source.getDeliveredDate(), LocalDate.now()));
        } else {
            target.setDateDiff(null);

        }
        return target;
    }

    /**
     * Converts PackageData to Package
     *
     * @param source the {@link PackageData} to be converted
     * @return Package containing data from PackageData
     */
    @NotNull
    public Package to(@NonNull PackageData source) {
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
