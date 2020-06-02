package ru.halal.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.halal.market.model.Product;
import ru.halal.market.service.impl.ProductServiceImpl;

@Controller
public class AppController {
    private final ProductServiceImpl productService;

    public AppController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String viewProducts(
            Model model,
            @PageableDefault(sort = {"name"}, direction = Sort.Direction.DESC) Pageable pageable
            ) {
        Page<Product> page = productService.findAll(pageable);

        model.addAttribute("page", page);
        model.addAttribute("url", "/products");

        return "products";
    }
}
