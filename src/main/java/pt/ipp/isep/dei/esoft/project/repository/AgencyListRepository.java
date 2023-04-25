package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgencyListRepository {

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
}
