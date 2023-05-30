package belov.vlad.dapp.repository;

import belov.vlad.dapp.model.TechnologicalCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologicalCardRepository extends JpaRepository<TechnologicalCard, Long> {
}
