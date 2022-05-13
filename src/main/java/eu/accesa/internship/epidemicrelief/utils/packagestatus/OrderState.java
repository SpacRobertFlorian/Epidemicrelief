package eu.accesa.internship.epidemicrelief.utils.packagestatus;

public class OrderState implements PackageState {

    @Override
    public void next(PackageStatus pkg) {
        pkg.setState(new CreatedState());
    }

    @Override
    public String getStatus() {
        return "Not created";
    }
}
