package ru.halal.market.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.halal.market.model.Garbage;
import ru.halal.market.model.Product;
import ru.halal.market.model.User;

import java.util.List;

public interface CartRepo extends CrudRepository<Garbage, Long> {
    List<Garbage> findAllByBuyer(User buyer);

    Garbage findByBuyer(User buyer);

    Garbage findByDoneAndBuyer_Id(boolean done, long id);
}
