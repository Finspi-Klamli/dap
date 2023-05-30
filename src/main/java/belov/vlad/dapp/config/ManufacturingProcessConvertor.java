package belov.vlad.dapp.config;

import belov.vlad.dapp.model.ManufacturingProcess;
import belov.vlad.dapp.services.ManufacturingProcessService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ManufacturingProcessConvertor implements Converter<String,ManufacturingProcess> {
    private final ManufacturingProcessService manufacturingProcessService;

    public ManufacturingProcessConvertor(ManufacturingProcessService manufacturingProcessService) {
        this.manufacturingProcessService = manufacturingProcessService;
    }

    @Override
    public ManufacturingProcess convert(String s) {
        return manufacturingProcessService.findForConversion(s);
    }
}
