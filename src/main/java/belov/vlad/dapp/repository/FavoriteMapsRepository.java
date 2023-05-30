package belov.vlad.dapp.repository;

import belov.vlad.dapp.model.FavoriteMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteMapsRepository extends JpaRepository<FavoriteMap, Long> {
}
