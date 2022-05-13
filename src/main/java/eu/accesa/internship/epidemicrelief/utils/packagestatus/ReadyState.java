package eu.accesa.internship.epidemicrelief.utils.packagestatus;

public class ReadyState implements PackageState {
    @Override
    public void next(PackageStatus pkg) {
        pkg.setState(new DeliveredState());
    }

    @Override
    public String getStatus() {
        return "Ready";
    }
}
