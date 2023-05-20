package pt.ipp.isep.dei.esoft.project.domain;

public enum OrderState {
    PENDING,
    ACCEPTED,
    REJECTED;

    @Override
    public String toString() {
        switch (this) {
            case PENDING:
                return "PENDING";
            case ACCEPTED:
                return "ACCEPTED";
            case REJECTED:
                return "REJECTED";
            default:
                return "";
        }
    }


}
