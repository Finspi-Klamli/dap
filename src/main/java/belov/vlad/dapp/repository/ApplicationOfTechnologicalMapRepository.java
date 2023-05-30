package belov.vlad.dapp.repository;

import belov.vlad.dapp.model.ApplicationOfTechnologicalMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationOfTechnologicalMapRepository extends JpaRepository<ApplicationOfTechnologicalMap, Long> {
}
