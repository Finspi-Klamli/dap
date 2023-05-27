package belov.vlad.dapp.repository;

import belov.vlad.dapp.model.FavoriteMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteMapsRepository extends JpaRepository<FavoriteMap, Long> {
}
