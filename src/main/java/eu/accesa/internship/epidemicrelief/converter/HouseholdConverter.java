package eu.accesa.internship.epidemicrelief.converter;

import eu.accesa.internship.epidemicrelief.data.HouseholdData;
import eu.accesa.internship.epidemicrelief.model.Household;
import eu.accesa.internship.epidemicrelief.model.Package;
import eu.accesa.internship.epidemicrelief.utils.enums.EnumPackageStatus;
import org.springframework.lang.NonNull;

import java.util.Optional;

public class HouseholdConverter {

    @NonNull
    public HouseholdData from(@NonNull Household source) {
        HouseholdData target = new HouseholdData();

        target.setId(source.getId());
        target.setRepresentative(source.getRepresentative());
        target.setPhone(source.getPhone());
        Optional<Package> sourceStatus = source.getLatestPackage();
        if (sourceStatus.isPresent()) {
            target.setStatus(sourceStatus.get().getStatus());
        } else {
            target.setStatus(EnumPackageStatus.NOT_CREATED);
        }
        target.setPackages(source.getPackages());
        target.setNumberOfChildren(source.getNumberOfChildren());
        target.setNumberOfNonVegans(source.getNumberOfNonVegans());
        target.setNumberOfPeople(source.getNumberOfPeople());
        target.setNumberOfVegans(source.getNumberOfVegans());
        target.setEmail(source.getEmail());
        target.setAddress(source.getContactAddress());
        return target;
    }

    @NonNull
    public Household to(@NonNull HouseholdData source) {
        Household target = new Household();

        target.setRepresentative(source.getRepresentative());
        target.setPhone(source.getPhone());
        target.setId(source.getId());
        target.setNumberOfChildren(source.getNumberOfChildren());
        target.setNumberOfNonVegans(source.getNumberOfNonVegans());
        target.setNumberOfVegans(source.getNumberOfVegans());
        target.setNumberOfPeople(source.getNumberOfPeople());
        target.setEmail(source.getEmail());
        target.setContactAddress(source.getAddress());
        return target;
    }
}
