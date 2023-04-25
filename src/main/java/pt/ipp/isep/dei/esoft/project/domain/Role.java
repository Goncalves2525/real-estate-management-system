package pt.ipp.isep.dei.esoft.project.domain;

public enum Role {
    ADMIN,
    EMPLOYEE,
    STORE_NETWORK_MANAGER,
    STORE_MANAGER,
    CLIENT;

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        switch (this) {
            case ADMIN:
                return "Admin";
            case EMPLOYEE:
                return "Employee";
            case STORE_NETWORK_MANAGER:
                return "Store Network Manager";
            case STORE_MANAGER:
                return "Store Manager";
            case CLIENT:
                return "Client";
            default:
                return "User has no role";
        }
    }

}
