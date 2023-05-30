package belov.vlad.dapp.services;

import belov.vlad.dapp.model.Product;
import belov.vlad.dapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsServiceImpl implements ProductsService{
    @Autowired
    private final ProductRepository productRepository;

    public ProductsServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void update(Long id, Product product) {
        Product p = productRepository.findById(id).orElse(null);
        p.setName(product.getName());
        p.setDescription(product.getDescription());
        p.setManufacturingProcess(product.getManufacturingProcess());
        productRepository.save(p);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product findForConversion(String source) {
      return productRepository.findAll().stream().filter(p->p.getName().equals(source)).findFirst().orElse(null);
    }

    @Override
    public List<Product> findProductWithoutCard() {
        return productRepository.findAll().stream().filter(p->p.getTechnologicalCard() ==null).collect(Collectors.toList());
    }
}
