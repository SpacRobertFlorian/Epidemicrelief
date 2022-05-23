//package eu.accesa.internship.epidemicrelief.config;
//
//import eu.accesa.internship.epidemicrelief.utils.enums.EnumPackageStatus;
//import eu.accesa.internship.epidemicrelief.utils.enums.PackageEvent;
//import org.springframework.statemachine.listener.StateMachineListenerAdapter;
//import org.springframework.statemachine.state.State;
//
//public class StateMachineListener extends StateMachineListenerAdapter<EnumPackageStatus, PackageEvent> {
//    @Override
//    public void stateChanged(State<EnumPackageStatus, PackageEvent> from, State<EnumPackageStatus, PackageEvent> to) {
//        System.out.println("State changed from " + from.toString() + " to " + to.toString());
//    }
//}
