package eu.accesa.internship.epidemicrelief.utils.enums;

public enum EnumPackageStatus {
    NOT_CREATED {
        @Override
        public EnumPackageStatus next() {
            return CREATED;
        }

        @Override
        public EnumPackageStatus getStatus() {
            return NOT_CREATED;
        }
    },
    CREATED {
        @Override
        public EnumPackageStatus next() {
            return READY;
        }

        @Override
        public EnumPackageStatus getStatus() {
            return CREATED;
        }

    },
    READY {
        @Override
        public EnumPackageStatus next() {
            return DELIVERED;
        }

        @Override
        public EnumPackageStatus getStatus() {
            return READY;
        }


    },
    DELIVERED {
        @Override
        public EnumPackageStatus next() {
            return this;
        }

        @Override
        public EnumPackageStatus getStatus() {
            return DELIVERED;
        }

    };

    public abstract EnumPackageStatus next();

    public abstract EnumPackageStatus getStatus();
}
