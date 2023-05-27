package belov.vlad.dapp.services;

import belov.vlad.dapp.model.FavoriteMap;
import belov.vlad.dapp.model.User;
import belov.vlad.dapp.model.VersionTechnologicalCard;
import belov.vlad.dapp.repository.FavoriteMapsRepository;

import java.util.List;

public interface FavoriteMapsService {
    List<FavoriteMap> findAll();
    List<FavoriteMap> findByUserEmail(String email);

    public void create(User user, VersionTechnologicalCard versionTechnologicalMapId);
}
