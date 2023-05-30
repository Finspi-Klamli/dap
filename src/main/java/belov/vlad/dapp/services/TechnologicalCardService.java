package belov.vlad.dapp.services;

import belov.vlad.dapp.model.TechnologicalCard;

import java.util.List;

public interface TechnologicalCardService {
    List<TechnologicalCard> findAll();
    TechnologicalCard findForConversion(String string);

    TechnologicalCard findByName(String cardName);


    void save(TechnologicalCard tc);

    TechnologicalCard findById(Long id);

    void update(Long id, TechnologicalCard technologicalCard);
}
