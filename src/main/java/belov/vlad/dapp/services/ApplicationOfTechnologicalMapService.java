package belov.vlad.dapp.services;

import belov.vlad.dapp.model.ApplicationOfTechnologicalMap;

import java.util.List;

public interface ApplicationOfTechnologicalMapService {
    void save(ApplicationOfTechnologicalMap applicationOfTechnologicalMap);

    List<ApplicationOfTechnologicalMap> findByUserId(Long id);
}
