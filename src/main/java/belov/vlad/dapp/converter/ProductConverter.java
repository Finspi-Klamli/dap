package belov.vlad.dapp.converter;

import belov.vlad.dapp.model.Product;
import belov.vlad.dapp.services.ProductsService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter implements Converter<String, Product> {
    private final ProductsService productsService;

    public ProductConverter(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Override
    public Product convert(String source) {
        return productsService.findForConversion(source);
    }
}
