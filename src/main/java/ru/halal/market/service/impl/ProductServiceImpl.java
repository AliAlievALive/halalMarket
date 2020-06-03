package ru.halal.market.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.halal.market.model.Product;
import ru.halal.market.repository.ProductRepo;
import ru.halal.market.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo repo;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Product get(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void save(Product product) {
        repo.save(product);
    }

    public Page<Product> findAllByNameContaining(String name, Pageable pageable) {
        return repo.findAllByNameContaining(name, pageable);
    }
}
