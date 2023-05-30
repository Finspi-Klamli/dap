package belov.vlad.dapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final TechnologicalCardConverter technologicalCardConverter;
    private final EquipmentConverter equipmentConverter;
    private final ManufacturingProcessConvertor manufacturingProcessConvertor;
    private final ProductConverter productConverter;
    public WebConfig(TechnologicalCardConverter technologicalCardConverter, EquipmentConverter equipmentConverter
            , ManufacturingProcessConvertor manufacturingProcessConvertor, ProductConverter productConverter) {
        this.technologicalCardConverter = technologicalCardConverter;
        this.equipmentConverter = equipmentConverter;
        this.manufacturingProcessConvertor = manufacturingProcessConvertor;
        this.productConverter = productConverter;
    }
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(technologicalCardConverter);
        registry.addConverter(equipmentConverter);
        registry.addConverter(manufacturingProcessConvertor);
        registry.addConverter(productConverter);

    }
}