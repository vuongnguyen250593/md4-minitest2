package cg.service;

import cg.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IProductService {
    Page<Product> findAll(Pageable pageable);

    Optional<Product> findById(Long id);

    Product save(Product product);

    void delete(Long id);

    Iterable<Product> findAllByName(String name);
}
