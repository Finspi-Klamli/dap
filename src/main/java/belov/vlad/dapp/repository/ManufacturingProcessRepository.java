package belov.vlad.dapp.repository;

import belov.vlad.dapp.model.ManufacturingProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturingProcessRepository extends JpaRepository<ManufacturingProcess, Long> {
}
