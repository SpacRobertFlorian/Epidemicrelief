package eu.accesa.internship.epidemicrelief.repository;

import eu.accesa.internship.epidemicrelief.model.DeliveryDateThreshold;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryDateThresholdRepository extends CrudRepository<DeliveryDateThreshold, Long> {

    @NonNull
    Optional<DeliveryDateThreshold> findById(@NotNull Long id);
}
