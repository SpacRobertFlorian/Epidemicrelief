//package eu.accesa.internship.epidemicrelief.config;
//
//import eu.accesa.internship.epidemicrelief.utils.enums.EnumPackageStatus;
//import eu.accesa.internship.epidemicrelief.utils.enums.PackageEvent;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.statemachine.StateMachine;
//import org.springframework.statemachine.config.EnableStateMachineFactory;
//import org.springframework.statemachine.config.StateMachineBuilder;
//import org.springframework.statemachine.listener.StateMachineListener;
//import org.springframework.statemachine.listener.StateMachineListenerAdapter;
//import org.springframework.statemachine.state.State;
//
//import java.util.EnumSet;
//
//
//@Configuration
//@EnableStateMachineFactory
//public class StateMachineConfiguration {
//
//    @Bean
//    public StateMachine<EnumPackageStatus, PackageEvent> stateMachine(StateMachineListener<EnumPackageStatus, PackageEvent> listener) throws Exception {
//        StateMachineBuilder.Builder<EnumPackageStatus, PackageEvent> builder = StateMachineBuilder.builder();
//
//        builder.configureStates().withStates()
//                .initial(EnumPackageStatus.NOT_CREATED) //initial state of the state machine is gonna be new
//                .states(EnumSet.allOf(EnumPackageStatus.class)) //loading up all the States
//                .end(EnumPackageStatus.DELIVERED);
//
//        builder.configureTransitions().withExternal()
//                .source(EnumPackageStatus.NOT_CREATED).target(EnumPackageStatus.NOT_CREATED).event(PackageEvent.NOT_CREATED)
//                .and().withExternal()
//                .source(EnumPackageStatus.CREATED).target(EnumPackageStatus.CREATED).event(PackageEvent.CREATED)
//                .and().withExternal()
//                .source(EnumPackageStatus.READY).target(EnumPackageStatus.READY).event(PackageEvent.READY)
//                .and().withExternal()
//                .source(EnumPackageStatus.DELIVERED).target(EnumPackageStatus.DELIVERED).event(PackageEvent.DELIVERED);
//
//        StateMachine<EnumPackageStatus, PackageEvent> stateMachine = builder.build();
//        stateMachine.addStateListener(listener);
//        return stateMachine;
//    }
//
//    @Bean
//    public StateMachineListener<EnumPackageStatus, PackageEvent> listener() {
//        return new StateMachineListenerAdapter<EnumPackageStatus, PackageEvent>() {
//            @Override
//            public void stateChanged(State<EnumPackageStatus, PackageEvent> from, State<EnumPackageStatus, PackageEvent> to) {
//            }
//        };
//    }
//}
////@Configuration
////@EnableStateMachineFactory
////public class StateMachineConfiguration extends EnumStateMachineConfigurerAdapter<EnumPackageStatus, PackageEvent> {
////
////    @Override
////    public void configure(StateMachineStateConfigurer<EnumPackageStatus, PackageEvent> states) throws Exception {
////        states.withStates()
////                .initial(EnumPackageStatus.NOT_CREATED) //initial state of the state machine is gonna be new
////                .states(EnumSet.allOf(EnumPackageStatus.class)) //loading up all the States
////                .end(EnumPackageStatus.DELIVERED);
////    }
////
////    @Override
////    public void configure(StateMachineTransitionConfigurer<EnumPackageStatus, PackageEvent> transitions) throws Exception {
////        transitions.withExternal()
////                .source(EnumPackageStatus.NOT_CREATED).target(EnumPackageStatus.NOT_CREATED).event(PackageEvent.NOT_CREATED)
////                .and().withExternal()
////                .source(EnumPackageStatus.CREATED).target(EnumPackageStatus.CREATED).event(PackageEvent.CREATED)
////                .and().withExternal()
////                .source(EnumPackageStatus.READY).target(EnumPackageStatus.READY).event(PackageEvent.READY)
////                .and().withExternal()
////                .source(EnumPackageStatus.DELIVERED).target(EnumPackageStatus.DELIVERED).event(PackageEvent.DELIVERED);
////    }
////
////    @Override
////    public void configure(StateMachineConfigurationConfigurer<EnumPackageStatus, PackageEvent> config) throws Exception {
////        config.withConfiguration().autoStartup(false).listener(new StateMachineListener());
////    }
////
////    @Bean
////    public StateMachineModelFactory<EnumPackageStatus, PackageEvent> modelFactory() {
////        return new CustomStateMachineModelFactory();
////    }
////}
