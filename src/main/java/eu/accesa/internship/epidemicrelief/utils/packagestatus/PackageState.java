package eu.accesa.internship.epidemicrelief.utils.packagestatus;

public interface PackageState {
    void next(PackageStatus pkg);

    String getStatus();
}
