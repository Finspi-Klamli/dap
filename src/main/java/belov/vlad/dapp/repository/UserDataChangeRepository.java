package belov.vlad.dapp.repository;

import belov.vlad.dapp.model.UserDataChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserDataChangeRepository extends JpaRepository<UserDataChange, Long> {
    @Override
    Optional<UserDataChange> findById(Long aLong);
}