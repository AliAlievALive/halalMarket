package ru.halal.market.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.halal.market.model.Product;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);

    Product get(Long id);

    void delete(Long id);
}
