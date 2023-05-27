package belov.vlad.dapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final TechnologicalCardConverter technologicalCardConverter;

    public WebConfig(TechnologicalCardConverter technologicalCardConverter) {
        this.technologicalCardConverter = technologicalCardConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(technologicalCardConverter);
    }
}