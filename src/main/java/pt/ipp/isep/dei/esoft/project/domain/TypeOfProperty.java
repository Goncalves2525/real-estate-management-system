package pt.ipp.isep.dei.esoft.project.domain;

public enum TypeOfProperty {
    HOUSE,
    APARTMENT,
    LAND;

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        switch (this) {
            case HOUSE:
                return "House";
            case APARTMENT:
                return "Apartment";
            case LAND:
                return "Land";
            default:
                return "";
        }
    }
}
