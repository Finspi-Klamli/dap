package belov.vlad.dapp.services;

import belov.vlad.dapp.model.FavoriteMap;
import belov.vlad.dapp.model.User;
import belov.vlad.dapp.model.VersionTechnologicalCard;
import belov.vlad.dapp.repository.FavoriteMapsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteMapsServiceImpl implements FavoriteMapsService{
    private final FavoriteMapsRepository favoriteMapsRepository;

    public FavoriteMapsServiceImpl(FavoriteMapsRepository favoriteMapsRepository) {
        this.favoriteMapsRepository = favoriteMapsRepository;
    }

    @Override
    public List<FavoriteMap> findAll() {
        return favoriteMapsRepository.findAll();
    }

    @Override
    public List<FavoriteMap> findByUserEmail(String email) {
        return findAll().stream().filter(favoriteMap -> favoriteMap.getUser().getEmail()
                .equals(email)).collect(Collectors.toList());
    }

    @Override
    public void create(User user, VersionTechnologicalCard versionTechnologicalMapId) {
        FavoriteMap favoriteMap = new FavoriteMap(user, versionTechnologicalMapId);
        if(favoriteMapsRepository.findAll().stream().anyMatch(fm -> fm.getVersionTechnologicalCard()
                .equals(favoriteMap.getVersionTechnologicalCard()) &&
                fm.getUser().equals(favoriteMap.getUser())))
            return;
        favoriteMapsRepository.save(favoriteMap);
    }
}
