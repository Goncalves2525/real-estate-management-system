package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

/**
 * Transaction Type, Rent, Sale or Buy.
 */
public enum TransactionType implements Serializable {
    RENT,
    SALE,
    BUY;

    /**
     * Returns a string representation of the object.
     *
     * @return string representation of the object.
     */
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

