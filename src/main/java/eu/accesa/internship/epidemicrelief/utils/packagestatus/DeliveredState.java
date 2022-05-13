package eu.accesa.internship.epidemicrelief.utils.packagestatus;

public class DeliveredState implements PackageState {
    @Override
    public void next(PackageStatus pkg) {
        //Stop ca nu mai are unde sa mearga fra
    }

    @Override
    public String getStatus() {
        return "Delivered";
    }

}
