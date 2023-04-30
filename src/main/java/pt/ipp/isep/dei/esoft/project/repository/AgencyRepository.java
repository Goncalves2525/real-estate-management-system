package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Agency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class represents the repository for agencies.
 */
public class AgencyRepository {

    private final List<Agency> agencies = new ArrayList<>();

    /**
     * Add an agency to the repository.
     *
     * @param agency agency to be added
     * @return true if the agency is added, false if not
     */
    public Optional<Agency> add(Agency agency) {

        Optional<Agency> newAgency = Optional.empty();
        boolean operationSuccess = false;

        newAgency = Optional.of(agency.clone());
            operationSuccess = agencies.add(newAgency.get());

        if (!operationSuccess) {
            newAgency = Optional.empty();
        }

        return newAgency;

    }
        /**
         * Get the list of agencies.
         *
         * @return list of agencies
         */
    public ArrayList<Agency> getAgencies() {
        return new ArrayList<>(agencies);
    }
    /**
     * Get the agency by its id.
     *
     * @param id id of the agency
     * @return agency
     */
    public Agency getAgencyById(int id) {
        for (Agency agency : agencies) {
            if (agency.getId() == id) {
                return agency;
            }
        }
        return null;
    }
}
