package belov.vlad.dapp.services;

import belov.vlad.dapp.model.VersionTechnologicalCard;

import java.util.List;

public interface VersionTechnologicalCardService {
    List<VersionTechnologicalCard> findAll();
    VersionTechnologicalCard findById(Long id);
    VersionTechnologicalCard findByVersionAndTechnologicalCards(Long id, String version);
    Long getFileDataId(Long versionTechnologicalCard_id);

    void save(VersionTechnologicalCard versionTechnologicalCard);
}
