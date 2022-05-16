package eu.accesa.internship.epidemicrelief.converter;

import eu.accesa.internship.epidemicrelief.data.HouseholdData;
import eu.accesa.internship.epidemicrelief.model.Household;
import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.utils.enums.EnumPackageStatus;
import org.springframework.lang.NonNull;

public class HouseholdConverter {

    @NonNull
    public HouseholdData from(@NonNull Household source) {
        HouseholdData target = new HouseholdData();

        target.setId(source.getId());
        target.setRepresentative(source.getRepresentative());
        target.setPhone(source.getPhone());
        EnumPackageStatus status = source.getLatestPackage().map(Package::getStatus).orElse(EnumPackageStatus.NOT_CREATED);
        target.setStatus(status);

        return target;
    }

    @NonNull
    public Household to(@NonNull HouseholdData source) {
        Household target = new Household();

        target.setRepresentative(source.getRepresentative());
        target.setPhone(source.getPhone());
        target.setId(source.getId());

        return target;
    }
}
