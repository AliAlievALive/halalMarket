package ru.halal.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.halal.market.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
