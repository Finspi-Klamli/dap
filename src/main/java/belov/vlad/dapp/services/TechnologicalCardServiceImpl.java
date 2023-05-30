package belov.vlad.dapp.services;

import belov.vlad.dapp.model.TechnologicalCard;
import belov.vlad.dapp.repository.TechnologicalCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologicalCardServiceImpl implements TechnologicalCardService {
    @Autowired
    private final TechnologicalCardRepository technologicalCardRepository;

    public TechnologicalCardServiceImpl(TechnologicalCardRepository technologicalCardRepository) {
        this.technologicalCardRepository = technologicalCardRepository;
    }

    @Override
    public List<TechnologicalCard> findAll() {
        return technologicalCardRepository.findAll();
    }
    @Override
    public TechnologicalCard findForConversion(String string){
        String targetWord = " последняя";
        int startIndex = string.indexOf(targetWord)-1;
        String result = string.substring(0, startIndex);

        TechnologicalCard tc = technologicalCardRepository.findAll().stream().filter(t -> t.getName().equals(result))
                .findFirst().orElse(null);
        return tc;
    }

    @Override
    public TechnologicalCard findByName(String cardName) {
        return technologicalCardRepository.findAll().stream().filter(tc ->tc.getName().equals(cardName)).findFirst().orElse(null);
    }

    @Override
    public void save(TechnologicalCard tc) {
        technologicalCardRepository.save(tc);
    }

    @Override
    public TechnologicalCard findById(Long id) {
        return technologicalCardRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Long id, TechnologicalCard technologicalCard) {
        TechnologicalCard tc = technologicalCardRepository.findById(id).orElse(null);
        tc.setName(technologicalCard.getName());
        tc.setLastVersion(technologicalCard.getLastVersion());
        tc.setDescription(technologicalCard.getDescription());
        tc.setProduct(technologicalCard.getProduct());
        technologicalCardRepository.save(tc);
    }
}
