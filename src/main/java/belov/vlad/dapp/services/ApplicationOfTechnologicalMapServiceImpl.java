package belov.vlad.dapp.services;

import belov.vlad.dapp.model.ApplicationOfTechnologicalMap;
import belov.vlad.dapp.repository.ApplicationOfTechnologicalMapRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplicationOfTechnologicalMapServiceImpl implements ApplicationOfTechnologicalMapService{
    private final ApplicationOfTechnologicalMapRepository applicationOfTechnologicalMapRepository;

    public ApplicationOfTechnologicalMapServiceImpl(ApplicationOfTechnologicalMapRepository applicationOfTechnologicalMapRepository) {
        this.applicationOfTechnologicalMapRepository = applicationOfTechnologicalMapRepository;
    }

    @Override
    public void save(ApplicationOfTechnologicalMap applicationOfTechnologicalMap) {
        applicationOfTechnologicalMapRepository.save(applicationOfTechnologicalMap);
    }
}
