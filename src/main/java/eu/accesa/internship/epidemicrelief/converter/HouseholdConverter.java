package eu.accesa.internship.epidemicrelief.converter;

import eu.accesa.internship.epidemicrelief.data.HouseholdData;
import eu.accesa.internship.epidemicrelief.model.Household;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class HouseholdConverter {

    @NonNull
    public HouseholdData from(@NonNull Household source) {
        HouseholdData target = new HouseholdData();

        target.setId(source.getId());
        target.setRepresentative(source.getRepresentative());
        target.setPhone(source.getPhone());

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
