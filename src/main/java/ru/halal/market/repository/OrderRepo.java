package ru.halal.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.halal.market.model.ItemInGarbage;

public interface OrderRepo extends JpaRepository<ItemInGarbage, Long> {
}
