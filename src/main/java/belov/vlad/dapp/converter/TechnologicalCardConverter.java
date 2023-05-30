package belov.vlad.dapp.converter;

import belov.vlad.dapp.model.TechnologicalCard;
import belov.vlad.dapp.services.TechnologicalCardService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TechnologicalCardConverter implements Converter<String, TechnologicalCard> {
    private final TechnologicalCardService technologicalCardService;

    public TechnologicalCardConverter(TechnologicalCardService technologicalCardService) {
        this.technologicalCardService = technologicalCardService;
    }

    @Override
    public TechnologicalCard convert(String source) {
        return technologicalCardService.findForConversion(source);
    }
}