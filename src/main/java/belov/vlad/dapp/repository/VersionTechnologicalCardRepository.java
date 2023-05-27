package belov.vlad.dapp.repository;

import belov.vlad.dapp.model.VersionTechnologicalCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionTechnologicalCardRepository extends JpaRepository<VersionTechnologicalCard, Long> {

    @Query(value = "SELECT * FROM version_technological_cards WHERE technological_cards_id = :cardid AND version = :version", nativeQuery = true)
    VersionTechnologicalCard findByVersionAndTechnologicalCards(@Param("cardid") Long cardId, @Param("version") String version);
    @Query(value = "SELECT file_data_id FROM version_technological_cards WHERE technological_cards_id = :cardid", nativeQuery = true)
    Long findFileDataIdFromVersionCardId(@Param("cardid") Long cardId);

}
