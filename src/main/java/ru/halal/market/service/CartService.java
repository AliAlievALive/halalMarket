package ru.halal.market.service;

import ru.halal.market.model.Garbage;
import ru.halal.market.model.User;

import java.util.List;

public interface CartService {
    List<Garbage> findAllByBuyer(User buyer);
}
