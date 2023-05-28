package belov.vlad.dapp.services;

import belov.vlad.dapp.model.ApplicationOfTechnologicalMap;
import belov.vlad.dapp.repository.ApplicationOfTechnologicalMapRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ApplicationOfTechnologicalMap> findByUserId(Long id) {
        return applicationOfTechnologicalMapRepository.findAll().stream()
                .filter(atm -> atm.getUser().getId().equals(id)).collect(Collectors.toList());
    }
}
