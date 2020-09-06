package ru.halal.market.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.halal.market.model.Garbage;
import ru.halal.market.model.ItemInGarbage;
import ru.halal.market.model.Product;
import ru.halal.market.model.User;
import ru.halal.market.service.impl.CartServiceImpl;
import ru.halal.market.service.impl.ProductServiceImpl;
import ru.halal.market.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/cart")
public class GarbageController {
    private final UserServiceImpl userService;
    private final CartServiceImpl cartService;
    private final ProductServiceImpl productService;



    public GarbageController(CartServiceImpl cartService, UserServiceImpl userService, ProductServiceImpl productService) {
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
    }

    //все заказы покупателя
    @GetMapping
    public String viewGarbage(
            Model model,
            @AuthenticationPrincipal User buyer
    ) {
        List<Garbage> carts;

        carts = cartService.findAllByBuyer(buyer);

        model.addAttribute("carts", carts);

        return "garbage";
    }

    //Текущая корзина пользователя
    @GetMapping("new_cart")
    public String showNewCartForm(
            Model model
    ) {
        final Garbage cart = new Garbage();
        model.addAttribute("cart", cart);
        return "garbage";
    }

    @PostMapping("/product_add_to_cart/{product}")
    public void addProductToCart(
            @PathVariable Product product
    ) {

//        Garbage byBuyerAndOrderNotDone = cartService.findByBuyerAndOrderNotDone(buyer);
//        if (byBuyerAndOrderNotDone == null) {
//            byBuyerAndOrderNotDone = new Garbage();
//            Date cartCreateDate = new Date();
//            Calendar cartCreate = new GregorianCalendar(cartCreateDate.getYear(), cartCreateDate.getMonth(), cartCreateDate.getDate());
//            Calendar timeToOrderMustBeReady = new GregorianCalendar(cartCreateDate.getYear(), cartCreateDate.getMonth(), cartCreateDate.getDate(), 20, 0);
//            byBuyerAndOrderNotDone.setNumber("" + cartCreateDate + buyer);
//            byBuyerAndOrderNotDone.setPrice(product.getPrice());
//            byBuyerAndOrderNotDone.setOrdersDate(cartCreate);
//            byBuyerAndOrderNotDone.setTimeToOrderMustBeReady(timeToOrderMustBeReady);
//            byBuyerAndOrderNotDone.setDone(false);
//            byBuyerAndOrderNotDone.setBuyer(userService.findUserById(buyer));
//            Set<ItemInGarbage> itemsInGarbage = byBuyerAndOrderNotDone.getItemsInGarbage();
//            itemsInGarbage.add(new ItemInGarbage(product, 1, byBuyerAndOrderNotDone));
//            byBuyerAndOrderNotDone.setItemsInGarbage(itemsInGarbage);
//
//            System.out.println(byBuyerAndOrderNotDone);
//        } else {
//            ItemInGarbage productInCart = new ItemInGarbage(product, 1, byBuyerAndOrderNotDone);
//            byBuyerAndOrderNotDone.getItemsInGarbage().add(productInCart);
//        }
//
//        System.out.println(byBuyerAndOrderNotDone.getItemsInGarbage());
//
//        cartService.save(byBuyerAndOrderNotDone);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
//        cartService.delete(id);

        return "redirect:/";
    }
}
