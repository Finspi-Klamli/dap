package belov.vlad.dapp.services;

import belov.vlad.dapp.model.VersionTechnologicalCard;

public interface VersionTechnologicalCardService {
    VersionTechnologicalCard findById(Long id);
    VersionTechnologicalCard findByVersionAndTechnologicalCards(Long id, String version);
    Long getFileDataId(Long versionTechnologicalCard_id);
}
