package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Agency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgencyRepository {

    private final List<Agency> agencies = new ArrayList<>();


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
    public List<Agency> getAgencies() {
        return new ArrayList<>(agencies);
    }

    public Agency getAgencyById(int id) {
        for (Agency agency : agencies) {
            if (agency.getId() == id) {
                return agency;
            }
        }
        return null;
    }
}
