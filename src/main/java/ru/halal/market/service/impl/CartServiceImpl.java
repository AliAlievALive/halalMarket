package ru.halal.market.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.halal.market.model.Garbage;
import ru.halal.market.model.User;
import ru.halal.market.repository.CartRepo;
import ru.halal.market.repository.UserRepo;
import ru.halal.market.service.CartService;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final UserRepo userRepo;
    private final CartRepo cartRepo;

    public CartServiceImpl(UserRepo userRepo, CartRepo cartRepo) {
        this.userRepo = userRepo;
        this.cartRepo = cartRepo;
    }

    @Override
    public List<Garbage> findAllByBuyer(User buyer) {
        return cartRepo.findAllByBuyer(buyer);
    }

    public Garbage findByBuyerAndOrderNotDone(long buyer_id) {
        return cartRepo.findByDoneAndBuyer_Id(true, buyer_id);
    }

    public void save(Garbage garbage) {
        cartRepo.save(garbage);
    }
}
