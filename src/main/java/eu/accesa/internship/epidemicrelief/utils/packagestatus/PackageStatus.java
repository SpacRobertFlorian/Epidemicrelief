package eu.accesa.internship.epidemicrelief.utils.packagestatus;

public class PackageStatus {
    private PackageState state = new OrderState();

    public PackageState getState() {
        return state;
    }

    public void setState(PackageState state) {
        this.state = state;
    }

    public void nextState() {
        state.next(this);
    }

    public String getStatus() {
        return state.getStatus();
    }

}
