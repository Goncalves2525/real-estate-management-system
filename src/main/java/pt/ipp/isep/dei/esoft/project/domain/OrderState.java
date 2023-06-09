package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

/**
 * Enumeration of Order State.
 * Can be PENDING, ACCEPTED or REJECTED.
 */
public enum OrderState implements Serializable {
    PENDING,
    ACCEPTED,
    REJECTED;

    /**
     * @return string of the order state
     */
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
