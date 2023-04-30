package pt.ipp.isep.dei.esoft.project.domain;

public enum TransactionType {
    RENT,
    SALE,
    BUY;

    @Override
    public String toString() {
        switch (this) {
            case RENT:
                return "Rent";
            case SALE:
                return "Sale";
            case BUY:
                return "Buy";
            default:
                return "";
        }
    }
}

