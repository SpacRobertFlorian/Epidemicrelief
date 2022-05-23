//package eu.accesa.internship.epidemicrelief.config;
//
//import eu.accesa.internship.epidemicrelief.utils.enums.EnumPackageStatus;
//import eu.accesa.internship.epidemicrelief.utils.enums.PackageEvent;
//import org.springframework.statemachine.config.model.*;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//public class CustomStateMachineModelFactory implements StateMachineModelFactory<EnumPackageStatus, PackageEvent> {
//
//
//    @Override
//    public StateMachineModel<EnumPackageStatus, PackageEvent> build() {
//        ConfigurationData<EnumPackageStatus, PackageEvent> configurationData = new ConfigurationData<>();
//        Collection<StateData<EnumPackageStatus, PackageEvent>> stateData = new ArrayList<>();
//        stateData.add(new StateData<EnumPackageStatus, PackageEvent>(EnumPackageStatus.NOT_CREATED));
//        stateData.add(new StateData<EnumPackageStatus, PackageEvent>(EnumPackageStatus.CREATED));
//
//        StatesData<EnumPackageStatus, PackageEvent> statesData = new StatesData<>(stateData);
//        Collection<TransitionData<EnumPackageStatus, PackageEvent>> transitionData = new ArrayList<>();
//        transitionData.add(new TransitionData<EnumPackageStatus, PackageEvent>(EnumPackageStatus.NOT_CREATED, EnumPackageStatus.CREATED, PackageEvent.NOT_CREATED));
//        TransitionsData<EnumPackageStatus, PackageEvent> transitionsData = new TransitionsData<>(transitionData);
//        return new DefaultStateMachineModel<EnumPackageStatus, PackageEvent>(configurationData,
//                statesData, transitionsData);
//    }
//
//    @Override
//    public StateMachineModel<EnumPackageStatus, PackageEvent> build(String machineId) {
//        ConfigurationData<EnumPackageStatus, PackageEvent> configurationData = new ConfigurationData<>();
//        Collection<StateData<EnumPackageStatus, PackageEvent>> stateData = new ArrayList<>();
//        stateData.add(new StateData<EnumPackageStatus, PackageEvent>(EnumPackageStatus.NOT_CREATED));
//        stateData.add(new StateData<EnumPackageStatus, PackageEvent>(EnumPackageStatus.CREATED));
//
//        StatesData<EnumPackageStatus, PackageEvent> statesData = new StatesData<>(stateData);
//        Collection<TransitionData<EnumPackageStatus, PackageEvent>> transitionData = new ArrayList<>();
//        transitionData.add(new TransitionData<EnumPackageStatus, PackageEvent>(EnumPackageStatus.NOT_CREATED, EnumPackageStatus.CREATED, PackageEvent.NOT_CREATED));
//        TransitionsData<EnumPackageStatus, PackageEvent> transitionsData = new TransitionsData<>(transitionData);
//        return new DefaultStateMachineModel<EnumPackageStatus, PackageEvent>(configurationData,
//                statesData, transitionsData);
//    }
//}
