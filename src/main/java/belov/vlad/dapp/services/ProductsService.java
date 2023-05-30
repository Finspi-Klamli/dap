package belov.vlad.dapp.services;

import belov.vlad.dapp.model.Product;

import java.util.List;

public interface ProductsService {
    List<Product> findAll();

    void save(Product product);

    void update(Long id, Product product);

    Product findById(Long id);

    Product findForConversion(String source);

    List<Product>  findProductWithoutCard();
}
