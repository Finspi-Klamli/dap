package belov.vlad.dapp.services;

import belov.vlad.dapp.model.StatusTechnologicalCard;
import belov.vlad.dapp.repository.StatusTechnologicalCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StatusTechnologicalCardServiceImpl implements StatusTechnologicalCardService{
    @Autowired
    private final StatusTechnologicalCardRepository statusTechnologicalCardRepository;

    public StatusTechnologicalCardServiceImpl(StatusTechnologicalCardRepository statusTechnologicalCardRepository) {
        this.statusTechnologicalCardRepository = statusTechnologicalCardRepository;
    }

    @Override
    public List<StatusTechnologicalCard> findAll() {
        return null;
    }
}
