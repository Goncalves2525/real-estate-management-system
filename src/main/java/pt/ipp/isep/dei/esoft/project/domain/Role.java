package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

public enum Role implements Serializable {
    ADMIN,
    AGENT,
    STORE_NETWORK_MANAGER,
    STORE_MANAGER,
    CLIENT;

    public int getValue() {
        return this.ordinal();
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        switch (this) {
            case ADMIN:
                return "ADMIN";
            case AGENT:
                return "AGENT";
            case STORE_NETWORK_MANAGER:
                return "STORE_NETWORK_MANAGER";
            case STORE_MANAGER:
                return "STORE_MANAGER";
            case CLIENT:
                return "CLIENT";
            default:
                return "";
        }
    }

}
