package belov.vlad.dapp.repository;

import belov.vlad.dapp.model.UserDataChange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDataChangeRepository extends JpaRepository<UserDataChange, Long> {
    @Override
    Optional<UserDataChange> findById(Long aLong);
}