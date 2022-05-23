package eu.accesa.internship.epidemicrelief.config;

import eu.accesa.internship.epidemicrelief.utils.enums.EnumPackageStatus;
import eu.accesa.internship.epidemicrelief.utils.enums.PackageEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import java.util.UUID;

@SpringBootTest
class StateMachineConfigurationTest {
//    @Autowired
//    StateMachineFactory<EnumPackageStatus, PackageEvent> factory;
//
//    @Test
//    public void testNewPackage() {
//        StateMachine<EnumPackageStatus, PackageEvent> sm = factory.getStateMachine(UUID.randomUUID());
//
//        sm.start();
//        System.out.println(sm.getState().toString());
//
//        sm.sendEvent(PackageEvent.READY);
//        System.out.println(sm.getState());
//    }

}