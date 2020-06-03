package ru.halal.market.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.halal.market.model.Product;
import ru.halal.market.service.impl.ProductServiceImpl;

import java.util.Date;

@Controller
public class AppController {
    private final ProductServiceImpl productService;

    public AppController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String viewProducts(
            @RequestParam(required = false) String filter,
            Model model,
            @PageableDefault(sort = {"name"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Product> page;

        if (filter != null && !filter.isEmpty()) {
            page = productService.findAllByNameContaining(filter, pageable);
        } else {
            page = productService.findAll(pageable);
        }
        model.addAttribute("page", page);
        model.addAttribute("filter", filter);

        return "products";
    }

    @GetMapping("new_product")
    public String showNewProductForm(
            Model model
    ) {
        final Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }

    @PostMapping("new_product")
    public String saveProduct(
            @ModelAttribute Product product,
            Model model
    ) {
        if (product.getMadeDate() == null) {
            product.setMadeDate(new Date());
        }
        model.addAttribute(product);
        product.setSale(true);
        productService.save(product);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_product");
        Product product = productService.get(id);
        mav.addObject("product", product);
        System.out.println(mav);
        return mav;
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        productService.delete(id);

        return "redirect:/";
    }
//    @PostMapping("product_filter")
//    public String childFio(@RequestParam String name,
//                           Model model,
//                           @PageableDefault(sort = {"name"}, direction = Sort.Direction.DESC) Pageable pageable
//    ) {
////        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), 50);
//        Page<Product> page;
//        if (name != null) {
//            page = productService.findByNameContaining(name, pageable);
//        } else {
//            page = productService.findAll(pageable);
//        }
//        model.addAttribute("page", page);
//        return "products";
//    }
}
