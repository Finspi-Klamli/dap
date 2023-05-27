package belov.vlad.dapp.services;


import belov.vlad.dapp.model.VersionTechnologicalCard;
import belov.vlad.dapp.repository.VersionTechnologicalCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionTechnologicalCardServiceImpl implements VersionTechnologicalCardService {
    private final VersionTechnologicalCardRepository versionTechnologicalCardRepository;

    public VersionTechnologicalCardServiceImpl(VersionTechnologicalCardRepository versionTechnologicalCardRepository) {
        this.versionTechnologicalCardRepository = versionTechnologicalCardRepository;
    }

    @Override
    public List<VersionTechnologicalCard> findAll() {
        return versionTechnologicalCardRepository.findAll();
    }

    @Override
    public VersionTechnologicalCard findById(Long id) {
        return versionTechnologicalCardRepository.findById(id).get();
    }

    @Override
    public VersionTechnologicalCard findByVersionAndTechnologicalCards(Long id, String version) {
        return versionTechnologicalCardRepository.findByVersionAndTechnologicalCards(id, version);
    }

    @Override
    public Long getFileDataId(Long versionTechnologicalCard_id) {
        return versionTechnologicalCardRepository.findFileDataIdFromVersionCardId(versionTechnologicalCard_id);
    }

    @Override
    public void save(VersionTechnologicalCard versionTechnologicalCard) {
        versionTechnologicalCardRepository.save(versionTechnologicalCard);
    }

}
