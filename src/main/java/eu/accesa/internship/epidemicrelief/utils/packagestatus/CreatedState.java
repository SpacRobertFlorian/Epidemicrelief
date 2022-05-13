package eu.accesa.internship.epidemicrelief.utils.packagestatus;

public class CreatedState implements PackageState {
    @Override
    public void next(PackageStatus pkg) {
        pkg.setState(new ReadyState());
    }

    @Override
    public String getStatus() {
        return "Create";
    }
}
